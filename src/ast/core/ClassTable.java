package ast.core;

import java.util.HashMap;
import java.util.Map;
/**
 * contiene le dichiarazioni di classi, per ogni id viene associato 
 * la sua virtual table
 */
public class ClassTable {
	private final Map<String, Map<String, STentry>> classTable = new HashMap<>();
	/**
	 * aggiunge un alla class table
	 * @param id il nome della classe
	 * @param virtualTable la virtual table associata alla classe
	 * @return
	 */
	public boolean addClass(final String id, Map<String, STentry> virtualTable) {
		return classTable.put(id, virtualTable) == null;
	}
	/**
	 * restituisce la virtual table associata alla class table
	 * @param id il nome della classe
	 * @return la virtual table associata alla classe 
	 */
	public Map<String, STentry> getVirtualTable(final String id) {
		final Map<String, STentry> virtualTable = classTable.get(id) == null ? new HashMap<>() : classTable.get(id);
		return virtualTable;
	}
	/**
	 * verifica se l'id di una classe è presente della class table
	 * @param id il nome della classe
	 * @return true se il nome è presente nella classe table false altrimenti
	 */
	public boolean isClassPresent(final String id) {
		return classTable.containsKey(id);
	}

}
