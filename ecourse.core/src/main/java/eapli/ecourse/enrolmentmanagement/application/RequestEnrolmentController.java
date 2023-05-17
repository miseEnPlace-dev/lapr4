package eapli.ecourse.enrolmentmanagement.application;

import eapli.ecourse.coursemanagement.application.ListCourseService;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.enrolmentmanagement.domain.Enrolment;
import eapli.ecourse.enrolmentmanagement.dto.EnrolmentDTO;
import eapli.ecourse.enrolmentmanagement.repositories.EnrolmentRepository;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

@UseCaseController
public class RequestEnrolmentController {
  private CourseRepository courseRepository;
  private EnrolmentRepository enrolmentRepository;
  private StudentRepository studentRepository;
  private final ListCourseService listCoursesService;
  private AuthorizationService authz;

  public RequestEnrolmentController(final CourseRepository courseRepository,
      final EnrolmentRepository enrolmentRepository, StudentRepository studentRepository, AuthorizationService authz) {
    this.courseRepository = courseRepository;
    this.enrolmentRepository = enrolmentRepository;
    this.studentRepository = studentRepository;
    this.authz = authz;
    this.listCoursesService = new ListCourseService(courseRepository);
  }

  public Iterable<CourseDTO> listOpenForEnrolmentCourses() {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.STUDENT, ClientRoles.POWER_USER);
    return listCoursesService.listOpenForEnrolment();
  }

  public EnrolmentDTO requestEnrolment(final CourseDTO courseDTO) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.STUDENT, ClientRoles.POWER_USER);
    SystemUser authenticatedUser = authz.loggedinUserWithPermissions(ClientRoles.STUDENT).orElseThrow();

    Student student = studentRepository.findByUsername(authenticatedUser.username()).orElseThrow();

    Course course = courseRepository.ofIdentity(courseDTO.getCode()).orElseThrow();
    Enrolment enrolment = new Enrolment(student, course);

    if (enrolmentRepository.findWithUserAndCourse(student.identity(), course.code()).isPresent())
      throw new IllegalStateException("You are already enrolled in this course");

    return enrolmentRepository.save(enrolment).toDto();
  }
}
