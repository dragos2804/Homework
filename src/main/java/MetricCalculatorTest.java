import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class MetricCalculatorTest {
    @Test
    public void testSingleUnit() {
        MetricCalculator calculator = new MetricCalculator();
        double result = calculator.calculateDistance("5 cm", "mm");
        assertEquals(50, result, 0.001);
    }

    @Test
    public void testAdditionSubtraction() {
        MetricCalculator calculator = new MetricCalculator();
        double result = calculator.calculateDistance("10 cm + 1 m - 10 mm", "mm");
        assertEquals(1090, result, 0.001);
    }

    @Test
    public void testAllOperations() {
        MetricCalculator calculator = new MetricCalculator();
        assertEquals(50, calculator.calculateDistance("5 cm", "mm"), 0.001);
        assertEquals(99500, calculator.calculateDistance("10 m - 5 cm", "mm"), 0.001);
        assertEquals(2505, calculator.calculateDistance("2 km + 500 m - 5 dm", "m"), 0.001);
        assertEquals(99985, calculator.calculateDistance("1 km - 1 m + 5 cm - 10 mm", "cm"), 0.001);
    }
}