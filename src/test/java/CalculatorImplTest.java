import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CalculatorImplTest {

    private CalculatorImpl calculator;

    @Before
    public void setUp() {
        calculator = new CalculatorImpl();
    }

    @Test
    public void calculate_shouldReturnZero() {
        double result = calculator.calculate("0");
        assertEquals(0, result, 1e-9);
    }

    @Test
    public void calculate_shouldReturnNegativeNumber() {
        double result = calculator.calculate("-1");
        assertEquals(-1, result, 1e-9);
        Assertions.assertThat(result).isNegative();
    }

    @Test
    public void calculate_shouldReturnSumOfTwoNumbers() {
        double result = calculator.calculate("2+2");
        assertEquals(4, result, 1e-9);
    }

    @Test
    public void calculate_shouldReturnResultOfComplexExpression() {
        double result = calculator.calculate("(2+2)*1.5/10-444");
        assertEquals(-443.4, result, 1e-9);
    }

    @Test
    public void calculate_shouldReturnResultOfFunctionExpression() {
        double result = calculator.calculate("sin(1)*sin(1) + cos(1)*cos(1)");
        assertEquals(1, result, 1e-9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculate_shouldThrowIllegalArgumentException_Expected() {
        calculator.calculate("invalid input");
    }

    @Test
    public void calculate_shouldThrowIllegalArgumentException_TryCatch() {
        try {
            calculator.calculate("invalid input");
            fail("Expected exception to be thrown");
        } catch (Exception exception) {
            assertTrue(exception instanceof IllegalArgumentException);
            assertEquals("Failed to calculate expression", exception.getMessage());
            Assertions.assertThat(exception.getMessage()).isNotBlank();
        }
    }

    //Ограничить время выполнения теста в милисекундах
    @Test(timeout = 1000L)
    @Ignore
    public void calculate_ShouldWorkFasterThanTimeout() throws InterruptedException {
        Thread.sleep(2000L);
        calculator.calculate("1+2");
    }

    @Test
    public void demonstrateReadableAssertions() {
        Map<String, String> testMap = new HashMap<>();
        Assertions.assertThat(testMap).isEmpty();
        Assertions.assertThat(testMap).hasSize(0);
        Assertions.assertThat(testMap).doesNotContainKeys("*");
        Assertions.assertThat(testMap.entrySet().iterator().hasNext()).isFalse();
    }
}