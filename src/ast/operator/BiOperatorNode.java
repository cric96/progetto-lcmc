package ast.operator;

import ast.core.Node;
import ast.core.Type;
import ast.exception.WrongTypeException;

/**
 * lo scheletro di un node che simula un operatore binario
 * serve per evitare ripetizioni negli altri operatori
 */
public abstract class BiOperatorNode implements Node {
	//il nodo di sinistra nell'operatore
	protected final Node left;
	//il nodo di destra nell'operatore
	protected final Node right;
	//il nodo dell'operatore
	private final String operatorName;
	//il tipo di ritorno dell'operatore
	private final Type returnType;
	//il tipo da verificare dei due operatori
	private final Type checkType;
	
	protected BiOperatorNode(Node left, Node right, String operatorName, Type returnType, Type checkType) {
		this.left = left;
		this.right = right;
		this.operatorName = operatorName;
		this.returnType = returnType;
		this.checkType = checkType;
	}

	@Override
	public String toPrint(String indent) {
		return indent + operatorName + "\n" + left.toPrint(indent + "  ") + right.toPrint(indent + "  ");
	}

	public Type typeCheck() throws WrongTypeException {
		checkCorrectness(left);
		checkCorrectness(right);
		return returnType;
	}

	private void checkCorrectness(final Node node) throws WrongTypeException {
		if (!(node.typeCheck().isSubtype(checkType))) {
			throw new WrongTypeException("Non " + checkType + " in " + operatorName, checkType, node.typeCheck());
		}
	}

}
