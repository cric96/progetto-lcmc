package ast;

import ast.core.Node;
import ast.core.Type;
import ast.type.IntType;

public class IntNode implements Node {

	private final Integer val;

	public IntNode(final Integer n) {
		val = n;
	}

	public String toPrint(String s) {
		return s + "Int:" + Integer.toString(val) + "\n";
	}

	public Type typeCheck() {
		return IntType.instance();
	}

	public String codeGeneration() {
		return "push " + val + "\n";
	}

}