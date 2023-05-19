package eapli.ecourse.app.teacher.console.presentation.exams;

import java.io.IOException;

import eapli.ecourse.app.common.console.presentation.course.CourseHeader;
import eapli.ecourse.app.common.console.presentation.course.CoursePrinter;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.exammanagement.application.CreateExamController;
import eapli.ecourse.exammanagement.application.exceptions.ParseException;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class CreateExamUI extends AbstractUI {
  CreateExamController ctrl = new CreateExamController(AuthzRegistry.authorizationService(),
      PersistenceContext.repositories().teachers(), PersistenceContext.repositories().exams(),
      PersistenceContext.repositories().courses());

  @Override
  protected boolean doShow() {
    final Iterable<CourseDTO> courses = this.ctrl.listInProgressCourses();

    final SelectWidget<CourseDTO> selector = new SelectWidget<>(new CourseHeader().header(), courses,
        new CoursePrinter());
    selector.show();
    final CourseDTO selectedCourse = selector.selectedElement();

    String filePath = Console.readLine("Enter the file path where the exam is defined: ");

    try {
      this.ctrl.parseExam(filePath);
    } catch (IOException ex) {
      System.out.println("The specified file does not exist.");
    } catch (ParseException ex) {
      System.out.println(ex.getMessage());
    }

    Time startTime = Time.valueOf(
        Console.readCalendar("Enter the start date/time for the exam (dd/mm/yyyy hh:mm): ", "dd/MM/yyyy HH:mm"));

    Time endTime = Time.valueOf(
        Console.readCalendar("Enter the end date/time for the exam (dd/mm/yyyy hh:mm): ", "dd/MM/yyyy HH:mm"));

    this.ctrl.createExam(selectedCourse, startTime, endTime);

    return true;
  }

  @Override
  public String headline() {
    return "Create Exam";
  }
}
