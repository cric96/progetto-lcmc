package ast;

import ast.core.Node;
import ast.core.Type;
import ast.exception.WrongTypeException;
/**
 * Nodo semplice associato all'istruzione print
 */
public class PrintNode implements Node {

	private final Node exp;

	public PrintNode(final Node exp) {
		this.exp = exp;
	}

	public String toPrint(String s) {
		return s + "Print\n" + exp.toPrint(s + "  ");
	}

	public Type typeCheck() throws WrongTypeException {
		return exp.typeCheck();
	}

	public String codeGeneration() {
		return exp.codeGeneration() + "print\n";
	}

}