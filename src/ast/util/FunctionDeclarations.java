package ast.util;

public class FunctionDeclarations {
	private FunctionDeclarations() {}
	
	private static String functions = "";
	
	public static void add(final String code) {
		functions += "\n" + code;
	}
	
	public static String get() {
		return functions;
	}
}
