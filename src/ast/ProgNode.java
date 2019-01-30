package ast;

import ast.core.Node;
import ast.core.Type;
import ast.exception.WrongTypeException;

public class ProgNode implements Node {

	private final Node exp;

	public ProgNode(final Node exp) {
		this.exp = exp;
	}

	public String toPrint(String s) {
		return s + "Prog\n" + exp.toPrint(s + "  ");
	}

	public Type typeCheck() throws WrongTypeException {
		return exp.typeCheck();
	}

	public String codeGeneration() {
		return exp.codeGeneration() + "halt\n";
	}

}