package eapli.ecourse.coursemanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseBuilder;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

@UseCaseController
public class CreateCourseController {
  private final CourseRepository courseRepository;
  private final AuthorizationService authz;

  public CreateCourseController(final CourseRepository courseRepository, final AuthorizationService authz) {
    this.courseRepository = courseRepository;
    this.authz = authz;
  }

  public Course createCourse(String code, String title, String description,
      int min, int max) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);

    Course course = new CourseBuilder().withCode(code).withTitle(title).withDescription(description)
        .withEnrolmentLimits(min, max).build();

    if (courseRepository.containsOfIdentity(course.code()))
      throw new IllegalStateException("There is already a course with that code.");

    return saveCourse(course);
  }

  private Course saveCourse(Course course) {
    if (course == null) {
      throw new IllegalArgumentException();
    }

    return courseRepository.save(course);
  }
}
