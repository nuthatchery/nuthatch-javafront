package nuthatch.demo.javafront;

import static nuthatch.javafront.JavaPatterns.ConstrDec;
import static nuthatch.javafront.JavaPatterns.ConstrDecHead;
import static nuthatch.javafront.JavaPatterns.Invoke;
import static nuthatch.javafront.JavaPatterns.Method;
import static nuthatch.javafront.JavaPatterns.MethodDec;
import static nuthatch.javafront.JavaPatterns.MethodDecHead;
import static nuthatch.javafront.JavaPatterns.MethodName;
import static nuthatch.pattern.StaticPatternFactory.ancestor;
import static nuthatch.pattern.StaticPatternFactory.or;
import static nuthatch.pattern.StaticPatternFactory.var;
import static nuthatch.stratego.actions.SActionFactory.down;
import static nuthatch.stratego.actions.SActionFactory.match;
import static nuthatch.stratego.actions.SActionFactory.seq;
import static nuthatch.stratego.actions.SActionFactory.walk;
import static nuthatch.stratego.pattern.SPatternFactory._;
import static nuthatch.stratego.pattern.SPatternFactory.var;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import nuthatch.demo.javafront.class2table.Class2Table;
import nuthatch.javafront.JavaAdapter;
import nuthatch.javafront.JavaParser;
import nuthatch.library.Action;
import nuthatch.pattern.Environment;
import nuthatch.pattern.EnvironmentFactory;
import nuthatch.pattern.VarName;
import nuthatch.stratego.actions.SAction;
import nuthatch.stratego.actions.SMatchAction;
import nuthatch.stratego.adapter.STermCursor;
import nuthatch.stratego.adapter.STermVar;
import nuthatch.stratego.adapter.SWalker;
import nuthatch.tree.TreeCursor;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.jsglr.client.InvalidParseTableException;
import org.spoofax.jsglr.client.ParseException;
import org.spoofax.jsglr.shared.BadTokenException;
import org.spoofax.jsglr.shared.SGLRException;
import org.spoofax.jsglr.shared.TokenExpectedException;
import org.spoofax.terms.ParseError;

public class CallAnalysis {

	/**
	 * @param args
	 * @throws InvalidParseTableException
	 * @throws IOException
	 * @throws SGLRException
	 * @throws ParseError
	 * @throws ParseException
	 * @throws BadTokenException
	 * @throws TokenExpectedException
	 */
	public static void main(String[] args) throws SGLRException, IOException, InvalidParseTableException {
		JavaParser.init();
		STermCursor term = JavaParser.parseStream(Class2Table.class.getResourceAsStream("../examples/Example.java.ex"), "Example.java");
		final MultiMap<String, String> resultMap = aspectVariant(term);
		final MultiMap<String, String> resultMap2 = ancestorVariant(term);

		for(Entry<String, List<String>> entry : resultMap.entrySet()) {
			System.out.printf("%25s calls: ", entry.getKey());
			for(String s : entry.getValue()) {
				System.out.print(s + " ");
			}
			System.out.println();
		}

		System.out.println("aspectVariant(t).equals(ancestorVariant(t)) = " + resultMap.equals(resultMap2));
	}


	private static MultiMap<String, String> ancestorVariant(STermCursor term) {
		final MultiMap<String, String> resultMap = new MultiMap<String, String>();

		Action<SWalker> calls = down(match(Invoke(Method(MethodName(var("name"))), var("args")), new SMatchAction() {
			@Override
			public int step(SWalker e, Environment<STermCursor> env) {
				resultMap.add(getSurroundingMethodName(e), JavaAdapter.nameToStr(env.get("name")));
				return PROCEED;
			}

		}));
		SWalker engine = new SWalker(term, walk(calls));
		engine.start();

		return resultMap;
	}


	private static MultiMap<String, String> aspectVariant(STermCursor term) {
		final MultiMap<String, String> resultMap = new MultiMap<String, String>();
		final VarName<STermCursor> scopeName = new VarName<>("scopeName");

		Action<SWalker> visitor = down(match(Invoke(Method(MethodName(var("name"))), var("args")), new SMatchAction() {
			@Override
			public int step(SWalker e, Environment<STermCursor> env) {
				resultMap.add(JavaAdapter.nameToStr(e.getSubtreeVar(scopeName)), JavaAdapter.nameToStr(env.get("name")));
				return PROCEED;
			}
		}));

		Action<SWalker> nameTracker = seq(down(new SAction() {
			@Override
			public int step(SWalker e) {
				Environment<TreeCursor<IStrategoTerm, Integer>> env = EnvironmentFactory.env();
				STermVar name = new STermVar(env);

				if(MethodDec(var("head", MethodDecHead(_, _, _, name, _, _)), _).match(e, env)) {
					e.setSubtreeVar(scopeName, name.get());
				}
				else if(ConstrDec(var("head", ConstrDecHead(_, _, name, _, _)), _).match(e, env)) {
					e.setSubtreeVar(scopeName, name.get());
				}
				return PROCEED;
			}

		}), visitor);

		SWalker engine = new SWalker(term, walk(nameTracker));
		engine.start();
		return resultMap;
	}


	private static String getSurroundingMethodName(SWalker e) {
		Environment<TreeCursor<IStrategoTerm, Integer>> env = EnvironmentFactory.env();
		if(ancestor(or(MethodDec(MethodDecHead(_, _, _, var("scopeName"), _, _), _), ConstrDec(ConstrDecHead(_, _, var("scopeName"), _, _), _))).match(e, env)) {
			return JavaAdapter.nameToStr(env.get("scopeName"));
		}
		else {
			return null;
		}
	}
}
