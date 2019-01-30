package ast;

import ast.core.Node;
import ast.core.Type;
import ast.type.BoolType;

public class BoolNode implements Node {

	private final boolean value;

	public BoolNode(final boolean value) {
		this.value = value;
	}

	public String toPrint(String s) {
		if (this.value)
			return s + "Bool:true\n";
		else
			return s + "Bool:false\n";
	}

	public Type typeCheck() {
		return BoolType.instance();
	}

	public String codeGeneration() {
		return "push " + (value ? 1 : 0) + "\n";
	}

}