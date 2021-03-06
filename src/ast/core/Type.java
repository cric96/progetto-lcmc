package ast.core;

import java.util.Optional;

/**
 * concetto radice di tutti i possibili tipi
 */
public interface Type {
	/**
	 * serve per stampare le informazioni del tipo
	 * @param indent indentazione 
	 * @return restituisce una rappresentazione in stringa del tipo
	 */
	String toPrint(String indent);
	/**
	 * Controlla se l'oggetto su cui chiamo il metodo � sottotipo del tipo passato
	 * @param type, tipo di cui devo controllare l'esserne sottotipo
	 * @return true se l'oggetto su cui chiamo il metodo � sottotipo di quello passato come parametro, false altrimenti
	 */
	boolean isSubtype (Type type);
	
	/**
	 * Trova se c'� un antenato comune tra il tipo da cui lo sto chiamando
	 * e il tipo passato
	 * @param other
	 * @return il tipo antenato comune al interno di un Optional, Optional.empty altrimenti
	 */
	Optional<Type> lowestCommonAncestor(Type other);
}
