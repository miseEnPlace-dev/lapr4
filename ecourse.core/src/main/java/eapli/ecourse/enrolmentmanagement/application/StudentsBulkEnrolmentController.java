package eapli.ecourse.enrolmentmanagement.application;

import java.io.IOException;
import java.util.List;

import eapli.ecourse.coursemanagement.application.ListCourseService;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.enrolmentmanagement.dto.EnrolmentDTO;
import eapli.ecourse.enrolmentmanagement.repositories.EnrolmentRepository;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

@UseCaseController
public class StudentsBulkEnrolmentController {
  private CourseRepository courseRepository;
  private EnrolmentRepository enrolmentRepository;
  private StudentRepository studentRepository;
  private final ListCourseService listCoursesService;
  private AuthorizationService authz;
  private BulkEnrolmentService bulkEnrolmentService;

  public StudentsBulkEnrolmentController(final CourseRepository courseRepository,
      final EnrolmentRepository enrolmentRepository, StudentRepository studentRepository, AuthorizationService authz) {
    this.courseRepository = courseRepository;
    this.enrolmentRepository = enrolmentRepository;
    this.studentRepository = studentRepository;
    this.authz = authz;
    this.listCoursesService = new ListCourseService(courseRepository);
    this.bulkEnrolmentService = new BulkEnrolmentService(studentRepository, courseRepository, enrolmentRepository);
  }

  public Iterable<CourseDTO> listOpenForEnrolmentCourses() {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.MANAGER, ClientRoles.POWER_USER);
    return listCoursesService.listOpenForEnrolment();
  }

  public void enrolStudents(final CourseDTO courseDTO, final String filePath) throws IOException {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.MANAGER, ClientRoles.POWER_USER);
    this.bulkEnrolmentService.enrolStudentsFromFile(filePath, courseDTO);
  }
}
