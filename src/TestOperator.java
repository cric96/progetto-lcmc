import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import ast.core.Node;
import ast.exception.WrongTypeException;
import ast.type.BoolType;
import ast.type.IntType;

/**
 * classe usata per testare gli operatori
 */
public class TestOperator {

	@Test
	public void testMinus() throws Exception {
		final Node ast = TestUtils.ASTFromString("10-(3);");
		assertEquals(ast.typeCheck(),IntType.instance());
		assertEquals(7, TestUtils.executeASMCode(ast.codeGeneration()));
		
		assertEquals(2, TestUtils.parseAndExecute("-1-(-3);"));
		
		final Node wrongAst = TestUtils.ASTFromString("-1-(null);");
		assertThrows(WrongTypeException.class, () -> wrongAst.typeCheck());
		
		assertEquals(-2, TestUtils.parseAndExecute("-1+1-(2);"));
		
		assertEquals(8,  TestUtils.parseAndExecute("1*3*4-(2+1*2);"));
	}
	
	@Test
	public void testAnd() throws Exception {
		Node ast = TestUtils.ASTFromString("true && false;");
		assertEquals(ast.typeCheck(), BoolType.instance());
		assertEquals(0, TestUtils.executeASMCode(ast.codeGeneration()));
		
		assertIterableEquals(
				Arrays.asList(1, 0, 0, 0), 
				TestUtils.parseAndExecuteAll(
						"true && true;",
						"false && true;",
						"true && false;",
						"false && false;"
						));

		final Node wrongAst = TestUtils.ASTFromString("true && 1;");
		assertThrows(WrongTypeException.class, () -> wrongAst.typeCheck());
	}
	
	@Test
	public void testOr() throws Exception {
		Node ast = TestUtils.ASTFromString("true || false;");
		assertEquals(ast.typeCheck(), BoolType.instance());
		assertEquals(1, TestUtils.executeASMCode(ast.codeGeneration()));
		
		assertIterableEquals(
				Arrays.asList(1, 1, 1, 0), 
				TestUtils.parseAndExecuteAll(
						"true || true;",
						"false || true;",
						"true || false;",
						"false || false;"
						));
		final Node wrongAst = TestUtils.ASTFromString("true || 1;");
		assertThrows(WrongTypeException.class, () -> wrongAst.typeCheck());
	}
	
	@Test
	public void testNot() throws Exception {
		Node ast = TestUtils.ASTFromString("!(true);");
		assertEquals(ast.typeCheck(), BoolType.instance());
		assertEquals(0, TestUtils.executeASMCode(ast.codeGeneration()));
		
		assertEquals(1, TestUtils.parseAndExecute("!(false);"));
		
		final String code = new TestUtils.CodeBuilder()
				.put("let")
				.put("var id : bool = true;")
				.put("in")
				.put("!(id);").build();
		ast = TestUtils.ASTFromString(code);
		assertEquals(ast.typeCheck(), BoolType.instance());
		assertEquals(0, TestUtils.executeASMCode(ast.codeGeneration()));
		
		assertThrows(WrongTypeException.class, () -> TestUtils.ASTFromString("!(1);").typeCheck());
	}
	
	@Test
	public void testGreaterEqual() throws Exception {
		final Node ast = TestUtils.ASTFromString("1 >= 2;");
		assertEquals(ast.typeCheck(), BoolType.instance());
		assertEquals(0, TestUtils.executeASMCode(ast.codeGeneration()));
		
		assertIterableEquals(
				Arrays.asList(1, 1, 0, 1), 
				TestUtils.parseAndExecuteAll(
						"1 >= 1;",
						"1 >= 0;",
						"1 >= 3;",
						"true >= false;"
						));

		assertThrows(WrongTypeException.class, () -> TestUtils.ASTFromString("1 >= null;").typeCheck());
	}
	
	@Test
	public void testLessEqual() throws Exception {

		final Node ast = TestUtils.ASTFromString("1 <= 2;");
		assertEquals(ast.typeCheck(), BoolType.instance());
		assertEquals(1, TestUtils.executeASMCode(ast.codeGeneration()));
		
		assertIterableEquals(
				Arrays.asList(1, 0, 1,0), 
				TestUtils.parseAndExecuteAll(
						"1 <= 1;",
						"1 <= 0;",
						"1 <= 3;",
						"true <= 0;"
						));

		assertThrows(WrongTypeException.class, () -> TestUtils.ASTFromString("1 <= null;").typeCheck());
	}
	
	@Test
	public void testDivision() throws Exception {
		final Node ast = TestUtils.ASTFromString("1 / 2;");
		assertEquals(ast.typeCheck(), IntType.instance());
		assertEquals(0, TestUtils.executeASMCode(ast.codeGeneration()));
		
		assertIterableEquals(
				Arrays.asList(2, 1, 3, 10), 
				TestUtils.parseAndExecuteAll(
						"6 / 3;",
						"10 / 8;",
						"15 / 5;",
						"10 / true;"
						));
		assertThrows(WrongTypeException.class, () -> TestUtils.ASTFromString("1 / null;").typeCheck());
	}
	
	@Test
	public void advancedTest() throws Exception {
		final Node ast = TestUtils.ASTFromString("if(1>=1) then { true } else { 10 };");
		assertEquals(IntType.instance(), ast.typeCheck());
		assertEquals(1, TestUtils.executeASMCode(ast.codeGeneration()));
		
		assertEquals(1, TestUtils.parseAndExecute("if((1>=1) && (true)) then { true } else { 10 };"));
		
		assertThrows(WrongTypeException.class, () -> TestUtils.ASTFromString("if(true) then { 1 } else { null };").typeCheck());
		
		final String letCode = new TestUtils.CodeBuilder()
				.put("let")
				.put("var a : int = 10;")
				.put("var b : bool = false;")
				.put("var c : int = 20;")
				.put("var d : bool = true;")
				.put("in")
				.build();
		
		assertIterableEquals(
				Arrays.asList(10, -10, 1, 0, 0, 1, 1), 
				TestUtils.parseAndExecuteAll(
						new TestUtils.CodeBuilder().put(letCode).put("a - b;").build(),
						new TestUtils.CodeBuilder().put(letCode).put("a - c;").build(),
						new TestUtils.CodeBuilder().put(letCode).put("a >= b;").build(),
						new TestUtils.CodeBuilder().put(letCode).put("a <= b;").build(),
						new TestUtils.CodeBuilder().put(letCode).put("b && d;").build(),
						new TestUtils.CodeBuilder().put(letCode).put("b || d;").build(),
						new TestUtils.CodeBuilder().put(letCode).put("!(b) && d;").build()
						));
				
	}
	
}
