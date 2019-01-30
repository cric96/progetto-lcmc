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
}
