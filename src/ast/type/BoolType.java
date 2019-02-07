package ast.type;

import java.util.Optional;

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

	@Override
	public boolean isSubtype(Type type) {
		return type == IntType.instance() || type == BOOL_TYPE;
	}

	@Override
	public Optional<Type> lowestCommonAncestor(Type other) {
		if(other instanceof IntType) {
			return Optional.of(IntType.instance());
		}
		return other instanceof BoolType ? Optional.of(BOOL_TYPE) : Optional.empty();
	}
}
