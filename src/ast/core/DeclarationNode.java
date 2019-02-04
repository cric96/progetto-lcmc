package ast.core;
/**
 *Interfaccia che caratterizza quei nodi di cui si ha necessità di conoscere il tipo
 */
public interface DeclarationNode extends Node {
	/**
	 * 
	 * @return il tipo del nodo
	 */
	Type getSymbolType();

}
