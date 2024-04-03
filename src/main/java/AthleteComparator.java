import java.util.Comparator;

public class AthleteComparator implements Comparator<BiathlonAthlete> {
    @Override
    public int compare(BiathlonAthlete a1, BiathlonAthlete a2) {
        return Integer.compare(a1.calculateTotalTime(), a2.calculateTotalTime());
    }
}
