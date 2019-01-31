package ast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ast.core.Node;
import ast.core.Type;
import ast.exception.WrongTypeException;
import ast.util.FunctionDeclarations;
import ast.util.LabelGenerator;
import ast.util.LabelGenerator.GenerationSeed;

public class FunNode implements Node {
	//TODO GUARDA PAR LIST E DECLARATION LIST, SONO TIPO??
	private final String id;
	private final Type type;
	private List<Node> parlist = new ArrayList<Node>(); // campo "parlist" che è lista di Node
	private List<Node> declist = new ArrayList<Node>();
	private Node exp;

	public FunNode(final String i, final Type  t) {
		id = i;
		type = t;
	}

	public void addDec(List<Node> d) {
		declist = d;
	}

	public void addBody(Node b) {
		exp = b;
	}

	public void addPar(Node p) { // metodo "addPar" che aggiunge un nodo a campo "parlist"
		parlist.add(p);
	}

	public String toPrint(String s) {
		final String parlstr = parlist.stream().map(x -> x.toPrint(s + " ")).collect(Collectors.joining());
		final String declstr = declist.stream().map(x -> x.toPrint(s + " ")).collect(Collectors.joining());
		return s + "Fun:" + id + "\n" + type.toPrint(s + "  ") + parlstr + declstr + exp.toPrint(s + "  ");
	}

	public Type typeCheck() throws WrongTypeException {
		for (final Node dec : declist) {
			dec.typeCheck();
		}
		if (!exp.typeCheck().isSubtype(type)) {
			throw new WrongTypeException("Incompatible value for variable",type,exp.typeCheck());
		}
		return null;
	}

	public String codeGeneration() {

		final String popDecl = declist.stream().map(x -> "pop\n").collect(Collectors.joining());
		final String popParl = parlist.stream().map(x -> "pop\n").collect(Collectors.joining());
		final String declCode = declist.stream().map(Node::codeGeneration).collect(Collectors.joining());
		
		String funl = LabelGenerator.generate(GenerationSeed.createFunctionSeed(id));

		FunctionDeclarations.putCode(funl + ":\n" + "cfp\n" + // setta $fp a $sp
				"lra\n" + // inserisce return address
				declCode + // inresisce dichiarazioni locali
				exp.codeGeneration() + "srv\n" + // pop del return value
				popDecl + // pop delle dichiarazioni
				"sra\n" + // pop del return address
				"pop\n" + // pop di AL
				popParl + // pop dei parametri
				"sfp\n" + // setto $fp al valore del CL
				"lrv\n" + // risultato della funzione sullo stack
				"lra\n" + "js\n" // salta a $ra
		);

		return "push " + funl + "\n";
	}

}