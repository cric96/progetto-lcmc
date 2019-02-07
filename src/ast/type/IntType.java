package ast.type;

import java.util.Optional;

import ast.core.Type;

public class IntType implements Type {
	/*
	 * Pattern singleton:
	 * ogni nodo foglia fa riferimento (se � di tipo intero) sempre alla stessa
	 * istanza, non c'� bisogno quindi di instaziare un oggetto nuovo ogni
	 * volta che incotriamo un intero.
	 * Per questo motivo si � utilizzato il pattern sigleton (usato anche nei bool)
	 */
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
	
	@Override
	public boolean isSubtype(Type type) {
		//posso farlo perch� utilizzo il pattern singleton e perci� se � un intero sicuramente � dell'istanza INT_TYPE
		return type == INT_TYPE;
	}

	@Override
	public Optional<Type> lowestCommonAncestor(Type other) {
		return other instanceof IntType || other instanceof BoolType ? Optional.of(INT_TYPE) : Optional.empty();
	}
}
