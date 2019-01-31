package ast;

import ast.core.Node;
import ast.core.Type;
import ast.exception.WrongTypeException;
import ast.type.BoolType;
import ast.type.IntType;
import ast.util.LabelGenerator;

public class GreaterEqualNode implements Node {
	private final Node left;
	private final Node right;

	public GreaterEqualNode(final Node left, final Node right) {
		this.left = left;
		this.right = right;
	}

	public String toPrint(String s) {
		return s + "LessEqual\n" + left.toPrint(s + "  ") + right.toPrint(s + "  ");
	}

	public Type typeCheck() throws WrongTypeException {
		checkCorrectness(left);
		checkCorrectness(right);
		return BoolType.instance();
	}

	private void checkCorrectness(final Node node) throws WrongTypeException {
		if (!(node.typeCheck().isSubtype(IntType.instance()))) {
			throw new WrongTypeException("Non integers in less equal", IntType.instance(), node.typeCheck());
		}
	}

	@Override
	public String codeGeneration() {
		final String l1 = LabelGenerator.generate();
		final String l2 = LabelGenerator.generate();
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
