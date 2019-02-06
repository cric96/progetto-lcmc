package ast;

import ast.core.Node;
import ast.core.Type;
import ast.exception.WrongTypeException;
import ast.type.BoolType;
import ast.util.LabelGenerator;
import ast.util.LabelGenerator.GenerationSeed;
/**
 * identifica un nodo if
 */
public class IfNode implements Node {

	private final Node cond;
	private final Node th;
	private final Node el;

	public IfNode(final Node c, final Node t, final Node e) {
		cond = c;
		th = t;
		el = e;
	}

	public String toPrint(String s) {
		return s + "If\n" + cond.toPrint(s + "  ") + th.toPrint(s + "  ") + el.toPrint(s + "  ");
	}

	public Type typeCheck() throws WrongTypeException {
		if (!(cond.typeCheck().isSubtype(BoolType.instance()))) {
			throw new WrongTypeException("non boolean condition in if");
		}
		final Type then = th.typeCheck();
		final Type e = el.typeCheck();
		if (then.isSubtype(e))
			return e;
		if (e.isSubtype(then))
			return then;
		throw new WrongTypeException("Incompatible types in then-else branches");
	}

	public String codeGeneration() {
		String l1 = LabelGenerator.generate(GenerationSeed.Standard.IfTrue);
		String l2 = LabelGenerator.generate(GenerationSeed.Standard.IfFalse);
		return cond.codeGeneration() + "push 1\n" + "beq " + l1 + "\n" + el.codeGeneration() + "b " + l2 + "\n" + l1
				+ ": \n" + th.codeGeneration() + l2 + ": \n";
	}

}