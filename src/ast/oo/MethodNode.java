package ast.oo;

import java.util.stream.Collectors;

import ast.core.FunctionalNode;
import ast.core.Type;
/**
 * descrive un nodo associato ad un metodo di una classe
 */
public class MethodNode extends FunctionalNode {
	private final int offset;
	public MethodNode(final String id, final Type returnType, final int offset) {
		super(id, returnType);
		this.offset = offset;
	}

	@Override
	public String toPrint(String indent) {
		final String parlstr = parlist.stream().map(x -> x.toPrint(indent + " ")).collect(Collectors.joining());
		final String declstr = declist.stream().map(x -> x.toPrint(indent + " ")).collect(Collectors.joining());
		return indent + "Method:" + id + "\n" + returnType.toPrint(indent + "  ") + parlstr + declstr
				+ exp.toPrint(indent + "  ");
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public int getOffset() {
		return this.offset;
	}

	@Override
	protected String getCodeStacked() {
		return null;
	}
}
