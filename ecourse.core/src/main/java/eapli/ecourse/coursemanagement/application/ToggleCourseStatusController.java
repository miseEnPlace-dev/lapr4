package eapli.ecourse.coursemanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

import java.util.Optional;

public class ToggleCourseStatusController {
  private final CourseRepository courseRepository;

  private final AuthorizationService authz;

  private final CourseService service;

  public ToggleCourseStatusController(CourseRepository courseRepository, AuthorizationService authz) {
    this.courseRepository = courseRepository;
    this.authz = authz;
    this.service = new CourseService(courseRepository);
  }

  public Iterable<CourseDTO> listOpenCourses() {
    return service.listOpenCourses();
  }

  public Iterable<CourseDTO> listClosedCourses() {
    return service.listClosedCourses();
  }

  public void toggleCourseStatus(CourseDTO courseDTO) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);

    Optional<Course> course = courseRepository.findByCode(courseDTO.getCode());

    if (course.isEmpty())
      throw new IllegalArgumentException("There is no Course with the given code");

    course.get().toggleState();
  }
}
