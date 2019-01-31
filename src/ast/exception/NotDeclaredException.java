package ast.exception;
/**
 * questa eccezione viene lanciata quando il parser trova un'uso e il suo id non è stato ancora dichiarato
 */
public class NotDeclaredException extends ParserException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String id;
	private final int line;
	
	public NotDeclaredException(String id, int line) {
		super();
		this.id = id;
		this.line = line;
	}
	
	public String toString() {
		return "Id "+id+" at line "+line+" not declared";
	}
}
