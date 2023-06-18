package eapli.ecourse.app.backoffice.console.presentation.course;

import eapli.ecourse.app.common.console.presentation.course.CoursePrinter;
import eapli.ecourse.coursemanagement.application.ToggleCourseStatusController;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class ToggleCourseStatusUI extends AbstractUI {

  @Override
  protected boolean doShow() {
    CourseRepository courseRepository = PersistenceContext.repositories().courses();
    ToggleCourseStatusController ctrl = new ToggleCourseStatusController(courseRepository,
        AuthzRegistry.authorizationService());

    System.out.println("[1] - Open Course");
    System.out.println("[2] - Close Course");
    System.out.println("[3] - Start Course");
    System.out.println("[4] - Finish Course");
    System.out.println("[0] - Exit");

    int option = Console.readOption(1, 4, 0);

    if (option == 0)
      return true;

    Iterable<CourseDTO> courses;
    switch (option) {
      case 1:
        courses = ctrl.listClosedCourses();
        break;
      case 2:
      case 3:
        courses = ctrl.listOpenCourses();
        break;
      case 4:
        courses = ctrl.listInProgressCourses();
        break;
      default:
        return false;
    }

    if (!courses.iterator().hasNext()) {
      System.out.println("There are no courses available to the selected toggle operation");
      return false;
    }

    final SelectWidget<CourseDTO> selector = new SelectWidget<>("Courses:", courses, new CoursePrinter());
    selector.show();
    final CourseDTO selected = selector.selectedElement();
    if (selected == null)
      return false;
    System.out.println("Current course state: " + selected.getEnrolmentState().toString());

    String confirm = "";
    System.out.println("Do you want to confirm the toggle of the Course Status? [Y/N]");

    while (!confirm.equals("Y") && !confirm.equals("N")) {
      confirm = Console.readLine("Option: ").toUpperCase();
    }

    if (confirm.equals("N")) {
      System.out.println("Operation Cancelled");
      return false;
    }

    try {
      if (option == 2)
        ctrl.toggleToPreviousCourseStatus(selected);
      else
        ctrl.toggleToNextCourseStatus(selected);
    } catch (IllegalArgumentException exception) {
      System.out.println("There is no course with the given code");
      return false;
    }

    System.out.println("Toggle operation successful");

    return true;
  }

  @Override
  public String headline() {
    return "Toggle Course Status";
  }
}
