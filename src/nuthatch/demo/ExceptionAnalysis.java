package nuthatch.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nuthatch.javafront.JavaPatterns;
import nuthatch.library.strategies.Visitor;
import static nuthatch.javafront.JavaPatterns.*;
import nuthatch.pattern.Environment;
import nuthatch.pattern.EnvironmentFactory;
import nuthatch.pattern.Pattern;
import nuthatch.pattern.impl.Var;
import nuthatch.stratego.adapter.StrategoAdapter;
import nuthatch.stratego.adapter.TermEngine;
import nuthatch.stratego.pattern.TermPatternFactory;
import nuthatch.strategy.Before;
import nuthatch.strategy.Strategy;
import nuthatch.tree.TreeCursor;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.jsglr.client.InvalidParseTableException;
import org.spoofax.jsglr.client.ParseException;
import org.spoofax.jsglr.client.ParseTable;
import org.spoofax.jsglr.shared.BadTokenException;
import org.spoofax.jsglr.shared.SGLRException;
import org.spoofax.jsglr.shared.TokenExpectedException;
import org.spoofax.terms.ParseError;
public class ExceptionAnalysis {

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
	public static void main(String[] args) throws TokenExpectedException, BadTokenException, ParseException, ParseError, SGLRException, IOException, InvalidParseTableException {

		IStrategoTerm term = StrategoAdapter.parseFile("/tmp/RationalValue.java", getJavaParseTable());
		final Map<String, List<String>> resultMap = new HashMap<String, List<String>>();
		
		Visitor<TermEngine> visitor = new Visitor<TermEngine>() {
			@Override
			public void onEntry(TermEngine e) {
				Environment<TreeCursor<IStrategoTerm, Integer>> env = EnvironmentFactory.env();
				if(Invoke(Method(MethodName(var("name"))), var("args")).match(e, env)) {
					List<String> list = resultMap.get(e.getS());
					if(list == null) {
						list = new ArrayList<String>();
						resultMap.put(e.getS(), list);
					}
					list.add(getName(env.get("name")));
				}
				/*if(ClassBody(var("body")).match(e, env)) {
					for(TreeCursor<IStrategoTerm, Integer> child : env.get("body")) {
						System.out.println(child.treeToString());
					}
				}
				/*else if(MethodDec(MethodDecHead(var("modifiers"),var("typeParams"),var("retType"),var("methodName"),var("params"),var("throws")),var("body")).match(e, env)) {
					e.setS(getName(env.get("methodName")));
				}*/
			}
		};
		
		Strategy<TermEngine> nameTracker = new Before<TermEngine>(visitor) {
			@Override
			public int visitBefore(TermEngine e) {
				Environment<TreeCursor<IStrategoTerm, Integer>> env = EnvironmentFactory.env();	
				if(MethodDec(MethodDecHead(var("modifiers"),var("typeParams"),var("retType"),var("methodName"),var("params"),var("throws")),var("body")).match(e, env)) {
					e.setS(getName(env.get("methodName")));
				}
				else if(ConstrDec(ConstrDecHead(var("modifiers"),var("typeParams"),var("methodName"),var("params"),var("throws")),var("body")).match(e, env)) {
					e.setS(getName(env.get("methodName")));
				}
				return PROCEED;
			}
			
		};

		TermEngine engine = new TermEngine(StrategoAdapter.termToTree(term), nameTracker);
		engine.engage();
		
		for(Entry<String, List<String>> entry : resultMap.entrySet()) {
			System.out.printf("%25s calls: ", entry.getKey());
			for(String s : entry.getValue())
				System.out.print(s + " ");
			System.out.println();
		}
	}
	
	public static ParseTable getJavaParseTable() throws ParseError, IOException, InvalidParseTableException {
		InputStream stream = JavaPatterns.class.getResourceAsStream("syntax/JavaCompilationUnit-15.tbl");
		
		return StrategoAdapter.getParseTableManager().loadFromStream(stream);
	}

	public static Pattern<IStrategoTerm, Integer> var(String name) {
		return TermPatternFactory.getInstance().var(name);
	}
	
	public static String getName(TreeCursor<IStrategoTerm, Integer> tree) {
		Var<IStrategoTerm, Integer> s = new Var<>();
		Var<IStrategoTerm, Integer> t = new Var<>();
		if(Id(s).match(tree)) {
			return s.get().getName();
		}
		else if(AmbName(s, t).match(tree)) {
			return getName(s.get()) + "." + getName(t.get());
		}
		else {
			throw new RuntimeException("Unknown name: " + tree.treeToString());
		}
	}
}
