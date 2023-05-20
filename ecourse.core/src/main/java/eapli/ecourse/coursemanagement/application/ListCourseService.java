package eapli.ecourse.coursemanagement.application;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.teachermanagement.domain.Teacher;

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
    final Iterable<Course> courses = courseRepository.findAllInProgress();
    return convertToDto(courses);
  }

  public Iterable<CourseDTO> listInProgressCoursesThatTeacherLectures(Teacher teacher) {
    final Iterable<Course> courses = courseRepository.findAllInProgressLecturedByTeacher(teacher);
    return convertToDto(courses);
  }

  public Iterable<CourseDTO> listNotFinishedCourses() {
    final Iterable<Course> courses = courseRepository.findAllNotFinished();
    return convertToDto(courses);
  }

  public Iterable<CourseDTO> listAll() {
    final Iterable<Course> courses = courseRepository.findAll();
    return convertToDto(courses);
  }

  private Iterable<CourseDTO> convertToDto(Iterable<Course> courses) {
    return StreamSupport.stream(courses.spliterator(), true)
        .map(Course::toDto)
        .collect(Collectors.toUnmodifiableList());
  }
}
