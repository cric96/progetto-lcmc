package ast.exception;
/**
 * identifica tutte le possibili dichiarazioni, viene utilizzato nell'eccezioni
 */
public enum Declaration {
	Id("Id"),
	Class("Class"),
	Method("Method"),
	Var("Var"),
	Function("Function"),
	Parameter("Parameter"),
	Field("Field");
	
	private final String name;
	private Declaration(final String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}
}
