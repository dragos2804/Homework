import java.util.List;
import java.util.ArrayList;

public class SkiBiathlonStandings {
    public static List<BiathlonAthlete> determineStandings(List<BiathlonAthlete> athletes) {
        athletes.sort(new AthleteComparator());
        return athletes;
    }

    public static List<BiathlonAthlete> parseCSV(String csvData) {
        List<BiathlonAthlete> athletes = new ArrayList<>();
        String[] lines = csvData.split("\n");
        for (String line : lines) {
            String[] fields = line.split(",");
            try {
                athletes.add(new BiathlonAthlete(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3], fields[4],
                        fields[5], fields[6]));
            } catch (NumberFormatException e) {
                System.err.println("Error parsing athlete data: " + e.getMessage());
            }
        }
        return athletes;
    }
}
