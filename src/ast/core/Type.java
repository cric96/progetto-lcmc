package ast.core;
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
	 * Controlla se l'oggetto su cui chiamo il metodo è sottotipo del tipo passato
	 * @param type, tipo di cui devo controllare l'esserne sottotipo
	 * @return true se l'oggetto su cui chiamo il metodo è sottotipo di quello passato come parametro, false altrimenti
	 */
	boolean isSubtype (Type type);
}
