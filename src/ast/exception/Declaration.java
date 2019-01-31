package ast.exception;

public enum Declaration {
	Var("Var"),
	Function("Function"),
	Parameter("Parameter");
	
	private final String name;
	private Declaration(final String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}
}
