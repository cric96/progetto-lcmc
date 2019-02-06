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
	private final boolean method;
	
	/**
	 * static factory, create a standard entry
	 * @param nestingLevel the entry nesting level
	 * @param type the entry type
	 * @param offset the entry offset
	 * @return the entry created
	 */
	public static STentry createStandard(int nestingLevel, Type type, int offset) {
		return new STentry(nestingLevel, type, offset, false);
	}
	/**
	 * static factory, create a method entry
	 * @param nestingLevel the entry nesting level
	 * @param type the entry type
	 * @param offset the entry offset
	 * @return the entry created
	 */
	public static STentry createMethod(int nestingLevel, Type type, int offset) {
		return new STentry(nestingLevel, type, offset, true);
	}
	
	private STentry(int nestingLevel, Type type, int offset, boolean method) {
		super();
		this.nestingLevel = nestingLevel;
		this.type = type;
		this.offset = offset;
		this.method = method;
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
	
	public boolean isMethod() {
		return this.method;
	}

	public String toPrint(String s) {
		return s + "STentry: nestlev " + Integer.toString(nestingLevel) + "\n" + s + "STentry: type\n " + type.toPrint(s + "  ")
				+ s + "STentry: offset " + offset + "\n";
	}

	@Override
	public String toString() {
		return "STentry [nestingLevel=" + nestingLevel + ", type=" + type + ", offset=" + offset + ", method=" + method
				+ "]";
	}
}