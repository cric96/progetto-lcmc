package ast;

import java.util.List;
import java.util.stream.Collectors;

import ast.core.Node;
import ast.core.STentry;
import ast.core.Type;
import ast.exception.WrongTypeException;
import ast.type.ArrowType;

/**
 * Rappresenta il nodo derivante da una chiamata a funzione
 */
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
		for (int i = 0; i < parlist.size(); i++) {
			if (!(parlist.get(i)).typeCheck().isSubtype((p.get(i)))) {
				throw new WrongTypeException("Wrong type for " + (i + 1) + "-th parameter of " + id,
						parlist.get(i).typeCheck(), p.get(i));
			}
		}
		return t.getReturnType();
	}

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
		for (int i = 0; i < nestingLevel - entry.getNestinglevel(); i++) {
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