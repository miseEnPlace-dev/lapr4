package eapli.ecourse.enrolmentmanagement.application;

import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.util.ArrayList;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.enrolmentmanagement.domain.Enrolment;
import eapli.ecourse.enrolmentmanagement.dto.EnrolmentDTO;
import eapli.ecourse.enrolmentmanagement.repositories.EnrolmentRepository;
import eapli.ecourse.studentmanagement.application.StudentService;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.studentmanagement.dto.StudentDTO;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class BulkEnrolmentService {
  private final StudentRepository studentRepository;
  private final CourseRepository courseRepository;
  private final EnrolmentRepository enrolmentRepository;
  private final StudentService studentService;

  public BulkEnrolmentService(StudentRepository studentRepository, CourseRepository courseRepository,
      EnrolmentRepository enrolmentRepository) {
    this.studentRepository = studentRepository;
    this.courseRepository = courseRepository;
    this.enrolmentRepository = enrolmentRepository;
    this.studentService = new StudentService();
  }

  private Iterable<StudentDTO> getStudentsFromFile(final String filePath) throws IOException {
    List<String> usernames = CsvFileReader.extractLines(filePath);
    List<StudentDTO> students = new ArrayList<>();
    for (String username : usernames) {
      Student student = studentRepository.findByUsername(Username.valueOf(username)).orElseThrow();
      students.add(student.toDto());
    }
    return students;
  }

  public void enrolStudentsFromFile(final String filePath, final CourseDTO courseDTO)
      throws IOException {
    Iterable<StudentDTO> students = getStudentsFromFile(filePath);
    Course course = courseRepository.ofIdentity(courseDTO.getCode()).orElseThrow();
    for (StudentDTO studentDTO : students) {
      Student student = studentRepository.findByUsername(studentDTO.getUsername()).orElseThrow();
      Enrolment enrolment = new Enrolment(student, course);

      Optional<Enrolment> enrolled = enrolmentRepository.findWithUserAndCourse(student.identity(), course.code());
      if (enrolled.isPresent()) {
        enrolled.get().accept();
        enrolmentRepository.save(enrolled.get());
        continue;
      }

      enrolment.accept();
      enrolmentRepository.save(enrolment);
    }
  }
}
