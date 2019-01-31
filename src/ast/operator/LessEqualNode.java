package ast.operator;

import ast.core.Node;
import ast.type.BoolType;
import ast.type.IntType;
import ast.util.LabelGenerator;
import ast.util.LabelGenerator.GenerationSeed;

public class LessEqualNode extends BiOperatorNode {
	
	public LessEqualNode(final Node left, final Node right) {
		super(left, right, "LessEqual", BoolType.instance(), IntType.instance());
	}
	
	@Override
	public String codeGeneration() {
		final String l1 = LabelGenerator.generate(GenerationSeed.Standard.LessEqual);
		final String l2 = LabelGenerator.generate(GenerationSeed.Standard.LessEqual);
		return left.codeGeneration() + 
				right.codeGeneration() +
				"bleq " + l1 + "\n" + //controllo se l'espressione di sinistra è minore o uguale di quella di destra, in questo caso salto a l1
				"push 0 \n" + // è maggiore di quella di destra allora metto false sullo stack (ramo false)
				"b " + l2 + " \n" + //salto ad un punto successivo al ramo true (l1)
				l1  +": \n" + //ramo true
				"push 1 \n" + //metto true in cima alla pila
				l2 + ": \n"; //fine dell'istruzione		
	}

}
