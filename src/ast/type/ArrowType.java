package ast.type;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ast.core.Type;

/**
 * Descrizione del tipo freccia, viene usato per associare un tipo alle
 * funzioni. esempio : fun f:int (i:bool, j:int) ha il tipo freccia seguente:
 * (bool,int) -> int
 */
public class ArrowType implements Type {
	private final List<Type> parlist;
	private final Type ret;

	public ArrowType(final List<Type> parameterList, final Type returnType) {
		parlist = new ArrayList<>(parameterList);
		ret = returnType;
	}
	/**
	 * 
	 * @return il tipo di ritorno dell'arrow type
	 */
	public Type getReturnType() {
		return ret;
	}
	/**
	 * 
	 * @return la lista dei tipi dei parametri dell'arrow type
	 */
	public List<Type> getParList() {
		return parlist;
	}

	public String toPrint(String s) {
		String parlstr = parlist.stream().map(x -> x.toPrint(" " + s)).collect(Collectors.joining());
		return s + "ArrowType\n" + parlstr + ret.toPrint(s + "  ->");
	}
	
	public String toString() {
		return parlist.stream().map(Type::toString).collect(Collectors.joining(",", "(", ")")) + " -> " + ret;
	}
	@Override
	public boolean isSubtype(Type type) {
		if (!(type instanceof ArrowType)) {
			return false;
		}
		final ArrowType t = (ArrowType)type;
		final List<Type> p = t.getParList();
		if (!(p.size() == parlist.size())) {
			return false;
		}
		for (int i = 0; i < parlist.size(); i++) {
			if (!(p.get(i)).isSubtype((parlist.get(i)))) {
				return false;
			}
		}
		return ret.isSubtype(t.getReturnType());
	}
	@Override
	public Optional<Type> lowestCommonAncestor(Type other) {
		if(!(other instanceof ArrowType)) {
			return Optional.empty();
		}
		final ArrowType t = (ArrowType)other;
		final List<Type> p = t.getParList();
		if (!(p.size() == parlist.size())) {
			return Optional.empty();
		}
		final Optional<Type> returnLowestCommonAncestor = this.ret.lowestCommonAncestor(t.getReturnType());
		if(!returnLowestCommonAncestor.isPresent()) {
			return Optional.empty();
		}
		final List<Type> parListTypes = new ArrayList<>();
		for (int i = 0; i < parlist.size(); i++) {
			if ((p.get(i)).isSubtype((parlist.get(i)))) {
				parListTypes.add(p.get(i));
			} else if(parlist.get(i).isSubtype(p.get(i))) {
				parListTypes.add(parlist.get(i));
			} else {
				return null;
			}
		}
		
		return Optional.of(new ArrowType(parListTypes, returnLowestCommonAncestor.get()));
	}

}
