package ast.leaf;

import ast.core.Node;
import ast.core.STentry;
import ast.core.Type;
import ast.exception.WrongTypeException;
import ast.type.ArrowType;
import ast.type.ClassType;
/**
 * un id può avere sia un tipo arrow sia un tipo standard (Int, Bool,..) ma NON può essere una classe (non posso usare un id della classe
 * come se fosse una variabile)
 */
public class IdNode implements Node {

	private final String id;
	private final int nestingLevel;
	private final STentry entry;

	public IdNode(final String id, final STentry st, final int nestringLevel) {
		this.id = id;
		nestingLevel = nestringLevel;
		entry = st;
	}

	public String toPrint(String s) {
		return s + "Id:" + id + " at nestinglevel " + nestingLevel + "\n" + entry.toPrint(s + "  ");
	}

	public Type typeCheck() throws WrongTypeException {
		/*
		 * estensione higher order non serve controllare che non sia di tipo funzionale
		 * if (entry.getType() instanceof ArrowType) {
			throw new WrongTypeException("Wrong usage of function identifier with " + id);}
		 */	
		//un id non può essere un riferimento
		if(entry.getType() instanceof ClassType ) {
			throw new WrongTypeException("Wrong usage of class identifier with " + id);
		}
		/*
		 * estensione ho + oo, un id non può essere un metodo!
		 */
		if(entry.isMethod()) {
			throw new WrongTypeException("Wrong usage of method identifier with " + id);
		}
		return entry.getType();
	}

	public String codeGeneration() {
		String getAR = "";
		for (int i = 0; i < nestingLevel - entry.getNestinglevel(); i++) {
			getAR += "lw\n";
		}
		final String functionAddress = entry.getType() instanceof ArrowType ?
				"push " + (entry.getOffset()-1) + "\n" + "lfp\n" + getAR + "add\n" + "lw\n"
				: "";
		return "push " + entry.getOffset() + "\n" + "lfp\n" + getAR + // risalgo la catena statica per ottenere
																		// l'indirizzo dell'AR
																		// in cui è dichiarata la variabile
				"add\n" + "lw\n" + functionAddress;
	}

}