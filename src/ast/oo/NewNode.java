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
		if (!(entry.getType() instanceof ClassType)) {
			throw new WrongTypeException("Invocation of a non class " + id);
		}
		final ClassType t = (ClassType) entry.getType();
		final List<Type> p = t.getFields();
		if (!(p.size() == fields.size())) {
			throw new WrongTypeException("Wrong number of parameters in the invocation of " + id);
		}
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
		final String fieldCode = fields.stream().map(Node::codeGeneration).collect(Collectors.joining());
		final String putFieldsToHeap = fields.stream().map(x -> pushFieldToHeap()).collect(Collectors.joining());
		final String storeDP = "push " + (Utils.MEM_SIZE+entry.getOffset()) + "\n"
				+ "lw\n"
				+ "lhp\n"
				+ "sw\n";
		return "e:\n"+fieldCode
				+ putFieldsToHeap
				+ storeDP
				+"lhp\n"
				+"lhp\n"
				+"push 1\n"
				+"add\n"
				+"shp\n";
	}
	
	private String pushFieldToHeap() {
		return "lhp\n"
				+"sw\n"
				+"lhp\n"
				+"push 1\n"
				+"add\n"
				+"shp\n";
	}

}
