package eapli.ecourse.coursemanagement.application;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;

public class ListCourseService {
  private CourseRepository courseRepository;

  public ListCourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public Iterable<CourseDTO> listNotClosedCourses() {
    final Iterable<Course> courses = courseRepository.findAllNotClosed();
    return convertToDto(courses);
  }

  public Iterable<CourseDTO> listClosedCourses() {
    final Iterable<Course> courses = courseRepository.findAllClosed();
    return convertToDto(courses);
  }

  public Iterable<CourseDTO> listOpenCourses() {
    final Iterable<Course> courses = courseRepository.findAllOpen();
    return convertToDto(courses);
  }

  public Iterable<CourseDTO> listOpenForEnrolment() {
    final Iterable<Course> courses = courseRepository.findAllOpenForEnrolment();
    return convertToDto(courses);
  }

  public Iterable<CourseDTO> listInProgressCourses() {
    final Iterable<Course> types = courseRepository.findAllInProgress();
    return convertToDto(types);
  }

  private Iterable<CourseDTO> convertToDto(Iterable<Course> courses) {
    return StreamSupport.stream(courses.spliterator(), true)
        .map(Course::toDto)
        .collect(Collectors.toUnmodifiableList());

  }
}
