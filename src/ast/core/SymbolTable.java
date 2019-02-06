package ast.core;

import java.util.Map;
import java.util.Optional;
/**
 * intefaccia generale per la descrizione di una symbol table
 */
public interface SymbolTable {
	/**
	 * aumenta il livello di nesting della symbol table
	 * aggiungo una nuova tabella (nell'estensione ad oggetti è la virtual table)
	 */
	void increaseNesting(Map<String, STentry> newTable);
	/**
	 * diminuisce il livello di nesting della symbol table
	 */
	void decreaseNesting();
	/**
	 * prova ad aggiungere un'entry nella symbol table
	 * @param id, l'id del simbolo
	 * @param entry, la entry da aggiungere
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
