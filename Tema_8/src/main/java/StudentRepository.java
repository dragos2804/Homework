import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

class StudentRepository {
    private List<Student> students = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(StudentRepository.class.getName());

    public void addStudent(Student student) {
        students.add(student);
    }

    public void deleteStudent(String studentId) throws InvalidInputException {
        if (studentId.isEmpty()) {
            throw new InvalidInputException("Student ID cannot be empty");
        }

        boolean removed = students.removeIf(student -> student.getStudentId().equals(studentId));
        if (!removed) {
            throw new InvalidInputException("Student with the given ID does not exist");
        }
    }

    public List<Student> retrieveStudentsByAge(int age) throws InvalidInputException {
        if (age < 0) {
            throw new InvalidInputException("Age cannot be negative");
        }

        List<Student> studentsByAge = new ArrayList<>();
        for (Student student : students) {
            if (student.calculateAge() == age) {
                studentsByAge.add(student);
            }
        }
        return studentsByAge;
    }

    public List<Student> listStudents(String orderBy) throws InvalidInputException {
        if (orderBy.isEmpty()) {
            throw new InvalidInputException("Order by parameter cannot be empty");
        }

        switch (orderBy.toLowerCase()) {
            case "last_name":
                students.sort(Comparator.comparing(Student::getLastName));
                break;
            case "birth_date":
                students.sort(Comparator.comparing(Student::getDateOfBirth));
                break;
            default:
                throw new InvalidInputException("Invalid order by");
        }

        return students;
    }
}