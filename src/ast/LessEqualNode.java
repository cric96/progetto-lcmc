package ast;

import ast.core.Node;
import ast.core.Type;
import ast.exception.WrongTypeException;
import ast.type.BoolType;
import ast.type.IntType;
import ast.util.LabelGenerator;

public class LessEqualNode implements Node {
	private final Node left;
	private final Node right;

	public LessEqualNode(final Node left, final Node right) {
		this.left = left;
		this.right = right;
	}

	public String toPrint(String s) {
		return s + "GreaterEqual\n" + left.toPrint(s + "  ") + right.toPrint(s + "  ");
	}

	public Type typeCheck() throws WrongTypeException {
		checkCorrectness(left);
		checkCorrectness(right);
		return BoolType.instance();
	}

	private void checkCorrectness(final Node node) throws WrongTypeException {
		if (!(node.typeCheck().isSubtype(IntType.instance()))) {
			throw new WrongTypeException("Non integers in greater equal", IntType.instance(), node.typeCheck());
		}
	}

	@Override
	public String codeGeneration() {
		final String l1 = LabelGenerator.generate();
		final String l2 = LabelGenerator.generate();
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
