package ast.type;

import java.util.Optional;

import ast.core.Type;

public class NullType implements Type {
	private static final Type NULL_TYPE = new NullType();

	public static Type instance() {
		return NULL_TYPE;
	}

	private NullType() {}

	public String toPrint(String s) {
		return s + "NullType\n";
	}
	
	public String toString() {
		return "NullType";
	}

	@Override
	public boolean isSubtype(Type type) {
		return type == NULL_TYPE  || type instanceof RefType;
	}

	@Override
	public Optional<Type> lowestCommonAncestor(Type other) {
		if(other instanceof NullType) {
			return Optional.of(NULL_TYPE);
		}
		return other instanceof RefType ? Optional.of(other) : Optional.empty();
	}
}
