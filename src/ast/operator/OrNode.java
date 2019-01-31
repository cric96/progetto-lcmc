package ast.operator;

import ast.core.Node;
import ast.type.BoolType;
import ast.util.LabelGenerator;

public class OrNode extends BiOperatorNode {
	public OrNode(final Node left, final Node right) {
		super(left, right, "Or", BoolType.instance(), BoolType.instance());
	}
	/*
	 *per vedere se il risultato � true o false sommo il risultato di left e right (mi dar� il
	 *risultato sperato quindi o 0 o 1 tranne che se sono entrambi true : mi dar� 2). 
	 *per controllare questo caso controllo se l'add mi ha dato risultato 0, se l'ha fatto allora metto
	 *sullo stack 0 se non lo ha fatto (era o 1 o 2) allora vuol dire che lo devo sostituire con 1
	 */
	@Override
	public String codeGeneration() {	
		final String l1 = LabelGenerator.generate(LabelGenerator.GenerationSeed.Standard.Or);
		final String l2 = LabelGenerator.generate(LabelGenerator.GenerationSeed.Standard.Or);
		return left.codeGeneration() 
				+ right.codeGeneration() 
				+ "add\n"
				+ "push 0\n"
				+ "bleq " + l1 +"\n"
				+ "push 1\n"
				+ "b " + l2 + "\n"
				+ l1 + ": \n "
				+ "push 0\n"
				+ l2 + ": \n";
	}

}
