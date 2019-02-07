package ast.core;

import java.util.ArrayList;
import java.util.List;
/**
 * classe statica usata per aggiungere le varie dispatch table associate ad ogni classe
 */
public class DispatchTable {

	private static List<List<String>> dispatchTables = new ArrayList<>();
	private DispatchTable() {}
	/**
	 * aggiungo l'i-esima dispatch table
	 * @param methods i metodi dichiarati (ed ereditati) dalla dalla i-esima classe
	 */
	public static void addDispatchTable(List<String> methods) {
		dispatchTables.add(methods);
	}
	public static List<String> getDispatchTable(final int offset) {
		final int offsetToIndex = (offset + 2)*-1;
		return dispatchTables.get(offsetToIndex);
	}
 }
