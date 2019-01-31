package ast.operator;

import ast.core.Node;
import ast.type.IntType;

public class PlusNode extends BiOperatorNode {
	public PlusNode(final Node left, final Node right) {
		super(left, right, "Plus", IntType.instance(), IntType.instance());
	}

	public String codeGeneration() {
		return left.codeGeneration() + right.codeGeneration() + "add\n";
	}

}