package ast.operator;

import ast.core.Node;
import ast.type.BoolType;

public class AndNode extends BiOperatorNode {
	public AndNode(final Node left, final Node right) {
		super(left, right, "And", BoolType.instance(), BoolType.instance());
	}

	@Override
	public String codeGeneration() {
		return left.codeGeneration() + right.codeGeneration() + "mult\n";
	}

}
