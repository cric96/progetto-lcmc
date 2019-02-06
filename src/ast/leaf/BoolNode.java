package ast.leaf;

import ast.core.Node;
import ast.core.Type;
import ast.type.BoolType;
/**
 * identifica un nodo boolean, può avere il valore true o false.
 * ha come tipo BoolType
 */
public class BoolNode implements Node {
	private static final BoolNode TRUE_NODE = new BoolNode(true);
	private static final BoolNode FALSE_NODE = new BoolNode(false);
	private final boolean value;

	public static Node trueNode() {
		return TRUE_NODE;
	}
	
	public static Node falseNode() {
		return FALSE_NODE;
	}
	
	private BoolNode(final boolean value) {
		this.value = value;
	}

	public String toPrint(String s) {
		return s + (this.value ? "Bool:true\n" : "Bool:false\n");
	}

	public Type typeCheck() {
		return BoolType.instance();
	}

	public String codeGeneration() {
		return "push " + (value ? 1 : 0) + "\n";
	}

}