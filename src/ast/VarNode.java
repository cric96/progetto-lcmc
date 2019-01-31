package ast;

import ast.core.Node;
import ast.core.Type;
import ast.exception.WrongTypeException;

public class VarNode implements Node {

	private final String id;
	private final Type type;
	private final Node exp;

	public VarNode(final String id, final Type type, final Node exp) {
		this.id = id;
		this.type = type;
		this.exp = exp;
	}

	public String toPrint(String s) {
		return s + "Var:" + id + "\n" + type.toPrint(s + "  ") + exp.toPrint(s + "  ");
	}

	public Type typeCheck() throws WrongTypeException {
		if (!exp.typeCheck().isSubtype(type)) {
			throw new WrongTypeException("Incompatible value for variable: " + id, type, exp.typeCheck());
		}
		return null;
	}

	public String codeGeneration() {
		return exp.codeGeneration();
	}

}