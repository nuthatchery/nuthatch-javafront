package nuthatch.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nuthatch.javafront.JavaPatterns;
import nuthatch.library.strategies.Visitor;
import nuthatch.library.strategies.VisitorAspect;
import static nuthatch.javafront.JavaPatterns.*;
import nuthatch.pattern.Environment;
import nuthatch.pattern.EnvironmentFactory;
import nuthatch.pattern.Pattern;
import nuthatch.pattern.VarName;
import nuthatch.stratego.adapter.StrategoAdapter;
import nuthatch.stratego.adapter.TermCursor;
import nuthatch.stratego.adapter.TermEngine;
import nuthatch.stratego.adapter.TermVar;
import nuthatch.stratego.pattern.TermPatternFactory;
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
		final MultiMap<String,String> resultMap = ancestorVariant(term);

		for(Entry<String, List<String>> entry : resultMap.entrySet()) {
			System.out.printf("%25s calls: ", entry.getKey());
			for(String s : entry.getValue())
				System.out.print(s + " ");
			System.out.println();
		}
	}

	private static MultiMap<String,String> ancestorVariant(IStrategoTerm term) {
		final MultiMap<String, String> resultMap = new MultiMap<String, String>();

		Strategy<TermEngine> calls = new Visitor<TermEngine>() {
			@Override
			public void onEntry(TermEngine e) {
				Environment<TreeCursor<IStrategoTerm, Integer>> env = EnvironmentFactory.env();
				if(Invoke(Method(MethodName(var("name"))), var("args")).match(e, env)) {
					if(ancestor(or(MethodDec(MethodDecHead(_,_,_,var("scopeName"),_,_),_), 
							ConstrDec(ConstrDecHead(_,_,var("scopeName"),_,_),_))).match(e, env)) {
						String scopeName = nameToStr(env.get("scopeName"));
						resultMap.add(scopeName, nameToStr(env.get("name")));
					}
				}
			}
		};
		TermEngine engine = new TermEngine(StrategoAdapter.termToTree(term), calls);
		engine.engage();

		return resultMap;
	}
	private static MultiMap<String, String> aspectVariant(IStrategoTerm term) {
		final MultiMap<String, String> resultMap = new MultiMap<String, String>();
		final VarName<TermCursor> scopeName = new VarName<>("scopeName");

		Visitor<TermEngine> visitor = new Visitor<TermEngine>() {
			@Override
			public void onEntry(TermEngine e) {
				Environment<TreeCursor<IStrategoTerm, Integer>> env = EnvironmentFactory.env();
				if(Invoke(Method(MethodName(var("name"))), var("args")).match(e, env)) {
					resultMap.add(nameToStr(e.getSubtreeVar(scopeName)), nameToStr(env.get("name")));
				}
			}
		};

		Strategy<TermEngine> nameTracker = new VisitorAspect<TermEngine>(visitor) {

			@Override
			public void beforeEntry(TermEngine e) {
				Environment<TreeCursor<IStrategoTerm, Integer>> env = EnvironmentFactory.env();	
				TermVar name = new TermVar(env);

				if(MethodDec(var("head", MethodDecHead(_,_,_,name,_,_)),_).match(e, env)) {
					e.setSubtreeVar(scopeName, name.get());
				}
				else if(ConstrDec(var("head", ConstrDecHead(_,_,name,_,_)),_).match(e, env)) {
					e.setSubtreeVar(scopeName, name.get());
				}
			}

		};

		TermEngine engine = new TermEngine(StrategoAdapter.termToTree(term), nameTracker);
		engine.engage();
		return resultMap;
	}

	public static ParseTable getJavaParseTable() throws ParseError, IOException, InvalidParseTableException {
		InputStream stream = JavaPatterns.class.getResourceAsStream("syntax/JavaCompilationUnit-15.tbl");

		return StrategoAdapter.getParseTableManager().loadFromStream(stream);
	}

	public static Pattern<IStrategoTerm, Integer> var(String name) {
		return TermPatternFactory.getInstance().var(name);
	}

	public static Pattern<IStrategoTerm, Integer> var(String name, Pattern<IStrategoTerm, Integer> pat) {
		return TermPatternFactory.getInstance().var(name, pat);
	}

	public static Pattern<IStrategoTerm, Integer> or(Pattern<IStrategoTerm, Integer> pat1, Pattern<IStrategoTerm, Integer> pat2) {
		return TermPatternFactory.getInstance().or(pat1, pat2);
	}

	public static Pattern<IStrategoTerm, Integer> ancestor(Pattern<IStrategoTerm, Integer> pat) {
		return TermPatternFactory.getInstance().ancestor(pat);
	}

	public static Pattern<IStrategoTerm, Integer> _ = TermPatternFactory.getInstance().any();

	public static String nameToStr(TreeCursor<IStrategoTerm, Integer> tree) {
		Environment<TreeCursor<IStrategoTerm, Integer>> env = EnvironmentFactory.env();	
		TermVar s = new TermVar(env);
		TermVar t = new TermVar(env);
		if(Id(s).match(tree, env)) {
			return s.get().getName();
		}
		else if(AmbName(s, t).match(tree, env)) {
			return nameToStr(s.get()) + "." + nameToStr(t.get());
		}
		else {
			throw new RuntimeException("Unknown name: " + tree.treeToString());
		}
	}
	
	static class MultiMap<K,V> {
		Map<K, List<V>> resultMap = new HashMap<K, List<V>>();
		
		public void add(K key, V value) {
			List<V> list = resultMap.get(key);
			if(list == null) {
				list = new ArrayList<V>();
				resultMap.put(key, list);
			}
			list.add(value);
		}

		public Set<Entry<K,List<V>>> entrySet() {
			return resultMap.entrySet();
		}
	}
}
