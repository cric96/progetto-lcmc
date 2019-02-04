package ast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ast.core.DeclarationNode;
import ast.core.Node;
import ast.core.Type;
import ast.exception.WrongTypeException;
import ast.util.FunctionDeclarations;

public class ProgLetInNode implements Node {

	private final List<DeclarationNode> declarationList;
	private final Node exp;

	public ProgLetInNode(List<DeclarationNode> declarationList, final Node exp) {
		this.declarationList = new ArrayList<>(declarationList);
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
		return "push 0\n" + declCode + exp.codeGeneration() + "halt\n" + FunctionDeclarations.getCode();
	}

}