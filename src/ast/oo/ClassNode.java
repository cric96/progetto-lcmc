package ast.oo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ast.core.DeclarationNode;
import ast.core.DispatchTable;
import ast.core.Node;
import ast.core.STentry;
import ast.core.Type;
import ast.exception.WrongTypeException;
import ast.type.ClassType;
/**
 * è un nodo che descrive la dichiarazione di una classe
 */
public class ClassNode implements DeclarationNode {
	private final String id;
	private final ClassType type;
	private final STentry superEntry;
	private final List<FieldNode> fields = new ArrayList<>();
	private final List<MethodNode> methods = new ArrayList<>();
	
	/**
	 * 
	 * @param type il tipo associato alla classe
	 * @param id il nome della classe
	 * @param fields l'insieme dei campi della classe (non ereditarietati)
	 * @param methods l'insieme dei metodi della classe (non ereditarietati)
	 * @param superEntry, l'entry associata al super type della classe corrente 
	 */
	public ClassNode(final ClassType type, final String id, final List<FieldNode> fields, final List<MethodNode> methods, final STentry superEntry) {
		this.id = id;
		this.fields.addAll(fields);
		this.methods.addAll(methods);
		this.type = type;
		this.superEntry = superEntry;
	}

	@Override
	public String toPrint(String indent) {
		final String fieldsString = fields.stream().map(x -> x.toPrint(indent + " ")).collect(Collectors.joining());
		final String methodString = methods.stream().map(x -> x.toPrint(indent + " ")).collect(Collectors.joining());
		return indent + "Class:" + id + "\n" + type.toPrint(indent + "  ") + fieldsString + methodString + (superEntry == null ? "" : superEntry.toPrint(indent + " "));
	}

	@Override
	public Type typeCheck() throws WrongTypeException {
		//per prima cosa devo verificare il type checking di tutti i metodi (controllo il tipo di ritorno e le dichiarazioni)
		for (final Node met : methods) {
			met.typeCheck();
		}
		//ESTENSIONE EREDETARIETà: verifico che i tipi che ho overridato siano sottotipi dei tipi della classe padre
		if(superEntry != null) {
			final ClassType superType = (ClassType) superEntry.getType();
			//per tutti i campi overrided controllo che ci sia la relazione di sotto tipo
			for(int i : type.getOverridedFieldIndexes()) {
				if(!type.getFields().get(i).isSubtype(superType.getFields().get(i))) {
					throw new WrongTypeException("Wrong overriding of "+ i+" -th field ", superType.getFields().get(i), type.getFields().get(i));
				}
			}
			//per tutti i metodi overrided controllo che ci sia la relazione di sotto tipo
			for (int i : type.getOverridedMethodIndexes()) {
				if(!type.getMethods().get(i).isSubtype(superType.getMethods().get(i))) {
					throw new WrongTypeException("Wrong overriding of "+ i+" -th method ", superType.getMethods().get(i), type.getMethods().get(i));
				}
			}
		}
		
		return null;
	}


	@Override
	public String codeGeneration() {
		//prendo la dispatch teble della classe da cui eredito (se è presente[ESTENSIONE CON EREDITARIETà]) altrimenti inizializzo la lista al vuoto
		final List<String> dispatchTable = superEntry == null? new ArrayList<>(): DispatchTable.getDispatchTable(superEntry.getOffset());
		//per tutti i metodi mi vado a generare il codice 
		for(MethodNode method : methods) {
			method.codeGeneration();
			// mi salvo la label del metodo messa nel codice
			final String label = method.getLabel();
			// e l'offset relativo del metodo 
			final int offset = method.getOffset();
			//controllo se c'è stato overriding o no
			if(offset < dispatchTable.size()) {
				/*in questo caso c'è stato overriding, in quanto offset si trova ad un 
				 * indice inferiore rispesso alla corrente grandezza della dispatch table
				 * allora in questo caso sovrascrivo la label associata al metodo del padre
				 * mettendo la label del metodo overrided
				*/
				dispatchTable.set(offset, label);
			} else {
				/*
				 * altrimenti accodo semplicemente il metodo corrente alla dispatch table 
				 */
				dispatchTable.add(label);
			}
		}
		//mi calcolo tutto il codice da generare per ogi metodo 
		final String disp = dispatchTable.stream().map(this::generateLabelCode).collect(Collectors.joining());
		DispatchTable.addDispatchTable(dispatchTable);
		/*alla fine di questa generazione lo stato dell'heap e dello stack devono essere i seguenti
		 * STACK                         
		 * _________ MAX_MEMORY
		 * |   0   | MAX_MEMORY - 1
		 * |   .   | .
		 * |       | .
		 * |pointer| DISPATCH POINTER 
		 * |       | <- sp
		 * 
		 * HEAP
		 * |       | <- hp
		 * |  mn   | m + (n-1)
		 * |  .    | .
		 * |  m0   | m <- pointer (DISPATCH POINTER 
		 * |   .   | . 
		 * |   .   | 1
		 * _________ 0
		 * dove n sono il numero di metodi
		 * */ 
		return "lhp\n" //devo lasciare sullo stack il dispatch pointer associato alla classe, è semplicemente il corrente valore del heap pointer
				+ disp; // aggiungo le dichiarazioni di metodo dell'heap
	}
	/*
	 * usato per calcolare il codice da generare per un solo metodo
	 * le azione che devo fare sono:
	 * mettere sulla corrente posizione dell'heap l'etichetta del metodo
	 * ed aggiornare l'heap
	 */
	private String generateLabelCode(final String methodName) {
		return "push " + methodName + "\n" //carico il nome nel metodo sullo stack
				+"lhp\n" //carico l'heap pointer
				+"sw\n" //salvo il nome del metodo all'indirizzo puntato dall'heap pointer
				+"lhp\n" //carico di nuovo l'heap pointer per farlo incrementare di una posizione
				+"push 1\n"
				+"add\n" //sullo stack ora ho hp + 1
				+"shp\n" //salvo il nuovo valore
				;
	}
	@Override
	public Type getSymbolType() {
		return this.type;
	}

}
