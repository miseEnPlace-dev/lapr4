package eapli.ecourse.app.teacher.console.presentation.courseclass;

import java.util.Calendar;

import eapli.ecourse.app.common.console.presentation.course.CoursePrinter;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.eventsmanagement.classmanagement.application.ScheduleClassController;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.DayInWeek;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.Hours;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class ScheduleClassUI extends AbstractUI {

  @Override
  protected boolean doShow() {

    ScheduleClassController ctrl = new ScheduleClassController(
        PersistenceContext.repositories().classes(), PersistenceContext.repositories().courses(),
        PersistenceContext.repositories().teachers());

    Iterable<CourseDTO> courses = ctrl.listAllInProgressLecturedBy();
    if (!courses.iterator().hasNext()) {
      System.out.println("There are no courses in progress.");
      Console.readLine("Press Enter to continue...");
      return false;
    }

    SelectWidget<CourseDTO> selector = new SelectWidget<>("Courses:", courses, new CoursePrinter());

    selector.show();
    CourseDTO selectedCourse = selector.selectedElement();

    if (selectedCourse == null)
      return false;

    int dayOfWeek = Console
        .readInteger("Enter the day of the week for the class (0 is Sunday - 6 is Saturday): ");

    DayInWeek day;
    try {
      day = DayInWeek.valueOf(dayOfWeek);
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid day of the week.");
      Console.readLine("Press Enter to continue...");
      return false;
    }

    Calendar hour = Console.readCalendar("Enter the hour for the class (hh:mm): ", "HH:mm");

    Hours hours = Hours.valueOf(hour);

    int duration = Console.readInteger("Enter the duration of the class (in minutes): ");

    try {
      ctrl.createClass(selectedCourse.getCode(), duration, day, hours);
    } catch (Exception e) {
      System.out.println("Error scheduling class: " + e.getMessage());
      Console.readLine("Press Enter to continue...");
      return false;
    }
    System.out.println("Class scheduled with success!");
    Console.readLine("Press Enter to continue...");

    return false;
  }

  @Override
  public String headline() {
    return "Schedule Class";
  }
}
