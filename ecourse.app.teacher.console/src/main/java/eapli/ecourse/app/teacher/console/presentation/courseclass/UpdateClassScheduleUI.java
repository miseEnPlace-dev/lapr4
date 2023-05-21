package eapli.ecourse.app.teacher.console.presentation.courseclass;

import java.util.Calendar;

import eapli.ecourse.app.common.console.presentation.courseclass.CourseClassPrinter;
import eapli.ecourse.eventsmanagement.courseclassmanagement.application.UpdateClassScheduleController;
import eapli.ecourse.eventsmanagement.courseclassmanagement.dto.ClassDTO;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class UpdateClassScheduleUI extends AbstractUI {

  private UpdateClassScheduleController controller = new UpdateClassScheduleController(
      PersistenceContext.repositories().classes(), PersistenceContext.repositories().extraordinaryClasses(),
      AuthzRegistry.authorizationService(), PersistenceContext.repositories().invites(),
      PersistenceContext.repositories().enrollments(), PersistenceContext.repositories().students(),
      PersistenceContext.repositories().teachers());

  @Override
  protected boolean doShow() {

    final Iterable<ClassDTO> classes = this.controller.listAllClassesForAuthenticatedTeacher();
    if (!classes.iterator().hasNext()) {
      System.out.println("There are no registered classes");
      return false;
    }

    final SelectWidget<ClassDTO> selector = new SelectWidget<>("\nClasses:", classes, new CourseClassPrinter());
    selector.show();
    final ClassDTO selected = selector.selectedElement();

    if (selected != null) {
      System.out.println("\nClass selected: " + selected);

      Calendar timeString = Console.readCalendar("\nTime (dd/MM/yyyy HH:MM):", "dd/MM/yyyy HH:mm");
      Time time = Time.valueOf(timeString);

      if (this.controller.checkIfUsersAreAvailable(selected.getCourse(), time, selected.getDuration())) {
        System.out.println("The class is not available at the given time.");
        return false;
      }

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
        this.controller.updateScheduleClass(time, selected);
      } catch (final Exception e) {
        System.out.println("It was not possible to update the class schedule.");
        return false;
      }
    }

    System.out.println("\nClass scheduled updated successfully created");
    Console.readLine("Press Enter to continue...");

    return false;
  }

  @Override
  public String headline() {
    return "Update Class Schedule";
  }

}
