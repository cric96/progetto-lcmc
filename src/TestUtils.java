

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import ast.core.Node;

public class TestUtils {
	private TestUtils() {}
	public static Node ASTFromString(final String code) {
		CharStream chars = CharStreams.fromString(code);
        FOOLLexer lexer = new FOOLLexer(chars);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FOOLParser parser = new FOOLParser(tokens);
        
        return parser.prog().ast; //generazione AST con Id associate a relative entry symbol table
	}
	
	public static int parseAndExecute(final String code) {
		return executeASMCode(ASTFromString(code).codeGeneration());
	}
	
	public static List<Integer> parseAndExecuteAll(final String ... codes) {
		return Arrays.stream(codes).map(TestUtils::parseAndExecute).collect(Collectors.toList());
	}
	public static int executeASMCode(final String codeASM) {
		CharStream charsASM = CharStreams.fromString(codeASM);
        SVMLexer lexerASM = new SVMLexer(charsASM);
        CommonTokenStream tokensASM = new CommonTokenStream(lexerASM);
        SVMParser parserASM = new SVMParser(tokensASM);
 
        parserASM.assembly();
        
        ExecuteVM vm = new ExecuteVM(parserASM.code);
        vm.cpu();
        return vm.lastValue();
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
