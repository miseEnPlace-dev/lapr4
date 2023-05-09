package eapli.ecourse.coursemanagement.application;

import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;

public class CourseService {
  private CourseRepository courseRepository;

  CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public Iterable<CourseDTO> listNotClosedCourses() {
    // TODO implement
    return null;
  }
}
