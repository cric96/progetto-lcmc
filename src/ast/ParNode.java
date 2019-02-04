package ast;

import java.util.Objects;

import ast.core.DeclarationNode;
import ast.core.Type;

public class ParNode implements DeclarationNode {

	private final String id;
	private final Type type;

	public ParNode(final String id, final Type type) {
		Objects.requireNonNull(type);
		Objects.requireNonNull(id);
		this.id = id;
		this.type = type;
	}

	public String toPrint(String s) {
		return s + "Par:" + id + "\n" + type.toPrint(s + "  ");
	}

	// non utilizzato
	public Type typeCheck() {
		return null;
	}

	public String codeGeneration() {
		return "";
	}

	@Override
	public Type getSymbolType() {
		return this.type;
	}

	@Override
	public String toString() {
		return "ParNode [id=" + id + ", type=" + type + "]";
	}

}