package ast.operator;

import ast.core.Node;
import ast.type.IntType;

public class MinusNode extends BiOperatorNode {
	public MinusNode(final Node left, final Node right) {
		super(left, right, "Minus", IntType.instance(), IntType.instance());
	}

	@Override
	public String codeGeneration() {
		return left.codeGeneration() + right.codeGeneration() + "sub\n";
	}

}
