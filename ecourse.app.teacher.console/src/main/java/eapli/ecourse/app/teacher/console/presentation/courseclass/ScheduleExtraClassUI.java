package eapli.ecourse.app.teacher.console.presentation.courseclass;

import java.util.Calendar;

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
    SelectWidget<CourseDTO> selector = new SelectWidget<>("Courses:", courses, new CoursePrinter());
    selector.show();
    CourseDTO selected = selector.selectedElement();

    if (selected == null)
      return false;

    Iterable<StudentDTO> students = ctrl.listStudentsEnrolled(selected);
    MultipleSelectorWidget<StudentDTO> selector2 = new MultipleSelectorWidget<>("Students:", students,
        new StudentPrinter());
    Iterable<StudentDTO> selected2 = selector2.selectElements();

    if (selected2 == null)
      return false;

    Calendar time = Console.readCalendar("Enter the date and time of the class (dd-MM-yyyy HH:mm): ",
        "dd-MM-yyyy HH:mm");
    int duration = Console.readInteger("Enter the duration of the class (in minutes): ");

    try {
      ctrl.createExtraordinaryClass(selected.getCode(), duration, time, selected2);
    } catch (Exception e) {
      System.out.println("Error creating board: " + e.getMessage());
      Console.readLine("Press Enter to continue...");
      return false;
    }

    Console.readLine("Press Enter to continue...");

    return false;
  }

  @Override
  public String headline() {
    return "Schedule Extra Class";
  }
}
