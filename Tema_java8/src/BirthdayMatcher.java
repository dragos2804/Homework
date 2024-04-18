import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class BirthdayMatcher {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: BirthdayMatcher <input filename> <target month (1-12)> <output filename>");
            return;
        }

        String inputFilename = args[0];
        int targetMonth = Integer.parseInt(args[1]);
        String outputFilename = args[2];

        try {
            List<Person> people = readFromFile(inputFilename);
            List<Person> filteredPeople = filterByMonth(people, targetMonth);
            writeToFile(filteredPeople, outputFilename);
            System.out.println("Filtered and sorted names written to " + outputFilename);
        } catch (IOException e) {
            System.err.println("Error reading or writing files: " + e.getMessage());
        }
    }

     static List<Person> readFromFile(String filename) throws IOException {
         List<Person> people = Files.lines(Paths.get(filename))
                 .map(line -> {
                     String[] parts = line.split(",");
                     String firstName = parts[0].trim();
                     String lastName = parts[1].trim();
                     LocalDate dob = LocalDate.parse(parts[2].trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                     return new Person(firstName, lastName, dob);
                 })
                 .collect(Collectors.toList());

         System.out.println("Citite din fisier: " + people);

         return people;
    }

     static List<Person> filterByMonth(List<Person> people, int targetMonth) {
         List<Person> filteredPeople = people.stream()
                 .filter(person -> person.getDob().getMonthValue() == targetMonth)
                 .sorted(Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName))
                 .collect(Collectors.toList());

         System.out.println("Filtrate si sortate: " + filteredPeople);

         return filteredPeople;
    }

    static void writeToFile(List<Person> people, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Person person : people) {
                writer.write(person.getFirstName() + ", " + person.getLastName());
                writer.newLine();
            }
        }
    }
    static class Person {
        private final String firstName;
        private final String lastName;
        private final LocalDate dob;

        public Person(String firstName, String lastName, LocalDate dob) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.dob = dob;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public LocalDate getDob() {
            return dob;
        }
    }
}