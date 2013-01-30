NUTHATCHHOME=$(HOME)/git
SPOOFAXHOME=$(HOME)/magnolia/workspace
CLASSPATH=.:$(NUTHATCHHOME)/nuthatch/bin:$(NUTHATCHHOME)/nuthatch-stratego/bin:$(SPOOFAXHOME)/org.spoofax.terms/bin/:$(SPOOFAXHOME)/org.spoofax.jsglr/bin
SDFFILES= \
	languages/java/JavaMix.sdf \
	languages/java-15/arrays/Main.sdf \
	languages/java-15/arrays/ArrayInitializers.sdf \
	languages/java-15/lexical/Keywords.sdf \
	languages/java-15/lexical/WhiteSpace.sdf \
	languages/java-15/lexical/literals/StringLiterals.sdf \
	languages/java-15/lexical/literals/IntegerLiterals.sdf \
	languages/java-15/lexical/literals/EscapeSequences.sdf \
	languages/java-15/lexical/literals/BooleanLiterals.sdf \
	languages/java-15/lexical/literals/Main.sdf \
	languages/java-15/lexical/literals/NullLiteral.sdf \
	languages/java-15/lexical/literals/CharacterLiterals.sdf \
	languages/java-15/lexical/literals/FloatingPointLiterals.sdf \
	languages/java-15/lexical/Comments.sdf \
	languages/java-15/lexical/Modifiers.sdf \
	languages/java-15/lexical/Identifiers.sdf \
	languages/java-15/lexical/Main.sdf \
	languages/java-15/lexical/LineTerminators.sdf \
	languages/java-15/lexical/UnicodeEscapes.sdf \
	languages/java-15/interfaces/InterfaceDeclarations.sdf \
	languages/java-15/interfaces/Main.sdf \
	languages/java-15/interfaces/ConstantDeclarations.sdf \
	languages/java-15/interfaces/Annotations.sdf \
	languages/java-15/interfaces/AbstractMethodDeclarations.sdf \
	languages/java-15/interfaces/AnnotationTypes.sdf \
	languages/java-15/names/Main.sdf \
	languages/java-15/classes/InstanceInitializers.sdf \
	languages/java-15/classes/StaticInitializers.sdf \
	languages/java-15/classes/ConstructorDeclarations.sdf \
	languages/java-15/classes/EnumDeclarations.sdf \
	languages/java-15/classes/FieldDeclarations.sdf \
	languages/java-15/classes/Main.sdf \
	languages/java-15/classes/ClassDeclarations.sdf \
	languages/java-15/classes/MethodDeclarations.sdf \
	languages/java-15/types/PrimitiveTypes.sdf \
	languages/java-15/types/ReferenceTypes.sdf \
	languages/java-15/types/TypeVariables.sdf \
	languages/java-15/types/Main.sdf \
	languages/java-15/types/ParameterizedTypes.sdf \
	languages/java-15/Main.sdf \
	languages/java-15/packages/CompilationUnits.sdf \
	languages/java-15/packages/ImportDeclarations.sdf \
	languages/java-15/packages/PackageDeclarations.sdf \
	languages/java-15/packages/Main.sdf \
	languages/java-15/packages/TypeDeclarations.sdf \
	languages/java-15/statements/Statements.sdf \
	languages/java-15/statements/Blocks.sdf \
	languages/java-15/statements/Main.sdf \
	languages/java-15/statements/LocalVariableDeclarations.sdf \
	languages/java-15/expressions/Restrictions.sdf \
	languages/java-15/expressions/ArrayCreation.sdf \
	languages/java-15/expressions/FieldAccess.sdf \
	languages/java-15/expressions/AssignmentOperators.sdf \
	languages/java-15/expressions/BinaryOperators.sdf \
	languages/java-15/expressions/ArrayAccess.sdf \
	languages/java-15/expressions/Postfix.sdf \
	languages/java-15/expressions/Primary.sdf \
	languages/java-15/expressions/Priorities.sdf \
	languages/java-15/expressions/MethodInvocation.sdf \
	languages/java-15/expressions/ClassInstanceCreation.sdf \
	languages/java-15/expressions/UnaryOperators.sdf \
	languages/java-15/expressions/Main.sdf \

all: Java-15.tbl JavaCompilationUnit-15.tbl

%.def: %.sdf $(SDFFILES)
	pack-sdf -i $< -o $@

%.tbl: %.def
	sdf2table -i $< -m $* -o $@ && echo OK

%.rtg: %.def
	sdf2rtg --ignore-missing-cons -i $< -m $* -o $@

%.str: %.rtg
	rtg2sig -i $< --module $* -o $@

../JavaPatterns.java: Java-15.str
	CLASSPATH=$(CLASSPATH) java nuthatch.util.Sig2Java -i $< -o $@ -c JavaPatterns -p nuthatch.javafront

clean:
	rm -f *.def *.tbl *.str *.rtg

.PHONY: all clean
