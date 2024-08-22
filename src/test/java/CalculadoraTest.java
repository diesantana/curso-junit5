import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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
	
	@ParameterizedTest
	@ValueSource(strings = {"Maça", "Pera", "Uva"})
	public void testStrings(String str) {
		assertNotNull(str);
	}

	@ParameterizedTest
	@CsvSource({
		"5, 2, 7",
		"4, 8, 12",
		"1, 3, 4"
	})
	public void testSomarComParams(int value1, int value2, int result) {
		Calculadora calc = new Calculadora();
		assertTrue(calc.soma(value1, value2) == result);
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/sumParams.csv")
	public void testSomarParamsComCsvFile(int value1, int value2, int result) {
		Calculadora calc = new Calculadora();
		assertTrue(calc.soma(value1, value2) == result);
	}

}
