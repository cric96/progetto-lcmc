package ast.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
/**
 * a standard implementation of symbol table
 */
public class StandardSymbolTable implements SymbolTable {
	private final List<Map<String,STentry>> symTable = new ArrayList<Map<String,STentry>>();
	private Map<String,STentry> current;
	private int nestingLevel;
	
	public StandardSymbolTable() {
		current = new HashMap<>();
		symTable.add(current);
		this.nestingLevel = 0;
	}
	@Override
	public void increaseNesting(final Map<String, STentry> newTable) {
		current = new HashMap<>(newTable);
		this.nestingLevel ++;
		symTable.add(current);
	}

	@Override
	public void decreaseNesting() {
		symTable.remove(this.nestingLevel --);
		current = symTable.get(nestingLevel);
	}

	@Override
	public boolean addEntry(final String id, final STentry entry) {
		return symTable.get(entry.getNestinglevel()).put(id ,entry) == null;
	}
	
	@Override
	public int getNesting() {
		return nestingLevel;
	}

	@Override
	public Optional<STentry> findEntryById(String id) {
		int start = nestingLevel;
		Optional<STentry> returnValue = Optional.empty();
        while (start >= 0) {
         	returnValue = Optional.ofNullable((symTable.get(start--)).get(id));
         	if(returnValue.isPresent()) {
         		return returnValue;
         	}
        }
		return returnValue;
	}
	
}
