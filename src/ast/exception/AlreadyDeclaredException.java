package ast.exception;
/**
 * eccezione lanciata in fase di parsing quando una variabile, parametro o id è stato già dichiarato
 */
public class AlreadyDeclaredException extends ParserException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Declaration declaration;
	private final String id;
	private final int line;
	
	public AlreadyDeclaredException(final Declaration declaration, final String id, final int line) {
		super();
		this.declaration = declaration;
		this.id = id;
		this.line = line;
	}
	
	public String toString() {
		return "Already declared Exception: " + declaration + " id " + id + " at line " + line+ " already declared";
	}
}
