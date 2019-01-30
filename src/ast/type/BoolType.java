package ast.type;

import ast.core.Type;

public class BoolType implements Type {
	private static final Type BOOL_TYPE = new BoolType();

	public static Type instance() {
		return BOOL_TYPE;
	}

	private BoolType() {}

	public String toPrint(String s) {
		return s + "BoolType\n";
	}
	
	public String toString() {
		return "BoolType";
	}
}
