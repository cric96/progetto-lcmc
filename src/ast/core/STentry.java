package ast.core;
/**
 * concetto utilizzato ai fini della symbol table,
 * rappresenta una singola entry, contiene il nesting level
 * il tipo del nodo e l'offset della variabile
 */
public class STentry {

	private final int nestingLevel;
	private final Type type;
	private final int offset; 

	public STentry(int nestingLevel, Type type, int offset) {
		super();
		this.nestingLevel = nestingLevel;
		this.type = type;
		this.offset = offset;
	}
	
	public Type getType() {
		return type;
	}

	public int getOffset() {
		return offset;
	}

	public int getNestinglevel() {
		return nestingLevel;
	}

	public String toPrint(String s) {
		return s + "STentry: nestlev " + Integer.toString(nestingLevel) + "\n" + s + "STentry: type\n " + type.toPrint(s + "  ")
				+ s + "STentry: offset " + offset + "\n";
	}

}