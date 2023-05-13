package eapli.ecourse.app.backoffice.console.presentation.courses;

import eapli.ecourse.coursemanagement.application.ToggleCourseEnrolmentStateController;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

/**
 * UI for toggling course enrolment state
 *
 * @author Tomás Lopes <1211289>
 */
public class ToggleCourseEnrolmentStateUI extends AbstractUI {
  private final CourseRepository courseRepository = PersistenceContext.repositories().courses();
  private final ToggleCourseEnrolmentStateController ctrl = new ToggleCourseEnrolmentStateController(courseRepository,
      AuthzRegistry.authorizationService());

  @Override
  protected boolean doShow() {
    final Iterable<CourseDTO> courses = this.ctrl.listNotClosedCourses();
    if (!courses.iterator().hasNext()) {
      System.out.println("There are no registered courses");
      return false;
    }

    final SelectWidget<CourseDTO> selector = new SelectWidget<>("Courses:", courses, new CoursePrinter());
    selector.show();
    final CourseDTO selected = selector.selectedElement();

    try {
      this.ctrl.toggleEnrolmentState(selected);
      System.out.println("Course enrolment state toggled successfully\n");
    } catch (IllegalArgumentException e) {
      System.out.println("There is no course with the given code\n");
    } catch (IllegalStateException e) {
      System.out.println("Error: " + e.getMessage() + "\n");
    }

    return false;
  }

  @Override
  public String headline() {
    return "Toggle Course Enrolment State";
  }
}
