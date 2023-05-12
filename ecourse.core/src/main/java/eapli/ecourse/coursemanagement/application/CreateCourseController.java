package eapli.ecourse.coursemanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseBuilder;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.domain.CourseDescription;
import eapli.ecourse.coursemanagement.domain.CourseEnrolmentState;
import eapli.ecourse.coursemanagement.domain.CourseState;
import eapli.ecourse.coursemanagement.domain.CourseTitle;
import eapli.ecourse.coursemanagement.domain.EnrolmentLimits;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.validations.Preconditions;

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

    saveCourse(course);
    return course;
  }

  public Course createCourse(CourseCode code, CourseTitle title, CourseDescription description, EnrolmentLimits limits,
      CourseState courseState, CourseEnrolmentState enrolmentState) {

    Preconditions.noneNull(code, title, description, limits, courseState, enrolmentState);

    if (courseRepository.containsOfIdentity(code)) {
      throw new IllegalStateException("There is already a course with that code.");
    }

    Course course = new Course(code, title, description, limits, courseState, enrolmentState);

    saveCourse(course);
    return course;
  }

  private Course saveCourse(Course course) {
    if (course == null) {
      throw new IllegalArgumentException();
    }

    return courseRepository.save(course);
  }
}
