package ast.operator;

import ast.core.Node;
import ast.type.IntType;

public class MultNode extends BiOperatorNode {
	public MultNode(final Node left, final Node right) {
		super(left, right, "Multiplication", IntType.instance(), IntType.instance());
	}
	
	public String codeGeneration() {
		return left.codeGeneration() + right.codeGeneration() + "mult\n";
	}

}