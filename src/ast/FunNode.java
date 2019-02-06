package ast;

import java.util.stream.Collectors;

import ast.core.FunctionalNode;
import ast.core.Type;
/**
 * nodo che identifica la dichiarazione di una funzione.
 * verranno passati i parametri e il tipo di ritorno e la variabile 
 */
public class FunNode extends FunctionalNode {
	private Type functionType;

	public FunNode(final String id, final Type returnType) {
		super(id,returnType);	
	}

	public String toPrint(String s) {
		final String parlstr = parlist.stream().map(x -> x.toPrint(s + " ")).collect(Collectors.joining());
		final String declstr = declist.stream().map(x -> x.toPrint(s + " ")).collect(Collectors.joining());
		return s + "Fun:" + id + "\n" + returnType.toPrint(s + "  ") + parlstr + declstr + exp.toPrint(s + "  ");
	}

	@Override
	public String toString() {
		return "FunNode [id=" + id + ", returnType=" + returnType + ", functionType=" + functionType + ", parlist="
				+ parlist + ", declist=" + declist + ", exp=" + exp + "]";
	}

	@Override
	protected String getCodeStacked() {
		return "lfp\n" + //estensione higher order carico il valore dell'indirizzo della funzione oltre alla sua etichetta
				"push " + label + "\n";
	}
}