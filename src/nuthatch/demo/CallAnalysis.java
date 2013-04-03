package nuthatch.demo;

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
import static nuthatch.stratego.pattern.StaticTermPatternFactory._;
import static nuthatch.stratego.pattern.StaticTermPatternFactory.var;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import nuthatch.javafront.JavaAdapter;
import nuthatch.javafront.JavaParser;
import nuthatch.library.walks.DefaultVisitor;
import nuthatch.library.walks.Visitor;
import nuthatch.library.walks.VisitorAspect;
import nuthatch.pattern.Environment;
import nuthatch.pattern.EnvironmentFactory;
import nuthatch.pattern.VarName;
import nuthatch.stratego.adapter.TermCursor;
import nuthatch.stratego.adapter.TermVar;
import nuthatch.stratego.adapter.TermWalk;
import nuthatch.tree.TreeCursor;
import nuthatch.walk.Step;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.jsglr.client.InvalidParseTableException;
import org.spoofax.jsglr.client.ParseException;
import org.spoofax.jsglr.shared.BadTokenException;
import org.spoofax.jsglr.shared.SGLRException;
import org.spoofax.jsglr.shared.TokenExpectedException;
import org.spoofax.terms.ParseError;

public class CallAnalysis {

	private static MultiMap<String, String> ancestorVariant(TermCursor term) {
		final MultiMap<String, String> resultMap = new MultiMap<String, String>();

		Step<TermWalk> calls = new DefaultVisitor<TermWalk>() {
			@Override
			public void onEntry(TermWalk e) {
				Environment<TreeCursor<IStrategoTerm, Integer>> env = EnvironmentFactory.env();
				if(Invoke(Method(MethodName(var("name"))), var("args")).match(e, env)) {
					resultMap.add(getSurroundingMethodName(e), JavaAdapter.nameToStr(env.get("name")));
				}
			}

		};
		TermWalk engine = new TermWalk(term, calls);
		engine.start();

		return resultMap;
	}


	private static MultiMap<String, String> aspectVariant(TermCursor term) {
		final MultiMap<String, String> resultMap = new MultiMap<String, String>();
		final VarName<TermCursor> scopeName = new VarName<>("scopeName");

		Visitor<TermWalk> visitor = new DefaultVisitor<TermWalk>() {
			@Override
			public void onEntry(TermWalk e) {
				Environment<TreeCursor<IStrategoTerm, Integer>> env = EnvironmentFactory.env();
				if(Invoke(Method(MethodName(var("name"))), var("args")).match(e, env)) {
					resultMap.add(JavaAdapter.nameToStr(e.getSubtreeVar(scopeName)), JavaAdapter.nameToStr(env.get("name")));
				}
			}
		};

		Step<TermWalk> nameTracker = new VisitorAspect<TermWalk>(visitor) {

			@Override
			public void beforeEntry(TermWalk e) {
				Environment<TreeCursor<IStrategoTerm, Integer>> env = EnvironmentFactory.env();
				TermVar name = new TermVar(env);

				if(MethodDec(var("head", MethodDecHead(_, _, _, name, _, _)), _).match(e, env)) {
					e.setSubtreeVar(scopeName, name.get());
				}
				else if(ConstrDec(var("head", ConstrDecHead(_, _, name, _, _)), _).match(e, env)) {
					e.setSubtreeVar(scopeName, name.get());
				}
			}

		};

		TermWalk engine = new TermWalk(term, nameTracker);
		engine.start();
		return resultMap;
	}


	private static String getSurroundingMethodName(TermWalk e) {
		Environment<TreeCursor<IStrategoTerm, Integer>> env = EnvironmentFactory.env();
		if(ancestor(or(MethodDec(MethodDecHead(_, _, _, var("scopeName"), _, _), _), ConstrDec(ConstrDecHead(_, _, var("scopeName"), _, _), _))).match(e, env)) {
			return JavaAdapter.nameToStr(env.get("scopeName"));
		}
		else {
			return null;
		}
	}


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
		TermCursor term = JavaParser.parseFile("/tmp/RationalValue.java");
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
}
