package ast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ast.core.DeclarationNode;
import ast.core.Node;
import ast.core.Type;
import ast.exception.WrongTypeException;
import ast.type.ArrowType;
import ast.util.FunctionDeclarations;
import ast.util.LabelGenerator;
import ast.util.LabelGenerator.GenerationSeed;

public class FunNode implements DeclarationNode {
	//TODO GUARDA PAR LIST E DECLARATION LIST, SONO TIPO??
	private final String id;
	private final Type returnType;
	private final Type functionType;
	private List<DeclarationNode> parlist = new ArrayList<>(); // campo "parlist" che è lista di Node
	private List<DeclarationNode> declist = new ArrayList<>();
	private Node exp;

	public FunNode(final String i, final Type  t) {
		id = i;
		returnType = t;
		this.functionType = new ArrowType(parlist.stream().map(DeclarationNode::getSymbolType).collect(Collectors.toList()),returnType);
	}

	public void addDec(List<DeclarationNode> d) {
		declist = new ArrayList<>(d);
	}

	public void addBody(Node b) {
		exp = b;
	}

	public void addPar(DeclarationNode p) { // metodo "addPar" che aggiunge un nodo a campo "parlist"
		parlist.add(p);
	}

	public String toPrint(String s) {
		final String parlstr = parlist.stream().map(x -> x.toPrint(s + " ")).collect(Collectors.joining());
		final String declstr = declist.stream().map(x -> x.toPrint(s + " ")).collect(Collectors.joining());
		return s + "Fun:" + id + "\n" + returnType.toPrint(s + "  ") + parlstr + declstr + exp.toPrint(s + "  ");
	}

	public Type typeCheck() throws WrongTypeException {
		for (final Node dec : declist) {
			dec.typeCheck();
		}
		if (!exp.typeCheck().isSubtype(returnType)) {
			throw new WrongTypeException("Incompatible value for variable",returnType,exp.typeCheck());
		}
		return null;
	}

	public String codeGeneration() {
		//insieme di istruzioni per pulire lo stack dalle dichiarazioni
		final String popDecl = declist.stream().map(this::getPopTimes).collect(Collectors.joining());
		//insieme di istruzione per pulire lo stack dai parametri
		final String popParl = parlist.stream().map(this::getPopTimes).collect(Collectors.joining());
		//insieme di istruzione per inserire nello stack, in caso di variabile il valore da loro assunto, in caso di funzioni
		//la label associata alla funzione stessa
		final String declCode = declist.stream().map(Node::codeGeneration).collect(Collectors.joining());
		String funl = LabelGenerator.generate(GenerationSeed.createFunctionSeed(id));
		FunctionDeclarations.putCode(funl + ":\n" + "cfp\n" + // setta $fp a $sp
				"lra\n" + // inserisce return address
				declCode + // inresisce dichiarazioni locali
				exp.codeGeneration() + "srv\n" + // pop del return value, exp.codeGeneration visto l'invarianza, 
												 // alla fine della sua esecuzione lascerà il cima allo stack il valore calcolato
												 // che in questo caso corrisponde al valore di ritorno, perciò subito dopo viene
												 // salvato nel registro return value (rv) perchè prima di ritornare al chiamante
												 // dobbiamo pulire lo stack 
				//da qui in poi andiamo a pulire lo stack per lasciarci solamente il risultato (invarianza)
				popDecl + // pop delle dichiarazioni
				"sra\n" + // pop del return address e lo salvo nel registro ra per potere tornare al chiamante alla fine (usando js)
				"pop\n" + // pop di AL
				popParl + // pop dei parametri
				"sfp\n" + // setto $fp al valore del CL visto che il frame pointer "punta" al control link corrente, e alla fine
						  // dell'esecuzione della funzione punterà al chiamante (che corrisponde al valore corrente del control link)
						  // a questo punto in cima allo stack ho l fp del chiamante
				"lrv\n" + // risultato della funzione sullo stack
				"lra\n" + "js\n" // salta a $ra
		);

		return "lfp\n" + //estensione higher order carico il valore dell'indirizzo della funzione oltre alla sua etichetta
				"push " + funl + "\n";
	}

	@Override
	public Type getSymbolType() {
		return this.functionType;
	}
	//metodo che dato un DeclarationNode, mi dice quanti pop devo fare a seconda se  è di tipo funzionale o meno
	private String getPopTimes(final DeclarationNode node) {
		return node.getSymbolType() instanceof ArrowType ? "pop\npop\n" : "pop\n";
	}
}