import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Main.class.getName());
        try {
            StudentRepository repository = new StudentRepository();

            Student student1 = new Student("John", "Doe", LocalDate.of(1995, 5, 15), "Male", "12345");
            Student student2 = new Student("Jane", "Smith", LocalDate.of(2000, 8, 20), "Female", "54321");

            repository.addStudent(student1);
            repository.addStudent(student2);

            List<Student> students = repository.listStudents("last_name");
            System.out.println("Students ordered by last name:");
            for (Student student : students) {
                System.out.println(student);
            }

            List<Student> ageFilteredStudents = repository.retrieveStudentsByAge(26);
            System.out.println("\nStudents with age 26:");
            for (Student student : ageFilteredStudents) {
                System.out.println(student);
            }

            repository.deleteStudent("12345");

            System.out.println("\nRemaining students after deletion:");
            students = repository.listStudents("last_name");
            for (Student student : students) {
                System.out.println(student);
            }
        } catch (InvalidInputException e) {
            logger.log(Level.SEVERE, "Invalid input: " + e.getMessage());
        }
    }
}