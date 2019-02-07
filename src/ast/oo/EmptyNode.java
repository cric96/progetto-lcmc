package ast.oo;

import ast.core.Node;
import ast.core.Type;
import ast.exception.WrongTypeException;
import ast.type.NullType;
/**
 * descrive un nodo di tipo null 
 */
public class EmptyNode implements Node {
	private static final Node EMPTY = new EmptyNode();
	
	public static Node instance() {
		return EMPTY;
	}
	
	private EmptyNode() {
	
	}
	@Override
	public String toPrint(String indent) {
		// TODO Auto-generated method stub
		return indent + "EmptyNode\n";
	}

	@Override
	public Type typeCheck() throws WrongTypeException {
		return NullType.instance();
	}

	@Override
	public String codeGeneration() {
		//null è associato al valore -1
		return "push -1\n";
	}

}
