package eapli.ecourse.coursemanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.domain.CourseDescription;
import eapli.ecourse.coursemanagement.domain.CourseEnrolmentState;
import eapli.ecourse.coursemanagement.domain.CourseState;
import eapli.ecourse.coursemanagement.domain.CourseTitle;
import eapli.ecourse.coursemanagement.domain.EnrolmentLimits;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.validations.Preconditions;

@UseCaseController
public class CreateCourseController {
  private final CourseRepository courseRepository;

  public CreateCourseController(final CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public Course createCourse(CourseCode code, CourseTitle title, CourseDescription description,
      EnrolmentLimits limits) {

    Preconditions.noneNull(code, title, description, limits);

    if (courseRepository.containsOfIdentity(code)) {
      throw new IllegalStateException("There is already a course with that code.");
    }

    Course course = new Course(code, title, description, limits);

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
