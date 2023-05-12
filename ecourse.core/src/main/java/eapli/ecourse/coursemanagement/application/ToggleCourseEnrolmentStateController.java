package eapli.ecourse.coursemanagement.application;

import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;

public class ToggleCourseEnrolmentStateController {
  private CourseService service;
  private CourseRepository courseRepository;

  public ToggleCourseEnrolmentStateController(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
    this.service = new CourseService(courseRepository);
  }

  public Iterable<CourseDTO> listNotClosedCourses() {
    // TODO implement
    return null;
  }

  public CourseDTO toggleEnrolmentState(CourseDTO courseDTO) {
    // TODO implement
    return null;
  }
}
