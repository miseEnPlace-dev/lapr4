package eapli.ecourse.enrolmentmanagement.application;

import eapli.ecourse.coursemanagement.application.ListCourseService;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.enrolmentmanagement.domain.Enrolment;
import eapli.ecourse.enrolmentmanagement.dto.EnrolmentDTO;
import eapli.ecourse.enrolmentmanagement.repositories.EnrolmentRepository;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

@UseCaseController
public class RespondCourseApplicationController {

  private EnrolmentRepository enrolmentRepository;
  private final ListCourseService listCoursesService;
  private final ListEnrolmentService listEnrolmentService;
  private AuthorizationService authz;

  public RespondCourseApplicationController(final CourseRepository courseRepository,
      final EnrolmentRepository enrolmentRepository, AuthorizationService authz) {
    this.enrolmentRepository = enrolmentRepository;
    this.authz = authz;
    this.listCoursesService = new ListCourseService(courseRepository);
    this.listEnrolmentService = new ListEnrolmentService(enrolmentRepository);
  }

  public Iterable<CourseDTO> listOpenForEnrolmentCourses() {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.MANAGER, ClientRoles.POWER_USER);
    return listCoursesService.listOpenForEnrolment();
  }

  public Iterable<EnrolmentDTO> listPendingCourseApplications(final CourseDTO courseDTO) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.MANAGER, ClientRoles.POWER_USER);
    return listEnrolmentService.listPendingCourseApplications(courseDTO.getCode());
  }

  public EnrolmentDTO accept(final EnrolmentDTO enrolmentDTO) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.MANAGER, ClientRoles.POWER_USER);

    CourseCode code = enrolmentDTO.getCourseCode();
    MecanographicNumber number = enrolmentDTO.getStudentNumber();

    Enrolment enrolment = enrolmentRepository
        .findWithUserAndCourse(number, code).orElseThrow();

    enrolment.accept();

    return enrolmentRepository.save(enrolment).toDto();
  }

  public EnrolmentDTO reject(final EnrolmentDTO enrolmentDTO) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.MANAGER, ClientRoles.POWER_USER);

    CourseCode code = enrolmentDTO.getCourseCode();
    MecanographicNumber number = enrolmentDTO.getStudentNumber();

    Enrolment enrolment = enrolmentRepository
        .findWithUserAndCourse(number, code).orElseThrow();

    enrolment.reject();

    return enrolmentRepository.save(enrolment).toDto();
  }

}
