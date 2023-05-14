package eapli.ecourse.coursemanagement.application;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;

public class CourseService {
  private CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public Iterable<CourseDTO> listNotClosedCourses() {
    final Iterable<Course> types = courseRepository.findAllNotClosed();
    return convertToDto(types);
  }

  public Iterable<CourseDTO> listClosedCourses() {
    final Iterable<Course> types = courseRepository.findAllClosed();
    return convertToDto(types);
  }

  public Iterable<CourseDTO> listOpenCourses() {
    final Iterable<Course> types = courseRepository.findAllOpen();
    return convertToDto(types);
  }

  private Iterable<CourseDTO> convertToDto(Iterable<Course> courses) {
    return StreamSupport.stream(courses.spliterator(), true)
        .map(Course::toDto)
        .collect(Collectors.toUnmodifiableList());

  }
}
