package nuthatch.demo.class2table;

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
import static nuthatch.stratego.pattern.StaticTermPatternFactory._;
import static nuthatch.stratego.pattern.StaticTermPatternFactory.isList;
import static nuthatch.stratego.pattern.StaticTermPatternFactory.var;
import nuthatch.library.walks.VisitorAspect;
import nuthatch.pattern.Environment;
import nuthatch.pattern.EnvironmentFactory;
import nuthatch.pattern.VarName;
import nuthatch.stratego.adapter.TermAdapter;
import nuthatch.stratego.adapter.TermCursor;
import nuthatch.stratego.adapter.TermWalk;
import nuthatch.walk.Step;

public class TrackScopeName extends VisitorAspect<TermWalk> {
	private static final VarName<TermCursor> scopeName = new VarName<>("scopeName");


	public static TermCursor getScopeName(TermWalk w) {
		return w.getSubtreeVar(scopeName);
	}


	public TrackScopeName(Step<TermWalk> s) {
		super(s);
	}


	@Override
	public void beforeEntry(TermWalk w) {
		Environment<TermCursor> env = EnvironmentFactory.env();
		if(w.match(CompilationUnit(Some(PackageDec(_, PackageName(var("pkgName", isList())))), _, _), env)) {
			w.setSubtreeVar(scopeName, env.get("pkgName"));
		}
		else if(w.match(and(ClassBody(_), parent(ClassDec(ClassDecHead(_, var("name"), _, _, _), _))), env)) {
			TermCursor n = w.getSubtreeVar(scopeName);
			if(n == null) {
				w.setSubtreeVar(scopeName, env.get("name"));
			}
			else {
				w.setSubtreeVar(scopeName, TermAdapter.append(env.get("name"), n));
			}
		}
	}
}
