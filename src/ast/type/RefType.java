package ast.type;

import ast.core.Type;

public class RefType implements Type {
//id della classe
	private final String id;
	
	public RefType(final String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}

	@Override
	public String toPrint(String indent) {
		// TODO Auto-generated method stub
		return indent + "Ref to: " + id + "\n";
	}

	@Override
	public boolean isSubtype(Type type) {
		if(type instanceof RefType) {
			return this.id.equals(((RefType) type).getId());
		}
		return false;
	}

	@Override
	public String toString() {
		return "RefType [id=" + id + "]";
	}

}
