package ast.operator;

import ast.core.Node;
import ast.type.BoolType;
import ast.type.IntType;
import ast.util.LabelGenerator;
import ast.util.LabelGenerator.GenerationSeed;

public class GreaterEqualNode extends BiOperatorNode {

	public GreaterEqualNode(final Node left, final Node right) {
		super(left, right, "GreaterEqual", BoolType.instance(), IntType.instance());
	}

	@Override
	public String codeGeneration() {
		final String l1 = LabelGenerator.generate(GenerationSeed.Standard.GreaterEqual);
		final String l2 = LabelGenerator.generate(GenerationSeed.Standard.GreaterEqual);
		/*
		 * idea: per fare in modo di simulare il >= sottraggo i due numeri, se il
		 * risultato è negativo (<= -1) allora vuol dire che left <= right altrimento
		 * left >= right
		 */
		return left.codeGeneration() + right.codeGeneration() + "sub \n" + // sottrago i due numeri
				"push -1 \n" + // metto -1 sullo stack per verificare se la sottrazione risulta <= -1
				"bleq " + l1 + "\n" + // effettuo il controllo, se è minore o uguale a -1 allora metto sullo stack
										// false altrimenti true
				"push 1 \n" + // left è >= di right allora metto true sullo stack (in quanto la loro sottrazione è >= 0)
				"b " + l2 + " \n" + // salto ad un punto successivo al ramo true (l1)
				l1 + ": \n" + // ramo true
				"push 0 \n" + // metto false sulla pila in quanto la sottrazione risulta <= -1 (cioè left è minore di right)
				l2 +": \n"; // fine dell'istruzione

	}

}
