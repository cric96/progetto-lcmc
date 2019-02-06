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
	private final Declaration dec;
	
	public NotDeclaredException(String id, int line) {
		this(Declaration.Id,id,line);
	}
	
	public NotDeclaredException(Declaration dec, String id, int line) {
		this.id = id;
		this.line = line;
		this.dec = dec;
	}
	
	public String toString() {
		return dec + ": " +id+" at line "+line+" not declared";
	}
}
