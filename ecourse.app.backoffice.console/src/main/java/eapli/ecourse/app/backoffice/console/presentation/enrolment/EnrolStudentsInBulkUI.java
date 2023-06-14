package eapli.ecourse.app.backoffice.console.presentation.enrolment;

import java.io.IOException;


import eapli.ecourse.app.common.console.presentation.course.CourseHeader;
import eapli.ecourse.app.common.console.presentation.course.CoursePrinter;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.enrolmentmanagement.application.StudentsBulkEnrolmentController;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class EnrolStudentsInBulkUI extends AbstractUI {
  private StudentsBulkEnrolmentController ctrl = new StudentsBulkEnrolmentController(
      PersistenceContext.repositories().courses(), PersistenceContext.repositories().enrollments(),
      PersistenceContext.repositories().students(), AuthzRegistry.authorizationService());

  @Override
  protected boolean doShow() {
    final Iterable<CourseDTO> courses = this.ctrl.listOpenForEnrolmentCourses();

    System.out.println("Select the course where the students will be enrolled:");
    final SelectWidget<CourseDTO> selector =
        new SelectWidget<>(new CourseHeader().header(), courses, new CoursePrinter());
    selector.show();
    final CourseDTO selectedCourse = selector.selectedElement();

    if (selectedCourse == null)
      return false;

    String filePath = Console.readLine("Enter the file path where the students are defined: ");

    try {
      this.ctrl.enrolStudents(selectedCourse, filePath);
    } catch (IOException ex) {
      System.out.println("The specified file does not exist.");
      return false;
    } catch (Exception ex) {
      System.out.println("An error occurred while enrolling the students.");
      return false;
    }

    System.out.println("Students enrolled successfully.");
    return false;
  }

  @Override
  public String headline() {
    return "Enrol Students in Bulk";
  }
}
