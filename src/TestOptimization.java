import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import ast.core.Type;
import ast.exception.AlreadyDeclaredException;
import ast.exception.IllegalOverridingException;
import ast.exception.WrongTypeException;
import ast.type.ArrowType;
import ast.type.RefType;

public class TestOptimization {
	@Test
	public void testCannotOverringInTheSameLevel() {
		assertThrows(AlreadyDeclaredException.class,() -> TestUtils.parseAndExecute(
				new TestUtils.CodeBuilder()
				.put("let")
				.put("class A (a : int, a : bool)").build()));
		assertThrows(AlreadyDeclaredException.class,() -> TestUtils.parseAndExecute(
				new TestUtils.CodeBuilder()
				.put("let")
				.put("class A () {fun a : int () true; fun a : bool() false;}").build()));
		assertThrows(IllegalOverridingException.class,() -> TestUtils.parseAndExecute(
				new TestUtils.CodeBuilder()
				.put("let")
				.put("class A (a : int) {fun a : int () true; ").build()));
	}
	
	@Test
	public void testLowestCommonAncestor() throws Exception {
		final String code = new TestUtils.CodeBuilder()
				.put("let")
				.put("class A () {}")
				.put("class Void() {}")
				.put("class B extends A() {}")
				.put("class C extends B() {}")
				.put("class D extends A() {}")
				.put("fun m : C (a : B) new C();")
				.put("fun m1 : D (a : C) new D();")
				.put("in if(true) then {").build();
		final Type progType = TestUtils.ASTFromString(code + "new B() } else { new D()}; ").typeCheck();
		assertTrue(progType instanceof RefType);
		
		assertEquals("A", ((RefType)progType).getId());
		
		assertThrows(WrongTypeException.class, () -> TestUtils.ASTFromString(code + "new Void() } else { new D()}; ").typeCheck());
		
		final Type withNullType = TestUtils.ASTFromString(code + "new C() } else { null }; ").typeCheck();
		assertTrue(withNullType instanceof RefType);
		
		assertEquals("C", ((RefType)withNullType).getId());
		final Type withOther = TestUtils.ASTFromString(code + "new C() } else { new B() }; ").typeCheck();
		assertTrue(withOther instanceof RefType);
		
		assertEquals("B", ((RefType)withOther).getId());
		
		assertThrows(WrongTypeException.class, () -> TestUtils.ASTFromString(code + "new Void() } else { m}; ").typeCheck());
		
		final Type withFunction = TestUtils.ASTFromString(code + "m } else { m1 }; ").typeCheck();
		assertTrue(withFunction instanceof ArrowType);
		
		final Type funRet = ((ArrowType)withFunction).getReturnType();
		final List<Type> parList = ((ArrowType)withFunction).getParList();
		
		assertTrue(funRet instanceof RefType);
		assertTrue(parList.stream().allMatch(x -> x instanceof RefType));
		
		final RefType refRet = (RefType)funRet;
		final List<RefType> parRef = parList.stream().map(x -> {return (RefType) x;}).collect(Collectors.toList());
		
		assertIterableEquals(Arrays.asList("A","C"),Arrays.asList(refRet.getId(), parRef.get(0).getId()));
	}
}
