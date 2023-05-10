package eapli.ecourse.coursemanagement.application;

import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;

public class ToggleCourseEnrolmentStateController {
  private CourseService service;

  public ToggleCourseEnrolmentStateController() {

    this.service = new CourseService(PersistenceContext.repositories().courses());
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
