package ast.operator;

import ast.core.Node;
import ast.type.IntType;

public class DivNode extends BiOperatorNode {
	
	public DivNode(final Node left, final Node right) {
		super(left, right, "Division", IntType.instance(), IntType.instance());
	}

	@Override
	public String codeGeneration() {
		return left.codeGeneration() + right.codeGeneration() + "div\n";
	}

}
