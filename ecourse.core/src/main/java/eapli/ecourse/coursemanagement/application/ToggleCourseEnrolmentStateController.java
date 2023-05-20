package eapli.ecourse.coursemanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

public class ToggleCourseEnrolmentStateController {
  private final ListCourseService service;
  private final CourseRepository courseRepository;
  private final AuthorizationService authz;

  public ToggleCourseEnrolmentStateController(CourseRepository courseRepository, AuthorizationService authz) {
    this.courseRepository = courseRepository;
    this.authz = authz;
    this.service = new ListCourseService(courseRepository);
  }

  public Iterable<CourseDTO> listNotClosedCourses() {
    return service.listNotClosedCourses();
  }

  public CourseDTO toggleEnrolmentState(CourseDTO courseDTO) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);

    Course course = courseRepository.ofIdentity(courseDTO.getCode()).orElseThrow();

    course.toggleEnrolmentState();

    return courseRepository.save(course).toDto();
  }
}
