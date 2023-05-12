package eapli.ecourse.app.backoffice.console.presentation.courses;

import eapli.ecourse.coursemanagement.application.CourseService;
import eapli.ecourse.coursemanagement.application.ToggleCourseEnrolmentStateController;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

/**
 * UI for toggling course enrolment state
 *
 * @author Tomás Lopes <1211289>
 */
public class ToggleCourseEnrolmentStateUI extends AbstractUI {
  private final CourseRepository courseRepository = PersistenceContext.repositories().courses();
  private final CourseService courseService = new CourseService(courseRepository);
  private final ToggleCourseEnrolmentStateController ctrl = new ToggleCourseEnrolmentStateController(courseRepository);

  @Override
  protected boolean doShow() {
    final Iterable<CourseDTO> courses = this.ctrl.listNotClosedCourses();
    if (!courses.iterator().hasNext()) {
      System.out.println("There are no registered courses");
    } else {
      final SelectWidget<CourseDTO> selector = new SelectWidget<>("Courses:", courses, new CoursePrinter());
      selector.show();
      final CourseDTO selected = selector.selectedElement();
      System.out.println("Current course state: " + selected.getEnrolmentState().toString());
    }
    return false;
  }

  @Override
  public String headline() {
    return "Toggle Course Enrolment State";
  }
}
