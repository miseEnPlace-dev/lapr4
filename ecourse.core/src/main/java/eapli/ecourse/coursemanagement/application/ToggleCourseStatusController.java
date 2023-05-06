package eapli.ecourse.coursemanagement.application;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseState;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ToggleCourseStatusController {

  @Autowired
  private CourseRepository courseRepository;

  public ToggleCourseStatusController() {
    
  }

  public Iterable<Course> listOpenCourses() {
    return courseRepository.openCourses();
  }

  public Iterable<Course> listClosedCourses() {
    return courseRepository.closedCourses();
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
