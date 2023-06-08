package eapli.ecourse.app.backoffice.console.presentation.course;

import eapli.ecourse.app.common.console.presentation.teacher.TeacherPrinter;
import eapli.ecourse.app.common.console.util.FormatVerifier;
import eapli.ecourse.coursemanagement.application.CreateCourseController;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.teachermanagement.dto.TeacherDTO;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

/**
 * UI for creating a course
 *
 * @author André Barros <1211299@isep.ipp.pt>
 */

public class CreateCourseUI extends AbstractUI {

  private CreateCourseController ctrl = new CreateCourseController(PersistenceContext.repositories().courses(),
      AuthzRegistry.authorizationService(), PersistenceContext.repositories().teachers());

  @Override
  public boolean doShow() {
    System.out.println("Insert the following information: ");

    String code = FormatVerifier.validateNonEmptyString("Code: ");
    while (ctrl.checkIfCourseCodeExists(CourseCode.valueOf(code)).isPresent()) {
      code = FormatVerifier.validateNonEmptyString(
          "There is already a course with that code. Please enter a valid code: ");
    }

    String title = FormatVerifier.validateNonEmptyString("Title: ");
    String description = FormatVerifier.validateNonEmptyString("Description: ");

    int minLimit = FormatVerifier.readNonNegativeInteger("Minimum number of students: ");
    int maxLimit = FormatVerifier.readNonNegativeInteger("Maximum number of students: ");
    while (maxLimit < minLimit) {
      maxLimit = FormatVerifier.readNonNegativeInteger(
          "Maximum limit can not be lower than the minimum limit. Please enter a valid maximum limit:");
    }

    final Iterable<TeacherDTO> teachers = this.ctrl.listAllTeachers();
    if (!teachers.iterator().hasNext()) {
      System.out.println("There are no registered teachers");
      return false;
    }

    final SelectWidget<TeacherDTO> selector = new SelectWidget<>("\nTeachers:", teachers, new TeacherPrinter());
    selector.show();
    final TeacherDTO selected = selector.selectedElement();

    System.out.println("\nDo you want to submit the data? [Y/N]");

    String confirm = "";
    while (!confirm.equals("Y") && !confirm.equals("N")) {
      confirm = Console.readLine("Option: ").toUpperCase();
    }

    if (confirm.equals("N")) {
      System.out.println("Operation Cancelled!");
      return false;
    }

    try {
      ctrl.createCourse(code, title, description, minLimit, maxLimit, selected);
    } catch (IllegalStateException e) {
      System.out.println("Error creating course.");
      return false;
    }

    System.out.println("\nCourse successfully created");
    Console.readLine("Press Enter to continue...");

    return true;
  }

  @Override
  public String headline() {
    return "Create Course";
  }
}
