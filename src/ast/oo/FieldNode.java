package ast.oo;

import ast.core.DeclarationNode;
import ast.core.Type;
import ast.exception.WrongTypeException;
/**
 * descrive un attributo di una classe
 */
public class FieldNode implements DeclarationNode {
	private final String name;
	private final Type type;
	/**
	 * @param name il nome dell'attributo
	 * @param type il tipo dell'attributo
	 */
	public FieldNode(final String name, final Type type) {
		this.name = name;
		this.type = type;
	}
	
	@Override
	public String toPrint(String indent) {
		return indent + "Field:" + name + "\n" + type.toPrint(indent + "  ");
	}

	@Override
	public Type typeCheck() throws WrongTypeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String codeGeneration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type getSymbolType() {
		return this.type;
	}

}
