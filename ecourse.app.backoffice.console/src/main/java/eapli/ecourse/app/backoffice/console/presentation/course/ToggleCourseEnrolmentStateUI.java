package eapli.ecourse.app.backoffice.console.presentation.course;

import eapli.ecourse.app.common.console.presentation.course.CoursePrinter;
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

  @Override
  protected boolean doShow() {
    CourseRepository courseRepository = PersistenceContext.repositories().courses();
    ToggleCourseEnrolmentStateController ctrl = new ToggleCourseEnrolmentStateController(courseRepository,
        AuthzRegistry.authorizationService());

    final Iterable<CourseDTO> courses = ctrl.listNotClosedCourses();
    if (!courses.iterator().hasNext()) {
      System.out.println("There are no registered courses");
      return false;
    }

    new CoursePrinter().printHeader();
    final SelectWidget<CourseDTO> selector = new SelectWidget<>("Courses:", courses, new CoursePrinter());
    selector.show();
    final CourseDTO selected = selector.selectedElement();

    if (selected == null)
      return false;

    try {
      ctrl.toggleEnrolmentState(selected);
      System.out.println("\n\nCourse enrolment state toggled successfully\n");
    } catch (IllegalArgumentException e) {
      System.out.println("\n\nThere is no course with the given code\n");
    } catch (IllegalStateException e) {
      System.out.println("\n\nError: " + e.getMessage() + "\n");
    }

    return false;
  }

  @Override
  public String headline() {
    return "Toggle Course Enrolment State";
  }
}
