import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ast.core.Node;
import ast.core.Type;
import ast.exception.IllegalOverridingException;
import ast.exception.NotDeclaredException;
import ast.exception.WrongTypeException;
import ast.type.BoolType;
import ast.type.IntType;
import ast.type.RefType;
import ast.util.FunctionDeclarations;

public class TestExtendsClass {
	private final String common = new TestUtils.CodeBuilder()
			.put("let")
			.put("class A () {}")
			.put("class B extends A () {}")
			.put("class C (a : A, x : int) {")
				.put("fun m : A (b : B, l : bool) new B();")
			.put("}").build();
	@Test
	public void standardExtension() throws Exception {  
		FunctionDeclarations.clearCode();
		final Node ast = TestUtils.ASTFromString(common + "in 1;");
		ast.typeCheck();
		
		final Node checkSubtype = TestUtils.ASTFromString(new TestUtils.CodeBuilder()
				.put(common)
				.put("var c : C = new C(new B(),false);")
				.put("in c.m(new B(),false);").build());
		
		final Type returnType = checkSubtype.typeCheck();
		assertTrue(returnType instanceof RefType);
		assertEquals(6, TestUtils.executeASMCode(checkSubtype.codeGeneration()));
		
		FunctionDeclarations.clearCode();
		final Node checkVarSubtype = TestUtils.ASTFromString(new TestUtils.CodeBuilder()
				.put(common)
				.put("var a : A  = new B();")
				.put("in a;").build());
		
		final RefType aType = (RefType) checkVarSubtype.typeCheck();
		assertEquals("A", aType.getId());
		assertEquals(1, TestUtils.executeASMCode(checkVarSubtype.codeGeneration()));
	}
	@Test
	public void testOverridingFieldAndMethod() throws Exception {
		FunctionDeclarations.clearCode();
		
		final String code = new TestUtils.CodeBuilder()
				.put(common)
				.put("class Father (a : A, b : int) {}")
				.put("class Son extends Father (a : B) {}")
				.put("class SonOfSon extends Son(b : bool){} ")
				.put("in true;").build();
		TestUtils.ASTFromString(code).typeCheck();
		final String methodCode = new TestUtils.CodeBuilder()
				.put(common)
				.put("class Father() {\nfun m : int (a : B) 10; \n}")
				.put("class Son extends Father() { \nfun m : bool (a : A) true;\n}")
				.put("var s : Son = new Son();")
				.put("var f : Father = new Son();")
				.put("in").build();
		
		final Type fType = TestUtils.ASTFromString(methodCode + "f.m(new B());").typeCheck();
		assertTrue(fType instanceof IntType);
		final Type sType = TestUtils.ASTFromString(methodCode + "s.m(new A());").typeCheck();
		assertTrue(sType instanceof BoolType);
		assertThrows(WrongTypeException.class, () -> TestUtils.ASTFromString(methodCode + "f.m(new A());").typeCheck());
		assertEquals(1, TestUtils.parseAndExecute(methodCode + "s.m(new A());"));
		FunctionDeclarations.clearCode();
		assertEquals(1, TestUtils.parseAndExecute(methodCode + "f.m(new A());"));
	}
	@Test
	public void testClassError() {
		FunctionDeclarations.clearCode();
		
		final String code = new TestUtils.CodeBuilder()
				.put(common)
				.put("class Father(a : B, b : int) {fun m : bool() true;}").build();
		assertThrows(NotDeclaredException.class, () -> TestUtils.ASTFromString(code + "in new D();").typeCheck());
		assertThrows(NotDeclaredException.class, () -> TestUtils.ASTFromString(code + "class E extends D() {} in true;").typeCheck());
		
		assertThrows(WrongTypeException.class, 
				() -> TestUtils.ASTFromString(code + "class E extends Father(a : A){} in new E(new A());").typeCheck());
		assertThrows(IllegalOverridingException.class, 
				() -> TestUtils.ASTFromString(code + "class E extends Father(){fun a : bool () true;} in new E();").typeCheck());
		assertThrows(IllegalOverridingException.class, 
				() -> TestUtils.ASTFromString(code + "class E extends Father(m : bool){} in new E(false);").typeCheck());
				
	}
}
