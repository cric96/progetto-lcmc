

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import ast.core.Node;

public class TestUtils {
	public static Node ASTFromString(final String code) {
		CharStream chars = CharStreams.fromString(code);
        FOOLLexer lexer = new FOOLLexer(chars);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FOOLParser parser = new FOOLParser(tokens);
        
        return parser.prog().ast; //generazione AST con Id associate a relative entry symbol table
	}
	
	public static class CodeBuilder {
		private final StringBuffer code = new StringBuffer();
		
		public CodeBuilder put(String line) {
			code.append(line).append("\n");
			return this;
		}
		public String build() {
			return code.toString();
		}
	}
}
