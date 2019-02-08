import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestHigherAndObject {
	@Test
	public void testOandHO() {
		final String code = new TestUtils.CodeBuilder()
				.put("let")
				.put("class Void() {}")
				.put("class A() { fun m : Void (f : (int,int) -> Void) f(10,11);}")
				.put("fun f : Void (x : int, y : int) if((x + y)>= 10) then { null } else {new Void()};")
				.put("var a : A = new A();")
				.put("in")
				.put("a.m(f);").build();
		assertEquals(-1, TestUtils.parseAndExecute(code));
	}
}
