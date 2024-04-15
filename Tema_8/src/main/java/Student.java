import java.time.LocalDate;
import java.time.Period;

class Student {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String studentId;

    public Student(String firstName, String lastName, LocalDate dateOfBirth, String gender, String studentId) throws InvalidInputException {
        validateInput(firstName, lastName, dateOfBirth, gender, studentId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.studentId = studentId;
    }

    private void validateInput(String firstName, String lastName, LocalDate dateOfBirth, String gender, String studentId) throws InvalidInputException {
        if (firstName.isEmpty() || lastName.isEmpty()) {
            throw new InvalidInputException("First name and last name cannot be empty");
        }

        if (dateOfBirth.isBefore(LocalDate.of(1900, 1, 1)) || dateOfBirth.isAfter(LocalDate.now().minusYears(18))) {
            throw new InvalidInputException("Date of birth should be between 1900 and 18 years ago from today");
        }

        if (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female")) {
            throw new InvalidInputException("Gender should be 'male' or 'female'");
        }

        if (studentId.isEmpty()) {
            throw new InvalidInputException("Student ID cannot be empty");
        }
    }

    public int calculateAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", studentId='" + studentId + '\'' +
                '}';
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getStudentId() {
        return studentId;
    }
}