package ast;

import ast.core.Node;
import ast.core.Type;
import ast.exception.WrongTypeException;
import ast.type.BoolType;
import ast.type.IntType;
import ast.util.LabelGenerator;

public class OrNode implements Node {

	private final Node left;
	private final Node right; 
	
	public OrNode(final Node left, final Node right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public String toPrint(String indent) {
		return indent + "Or\n" + left.toPrint(indent + " ")
		+ right.toPrint(indent + " ");
	}

	public Type typeCheck() throws WrongTypeException {
		checkCorrectness(left);
		checkCorrectness(right);
		return BoolType.instance();
	}

	private void checkCorrectness(final Node node) throws WrongTypeException {
		if (!(node.typeCheck().isSubtype(IntType.instance()))) {
			throw new WrongTypeException("Non bool value in or", BoolType.instance(), node.typeCheck());
		}
	}
/*
 *per vedere se il risultato è true o false sommo il risultato di left e right (mi darà il
 *risultato sperato quindi o 0 o 1 tranne che se sono entrambi true : mi darà 2). 
 *per controllare questo caso controllo se l'add mi ha dato risultato 0, se l'ha fatto allora metto
 *sullo stack 0 se non lo ha fatto (era o 1 o 2) allora vuol dire che lo devo sostituire con 1
 */
	@Override
	public String codeGeneration() {	
		final String l1 = LabelGenerator.generate();
		final String l2 = LabelGenerator.generate();
		return left.codeGeneration() 
				+ right.codeGeneration() 
				+ "add\n"
				+ "push 0\n"
				+ "bleq " + l1 +"\n"
				+ "push 1\n"
				+ "b " + l2 + "\n"
				+ l1 + ": \n "
				+ "push 0\n"
				+ l2 + ": \n";
	}

}
