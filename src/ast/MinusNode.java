package ast;

import ast.core.Node;
import ast.core.Type;
import ast.exception.WrongTypeException;
import ast.type.IntType;

public class MinusNode implements Node {
	
	private final Node left;
	private final Node right;
	
	public MinusNode(final Node left, final Node right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public String toPrint(String indent) {
		return indent + "Minus\n" + left.toPrint(indent + " ") 
		+ right.toPrint(indent + " ");
	}

	public Type typeCheck() throws WrongTypeException {
		checkCorrectness(left);
		checkCorrectness(right);
		return IntType.instance();
	}

	private void checkCorrectness(final Node node) throws WrongTypeException {
		if (!(node.typeCheck().isSubtype(IntType.instance()))) {
			throw new WrongTypeException("Non integers in subtraction", IntType.instance(), node.typeCheck());
		}
	}
	@Override
	public String codeGeneration() {
		return left.codeGeneration() + right.codeGeneration() + "sub\n";
	}

}
