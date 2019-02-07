package ast.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ast.exception.WrongTypeException;
import ast.type.ArrowType;
import ast.util.FunctionDeclarations;
import ast.util.LabelGenerator;
import ast.util.LabelGenerator.GenerationSeed;

/**
 * descrive un generico nodo con il quale è possibile effettuare delle chiamate ad esso
 * (ad esempio una funzione o un metodo)
 */
public abstract class FunctionalNode implements DeclarationNode {
	protected final String id;
	protected final Type returnType;
	protected final List<DeclarationNode> parlist = new ArrayList<>(); // campo "parlist" che è lista di Node
	protected final List<DeclarationNode> declist = new ArrayList<>();
	protected String label;
	protected Node exp;
	protected Type functionType;
	/**
	 * 
	 * @param id nome del nodo "funzionale"
	 * @param type il tipo di ritorno
	 */
	public FunctionalNode(final String id, final Type type) {
		this.id = id;
		this.returnType = type;		
	}
	/**
	 * aggiungo una dichiarazione associato al nodo corrente
	 * @param d il nodo dichiarazione da aggiungere
	 */
	public void addDec(DeclarationNode d) {
		declist.add(d);
	}
	/**
	 * aggiungo il body del nodo corrente
	 * @param b il node associato al body
	 */
	public void addBody(Node b) {
		exp = b;
	}
	/**
	 * aggiungo un parametro al nodo corrente
	 * @param p il tipo del parametro
	 */
	public void addPar(DeclarationNode p) { // metodo "addPar" che aggiunge un nodo a campo "parlist"
		parlist.add(p);
	}

	public Type typeCheck() throws WrongTypeException {
		//chiamato type check su tutti i nodi
		for (final Node dec : declist) {
			dec.typeCheck();
		}
		//verifico che il tipo di ritorno sia sovratipo del tipo valutato nel body
		if (!exp.typeCheck().isSubtype(returnType)) {
			throw new WrongTypeException("Incompatible value for variable",returnType,exp.typeCheck());
		}
		return null;
	}
	//metodo che dato un DeclarationNode, mi dice quanti pop devo fare a seconda se  è di tipo funzionale o meno
	protected String getPopTimes(final DeclarationNode node) {
		return node.getSymbolType() instanceof ArrowType ? "pop\npop\n" : "pop\n";
	}	
	
	@Override
	public Type getSymbolType() {
		//inizializzazione lazy
		this.functionType = this.functionType == null 
				? new ArrowType(parlist.stream().map(DeclarationNode::getSymbolType).collect(Collectors.toList()),returnType)
					: this.functionType;
		return this.functionType;
	}

	public String codeGeneration() {
		//insieme di istruzioni per pulire lo stack dalle dichiarazioni
		final String popDecl = declist.stream().map(this::getPopTimes).collect(Collectors.joining());
		//insieme di istruzione per pulire lo stack dai parametri
		final String popParl = parlist.stream().map(this::getPopTimes).collect(Collectors.joining());
		//insieme di istruzione per inserire nello stack, in caso di variabile il valore da loro assunto, in caso di funzioni
		//la label associata alla funzione stessa
		final String declCode = declist.stream().map(Node::codeGeneration).collect(Collectors.joining());
		label = LabelGenerator.generate(GenerationSeed.createFunctionSeed(id));
		FunctionDeclarations.putCode(label + ":\n" + "cfp\n" + // setta $fp a $sp
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

		return getCodeStacked();
	}
	
	protected abstract String getCodeStacked();
}
