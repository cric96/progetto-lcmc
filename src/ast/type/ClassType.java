package ast.type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ast.core.Type;

public class ClassType implements Type {

	private final String id;
	//"all" perché memorizza anche i campi e i metodi ereditati
	private final List<Type> allMethods = new ArrayList<>();
	private final List<Type> allFields = new ArrayList<>();
	
	public ClassType(final String id, final List<Type> allMethods, final List<Type> allFields) {
		this.id = id;
		this.allMethods.addAll(allMethods);
		this.allFields.addAll(allFields);
	}

	public List<Type> getFields() {
		return this.allFields;
	}
	public List<Type> getMethods() {
		return this.allMethods;
	}
	@Override
	public String toPrint(String indent) {
		String fieldStr = allFields.stream().map(x -> x.toPrint(" " + indent)).collect(Collectors.joining());
		String methodStr = allMethods.stream().map(x -> x.toPrint(" " + indent)).collect(Collectors.joining());
		return indent + "ClassType " + id + "\n" + fieldStr + methodStr;
	}

	public void addMethod(final Type method) {
		allMethods.add(method);
	}
	@Override
	public boolean isSubtype(Type type) {
		// TODO Auto-generated method stub
		return false;
	}

}
