import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SkiBiathlonStandingsTest {

    @Test
    void testDetermineStandings() {
        List<BiathlonAthlete> athletes = List.of(
                new BiathlonAthlete(11, "Umar Jorgson", "SK", "30:27", "xxxox", "xxxxx", "xxoxo"),
                new BiathlonAthlete(1, "Jimmy Smiles", "UK", "29:15", "xxoox", "xooxo", "xxxxo"),
                new BiathlonAthlete(27, "Piotr Smitzer", "CZ", "30:10", "xxxxx", "xxxxx", "xxxxx")
        );

        List<BiathlonAthlete> standings = SkiBiathlonStandings.determineStandings(athletes);

        assertEquals("Jimmy Smiles", standings.get(0).getAthleteName());
        assertEquals("Piotr Smitzer", standings.get(1).getAthleteName());
        assertEquals("Umar Jorgson", standings.get(2).getAthleteName());
    }

    @Test
    void testParseCSV() {
        String csvData = "11,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo\n" +
                "1,Jimmy Smiles,UK,29:15,xxoox,xooxo,xxxxo\n" +
                "27,Piotr Smitzer,CZ,30:10,xxxxx,xxxxx,xxxxx";

        List<BiathlonAthlete> athletes = SkiBiathlonStandings.parseCSV(csvData);

        assertEquals(3, athletes.size());

        assertEquals(11, athletes.get(0).getAthleteNumber());
        assertEquals("Umar Jorgson", athletes.get(0).getAthleteName());
        assertEquals("SK", athletes.get(0).getCountryCode());
        assertEquals("30:27", athletes.get(0).getSkiTimeResult());
        assertEquals("xxxox", athletes.get(0).getFirstShooting());
        assertEquals("xxxxx", athletes.get(0).getSecondShooting());
        assertEquals("xxoxo", athletes.get(0).getThirdShooting());
    }
}