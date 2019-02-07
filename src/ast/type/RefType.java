package ast.type;

import java.util.Optional;

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
			return ClassHierarchy.isSubtype(((RefType) type).getId(), id);
		}
		return false;
	}

	@Override
	public String toString() {
		return "RefType [id=" + id + "]";
	}

	@Override
	public Optional<Type> lowestCommonAncestor(Type other) {
		if(other instanceof NullType) {
			return Optional.of(this);
		}
		if(other instanceof RefType) {
			Optional<RefType> check = Optional.of((RefType) this);
			while(check.isPresent()) {
				if(other.isSubtype(check.get())) {
					return Optional.of(check.get());
				}
				check = ClassHierarchy.getFather(check.get().getId());
			}
		}
		return Optional.empty();
	}

}
