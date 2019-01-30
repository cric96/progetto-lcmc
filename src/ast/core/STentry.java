package ast.core;
/**
 * concetto utilizzato ai fini della symbol table,
 * rappresenta una singola entry, contiene il nesting level
 * il tipo del nodo e l'offset della variabile
 */
public class STentry {

	private int nl;
	private Type type;
	private int offset; //TODO DA RIVEDERE

	public STentry(int n, int os) {
		nl = n;
		offset = os;
	}

	public STentry(int n, Type t, int os) {
		nl = n;
		type = t;
		offset = os;
	}

	public void addType(Type t) {
		type = t;
	}

	public Type getType() {
		return type;
	}

	public int getOffset() {
		return offset;
	}

	public int getNestinglevel() {
		return nl;
	}

	public String toPrint(String s) {
		return s + "STentry: nestlev " + Integer.toString(nl) + "\n" + s + "STentry: type\n " + type.toPrint(s + "  ")
				+ s + "STentry: offset " + offset + "\n";
	}

}