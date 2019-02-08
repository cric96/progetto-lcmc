import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import ast.core.Node;
import ast.core.Type;
import ast.type.ArrowType;
import ast.type.BoolType;
import ast.type.IntType;

public class TestHigherOrder {
	@Test
	public void testStandard() throws Exception {
		final String code = new TestUtils.CodeBuilder()
				.put("let")
				.put("fun ho : int (f : (int,int) -> bool) f(1,2);")
				.put("fun f: bool(x : int, y : int) x >= y;")
				.put("in")
				.put("ho(f);")
				.build();
		final Node ast = TestUtils.ASTFromString(code);
		assertEquals(IntType.instance(), ast.typeCheck());
		assertEquals(0, TestUtils.executeASMCode(ast.codeGeneration()));
		
		final Node checkFunctionType = TestUtils.ASTFromString(new TestUtils.CodeBuilder()
				.put("let")
				.put("fun g : int (x : bool, y : int) 1;")
				.put("in")
				.put("g;").build());
		
		final Type arrowType = checkFunctionType.typeCheck();
		assertTrue(arrowType instanceof ArrowType);
		final ArrowType blockType = (ArrowType)(arrowType);
		assertEquals(blockType.getParList().size(),2);
		assertIterableEquals(
				Arrays.asList(BoolType.instance(),IntType.instance(), IntType.instance()), 
				Arrays.asList(blockType.getParList().get(0), blockType.getParList().get(1),blockType.getReturnType()));		
	}
	@Test
	public void testSubtype() throws Exception {
		final String code = new TestUtils.CodeBuilder()
				.put("let")
				.put("fun ho : bool (x : int, y : int) true;")
				.put("var x : (bool, bool) -> int = ho;")
				.put("in")
				.put("1;")
				.build();
		
		assertEquals(IntType.instance(), TestUtils.ASTFromString(code).typeCheck());
	}
	@Test
	public void testInnerHigherOrder() throws Exception {
		final String code = new TestUtils.CodeBuilder()
				.put("let")
				.put("fun ho : bool (f : (int,int) -> bool) f(1,2);")
				.put("fun g : int (x : int)")
					.put("let")
					.put("fun inner : bool (z : int, k : int) z >= k;")
					.put("in")
					.put("ho(inner) + x;")
				.put("in")
				.put("g(10);")
				.build();
		assertEquals(IntType.instance(), TestUtils.ASTFromString(code).typeCheck());
		assertEquals(10, TestUtils.parseAndExecute(code));
		
		final String strangeCode = new TestUtils.CodeBuilder()
				.put("let")
				.put("fun ho : bool (f : (int,int) -> bool) f(1,true);")
				.put("fun dummy : int (x : int) x;")
				.put("fun g : int (x : int)")
					.put("let")
					.put("fun inner : bool (z : int, k : int)")
						.put("let")
						.put("fun veryDummy : bool (i : (int) -> int) i(10) >= 20;")
						.put("in")
						.put("veryDummy(dummy);")
					.put("in")
					.put("ho(inner) + x;")
			
				.put("in")
				.put("g(true);")
				.build();
		assertEquals(IntType.instance(), TestUtils.ASTFromString(strangeCode).typeCheck());
		assertEquals(1, TestUtils.parseAndExecute(strangeCode));
	}

}
