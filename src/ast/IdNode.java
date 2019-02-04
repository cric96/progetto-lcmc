package ast;

import ast.core.Node;
import ast.core.STentry;
import ast.core.Type;
import ast.exception.WrongTypeException;
import ast.type.ArrowType;

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