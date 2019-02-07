package ast.exception;
/**
 * eccezione lanciata quando vi è un override illegittimo (sovrascrivo un campo con un metodo o viceversa)
 */
public class IllegalOverridingException extends ParserException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Declaration expected;
	private final Declaration arrived;
	
	public IllegalOverridingException(final Declaration expected, final Declaration arrived) {
		this.expected = expected;
		this.arrived = arrived;
	}
	
	public String toString() {
		return "IllegalOverridingException " + expected + " but you tried to override " + arrived;
	}

}
