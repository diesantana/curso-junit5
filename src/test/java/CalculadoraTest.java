import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {

	@Test
	public void testSomar() {
		Calculadora calc = new Calculadora();
		assertTrue(calc.soma(2, 3) == 5);
	}
	
	@Test
	public void deveLancarUmaExcecaoQuandoDividirPorZero_JUnit4() {
		try {
			float result = 10 / 0;
			fail("Deveria lançar a exceção");
		} catch (ArithmeticException e) {
			assertEquals("/ by zero", e.getMessage());
		}
	}
	
	@Test
	public void deveLancarUmaExcecaoQuandoDividirPorZero_JUnit5() {
		assertThrows(ArithmeticException.class, () -> {
			float result = 10 / 07;
		});
	}
}
