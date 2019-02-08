package ast.util;
/**
 * questa classe di utilità permette di aggiungere il corpo delle funzioni
 * in fondo al codice da noi generato
 */
public class FunctionDeclarations {
	private FunctionDeclarations() {}
	private static String funCode = "";
	/**
	 * accoda il codice di una funzione ai codici delle funzioni dichiarate
	 * @param c il codice della funzione da aggiungere
	 */
	public static void putCode(String c) {
		funCode += "\n" + c; // aggiunge una linea vuota di separazione prima di funzione
	}
	/**
	 * 
	 * @return il codice di tutte le funzioni dichiarate
	 */
	public static String getCode() {
		return funCode;
	}
	
	public static void clearCode() {
		funCode = "";
	}

}
