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
	 * @param entry, aggiuge un entry nella tabella
	 * @return true se non è presente già un id in questo livello, false altrimenti
	 */
	boolean addEntry(String id, STentry entry);
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
