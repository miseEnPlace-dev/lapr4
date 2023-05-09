package eapli.ecourse.coursemanagement.application;

import org.springframework.beans.factory.annotation.Autowired;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseState;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.infrastructure.persistence.RepositoryFactory;

public class ToggleCourseStatusController {

  private final RepositoryFactory repositoryFactory;

  @Autowired
  private CourseRepository courseRepository;

  public ToggleCourseStatusController() {
    repositoryFactory = PersistenceContext.repositories();
  }

  public Iterable<Course> listOpenCourses() {
    return courseRepository.findAllOpen();
  }

  public Iterable<Course> listClosedCourses() {
    return courseRepository.findAllClosed();
  }

  public void toggleCourseStatus(Course course) {
    if (course.state().equals(CourseState.State.CLOSED))
      course.state().changeToOpen();
    else {
      course.state().changeToClose();
    }
    courseRepository.save(course);
  }
}
