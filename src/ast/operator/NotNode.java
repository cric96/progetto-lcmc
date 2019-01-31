package ast.operator;

import ast.core.Node;
import ast.core.Type;
import ast.exception.WrongTypeException;
import ast.type.BoolType;
import ast.util.LabelGenerator;
import ast.util.LabelGenerator.GenerationSeed;

public class NotNode implements Node {

	private final Node exp;
	
	public NotNode(final Node exp) {
		this.exp = exp;
	}

	@Override
	public String toPrint(String indent) {
		return indent + "Not\n" + exp.toPrint(indent + " ");
	}

	@Override
	public Type typeCheck() throws WrongTypeException {
		if(!(exp.typeCheck().isSubtype(BoolType.instance()))) {
			throw new WrongTypeException("No cond to negate");
		}
		return BoolType.instance();
	}

	@Override
	public String codeGeneration() {
	final String l1 = LabelGenerator.generate(GenerationSeed.Standard.Not);
	final String l2 = LabelGenerator.generate(GenerationSeed.Standard.Not);
	return exp.codeGeneration()
			+ "push 0\n"
			+ "beq " + l1 + "\n"
			+ "push 0\n"
			+ "b " + l2 + "\n"
			+ l1 + ": \n" 
			+ "push 1\n"
			+ l2 + ": \n";
	}

}
