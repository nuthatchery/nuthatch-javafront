package nuthatch.demo.javafront.class2table;

import static nuthatch.javafront.JavaPatterns.ClassBody;
import static nuthatch.javafront.JavaPatterns.ClassDec;
import static nuthatch.javafront.JavaPatterns.ClassDecHead;
import static nuthatch.javafront.JavaPatterns.CompilationUnit;
import static nuthatch.javafront.JavaPatterns.PackageDec;
import static nuthatch.javafront.JavaPatterns.PackageName;
import static nuthatch.javafront.JavaPatterns.Some;
import static nuthatch.pattern.StaticPatternFactory.and;
import static nuthatch.pattern.StaticPatternFactory.parent;
import static nuthatch.pattern.StaticPatternFactory.var;
import static nuthatch.stratego.pattern.SPatternFactory._;
import static nuthatch.stratego.pattern.SPatternFactory.isList;
import static nuthatch.stratego.pattern.SPatternFactory.var;
import nuthatch.library.BaseComposedAction;
import nuthatch.library.JoinPoints;
import nuthatch.library.Walk;
import nuthatch.pattern.Environment;
import nuthatch.pattern.EnvironmentFactory;
import nuthatch.pattern.VarName;
import nuthatch.stratego.adapter.STermAdapter;
import nuthatch.stratego.adapter.STermCursor;
import nuthatch.stratego.adapter.SWalker;

public class TrackScopeName extends BaseComposedAction<SWalker> {
	private static final VarName<STermCursor> scopeName = new VarName<>("scopeName");


	public TrackScopeName(Walk<SWalker> s) {
		super(s);
	}


	@Override
	public int step(SWalker w) {
		if(JoinPoints.down(w)) {
			Environment<STermCursor> env = EnvironmentFactory.env();
			if(w.match(CompilationUnit(Some(PackageDec(_, PackageName(var("pkgName", isList())))), _, _), env)) {
				w.setSubtreeVar(scopeName, env.get("pkgName"));
			}
			else if(w.match(and(ClassBody(_), parent(ClassDec(ClassDecHead(_, var("name"), _, _, _), _))), env)) {
				STermCursor n = w.getSubtreeVar(scopeName);
				if(n == null) {
					w.setSubtreeVar(scopeName, env.get("name"));
				}
				else {
					w.setSubtreeVar(scopeName, STermAdapter.append(env.get("name"), n));
				}
			}
		}
		return PROCEED;
	}


	public static STermCursor getScopeName(SWalker w) {
		return w.getSubtreeVar(scopeName);
	}
}
