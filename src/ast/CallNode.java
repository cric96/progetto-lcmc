package ast;

import java.util.List;
import java.util.stream.Collectors;

import ast.core.CallableNode;
import ast.core.Node;
import ast.core.STentry;

/**
 * Rappresenta il nodo derivante da una chiamata a funzione
 */
public class CallNode extends CallableNode {
	/**
	 * 
	 * @param id l'id del nodo associato alla chiamata (f(a,b..) l'id � f)
	 * @param entry entry posizionata nella symbol table
	 * @param parlist l'insieme di parametri associata ad una chiamata a funzione
	 * @param nestingLevel il nesting level dov'� usato il call node
	 */
	public CallNode(final String id, final STentry entry, final List<Node> parlist, final int nestingLevel) {
		super(id,entry, parlist,nestingLevel);
	}

	public String toPrint(final String s) {
		final String parlstr = parlist.stream().map(x -> x.toPrint(" " + s)).collect(Collectors.joining());
		return s + "Call:" + id + " at nestinglevel " + nestingLevel + "\n" + entry.toPrint(s + "  ") + parlstr;
	}

	public String codeGeneration() {
		// creiamo la lista dei parametri della funzione chiamata, per farlo andiamo a
		// valutare le espressioni scritte
		// nella chiamata a funzione esempio : f(5*3+2,g(2,3)) sullo stack avr� (in
		// ordine) il valore di g(2,3) e 5*2+2
		String parCode = "";
		for (int i = parlist.size() - 1; i >= 0; i--) {
			parCode += parlist.get(i).codeGeneration();
		}
		String getAR = "";
		// risalgo la catena statica con un numero di "salti" pari alla differenza di
		// nesting level
		for (int i = 0; i < nestingLevel - entry.getNestinglevel(); i++) {
			getAR += "lw\n"; // avr� alla fine l'indirizzo dell'al dove � dichiarata la funz che sto
								// chiamando
		}

		return "lfp\n" + // Control Link
				parCode + // allocazione valori parametri
				/*"lfp\n" + getAR + // risalgo la catena statica per ottenere l'indirizzo dell'AR
									// in cui � dichiarata la funzione (Access Link)
									// nell'indirizzo di fp � salvato il valore dell'access link del corrente AR
serviva prima dell'estensione higher order*/
				"push " + entry.getOffset() + "\n" + "lfp\n" + getAR + //risalgo la catena statica per ottenere l'access link
				"add\n" + "lw\n" +
				"push " + (entry.getOffset()-1) + "\n" + "lfp\n" + getAR + // risalgo la catena statica per recuperare l'indirizzo della funzione
				"add\n" + "lw\n" + // carica sullo stack l'indirizzo della funzione
				"js\n"; // effettua il salto
	}
}