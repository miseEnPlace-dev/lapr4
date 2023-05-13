package eapli.ecourse.coursemanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;

public class ToggleCourseStatusController {
  private CourseRepository courseRepository;

  public ToggleCourseStatusController(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;

  }

  public Iterable<Course> listOpenCourses() {
    return courseRepository.findAllOpen();
  }

  public Iterable<Course> listClosedCourses() {
    return courseRepository.findAllClosed();
  }

  public void toggleCourseStatus(Course course) {
    if (course == null)
      throw new IllegalArgumentException("Course cannot be null");

    course.toggleState();

    courseRepository.save(course);
  }
}
