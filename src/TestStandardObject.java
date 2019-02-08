import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import ast.core.Node;
import ast.core.Type;
import ast.exception.NotDeclaredException;
import ast.exception.WrongTypeException;
import ast.type.BoolType;
import ast.type.IntType;
import ast.type.NullType;
import ast.type.RefType;
import ast.util.FunctionDeclarations;

public class TestStandardObject {
	private final String common = new TestUtils.CodeBuilder()
			.put("let")
			.put("class A (a : int, b: bool) {")
			.put("fun m1 : bool (x : int) b;")
			.put("fun m : int (x : int) m1(x) + a;")
			.put("}")
			.build();
	@Test
	public void testClassDeclaration() throws Exception {
		FunctionDeclarations.clearCode();
		final String commonClassDeclaration = new TestUtils.CodeBuilder()
				.put(common)
				.put("in")
				.build();
		final Node ast = TestUtils.ASTFromString(commonClassDeclaration + "new A(10,true);");
		final Type retType = ast.typeCheck();
		assertTrue(retType instanceof RefType);
		final RefType classType = (RefType) retType;
		assertEquals(classType.getId(), "A");
		
		assertThrows(WrongTypeException.class, () ->TestUtils.ASTFromString(commonClassDeclaration + "new A(10,1);").typeCheck());
		
		assertThrows(WrongTypeException.class, () ->TestUtils.ASTFromString(commonClassDeclaration + "new A(null,true);").typeCheck());
		
		assertThrows(WrongTypeException.class, () ->TestUtils.ASTFromString(commonClassDeclaration + "new A(10);").typeCheck());
		
		assertThrows(WrongTypeException.class, () ->TestUtils.ASTFromString(commonClassDeclaration + "new A(null,true,1);").typeCheck());
		
		assertThrows(NotDeclaredException.class, () ->TestUtils.ASTFromString(commonClassDeclaration + "new B(null,true);").typeCheck());
		
	}
	
	@Test
	public void testClassCallMethod() throws Exception {

		FunctionDeclarations.clearCode();
		final String commonStringMethod = new TestUtils.CodeBuilder()
				.put(common)
				.put("var a : A = new A(10,true);")
				.put("in").build();
		assertEquals(IntType.instance(), TestUtils.ASTFromString(commonStringMethod + "a.m(false);").typeCheck());
		assertEquals(11, TestUtils.parseAndExecute(commonStringMethod + "a.m(false);"));
		
		assertThrows(WrongTypeException.class, () ->TestUtils.ASTFromString(commonStringMethod + "a.m(null);").typeCheck());

		assertThrows(WrongTypeException.class, () ->TestUtils.ASTFromString(commonStringMethod + "a.m();").typeCheck());

		assertThrows(WrongTypeException.class, () ->TestUtils.ASTFromString(commonStringMethod + "a.m(1,2);").typeCheck());
		
		assertThrows(NotDeclaredException.class, () ->TestUtils.ASTFromString(commonStringMethod + "a.y(1,2);").typeCheck());
		
		assertThrows(NotDeclaredException.class, () ->TestUtils.ASTFromString(commonStringMethod + "m(1,2);").typeCheck());
	}
	
	@Test
	public void testNull() throws Exception {

		FunctionDeclarations.clearCode();
		assertEquals(NullType.instance(),TestUtils.ASTFromString("null;").typeCheck());
		assertEquals(-1,TestUtils.parseAndExecute("null;"));
		assertEquals(1,TestUtils.parseAndExecute("null == null;"));
		assertEquals(-1, TestUtils.parseAndExecute(common + new TestUtils.CodeBuilder()
				.put("var a : A = null;")
				.put("in")
				.put("a;").build()));
		assertEquals(0, TestUtils.parseAndExecute(common + new TestUtils.CodeBuilder()
				.put("var a : A = null;")
				.put("var b : A = new A(true,true);")
				.put("in")
				.put("a == b;").build()));
	}
	
	@Test
	public void testMethodCall() throws Exception {

		FunctionDeclarations.clearCode();
		final String code = new TestUtils.CodeBuilder()
				.put("let")
				.put("class N () {")
				.put("fun lemmy : bool () true;")
				.put("fun ti : bool (x : bool) x || lemmy();")
				.put("fun amo : bool (x : int)")
					.put("let")
					.put("var y : int = x + 10;")
					.put("in")
					.put("ti((x+y) >= 10);")
				.put("}")
				.put("var n : N = new N();")
				.put("in")
				.build();
		final Node ast = TestUtils.ASTFromString(code + "n.amo(10);");
		FunctionDeclarations.clearCode();
		assertEquals(BoolType.instance(),ast.typeCheck());
		assertEquals(1,TestUtils.executeASMCode(ast.codeGeneration()));
	}
	@Test
	public void testObjectParameter() throws Exception {
		FunctionDeclarations.clearCode();
		
		final String classToClass = new TestUtils.CodeBuilder()
				.put(common)
				.put("class B (a : A) {}")
				.put("var a : A = new A(true,true);")
				.put("var b : B = new B(a);")
				.put("in")
				.put("a;").build();
		TestUtils.parseAndExecute(classToClass);
		FunctionDeclarations.clearCode();
		
		final String classToMethod = new TestUtils.CodeBuilder()
				.put(common)
				.put("class B () {")
					.put("fun cicci : int (a : A) a.m(1);")
				.put("}")
				.put("var a : A = new A(true,false);")
				.put("var b : B = new B();")
				.put("in")
				.put("b.cicci(a);").build();
		
		assertEquals(IntType.instance(), TestUtils.ASTFromString(classToMethod).typeCheck());
		assertEquals(1,TestUtils.parseAndExecute(classToMethod));
		
		FunctionDeclarations.clearCode();
		
	}
	
	public void testObjectOperator() throws Exception {
		FunctionDeclarations.clearCode();
		final String standardCode = new TestUtils.CodeBuilder()
				.put("class D () {}")
				.put("var a : D = new D();")
				.put("var b : D = new D();")
				.put("var c : D = null;")
				.put("var d : D= a")
				.put("in").build();
		
		assertIterableEquals(
				Arrays.asList(1,0,0,1), 
				TestUtils.parseAndExecuteAll(
						standardCode + "a == a",
						standardCode + "a == b",
						standardCode + "a == c",
						standardCode + "a == d"));
		
		assertThrows(WrongTypeException.class, () -> TestUtils.parseAndExecute(standardCode + "a >= b"));
		assertThrows(WrongTypeException.class, () -> TestUtils.parseAndExecute(standardCode + "a <= b"));
		assertThrows(WrongTypeException.class, () -> TestUtils.parseAndExecute(standardCode + "a || b"));
		assertThrows(WrongTypeException.class, () -> TestUtils.parseAndExecute(standardCode + "a && b"));
		assertThrows(WrongTypeException.class, () -> TestUtils.parseAndExecute(standardCode + "!(b)"));
		assertThrows(WrongTypeException.class, () -> TestUtils.parseAndExecute(standardCode + "a+b"));
		assertThrows(WrongTypeException.class, () -> TestUtils.parseAndExecute(standardCode + "a-(b)"));
		assertThrows(WrongTypeException.class, () -> TestUtils.parseAndExecute(standardCode + "a * b"));
		assertThrows(WrongTypeException.class, () -> TestUtils.parseAndExecute(standardCode + "a / b"));
	}
}
