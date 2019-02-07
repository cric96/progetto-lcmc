package ast.type;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * classe statica nella quale vengono memorizzate tutte le informazioni di
 * gerarchia di classi
 */
public class ClassHierarchy {
	private static final Map<String, String> hierarchy = new HashMap<>();

	private ClassHierarchy() {
	}

	/**
	 * si aggiungere una relazione padre figlio tra due nomi di classi
	 * 
	 * @param father
	 *            nome della classe padre
	 * @param son
	 *            nome della classe figlio
	 */
	public static void addRelation(final String father, final String son) {
		hierarchy.put(son, father);
	}
	/**
	 * verifico se una certa classe figlio è sottotipo di una certa classe padre
	 * @param father nome della classe padre
	 * @param son nome della classe figlio 
	 * @return
	 */
	public static boolean isSubtype(final String father, final String son) {
		if (son == null) {
			return false;
		}
		if (son.equals(father)) {
			return true;
		}
		if (hierarchy.get(son) == null) {
			return false;
		}
		if (hierarchy.get(son).equals(father)) {
			return true;
		}
		return isSubtype(father, hierarchy.get(son));
	}
	/**
	 * verifico se la classe passata ha un padre 
	 * @param son il nome della classe padre
	 * @return null se non esiste un padre, 
	 */
	public static Optional<RefType> getFather(final String son) {
		return hierarchy.get(son) == null ? Optional.empty() : Optional.of(new RefType(hierarchy.get(son)));
	}

}
