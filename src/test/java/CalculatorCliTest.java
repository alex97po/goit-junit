import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.StringReader;

import static org.mockito.Mockito.*;

public class CalculatorCliTest {

    private Calculator calculator;
    private CalculatorCli calculatorCli;

    @Before
    public void setUp() {
        calculator = Mockito.mock(Calculator.class);
        calculatorCli = new CalculatorCli(calculator);
    }

    @Test
    public void calculatorCli_shouldSkipEmptyLines() {
        calculatorCli.runInteractiveSession(new StringReader(";\n;   ;;;\t\n;"));
        verifyZeroInteractions(calculator);
    }

    @Test
    public void calculatorCli_shouldCalculateEachExpression() {
        calculatorCli.runInteractiveSession(new StringReader("1;2;3;"));

        verify(calculator).calculate("1");
        verify(calculator).calculate("2");
        verify(calculator).calculate("3");
        verifyNoMoreInteractions(calculator);
    }


    @Test
    public void calculatorCli_shouldReturnValue() {
        when(calculator.calculate("1")).thenReturn(1d);
        when(calculator.calculate("2")).thenReturn(2d);
        when(calculator.calculate("3")).thenReturn(3d);

        calculatorCli.runInteractiveSession(new StringReader("1;2;3;"));
        verify(calculator).calculate("1");
        verify(calculator).calculate("2");
        verify(calculator).calculate("3");
        verifyNoMoreInteractions(calculator);
    }

}