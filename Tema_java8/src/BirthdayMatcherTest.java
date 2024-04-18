import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BirthdayMatcherTest {

    private final String testFilename = "test_input.txt";
    private final String outputFilename = "test_output.txt";

    @BeforeEach
    public void setUp() throws IOException {
        Files.write(Paths.get(testFilename), List.of(
                "Ana, Popescu, 01-01-1990",
                "Mihai, Ionescu, 02-02-1991",
                "Elena, Radu, 01-03-1992",
                "Adrian, Dumitru, 04-04-1993",
                "Cristina, Stoica, 05-05-1994"
        ));
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(testFilename));
        Files.deleteIfExists(Paths.get(outputFilename));
    }

    @Test
    public void testFilterByMonth() throws IOException {
        List<BirthdayMatcher.Person> people = BirthdayMatcher.readFromFile(testFilename);
        List<BirthdayMatcher.Person> filteredPeople = BirthdayMatcher.filterByMonth(people, 1);


        assertEquals(2, filteredPeople.size());
        assertEquals("Ana", filteredPeople.get(0).getFirstName());
        assertEquals("Popescu", filteredPeople.get(0).getLastName());
        assertEquals("Elena", filteredPeople.get(1).getFirstName());
        assertEquals("Radu", filteredPeople.get(1).getLastName());
    }

    @Test
    public void testWriteToFile() throws IOException {
        List<BirthdayMatcher.Person> people = List.of(
                new BirthdayMatcher.Person("Ana", "Popescu", LocalDate.of(1990, 1, 1)),
                new BirthdayMatcher.Person("Elena", "Radu", LocalDate.of(1992, 1, 3))
        );

        BirthdayMatcher.writeToFile(people, outputFilename);

        List<String> lines = Files.readAllLines(Paths.get(outputFilename));
        assertEquals(2, lines.size());
        assertEquals("Ana, Popescu", lines.get(0));
        assertEquals("Elena, Radu", lines.get(1));
    }
}