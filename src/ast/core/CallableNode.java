package ast.core;

import java.util.List;

import ast.exception.WrongTypeException;
import ast.type.ArrowType;
/**
 * classe astratta che descrive un generico "callable" node, cioè un nodo che può essere chiamato (ad esempio una funzione
 * oppue un metodo)
 */
public abstract class CallableNode implements Node {
	protected final String id;
	protected final STentry entry;
	protected final List<Node> parlist;
	protected final int nestingLevel;
	/**
	 * 
	 * @param id l'id del nodo chiamato (ad esempio in f(..) è f in o.m(..) è m)
	 * @param entry l'entry associato alla dichiarazione del nodo
	 * @param parlist i parametri passati al chiamato
	 */
	public CallableNode(final String id, 
			final STentry entry, 
			final List<Node> parlist,
			final int nestingLevel) {
		this.id = id;
		this.entry = entry;
		this.parlist = parlist;
		this.nestingLevel = nestingLevel;
	}
	public Type typeCheck() throws WrongTypeException {
		//verifico che il tipo dell'entry sia un arrow type, altrimenti vuol dire che sto provando a fare una chiamata di funzione su una variabile
		if (!(entry.getType() instanceof ArrowType)) {
			throw new WrongTypeException("Wrong invocation of: " + id);
		}
		final ArrowType t = (ArrowType) entry.getType();
		final List<Type> p = t.getParList();
		//da qua in poi verifico la correttezza dei parametri, per prima cosa devo vedere se il numero dei parametri è lo stesso
		if (!(p.size() == parlist.size())) {
			throw new WrongTypeException("Wrong number of parameters in the invocation of " + id);
		}
		//in seguito devo verificare la varianza dei parametri passati
		for (int i = 0; i < parlist.size(); i++) {
			if (!(parlist.get(i)).typeCheck().isSubtype((p.get(i)))) {
				throw new WrongTypeException("Wrong type for " + (i + 1) + "-th parameter of " + id,
						parlist.get(i).typeCheck(), p.get(i));
			}
		}
		return t.getReturnType();
	}

}
