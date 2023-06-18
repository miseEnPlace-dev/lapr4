package eapli.ecourse.app.teacher.console.presentation.courseclass;

import java.util.Calendar;
import java.util.Collection;

import eapli.ecourse.app.common.console.presentation.course.CoursePrinter;
import eapli.ecourse.app.common.console.presentation.student.StudentPrinter;
import eapli.ecourse.app.common.console.util.MultipleSelectorWidget;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.eventsmanagement.courseclassmanagement.application.ScheduleExtraClassController;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.studentmanagement.dto.StudentDTO;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class ScheduleExtraClassUI extends AbstractUI {

  @Override
  protected boolean doShow() {

    ScheduleExtraClassController ctrl = new ScheduleExtraClassController(PersistenceContext.repositories().courses(),
        PersistenceContext.repositories().enrollments(), PersistenceContext.repositories().extraordinaryClasses(),
        PersistenceContext.repositories().invites(), PersistenceContext.repositories().students(),
        PersistenceContext.repositories().teachers(), PersistenceContext.repositories().classes());

    Iterable<CourseDTO> courses = ctrl.listAllInProgressLecturedBy();
    if (!courses.iterator().hasNext()) {
      System.out.println("There are no courses in progress.");
      Console.readLine("Press Enter to continue...");
      return false;
    }

    SelectWidget<CourseDTO> selector = new SelectWidget<>("Courses:", courses, new CoursePrinter());

    selector.show();
    CourseDTO selected = selector.selectedElement();

    if (selected == null)
      return false;

    Collection<StudentDTO> students = ctrl.listStudentsEnrolled(selected);

    if (!students.iterator().hasNext()) {
      System.out.println("There are no students enrolled in this course.");
      Console.readLine("Press Enter to continue...");
      return false;
    }
    MultipleSelectorWidget<StudentDTO> selector2 = new MultipleSelectorWidget<>("\nStudents:", students,
        new StudentPrinter());

    Iterable<StudentDTO> selected2 = selector2.selectElements();

    Calendar time = Console.readCalendar("Enter the date and time of the class (dd-MM-yyyy HH:mm): ",
        "dd-MM-yyyy HH:mm");
    while (!ctrl.validateTime(time)) {
      System.out.println("\nThe date and time must be in the future.");
      time = Console.readCalendar("Enter the date and time of the class (dd-MM-yyyy HH:mm): ",
          "dd-MM-yyyy HH:mm");
    }

    int duration = Console.readInteger("\nEnter the duration of the class (in minutes): ");

    if (!ctrl.checkIfUsersAreAvailable(time, duration, selected2)) {
      System.out.println("Some of the selected students are not available at the given time.");
      Console.readLine("Press Enter to continue...");
      return false;
    }

    try {
      ctrl.createExtraordinaryClass(selected.getCode(), duration, time, selected2);
    } catch (Exception e) {
      System.out.println("Error scheduling extraordinary class: " + e.getMessage());
      Console.readLine("Press Enter to continue...");
      return false;
    }
    System.out.println("Extraordinary class scheduled with success!");
    Console.readLine("Press Enter to continue...");

    return false;
  }

  @Override
  public String headline() {
    return "Schedule Extra Class";
  }
}
