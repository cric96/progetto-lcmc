package ast.oo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ast.core.DeclarationNode;
import ast.core.Node;
import ast.core.Type;
import ast.exception.WrongTypeException;
/**
 * è un nodo che descrive la dichiarazione di una classe
 */
public class ClassNode implements DeclarationNode {
	private final String id;
	private final Type type;
	private final List<FieldNode> fields = new ArrayList<>();
	private final List<MethodNode> methods = new ArrayList<>();
	
	/**
	 * 
	 * @param type il tipo associato alla classe
	 * @param id il nome della classe
	 * @param fields l'insieme dei campi della classe (non ereditarietati)
	 * @param methods l'insieme dei metodi della classe (non ereditarietati)
	 */
	public ClassNode(final Type type, final String id, final List<FieldNode> fields, final List<MethodNode> methods) {
		this.id = id;
		this.fields.addAll(fields);
		this.methods.addAll(methods);
		this.type = type;
	}

	@Override
	public String toPrint(String indent) {
		final String fieldsString = fields.stream().map(x -> x.toPrint(indent + " ")).collect(Collectors.joining());
		final String methodString = methods.stream().map(x -> x.toPrint(indent + " ")).collect(Collectors.joining());
		return indent + "Class:" + id + "\n" + type.toPrint(indent + "  ") + fieldsString + methodString;
	}

	@Override
	public Type typeCheck() throws WrongTypeException {
		for (final Node met : methods) {
			met.typeCheck();
		}
		return null;
	}


	@Override
	public String codeGeneration() {
		final List<String> dispatchTable = new ArrayList<>();
		for(MethodNode method : methods) {
			method.codeGeneration();
			final String label = method.getLabel();
			final int offset = method.getOffset();
			dispatchTable.add(offset, label);
		}
		
		final String disp = dispatchTable.stream().map(this::generateLabelCode).collect(Collectors.joining());
		return "lhp\n"
				+ disp;
	}

	private String generateLabelCode(final String methodName) {
		return "push " + methodName + "\n"
				+"lhp\n"
				+"sw\n"
				+"lhp\n"
				+"push 1\n"
				+"add\n"
				+"shp\n";
	}
	@Override
	public Type getSymbolType() {
		return this.type;
	}

}
