package ast.exception;

import java.util.Objects;

import ast.core.Type;

public class WrongTypeException extends Exception {
	private final Type expectedType;
	private final Type receivedType;
	private String prefix;
	
	public WrongTypeException(final String prefix) {
		Objects.requireNonNull(prefix);
		this.prefix = prefix;
		this.expectedType = null;
		this.receivedType = null;
	}
	public WrongTypeException(final Type expectedType, final Type receivedType) {
		super();
		Objects.requireNonNull(expectedType);
		Objects.requireNonNull(receivedType);
		this.expectedType = expectedType;
		this.receivedType = receivedType;
		this.prefix = "";
	}
	
	public WrongTypeException(final String prefix, final Type expectedType, final Type receivedType) {
		this(expectedType,receivedType);
		Objects.requireNonNull(prefix);
		this.prefix = prefix;
	}
	
	public String toString() {
		return "Wrong type Exception: " + prefix + expectedType != null ? ": type" + receivedType + " doesn't match with " + expectedType : ""; 
	}
	
}
