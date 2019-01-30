package ast.core;

import ast.exception.WrongTypeException;

public interface Node {
	
	/**
	 * stampa l'ast generato durante la fase di parsing
	 * @param indent usato per motivi di indentazione
	 * @return una stringa che rappresenta tutto il sotto albero dal nodo corrente
	 */
	String toPrint(String indent);
	
	//TODO DA RIVEDERE
	/**
	 fa il type checking e ritorna:
	 per una espressione, il suo tipo (oggetto BoolType, IntType, ...)
	 per una dichiarazione, "null" 
	**/
	Type typeCheck() throws WrongTypeException;
	
	/**
	 * visita l'albero con l'obiettivo di costruire il codice
	 * a partire dalla struttura dell'albero stessa 
	 * @return il codice generato 
	 */
	String codeGeneration();

}