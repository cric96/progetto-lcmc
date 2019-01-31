package ast.operator;

import ast.core.Node;
import ast.core.Type;
import ast.exception.WrongTypeException;
import ast.type.BoolType;
import ast.util.LabelGenerator;
import ast.util.LabelGenerator.GenerationSeed;

public class EqualNode implements Node {

	private final Node left;
	private final Node right;

	public EqualNode(final Node left, final Node right) {
		this.left = left;
		this.right = right;
	}

	public String toPrint(String s) {
		return s + "Equal\n" + left.toPrint(s + "  ") + right.toPrint(s + "  ");
	}

	public Type typeCheck() throws WrongTypeException {
		final Type l = left.typeCheck();
		final Type r = right.typeCheck();
		if (!(l.isSubtype(r) || r.isSubtype(l))) {
			throw new WrongTypeException("Incompatible types in equal");
		}
		return BoolType.instance();
	}

	public String codeGeneration() {
		String l1 = LabelGenerator.generate(GenerationSeed.Standard.Equal);
		String l2 = LabelGenerator.generate(GenerationSeed.Standard.Equal);
		return left.codeGeneration() + right.codeGeneration() + "beq " + l1 + "\n" + "push 0\n" + "b " + l2 + "\n" + l1
				+ ": \n" + "push 1\n" + l2 + ": \n";
	}

}