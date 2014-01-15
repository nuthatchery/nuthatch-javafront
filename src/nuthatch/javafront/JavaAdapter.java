package nuthatch.javafront;

import static nuthatch.javafront.JavaPatterns.AmbName;
import static nuthatch.javafront.JavaPatterns.Id;
import nuthatch.pattern.Environment;
import nuthatch.pattern.EnvironmentFactory;
import nuthatch.stratego.adapter.STermVar;
import nuthatch.tree.TreeCursor;

import org.spoofax.interpreter.terms.IStrategoTerm;

public class JavaAdapter {

	public static String nameToStr(TreeCursor<IStrategoTerm, Integer> tree) {
		Environment<TreeCursor<IStrategoTerm, Integer>> env = EnvironmentFactory.env();
		STermVar s = new STermVar("s");
		STermVar t = new STermVar("t");
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

}
