package ast;

import java.util.List;
import java.util.stream.Collectors;

import ast.core.Node;
import ast.core.STentry;
import ast.core.Type;
import ast.exception.WrongTypeException;
import ast.type.ArrowType;
import lib.FOOLlib;

public class CallNode implements Node {

	private final String id;
	private final int nestingLevel;
	private final STentry entry;
	private final List<Node> parlist;

	public CallNode(final String id, final STentry entry, final List<Node> parlist, final int nestingLevel) {
		this.id = id;
		this.nestingLevel = nestingLevel;
		this.entry = entry;
		this.parlist = parlist;
	}

	public String toPrint(final String s) {
		final String parlstr = parlist.stream().map(x -> x.toPrint(" " + s)).collect(Collectors.joining());
		return s + "Call:" + id + " at nestinglevel " + nestingLevel + "\n" + entry.toPrint(s + "  ") + parlstr;
	}

	public Type typeCheck() throws WrongTypeException {
		if (!(entry.getType() instanceof ArrowType)) {
			throw new WrongTypeException("Invocation of a non-function " + id);

		}
		final ArrowType t = (ArrowType) entry.getType();
		final List<Type> p = t.getParList();
		if (!(p.size() == parlist.size())) {
			throw new WrongTypeException("Wrong number of parameters in the invocation of " + id);
		}
		for (int i = 0; i < parlist.size(); i++)
			if (!(FOOLlib.isSubtype((parlist.get(i)).typeCheck(), p.get(i)))) {
				throw new WrongTypeException("Wrong type for " + (i + 1) + "-th parameter of " + id, parlist.get(i).typeCheck(), p.get(i));
			}
		return t.getReturnType();
	}

	public String codeGeneration() {
		String parCode = "";
		for (int i = parlist.size() - 1; i >= 0; i--) {
			parCode += parlist.get(i).codeGeneration();
		}
		String getAR = "";
		for (int i = 0; i < nestingLevel - entry.getNestinglevel(); i++)
			getAR += "lw\n";
		return "lfp\n" + // Contro Link
				parCode + // allocazione valori parametri
				"lfp\n" + getAR + // risalgo la catena statica per ottenere l'indirizzo dell'AR
									// in cui è dichiarata la funzione (Access Link)
				"push " + entry.getOffset() + "\n" + "lfp\n" + getAR + // risalgo la catena statica per ottenere
																		// l'indirizzo dell'AR
																		// in cui è dichiarata la funzione (Access Link)
				"add\n" + "lw\n" + // carica sullo stack l'indirizzo della funzione
				"js\n"; // effettua il salto
	}
}