package ast;

import ast.core.Node;
import ast.core.Type;
import ast.exception.WrongTypeException;
import ast.type.BoolType;

public class AndNode implements Node {
	private final Node left;
	private final Node right;
	
	public AndNode(final Node left, final Node right) {
		super();
		this.left = left;
		this.right = right;
	}

	@Override
	public String toPrint(String indent) {
		return indent + "And\n" + left.toPrint(indent + "  ") + right.toPrint(indent + "  ");
	}

	public Type typeCheck() throws WrongTypeException {
		checkCorrectness(left);
		checkCorrectness(right);
		return BoolType.instance();
	}

	private void checkCorrectness(final Node node) throws WrongTypeException {
		if (!(node.typeCheck().isSubtype(BoolType.instance()))) {
			throw new WrongTypeException("Non boolean in and", BoolType.instance(), node.typeCheck());
		}
	}

	@Override
	public String codeGeneration() {
		return left.codeGeneration() + right.codeGeneration() + "mult\n";
	}

}
