package ast.type;

import java.util.ArrayList;
import java.util.List;
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
		String parlstr = "";
		for (Type par : parlist) {
			parlstr += par.toPrint(s + "  ");
		}
		return s + "ArrowTypeNode\n" + parlstr + ret.toPrint(s + "  ->");
	}
	
	public String toString() {
		return parlist.stream().map(Type::toString).collect(Collectors.joining(",", "(", ")")) + " -> " + ret;
	}

}
