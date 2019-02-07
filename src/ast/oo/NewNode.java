package ast.oo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ast.core.Node;
import ast.core.STentry;
import ast.core.Type;
import ast.exception.WrongTypeException;
import ast.type.ClassType;
import ast.type.RefType;
import ast.util.Utils;

/**
 * descrive un node usato per istanziare un nuovo oggetto
 */
public class NewNode implements Node {
	private final String id; 
	private final STentry entry;
	private final List<Node> fields = new ArrayList<>();
	private final RefType type;
	
	/**
	 * 
	 * @param id il nome della classe
	 * @param entry descrive dov'è dichiarata la classe
	 * @param parList la lista dei parametri passati al costruttore 
	 */
	public NewNode(final String id, final STentry entry, final List<Node> parList) {
		super();
		this.id = id;
		this.entry = entry;
		this.fields.addAll(parList);
		this.type = new RefType(id);
	}

	@Override
	public String toPrint(String indent) {
		final String parlstr = fields.stream().map(x -> x.toPrint(" " + indent)).collect(Collectors.joining());
		return indent + "New:" + id + "\n" + entry.toPrint(indent + "  ") + parlstr;
	}

	@Override
	public Type typeCheck() throws WrongTypeException {
		//controllo che l'id sia una classe, per farlo prendo il tipo dell'entry e controllo che sia un class type
		if (!(entry.getType() instanceof ClassType)) {
			throw new WrongTypeException("Invocation of a non class " + id);
		}
		final ClassType t = (ClassType) entry.getType();
		final List<Type> p = t.getFields();
		if (!(p.size() == fields.size())) {
			throw new WrongTypeException("Wrong number of parameters in the invocation of " + id);
		}
		//verifico la varianza dei parametri (i parametri devono essere sotto tipo dei parametri del costruttore)
		for (int i = 0; i < fields.size(); i++) {
			if (!(fields.get(i)).typeCheck().isSubtype((p.get(i)))) {
				throw new WrongTypeException("Wrong type for " + (i + 1) + "-th parameter of " + id,
						fields.get(i).typeCheck(), p.get(i));
			}
		}
		return type;
	}

	@Override
	public String codeGeneration() {
		/*
		 * 
		 * NOTA BENE! anche in questo caso i campi devono trovarsi a posizioni negative, il primo deve stare ad offset-1,.. ecc
		 * allora per farlo prima di inserisico i parametri nell'ordine con i quali l'incotro durante la chiamata a new
		 * così quando svuoto lo stack e li metto nell'heap saranno nell'ordine inverso (che è quello che voglio)
		 * 
		 * esempio grafico:
		 * 
		 * new id(1,2,3)
		 * 
		 * sullo stack:
		 * 
		 * 	 _____
		 * 	| 1  | -> svutando il contenuto nell'heap -->       <--- hp
		 *  | 2  |										  | 1  |
		 *  | 3  |  									  | 2  |
		 *  	  <--- sp								  | 3  | 
		 												  ______
		 */											      
		//mi genero il codice per valutare tutti i parametri passati 
		final String fieldCode = fields.stream().map(Node::codeGeneration).collect(Collectors.joining());
		//copio il risultato nello heap (il contenuto dell'oggetto deve stare nello heap, altrimenti quando si esce da un nesting level verebbe distrutto!
		final String putFieldsToHeap = fields.stream().map(x -> pushFieldToHeap()).collect(Collectors.joining());
		//store DP serve per salvare il dispatch pointer nella posizione object pointer (si trova a posizione MEMSITE + entry offset in quanto le classi sono nelle prime m posizioni
		final String storeDP = "push " + (Utils.MEM_SIZE+entry.getOffset()) + "\n" //carico l'indirizzo della classe
				+ "lw\n"  //vado a prendere il valore dell'indirizzo puntato dalla classe (dispatch pointer
				+ "lhp\n" //carico l'hp
				+ "sw\n"; //salvo il hp il valore del dispatch pointer (sarà l'indirizzo del object pointer
		return "\n"+fieldCode //carico i valori dei campi sullo stack
				+ putFieldsToHeap //metto i valori nello heap
				+ storeDP //carico il dispatch pointer nella posizione dell'object pointer
				+"lhp\n" //carico sullo stack l'object pointer 
				+"lhp\n" //da qua in poi aggiorno il valore del heap pointer
				+"push 1\n"
				+"add\n"
				+"shp\n";
		/*
		 * SITUAZIONE STACK HEAP ALLA FINE DELLA NEW
		 * (ad esempio var a = new A(a1,....az);
		 * STACK
		 * _______  MAX _ MEM
		 * |      | MAX _ MEM - 1
		 * |      | .
		 * |  dp A| MAX_MEM - m (dp sta per dispatch pointer)
		 * |      |
		 * |  op a| MAX_MEM - n (op sta per object pointer) (a tipo riferimento)
		 * |      |
		 * 
		 * |  dp A| m <- op (object pointer)
		 * |  a1  | m - 1
		 * |      | .
		 * |  az  | m - z
		 * |      | .
		 * |      | .
		 * |      | .
		 * |  ml  | k + l - 1
		 * |      | .
		 * |  m0  | k <- dp (dispatch pointer di A) 
		 * |      | .
		 * |      | 1
		 * |______| 0 
		 */
	}
	
	private String pushFieldToHeap() {
		//in cima allo stack ho il valore da caricare nello heap
		return "lhp\n"     //carico l'heap pointer
				+"sw\n"	   //salvo il valore in cima allo stack in posizione heap pointer
				+"lhp\n"   // da qui in poi incremento l'heap
				+"push 1\n"
				+"add\n"
				+"shp\n";
	}

}
