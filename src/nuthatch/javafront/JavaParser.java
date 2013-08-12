package nuthatch.javafront;

import java.io.IOException;
import java.io.InputStream;

import nuthatch.stratego.adapter.STermCursor;
import nuthatch.stratego.adapter.StrategoAdapter;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.jsglr.client.InvalidParseTableException;
import org.spoofax.jsglr.client.ParseTable;
import org.spoofax.jsglr.shared.SGLRException;
import org.spoofax.terms.ParseError;

public class JavaParser {
	private static ParseTable javaParseTable;


	/**
	 * init() must be called first
	 * 
	 * @return the Java parse table
	 */
	public static ParseTable getJavaParseTable() {
		return javaParseTable;
	}


	/**
	 * Load the parse table for Java
	 * 
	 * @throws ParseError
	 * @throws IOException
	 * @throws InvalidParseTableException
	 */
	public static void init() throws ParseError, IOException, InvalidParseTableException {
		if(javaParseTable == null) {
			try (InputStream stream = JavaParser.class.getResourceAsStream("syntax/JavaCompilationUnit-15.tbl")) {
				javaParseTable = StrategoAdapter.getParseTableManager().loadFromStream(stream);
			}
		}
	}


	/**
	 * Parse a file.
	 * 
	 * Note: init() must be called first
	 * 
	 * @param fileName
	 * @return
	 * @throws SGLRException
	 * @throws IOException
	 */
	public static STermCursor parseFile(String fileName) throws SGLRException, IOException {
		IStrategoTerm term = StrategoAdapter.parseFile(fileName, getJavaParseTable());
		return StrategoAdapter.termToTree(term);
	}


	public static STermCursor parseFileToAsfix(String fileName) throws SGLRException, IOException {
		IStrategoTerm term = StrategoAdapter.parseFileToAsfix(fileName, null, getJavaParseTable());
		return StrategoAdapter.termToTree(term);
	}


	public static STermCursor parseStream(InputStream input, String fileName) throws SGLRException, IOException {
		IStrategoTerm term = StrategoAdapter.parseStream(input, fileName, null, getJavaParseTable());
		return StrategoAdapter.termToTree(term);
	}


	public static STermCursor parseStreamToAsfix(InputStream input, String fileName) throws SGLRException, IOException {
		IStrategoTerm term = StrategoAdapter.parseStreamToAsfix(input, fileName, null, getJavaParseTable());
		return StrategoAdapter.termToTree(term);
	}


	public static STermCursor parseString(String input, String fileName) throws SGLRException {
		IStrategoTerm term = StrategoAdapter.parseString(input, fileName, getJavaParseTable());
		return StrategoAdapter.termToTree(term);
	}


	public static STermCursor parseStringToAsfix(String input, String fileName) throws SGLRException {
		IStrategoTerm term = StrategoAdapter.parseStringToAsfix(input, fileName, null, getJavaParseTable());
		return StrategoAdapter.termToTree(term);
	}

}
