package nuthatch.javafront;

import nuthatch.pattern.Pattern;
import nuthatch.stratego.pattern.impl.TermPatternFactory;

import org.spoofax.interpreter.terms.IStrategoTerm;

public class JavaPatterns {
	private static final TermPatternFactory pf = TermPatternFactory.getInstance();


	public static Pattern<IStrategoTerm, Integer> Abstract() {
		return pf.appl("Abstract");
	}


	public static Pattern<IStrategoTerm, Integer> AbstractMethodDec(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2, Pattern<IStrategoTerm, Integer> arg3, Pattern<IStrategoTerm, Integer> arg4, Pattern<IStrategoTerm, Integer> arg5) {
		return pf.appl("AbstractMethodDec", arg0, arg1, arg2, arg3, arg4, arg5);
	}


	public static Pattern<IStrategoTerm, Integer> AltConstrInv(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("AltConstrInv", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> AmbName(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("AmbName", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> AmbName(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("AmbName", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> And(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("And", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Anno(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("Anno", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> AnnoDec(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("AnnoDec", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> AnnoDecHead(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("AnnoDecHead", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> AnnoMethodDec(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2, Pattern<IStrategoTerm, Integer> arg3) {
		return pf.appl("AnnoMethodDec", arg0, arg1, arg2, arg3);
	}


	public static Pattern<IStrategoTerm, Integer> ArrayAccess(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("ArrayAccess", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> ArrayInit(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("ArrayInit", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> ArrayType(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("ArrayType", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> ArrayVarDecId(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("ArrayVarDecId", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> AssertStm(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("AssertStm", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> AssertStm(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("AssertStm", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Assign(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("Assign", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> AssignAnd(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("AssignAnd", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> AssignDiv(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("AssignDiv", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> AssignExcOr(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("AssignExcOr", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> AssignLeftShift(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("AssignLeftShift", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> AssignMinus(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("AssignMinus", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> AssignMul(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("AssignMul", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> AssignOr(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("AssignOr", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> AssignPlus(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("AssignPlus", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> AssignRemain(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("AssignRemain", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> AssignRightShift(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("AssignRightShift", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> AssignURightShift(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("AssignURightShift", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Block(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Block", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> Bool(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Bool", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> Boolean() {
		return pf.appl("Boolean");
	}


	public static Pattern<IStrategoTerm, Integer> Break(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Break", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> Byte() {
		return pf.appl("Byte");
	}


	public static Pattern<IStrategoTerm, Integer> Case(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Case", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> CastPrim(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("CastPrim", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> CastRef(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("CastRef", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Catch(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("Catch", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Char() {
		return pf.appl("Char");
	}


	public static Pattern<IStrategoTerm, Integer> Char(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Char", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> Chars(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Chars", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> Class(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Class", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> ClassBody(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("ClassBody", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> ClassDec(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("ClassDec", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> ClassDecHead(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2, Pattern<IStrategoTerm, Integer> arg3, Pattern<IStrategoTerm, Integer> arg4) {
		return pf.appl("ClassDecHead", arg0, arg1, arg2, arg3, arg4);
	}


	public static Pattern<IStrategoTerm, Integer> ClassDecStm(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("ClassDecStm", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> ClassOrInterfaceType(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("ClassOrInterfaceType", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> ClassType(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("ClassType", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> CompilationUnit(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2) {
		return pf.appl("CompilationUnit", arg0, arg1, arg2);
	}


	public static Pattern<IStrategoTerm, Integer> Complement(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Complement", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> Conc(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("Conc", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Cond(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2) {
		return pf.appl("Cond", arg0, arg1, arg2);
	}


	public static Pattern<IStrategoTerm, Integer> Cons(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("Cons", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> ConstantDec(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2) {
		return pf.appl("ConstantDec", arg0, arg1, arg2);
	}


	public static Pattern<IStrategoTerm, Integer> ConstrBody(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("ConstrBody", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> ConstrDec(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("ConstrDec", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> ConstrDecHead(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2, Pattern<IStrategoTerm, Integer> arg3, Pattern<IStrategoTerm, Integer> arg4) {
		return pf.appl("ConstrDecHead", arg0, arg1, arg2, arg3, arg4);
	}


	public static Pattern<IStrategoTerm, Integer> Continue(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Continue", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> Deci(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Deci", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> Default() {
		return pf.appl("Default");
	}


	public static Pattern<IStrategoTerm, Integer> DefaultVal(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("DefaultVal", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> DeprAbstractMethodDec(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2, Pattern<IStrategoTerm, Integer> arg3, Pattern<IStrategoTerm, Integer> arg4, Pattern<IStrategoTerm, Integer> arg5, Pattern<IStrategoTerm, Integer> arg6) {
		return pf.appl("DeprAbstractMethodDec", arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}


	public static Pattern<IStrategoTerm, Integer> DeprMethodDecHead(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2, Pattern<IStrategoTerm, Integer> arg3, Pattern<IStrategoTerm, Integer> arg4, Pattern<IStrategoTerm, Integer> arg5, Pattern<IStrategoTerm, Integer> arg6) {
		return pf.appl("DeprMethodDecHead", arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}


	public static Pattern<IStrategoTerm, Integer> Dim() {
		return pf.appl("Dim");
	}


	public static Pattern<IStrategoTerm, Integer> Dim(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Dim", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> Div(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("Div", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Double() {
		return pf.appl("Double");
	}


	public static Pattern<IStrategoTerm, Integer> DoWhile(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("DoWhile", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> ElemValArrayInit(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("ElemValArrayInit", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> ElemValPair(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("ElemValPair", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Empty() {
		return pf.appl("Empty");
	}


	public static Pattern<IStrategoTerm, Integer> EnumBody(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("EnumBody", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> EnumBodyDecs(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("EnumBodyDecs", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> EnumConst(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2) {
		return pf.appl("EnumConst", arg0, arg1, arg2);
	}


	public static Pattern<IStrategoTerm, Integer> EnumDec(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("EnumDec", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> EnumDecHead(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2) {
		return pf.appl("EnumDecHead", arg0, arg1, arg2);
	}


	public static Pattern<IStrategoTerm, Integer> Eq(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("Eq", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> ExcOr(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("ExcOr", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> ExprName(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("ExprName", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> ExprName(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("ExprName", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> ExprStm(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("ExprStm", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> ExtendsInterfaces(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("ExtendsInterfaces", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> False() {
		return pf.appl("False");
	}


	public static Pattern<IStrategoTerm, Integer> Field(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("Field", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> FieldDec(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2) {
		return pf.appl("FieldDec", arg0, arg1, arg2);
	}


	public static Pattern<IStrategoTerm, Integer> Final() {
		return pf.appl("Final");
	}


	public static Pattern<IStrategoTerm, Integer> Float() {
		return pf.appl("Float");
	}


	public static Pattern<IStrategoTerm, Integer> Float(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Float", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> For(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2, Pattern<IStrategoTerm, Integer> arg3) {
		return pf.appl("For", arg0, arg1, arg2, arg3);
	}


	public static Pattern<IStrategoTerm, Integer> ForEach(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2) {
		return pf.appl("ForEach", arg0, arg1, arg2);
	}


	public static Pattern<IStrategoTerm, Integer> GenericMethod(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2) {
		return pf.appl("GenericMethod", arg0, arg1, arg2);
	}


	public static Pattern<IStrategoTerm, Integer> Gt(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("Gt", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> GtEq(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("GtEq", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Hexa(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Hexa", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> Id(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Id", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> If(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("If", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> If(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2) {
		return pf.appl("If", arg0, arg1, arg2);
	}


	public static Pattern<IStrategoTerm, Integer> ImplementsDec(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("ImplementsDec", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> InstanceInit(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("InstanceInit", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> InstanceOf(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("InstanceOf", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Int() {
		return pf.appl("Int");
	}


	public static Pattern<IStrategoTerm, Integer> InterfaceDec(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("InterfaceDec", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> InterfaceDecHead(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2, Pattern<IStrategoTerm, Integer> arg3) {
		return pf.appl("InterfaceDecHead", arg0, arg1, arg2, arg3);
	}


	public static Pattern<IStrategoTerm, Integer> InterfaceType(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("InterfaceType", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Invoke(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("Invoke", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Labeled(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("Labeled", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> LazyAnd(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("LazyAnd", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> LazyOr(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("LazyOr", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> LeftShift(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("LeftShift", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Lit(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Lit", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> LocalVarDec(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2) {
		return pf.appl("LocalVarDec", arg0, arg1, arg2);
	}


	public static Pattern<IStrategoTerm, Integer> LocalVarDecStm(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("LocalVarDecStm", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> Long() {
		return pf.appl("Long");
	}


	public static Pattern<IStrategoTerm, Integer> Lt(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("Lt", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> LtEq(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("LtEq", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> MarkerAnno(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("MarkerAnno", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> Member(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2) {
		return pf.appl("Member", arg0, arg1, arg2);
	}


	public static Pattern<IStrategoTerm, Integer> Method(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Method", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> Method(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2) {
		return pf.appl("Method", arg0, arg1, arg2);
	}


	public static Pattern<IStrategoTerm, Integer> MethodDec(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("MethodDec", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> MethodDecHead(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2, Pattern<IStrategoTerm, Integer> arg3, Pattern<IStrategoTerm, Integer> arg4, Pattern<IStrategoTerm, Integer> arg5) {
		return pf.appl("MethodDecHead", arg0, arg1, arg2, arg3, arg4, arg5);
	}


	public static Pattern<IStrategoTerm, Integer> MethodName(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("MethodName", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> MethodName(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("MethodName", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Minus(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Minus", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> Minus(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("Minus", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Mul(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("Mul", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> NamedEscape(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("NamedEscape", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> Native() {
		return pf.appl("Native");
	}


	public static Pattern<IStrategoTerm, Integer> NewArray(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2) {
		return pf.appl("NewArray", arg0, arg1, arg2);
	}


	public static Pattern<IStrategoTerm, Integer> NewInstance(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2, Pattern<IStrategoTerm, Integer> arg3) {
		return pf.appl("NewInstance", arg0, arg1, arg2, arg3);
	}


	public static Pattern<IStrategoTerm, Integer> Nil() {
		return pf.appl("Nil");
	}


	public static Pattern<IStrategoTerm, Integer> NoMethodBody() {
		return pf.appl("NoMethodBody");
	}


	public static Pattern<IStrategoTerm, Integer> None() {
		return pf.appl("None");
	}


	public static Pattern<IStrategoTerm, Integer> Not(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Not", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> NotEq(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("NotEq", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Null() {
		return pf.appl("Null");
	}


	public static Pattern<IStrategoTerm, Integer> Octa(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Octa", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> OctaEscape1(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("OctaEscape1", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> OctaEscape2(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("OctaEscape2", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> OctaEscape3(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2) {
		return pf.appl("OctaEscape3", arg0, arg1, arg2);
	}


	public static Pattern<IStrategoTerm, Integer> Or(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("Or", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> PackageDec(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("PackageDec", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> PackageName(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("PackageName", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> PackageOrTypeName(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("PackageOrTypeName", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> PackageOrTypeName(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("PackageOrTypeName", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Param(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2) {
		return pf.appl("Param", arg0, arg1, arg2);
	}


	public static Pattern<IStrategoTerm, Integer> Plus(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Plus", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> Plus(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("Plus", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> PostDecr(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("PostDecr", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> PostIncr(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("PostIncr", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> PreDecr(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("PreDecr", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> PreIncr(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("PreIncr", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> Private() {
		return pf.appl("Private");
	}


	public static Pattern<IStrategoTerm, Integer> Protected() {
		return pf.appl("Protected");
	}


	public static Pattern<IStrategoTerm, Integer> Public() {
		return pf.appl("Public");
	}


	public static Pattern<IStrategoTerm, Integer> QNewInstance(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2, Pattern<IStrategoTerm, Integer> arg3, Pattern<IStrategoTerm, Integer> arg4, Pattern<IStrategoTerm, Integer> arg5) {
		return pf.appl("QNewInstance", arg0, arg1, arg2, arg3, arg4, arg5);
	}


	public static Pattern<IStrategoTerm, Integer> QSuperConstrInv(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2) {
		return pf.appl("QSuperConstrInv", arg0, arg1, arg2);
	}


	public static Pattern<IStrategoTerm, Integer> QSuperField(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("QSuperField", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> QSuperMethod(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2) {
		return pf.appl("QSuperMethod", arg0, arg1, arg2);
	}


	public static Pattern<IStrategoTerm, Integer> QThis(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("QThis", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> Remain(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("Remain", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Return(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Return", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> RightShift(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("RightShift", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Semicolon() {
		return pf.appl("Semicolon");
	}


	public static Pattern<IStrategoTerm, Integer> Short() {
		return pf.appl("Short");
	}


	public static Pattern<IStrategoTerm, Integer> Single(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Single", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> SingleElemAnno(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("SingleElemAnno", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Some(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Some", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> Static() {
		return pf.appl("Static");
	}


	public static Pattern<IStrategoTerm, Integer> StaticImportDec(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("StaticImportDec", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> StaticImportOnDemandDec(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("StaticImportOnDemandDec", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> StaticInit(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("StaticInit", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> StrictFP() {
		return pf.appl("StrictFP");
	}


	public static Pattern<IStrategoTerm, Integer> String(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("String", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> SuperConstrInv(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("SuperConstrInv", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> SuperDec(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("SuperDec", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> SuperField(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("SuperField", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> SuperMethod(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("SuperMethod", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Switch(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("Switch", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> SwitchBlock(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("SwitchBlock", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> SwitchGroup(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("SwitchGroup", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Synchronized() {
		return pf.appl("Synchronized");
	}


	public static Pattern<IStrategoTerm, Integer> Synchronized(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("Synchronized", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> This() {
		return pf.appl("This");
	}


	public static Pattern<IStrategoTerm, Integer> Throw(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Throw", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> ThrowsDec(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("ThrowsDec", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> Transient() {
		return pf.appl("Transient");
	}


	public static Pattern<IStrategoTerm, Integer> True() {
		return pf.appl("True");
	}


	public static Pattern<IStrategoTerm, Integer> Try(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("Try", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Try(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2) {
		return pf.appl("Try", arg0, arg1, arg2);
	}


	public static Pattern<IStrategoTerm, Integer> TypeArgs(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("TypeArgs", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> TypeBound(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("TypeBound", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> TypeImportDec(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("TypeImportDec", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> TypeImportOnDemandDec(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("TypeImportOnDemandDec", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> TypeName(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("TypeName", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> TypeName(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("TypeName", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> TypeParam(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("TypeParam", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> TypeParams(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("TypeParams", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> TypeVar(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("TypeVar", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> UnboundWld(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("UnboundWld", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> UnicodeEscape(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2, Pattern<IStrategoTerm, Integer> arg3, Pattern<IStrategoTerm, Integer> arg4) {
		return pf.appl("UnicodeEscape", arg0, arg1, arg2, arg3, arg4);
	}


	public static Pattern<IStrategoTerm, Integer> URightShift(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("URightShift", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> VarArityParam(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1, Pattern<IStrategoTerm, Integer> arg2) {
		return pf.appl("VarArityParam", arg0, arg1, arg2);
	}


	public static Pattern<IStrategoTerm, Integer> VarDec(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("VarDec", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> VarDec(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("VarDec", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Void() {
		return pf.appl("Void");
	}


	public static Pattern<IStrategoTerm, Integer> VoidClass() {
		return pf.appl("VoidClass");
	}


	public static Pattern<IStrategoTerm, Integer> Volatile() {
		return pf.appl("Volatile");
	}


	public static Pattern<IStrategoTerm, Integer> While(Pattern<IStrategoTerm, Integer> arg0, Pattern<IStrategoTerm, Integer> arg1) {
		return pf.appl("While", arg0, arg1);
	}


	public static Pattern<IStrategoTerm, Integer> Wildcard(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("Wildcard", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> WildcardLowerBound(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("WildcardLowerBound", arg0);
	}


	public static Pattern<IStrategoTerm, Integer> WildcardUpperBound(Pattern<IStrategoTerm, Integer> arg0) {
		return pf.appl("WildcardUpperBound", arg0);
	}

}
