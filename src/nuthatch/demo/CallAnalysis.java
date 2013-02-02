package nuthatch.demo;

import static nuthatch.javafront.JavaPatterns.AmbName;
import static nuthatch.javafront.JavaPatterns.ConstrDec;
import static nuthatch.javafront.JavaPatterns.ConstrDecHead;
import static nuthatch.javafront.JavaPatterns.Id;
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
import nuthatch.pattern.Environment;
import nuthatch.pattern.EnvironmentFactory;
import nuthatch.pattern.VarName;
import nuthatch.stratego.adapter.StrategoAdapter;
import nuthatch.stratego.adapter.TermCursor;
import nuthatch.stratego.adapter.TermEngine;
import nuthatch.stratego.adapter.TermVar;
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

public class CallAnalysis {

	public static ParseTable getJavaParseTable() throws ParseError, IOException, InvalidParseTableException {
		try (InputStream stream = JavaPatterns.class.getResourceAsStream("syntax/JavaCompilationUnit-15.tbl")) {

			return StrategoAdapter.getParseTableManager().loadFromStream(stream);
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
	public static void main(String[] args) throws TokenExpectedException, BadTokenException, ParseException, ParseError, SGLRException, IOException, InvalidParseTableException {
		IStrategoTerm term = StrategoAdapter.parseFile("/tmp/RationalValue.java", getJavaParseTable());
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


	private static MultiMap<String, String> ancestorVariant(IStrategoTerm term) {
		final MultiMap<String, String> resultMap = new MultiMap<String, String>();

		Strategy<TermEngine> calls = new Visitor<TermEngine>() {
			@Override
			public void onEntry(TermEngine e) {
				Environment<TreeCursor<IStrategoTerm, Integer>> env = EnvironmentFactory.env();
				if(Invoke(Method(MethodName(var("name"))), var("args")).match(e, env)) {
					resultMap.add(getSurroundingMethodName(e), nameToStr(env.get("name")));
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

				if(MethodDec(var("head", MethodDecHead(_, _, _, name, _, _)), _).match(e, env)) {
					e.setSubtreeVar(scopeName, name.get());
				}
				else if(ConstrDec(var("head", ConstrDecHead(_, _, name, _, _)), _).match(e, env)) {
					e.setSubtreeVar(scopeName, name.get());
				}
			}

		};

		TermEngine engine = new TermEngine(StrategoAdapter.termToTree(term), nameTracker);
		engine.engage();
		return resultMap;
	}


	private static String getSurroundingMethodName(TermEngine e) {
		Environment<TreeCursor<IStrategoTerm, Integer>> env = EnvironmentFactory.env();
		if(ancestor(or(MethodDec(MethodDecHead(_, _, _, var("scopeName"), _, _), _), ConstrDec(ConstrDecHead(_, _, var("scopeName"), _, _), _))).match(e, env)) {
			return nameToStr(env.get("scopeName"));
		}
		else {
			return null;
		}
	}


	static class MultiMap<K, V> {
		Map<K, List<V>> resultMap = new HashMap<K, List<V>>();


		public void add(K key, V value) {
			List<V> list = resultMap.get(key);
			if(list == null) {
				list = new ArrayList<V>();
				resultMap.put(key, list);
			}
			list.add(value);
		}


		public Set<Entry<K, List<V>>> entrySet() {
			return resultMap.entrySet();
		}


		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if(this == obj) {
				return true;
			}
			if(obj == null) {
				return false;
			}
			if(getClass() != obj.getClass()) {
				return false;
			}
			MultiMap<?, ?> other = (MultiMap<?, ?>) obj;
			if(resultMap == null) {
				if(other.resultMap != null) {
					return false;
				}
			}
			else if(!resultMap.equals(other.resultMap)) {
				return false;
			}
			return true;
		}


		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (resultMap == null ? 0 : resultMap.hashCode());
			return result;
		}
	}
}
