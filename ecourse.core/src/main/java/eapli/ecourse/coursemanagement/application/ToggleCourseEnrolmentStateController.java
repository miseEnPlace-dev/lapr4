package eapli.ecourse.coursemanagement.application;

import java.util.Optional;

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

    Optional<Course> c = courseRepository.findByCode(courseDTO.getCode());
    if (!c.isPresent())
      throw new IllegalArgumentException("There is no course with the given code");

    c.get().toggleEnrolmentState();

    return courseRepository.save(c.get()).toDto();
  }
}
