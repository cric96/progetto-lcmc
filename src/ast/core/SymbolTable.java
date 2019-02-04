package ast.core;

import java.util.Optional;

public interface SymbolTable {
	/**
	 * aumenta il livello di nesting della symbol table
	 */
	void increaseNesting();
	/**
	 * diminuisce il livello di nesting della symbol table
	 */
	void decreaseNesting();
	/**
	 * prova ad aggiungere un'entry nella symbol table
	 * @param id, l'id dell'entry
	 * @param type, il tipo associato all'id
	 * @param offset, offset usato a runtime per riuscire a trovare il giusto valore nello stack
	 * @return true se non è presente già un id in questo livello, false altrimenti
	 */
	boolean addEntry(String id, Type type, int offset);
	/**
	 * prova ad aggiungere un'entry nella symbol table nel nestring level passato
	 * @param id l'id del node
	 * @param type, il tipo associato all'id
	 * @param offset, offset usato a runtime per riuscire a trovare il giusto valore nello stack
	 * @param nesting il nesting level 
	 * @return true se non è presente già un id in questo livello, false altrimenti
	 */
	boolean addEntryIn(String id, Type type, int offset, int nesting);
	/**
	 * @return il corrente livello di nesting
	 */
	int getNesting();
	/**
	 * cerca un entry con l'id passato
	 * @param id l'id del entry che voglio cercare
	 * @return Optional.empty se non c'è nessun entry con questo id Optional.of altrimenti.
	 */
	Optional<STentry> findEntryById(String id);
}
