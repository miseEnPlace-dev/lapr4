package eapli.ecourse.coursemanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

public class ToggleCourseStatusController {
  private final CourseRepository courseRepository;

  private final AuthorizationService authz;

  private final ListCourseService service;

  public ToggleCourseStatusController(CourseRepository courseRepository, AuthorizationService authz) {
    this.courseRepository = courseRepository;
    this.authz = authz;
    this.service = new ListCourseService(courseRepository);
  }

  public Iterable<CourseDTO> listOpenCourses() {
    return service.listOpenCourses();
  }

  public Iterable<CourseDTO> listClosedCourses() {
    return service.listClosedCourses();
  }

  public Iterable<CourseDTO> listInProgressCourses() {
    return service.listInProgressCourses();
  }

  public Course toggleToNextCourseStatus(CourseDTO courseDTO) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);

    Course course = getCourse(courseDTO);

    course.nextState();
    return courseRepository.save(course);
  }

  public Course toggleToPreviousCourseStatus(CourseDTO courseDTO) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);

    Course course = getCourse(courseDTO);

    course.previousState();
    return courseRepository.save(course);
  }

  private Course getCourse(CourseDTO courseDTO) {
    return courseRepository.ofIdentity(courseDTO.getCode()).orElseThrow();
  }
}
