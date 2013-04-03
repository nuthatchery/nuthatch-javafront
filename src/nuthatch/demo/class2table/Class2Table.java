package nuthatch.demo.class2table;

import static nuthatch.javafront.JavaPatterns.Boolean;
import static nuthatch.javafront.JavaPatterns.Byte;
import static nuthatch.javafront.JavaPatterns.Char;
import static nuthatch.javafront.JavaPatterns.ClassBody;
import static nuthatch.javafront.JavaPatterns.ClassDec;
import static nuthatch.javafront.JavaPatterns.ClassDecHead;
import static nuthatch.javafront.JavaPatterns.ClassOrInterfaceType;
import static nuthatch.javafront.JavaPatterns.Double;
import static nuthatch.javafront.JavaPatterns.FieldDec;
import static nuthatch.javafront.JavaPatterns.Float;
import static nuthatch.javafront.JavaPatterns.Id;
import static nuthatch.javafront.JavaPatterns.Int;
import static nuthatch.javafront.JavaPatterns.Long;
import static nuthatch.javafront.JavaPatterns.None;
import static nuthatch.javafront.JavaPatterns.TypeName;
import static nuthatch.javafront.JavaPatterns.VarDec;
import static nuthatch.pattern.StaticPatternFactory.or;
import static nuthatch.stratego.pattern.StaticTermPatternFactory._;
import static nuthatch.stratego.pattern.StaticTermPatternFactory.list;
import static nuthatch.stratego.pattern.StaticTermPatternFactory.string;
import static nuthatch.stratego.pattern.StaticTermPatternFactory.var;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import nuthatch.javafront.JavaAdapter;
import nuthatch.javafront.JavaParser;
import nuthatch.library.walks.DefaultVisitor;
import nuthatch.library.walks.Visitor;
import nuthatch.pattern.Environment;
import nuthatch.pattern.EnvironmentFactory;
import nuthatch.stratego.adapter.TermCursor;
import nuthatch.stratego.adapter.TermWalk;
import nuthatch.util.Pair;
import nuthatch.walk.Step;

import org.spoofax.jsglr.client.InvalidParseTableException;
import org.spoofax.jsglr.shared.SGLRException;

/**
 * This example transforms Java classes to SQL tables.
 * 
 */
public class Class2Table {
	public static void main(String[] args) throws SGLRException, IOException, InvalidParseTableException {
		JavaParser.init();
		TermCursor term = JavaParser.parseStream(Class2Table.class.getResourceAsStream("examples/Example.java.ex"), "Example.java");

		Class2Table class2Table = new Class2Table(term);
		Collection<Table> tables = class2Table.transform();
		for(Table t : tables) {
			System.out.println(t.toSQL());
		}
	}

	private Map<TermCursor, Table> tables;

	private TermCursor javaTree;


	/**
	 * The tree should be a Java package (or list of packages) containing all
	 * the classes to be converted.
	 * 
	 * @param javaTree
	 *            The Java tree we wish to transform
	 */
	public Class2Table(TermCursor javaTree) {
		this.javaTree = javaTree;
	}


	/**
	 * This is the main class2table transformation.
	 * 
	 * @return A collection of tables
	 */
	public Collection<Table> transform() {
		tables = new HashMap<TermCursor, Table>();

		// we'll walk the entire tree, calling transformClassDec on every node
		// if we get a non-null result, we've hit a class declaration and obtained a table
		Visitor<TermWalk> visitor = new DefaultVisitor<TermWalk>() {
			@Override
			public void onEntry(TermWalk w) {
				Pair<TermCursor, Table> p = transformClassDec(w);
				if(p != null) {
					tables.put(p.getFirst(), p.getSecond());
				}
			}
		};

		// we don't actually make use of the scope names, but we could have, in order
		// to distinguish between classes with the same name in different packages
		TrackScopeName trackScopeName = new TrackScopeName(visitor);

		// do it!
		TermWalk walk = new TermWalk(javaTree, trackScopeName);
		walk.start();

		return tables.values();
	}


	/**
	 * This method matches a class declaration and returns the corresponding
	 * table.
	 * 
	 * @param w
	 *            The current walk
	 * @return A pair of <name, table>, with the name given in Java abstract
	 *         syntax
	 */
	private Pair<TermCursor, Table> transformClassDec(TermWalk w) {
		Environment<TermCursor> env = EnvironmentFactory.env();
		if(w.match(ClassDec(ClassDecHead(var("modifiers"), var("name"), _, var("extends"), var("implements")), ClassBody(var("body"))), env)) {
			String tableName = JavaAdapter.nameToStr(env.get("name")); // get the name as a string
			final Table table = new Table(tableName);

			// this subwalk will visit all the field declarations in this class declaration
			Step<TermWalk> step = new Step<TermWalk>() {
				@Override
				public int step(TermWalk w) {
					Environment<TermCursor> env = EnvironmentFactory.env();
					// go up if we hit an inner class declaration
					if(w.match(ClassDec(_, _), env)) {
						return PARENT;
					}

					// match a field declaration and convert to column
					Column col = transformField(w);
					if(col != null) {
						table.addColumn(col);
					}
					return NEXT; // default traversal
				}
			};
			w.walkSubtree(step);
			return new Pair<>(env.get("name"), table);
		}
		else {
			return null;
		}
	}


	/**
	 * Match a field declaration and convert it to a column.
	 * 
	 * @param w
	 *            Current walk
	 * @return A column, or null if the current node is not a field declaration
	 */
	private Column transformField(TermWalk w) {
		Environment<TermCursor> env = EnvironmentFactory.env();
		if(w.match(or(FieldDec(_, var("type"), list(VarDec(var("name")))), FieldDec(_, var("type"), list(VarDec(var("name"), _)))), env)) {
			// Obtain the type name. Second element of the pair will be the foreign key if this field is
			// a reference to another object.
			Pair<String, String> typeName = transformTypeName(env.get("type"));
			String fieldName = JavaAdapter.nameToStr(env.get("name"));
			return new Column(fieldName, typeName.getFirst(), typeName.getSecond());
		}
		else {
			return null;
		}
	}


	private Pair<String, String> transformTypeName(TermCursor c) {
		Environment<TermCursor> env = EnvironmentFactory.env();
		String type = null;
		String foreignKey = null;

		// primitive types
		if(Int().match(c, env)) {
			type = "INT";
		}
		else if(Double().match(c, env)) {
			type = "DOUBLE";
		}
		else if(Float().match(c, env)) {
			type = "FLOAT";
		}
		else if(Long().match(c, env)) {
			type = "BIGINT";
		}
		else if(Boolean().match(c, env)) {
			type = "BOOLEAN";
		}
		else if(Char().match(c, env)) {
			type = "CHAR(1)";
		}
		else if(Byte().match(c, env)) {
			type = "TINYINT";
		}
		// this one is an object in Java, but primitive in SQL
		else if(ClassOrInterfaceType(TypeName(Id(string("String"))), None()).match(c, env)) {
			type = "VARCHAR(MAX)";
		}
		// object references are translated to ID references in another table
		else if(ClassOrInterfaceType(TypeName(var("name")), None()).match(c, env)) {
			type = "INT";
			foreignKey = JavaAdapter.nameToStr(env.get("name"));
		}

		// whoops!
		return new Pair<>(type, foreignKey);
	}
}
