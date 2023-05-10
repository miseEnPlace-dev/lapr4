package eapli.ecourse.coursemanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.domain.CourseDescription;
import eapli.ecourse.coursemanagement.domain.CourseEnrolmentState;
import eapli.ecourse.coursemanagement.domain.CourseState;
import eapli.ecourse.coursemanagement.domain.CourseTitle;
import eapli.ecourse.coursemanagement.domain.EnrolmentLimits;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class CreateCourseController {

  @Autowired
  private CourseRepository courseRepository;

  public CreateCourseController() {

  }

  public Course createCourse(CourseCode code, CourseTitle title, CourseDescription description,
      EnrolmentLimits enrolmentLimits) {
    if (title == null || code == null || description == null || enrolmentLimits == null) {
      throw new IllegalArgumentException();
    }

    if (courseRepository.findByCode(code) == null) {
      throw new IllegalStateException("There is already a course with that code.");
    }

    Course course = new Course(code, title, description, enrolmentLimits);
    return course;
  }

  public Course createCourse(CourseCode code, CourseTitle title, CourseDescription description, EnrolmentLimits limits,
      CourseState courseState, CourseEnrolmentState enrolmentState) {
    if (title == null || code == null || description == null || limits == null || courseState == null
        || enrolmentState == null) {
      throw new IllegalArgumentException();
    }

    if (courseRepository.findByCode(code) == null) {
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

    if (checkDuplicates(course)) {
      throw new IllegalStateException("There is already a course with that code.");
    }

    return courseRepository.save(course);
  }

  private boolean checkDuplicates(Course course) {
    if (course == null) {
      throw new IllegalArgumentException();
    }

    return courseRepository.findByCode(course.code()) != null;
  }
}
