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
      PersistenceContext.repositories().classes(), AuthzRegistry.authorizationService(),
      PersistenceContext.repositories().meetings(), PersistenceContext.repositories().enrollments(),
      PersistenceContext.repositories().students(), PersistenceContext.repositories().teachers());

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

      Calendar timeString = Console.readCalendar("Time (dd/MM/yyyy HH:MM):", "dd/MM/yyyy HH:mm");
      Time time = Time.valueOf(timeString);

      try {
        this.controller.updateScheduleClass(time, selected);
      } catch (final Exception e) {
        System.out.println("It was not possible to update the class schedule.");
        return false;
      }
    }

    return false;
  }

  @Override
  public String headline() {
    return "Update Class Schedule";
  }

}