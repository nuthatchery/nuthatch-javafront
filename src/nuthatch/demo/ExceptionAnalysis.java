package nuthatch.demo;

import java.io.IOException;
import java.io.InputStream;

import nuthatch.engine.Engine;
import nuthatch.engine.impl.StrategicEngine;
import nuthatch.javafront.JavaPatterns;
import nuthatch.library.strategies.Visitor;
import static nuthatch.javafront.JavaPatterns.*;
import nuthatch.pattern.Environment;
import nuthatch.pattern.EnvironmentFactory;
import nuthatch.pattern.Pattern;
import nuthatch.stratego.adapter.StrategoAdapter;
import nuthatch.stratego.adapter.TermEngine;
import nuthatch.stratego.pattern.TermPatternFactory;
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
		
		Visitor<TermEngine> visitor = new Visitor<TermEngine>() {
			@Override
			public void onEntry(TermEngine e) {
				Environment<TreeCursor<IStrategoTerm, Integer>> env = EnvironmentFactory.env();

				if(ClassBody(var("body")).match(e, env)) {
					for(TreeCursor<IStrategoTerm, Integer> child : env.get("body")) {
						System.out.println(child.treeToString());
					}
				}
				else if(MethodDec(MethodDecHead(var("modifiers"),var("typeParams"),var("retType"),var("methodName"),var("params"),var("throws")),var("body")).match(e, env)) {
					System.out.println(env.get("methodName").treeToString());
				}
			}
		};
		
		TermEngine engine = new TermEngine(StrategoAdapter.termToTree(term), visitor);
		engine.engage();
	}
	
	public static ParseTable getJavaParseTable() throws ParseError, IOException, InvalidParseTableException {
		InputStream stream = JavaPatterns.class.getResourceAsStream("syntax/JavaCompilationUnit-15.tbl");
		
		return StrategoAdapter.getParseTableManager().loadFromStream(stream);
	}

	public static Pattern<IStrategoTerm, Integer> var(String name) {
		return TermPatternFactory.getInstance().var(name);
	}
}
