package ast.type;

import ast.core.Type;

public class IntType implements Type {
	private static final Type INT_TYPE = new IntType();
	
	public static Type instance() {
		return INT_TYPE;
	}
	
	private IntType () {}
	
	public String toPrint(String s) {
		return s + "IntType\n";
	}
	
	public String toString() {
		return "IntType";
	}
}
