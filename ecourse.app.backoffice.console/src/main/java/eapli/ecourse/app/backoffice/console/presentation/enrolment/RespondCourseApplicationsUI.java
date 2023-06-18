package eapli.ecourse.app.backoffice.console.presentation.enrolment;

import eapli.ecourse.app.common.console.presentation.course.CoursePrinter;
import eapli.ecourse.app.common.console.presentation.enrolment.EnrolmentPrinter;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.enrolmentmanagement.application.RespondCourseApplicationController;
import eapli.ecourse.enrolmentmanagement.dto.EnrolmentDTO;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class RespondCourseApplicationsUI extends AbstractUI {

  @Override
  protected boolean doShow() {
    RespondCourseApplicationController ctrl = new RespondCourseApplicationController(
        PersistenceContext.repositories().courses(),
        PersistenceContext.repositories().enrollments(),
        AuthzRegistry.authorizationService());

    final Iterable<CourseDTO> courses = ctrl.listOpenForEnrolmentCourses();
    if (!courses.iterator().hasNext()) {
      System.out.println("There are no courses open for enrolment");
      return false;
    }

    final SelectWidget<CourseDTO> courseSelector = new SelectWidget<>(new CoursePrinter().header(), courses,
        new CoursePrinter());
    courseSelector.show();
    final CourseDTO selectedCourse = courseSelector.selectedElement();

    final Iterable<EnrolmentDTO> enrolments = ctrl.listPendingCourseApplications(selectedCourse);
    if (!enrolments.iterator().hasNext()) {
      System.out.println("There are no pending applications for this course");
      Console.readLine("Press Enter to continue...");

      return false;
    }

    System.out.println();
    final SelectWidget<EnrolmentDTO> enrolmentSelector = new SelectWidget<>(new EnrolmentPrinter().header(), enrolments,
        new EnrolmentPrinter());
    enrolmentSelector.show();
    final EnrolmentDTO selectedEnrolment = enrolmentSelector.selectedElement();

    System.out.println("\nRespond to the course application:");
    System.out.println("1. Accept");
    System.out.println("2. Reject");
    System.out.println("0. Exit");
    Integer permission = Console.readOption(1, 2, 0);

    if (permission == 0) {
      return false;
    }

    if (permission == 1) {
      ctrl.accept(selectedEnrolment);
      System.out.println("\nCourse application accepted.");
    } else {
      ctrl.reject(selectedEnrolment);
      System.out.println("\nCourse application rejected.");
    }

    Console.readLine("Press Enter to continue...");

    return true;
  }

  @Override
  public String headline() {
    return "Respond to Course Application";
  }

}
