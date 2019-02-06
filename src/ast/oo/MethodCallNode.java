package ast.oo;

import java.util.List;

import ast.CallNode;
import ast.core.Node;
import ast.core.STentry;
/**
 * chiamata a metodo all'interno della classe
 */
public class MethodCallNode extends CallNode {
	
	public MethodCallNode(String id, STentry entry, List<Node> parlist, int nestingLevel) {
		super(id, entry, parlist, nestingLevel);
	}

	@Override
	public String codeGeneration() {
		// creiamo la lista dei parametri della funzione chiamata, per farlo andiamo a
		// valutare le espressioni scritte
		// nella chiamata a funzione esempio : f(5*3+2,g(2,3)) sullo stack avrò (in
		// ordine) il valore di g(2,3) e 5*2+2
		String parCode = "";
		for (int i = parlist.size() - 1; i >= 0; i--) {
			parCode += parlist.get(i).codeGeneration();
		}
		String getAR = "";
		// risalgo la catena statica con un numero di "salti" pari alla differenza di
		// nesting level
		//+1 per arrivare alla dispatch table, serve per estensione ad oggetti
		for (int i = 0; i < (nestingLevel - entry.getNestinglevel())+1; i++) {
			getAR += "lw\n"; // avrò alla fine l'indirizzo dell'al dove è dichiarata la funz che sto
								// chiamando
		}

		return "lfp\n" + // Control Link
				parCode + // allocazione valori parametri
				/*"lfp\n" + getAR + // risalgo la catena statica per ottenere l'indirizzo dell'AR
									// in cui è dichiarata la funzione (Access Link)
									// nell'indirizzo di fp è salvato il valore dell'access link del corrente AR
serviva prima dell'estensione higher order*/
				"push " + entry.getOffset() + "\n" + "lfp\n" + getAR + //risalgo la catena statica per ottenere l'access link
				"add\n" + "lw\n" +
				"push " + (entry.getOffset()-1) + "\n" + "lfp\n" + getAR + // risalgo la catena statica per recuperare l'indirizzo della funzione
				"add\n" + "lw\n" + // carica sullo stack l'indirizzo della funzione
				"js\n"; // effettua il salto
	}
}
