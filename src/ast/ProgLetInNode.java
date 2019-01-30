package ast;

import java.util.List;
import java.util.stream.Collectors;

import ast.core.Node;
import ast.core.Type;
import ast.exception.WrongTypeException;
import lib.FOOLlib;

public class ProgLetInNode implements Node {

	private final List<Node> declarationList;
	private final Node exp;

	public ProgLetInNode(List<Node> declarationList, final Node exp) {
		this.declarationList = declarationList;
		this.exp = exp;
	}

	public String toPrint(String s) {
		String declstr = "";
		for (Node dec : declarationList) {
			declstr += dec.toPrint(s + "  ");
		}
		return s + "ProgLetIn\n" + declstr + exp.toPrint(s + "  ");
	}

	public Type typeCheck() throws WrongTypeException {
		for (final Node dec : declarationList) {
			dec.typeCheck();
		}
		return exp.typeCheck();
	}

	public String codeGeneration() {
		String declCode = declarationList.stream().map(Node::codeGeneration).collect(Collectors.joining());
		return "push 0\n" + declCode + exp.codeGeneration() + "halt\n" + FOOLlib.getCode();
	}

}