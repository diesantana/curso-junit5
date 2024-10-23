import static org.mockito.ArgumentMatchers.anyInt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NewCalcTest {

	@Spy
	private Calculadora calc;
	
	@Test
	public void deveSomarComValoresValidos() {
		//Mockito.when(calc.soma(1, 1)).thenReturn(5);
		Mockito.when(calc.soma(anyInt(), anyInt()))
			.thenReturn(5)
			.thenCallRealMethod();
		//Mockito.when(calc.soma(anyInt(), 1)).thenReturn(5);
		//Mockito.doReturn(5).when(calc).soma(1, 1);
		//Mockito.doReturn(5).when(calc).soma(anyInt(), anyInt());
		System.out.println(calc.soma(1, 1));
		System.out.println(calc.soma(10, 2));
	}
	
}
