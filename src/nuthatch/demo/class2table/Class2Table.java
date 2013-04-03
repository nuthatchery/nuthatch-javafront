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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nuthatch.javafront.JavaAdapter;
import nuthatch.javafront.JavaParser;
import nuthatch.library.walks.Action;
import nuthatch.pattern.Environment;
import nuthatch.pattern.EnvironmentFactory;
import nuthatch.pattern.Pattern;
import nuthatch.stratego.actions.ActionFactory;
import nuthatch.stratego.actions.ActionFactory.MatchSwitchBuilder;
import nuthatch.stratego.actions.MatchedTermAction;
import nuthatch.stratego.adapter.TermCursor;
import nuthatch.stratego.adapter.TermWalk;
import nuthatch.walk.Step;

import org.spoofax.interpreter.terms.IStrategoTerm;
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

		Collection<Table> tables = transform(term);
		for(Table t : tables) {
			System.out.println(t.toSQL());
		}
	}


	/**
	 * This is the main class2table transformation.
	 * 
	 * @return A collection of tables
	 */
	public static Collection<Table> transform(TermCursor javaTree) {
		// we'll accumulate the result here
		final List<Table> tables = new ArrayList<Table>();

		// we'll do a default walk of the entire tree, doing classDecAction on every step 
		Step<TermWalk> step = ActionFactory.defaultStep(classDecAction(tables));
		TermWalk walk = new TermWalk(javaTree, step);
		walk.start();

		return tables;
	}


	/**
	 * Make an action that will recognize class declaration and transform them
	 * to tables
	 * 
	 * @param tables
	 *            The list where the result will be accumulated when the action
	 *            is performed
	 * @return an action
	 */
	private static Action<TermWalk> classDecAction(final List<Table> tables) {
		// The pattern for class declarations
		Pattern<IStrategoTerm, Integer> classDecPat = ClassDec(ClassDecHead(var("modifiers"), var("name"), _, var("extends"), var("implements")), ClassBody(var("body")));

		// we construct an action which will be executed
		//  - if we're on the way down (ifDown)
		//  - if the current node matches the pattern (ifMatches)
		Action<TermWalk> action = ActionFactory.ifDown(ActionFactory.ifMatches(classDecPat, new MatchedTermAction() {
			@Override
			public int action(TermWalk walk, Environment<TermCursor> env) {
				String tableName = JavaAdapter.nameToStr(env.get("name")); // get the name as a string
				final Table table = new Table(tableName);

				// this subwalk will visit all the field declarations in this class declaration
				walk.walkSubtree(ActionFactory.defaultStep(fieldDecAction(table)));

				tables.add(table);
				return PROCEED;
			}
		}));
		return action;
	}


	/**
	 * Make an action that will recognize field declarations and turn them into
	 * columns
	 * 
	 * @param table
	 *            The Table the columns should be added to
	 * @return an action
	 */
	private static Action<TermWalk> fieldDecAction(final Table table) {
		// we use this builder to build up what is basically a pattern-match switch statement
		MatchSwitchBuilder builder = ActionFactory.matchSwitchBuilder();

		// the pattern for field declarations, with and without initialisers
		// Note: declaring multiple fields in the same declaration is allowed in Java,
		// but not supported here
		Pattern<IStrategoTerm, Integer> fieldDecNoInit = FieldDec(_, var("type"), list(VarDec(var("name"))));
		Pattern<IStrategoTerm, Integer> fieldDecWithInit = FieldDec(_, var("type"), list(VarDec(var("name"), _)));
		Pattern<IStrategoTerm, Integer> fieldDecPat = or(fieldDecNoInit, fieldDecWithInit);

		// if fieldDecPat matches, we'll do this action
		builder.add(fieldDecPat, new MatchedTermAction() {
			// walk refers to the current walk, with context information on the current node
			// env will contain the variables we matched in the pattern
			@Override
			public int action(TermWalk walk, Environment<TermCursor> env) {
				// Obtain the type name. Second element of the pair will be the foreign key if this field is
				// a reference to another object.
				ColumnType type = transformTypeName(env.get("type"));
				String fieldName = JavaAdapter.nameToStr(env.get("name"));
				table.addColumn(new Column(fieldName, type.typeName, type.foreignKey));
				return Action.PROCEED;
			}
		});

		// if we hit a class declaration, we should stop, so we don't get the fields of inner classes
		builder.add(ClassDec(_, _), new MatchedTermAction() {
			@Override
			public int action(TermWalk walk, Environment<TermCursor> env) {
				return Step.PARENT;
			}
		});

		// we'll do the action only on the way down the tree
		return ActionFactory.ifDown(builder.done());
	}


	private static ColumnType transformTypeName(TermCursor c) {
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
		return new ColumnType(type, foreignKey);
	}


	static class ColumnType {
		String typeName;
		String foreignKey;


		ColumnType(String typeName, String foreignKey) {
			this.typeName = typeName;
			this.foreignKey = foreignKey;
		}
	}
}
