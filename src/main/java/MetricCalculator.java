
public class MetricCalculator {

    private static final int[] UNIT_CONVERSION = {1, 10, 100, 1000, 1000000};

    private double result;

    public double calculateDistance(String expression, String outputUnit) {
        String[] tokens = expression.split("\\s*[+\\-]\\s*");

        for (String token : tokens) {
            String[] parts = token.split("\\s+");
            int value = Integer.parseInt(parts[0]);
            String unit = parts[1];
            int unitConversion = UNIT_CONVERSION[getIndex(unit)];
            result += value * unitConversion * (token.contains("-") ? -1 : 1);
        }

        // Convert result to the specified output unit
        int outputConversion = UNIT_CONVERSION[getIndex(outputUnit)];
        result /= outputConversion;

        return result;
    }

    private int getIndex(String unit) {
        switch (unit) {
            case "mm":
                return 0;
            case "cm":
                return 1;
            case "dm":
                return 2;
            case "m":
                return 3;
            case "km":
                return 4;
            default:
                throw new IllegalArgumentException("Unsupported unit: " + unit);
        }
    }
}
