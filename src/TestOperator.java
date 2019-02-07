import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ast.core.Node;
import ast.exception.WrongTypeException;
import ast.type.IntType;

/**
 * classe usata per testare gli operatori
 */
public class TestOperator {
	@Test
	public void testMinus() throws Exception {
		Node ast = TestUtils.ASTFromString("10-(3);");
		assertEquals(ast.typeCheck(),IntType.instance());
	}
}
