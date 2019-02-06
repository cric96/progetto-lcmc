package ast.oo;

import java.util.List;
import java.util.stream.Collectors;

import ast.core.CallableNode;
import ast.core.Node;
import ast.core.STentry;

/**
 * descrive un nodo chiamata a metodo di una data classe
 */
public class ClassCallNode extends CallableNode {
	private final STentry objectEntry;

	/**
	 * 
	 * @param id
	 *            l'id del metodo
	 * @param entry
	 *            l'entry della associata alla dichiarazione della classe
	 * @param methodEntry
	 *            l'entry del metodo della dichiarazione al metodo
	 * @param methodParameters
	 *            i parametri passati alla chiamata a metodo
	 */
	public ClassCallNode(final String id, final STentry entry, final STentry methodEntry,
			final List<Node> methodParameters, final int nestingLevel) {
		super(id, methodEntry, methodParameters, nestingLevel);
		this.objectEntry = entry;
	}

	@Override
	public String toPrint(String indent) {
		final String parlstr = this.parlist.stream().map(x -> x.toPrint(" " + indent)).collect(Collectors.joining());
		return indent + "ClassCall:" + id + objectEntry.toPrint(indent + " ") + super.entry.toPrint(indent + "  ")
				+ parlstr;
	}

	@Override
	public String codeGeneration() {
		String getAR = "";
		for (int i = 0; i < nestingLevel - objectEntry.getNestinglevel(); i++) {
			getAR += "lw\n";
		}
		final String getAL = "push " + objectEntry.getOffset() + "\n" + "lfp\n" + getAR + // risalgo la catena statica
																							// per ottenere
		// l'indirizzo dell'AR
		// in cui è dichiarata la variabile
				"add\n" + "lw\n"; // qui abbiamo l'object pointer dell'id1 (da id1.id2())
		// creiamo la lista dei parametri della funzione chiamata, per farlo andiamo a
		// valutare le espressioni scritte
		// nella chiamata a funzione esempio : f(5*3+2,g(2,3)) sullo stack avrò (in
		// ordine) il valore di g(2,3) e 5*2+2
		String parCode = "";
		for (int i = parlist.size() - 1; i >= 0; i--) {
			parCode += parlist.get(i).codeGeneration();
		}
		return "lfp\n" + // Control Link
				parCode // allocazione valori parametri
				+ getAL 
				+ getAL + "lw\n" + "push " + super.entry.getOffset() + "\n"   // super.entry sarebbe la
																					// methodEntry che mi tiene l'offset
																					// del metodo nella dispatch table
				+ "add\n" + "lw\n" + "js\n";
	}

}
