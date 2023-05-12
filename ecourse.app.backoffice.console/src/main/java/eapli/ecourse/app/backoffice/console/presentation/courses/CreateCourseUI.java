package eapli.ecourse.app.backoffice.console.presentation.courses;

import eapli.ecourse.coursemanagement.application.CreateCourseController;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.domain.CourseDescription;
import eapli.ecourse.coursemanagement.domain.CourseEnrolmentState;
import eapli.ecourse.coursemanagement.domain.CourseState;
import eapli.ecourse.coursemanagement.domain.CourseTitle;
import eapli.ecourse.coursemanagement.domain.EnrolmentLimits;
import eapli.ecourse.coursemanagement.domain.CourseState.State;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.coursemanagement.domain.CourseEnrolmentState.EnrolmentState;
import eapli.framework.actions.Action;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.Scanner;

public class CreateCourseUI extends AbstractUI implements Action {

  private CreateCourseController ctrl = new CreateCourseController(PersistenceContext.repositories().courses());

  @Override
  public boolean execute() {
    Course course = null;
    State courseState = null;
    EnrolmentState enrolmentState = null;

    System.out.println("Insert the following information: ");

    String code = Console.readLine("Code: ");
    CourseCode courseCode = new CourseCode(code);

    String title = Console.readLine("Title: ");
    CourseTitle courseTitle = new CourseTitle(title);

    String description = Console.readLine("Description: ");
    CourseDescription courseDescription = new CourseDescription(description);

    int minLimit = Console.readInteger("Minimum Enrolment Limit: ");
    int maxLimit = Console.readInteger("Maximum Enrolment Limit: ");
    EnrolmentLimits enrolmentLimits = new EnrolmentLimits(minLimit, maxLimit);

    System.out.println("\nDo you want to the Course State and the Enrolment State? [Y/N]");

    String confirm = "";
    while (!confirm.equals("Y") && !confirm.equals("N")) {
      confirm = Console.readLine("Option: ").toUpperCase();
    }

    if (confirm.equals("N")) {
      System.out.println("Operation Cancelled");
    } else {
      courseState = selectCourseState(courseState);
      enrolmentState = selectEnrolmentState(enrolmentState);
    }

    System.out.println("Do you want to submit the data? [Y/N]");

    while (!confirm.equals("Y") && !confirm.equals("N")) {
      confirm = Console.readLine("Option: ").toUpperCase();
    }

    if (confirm.equals("Y")) {
      if (courseState == null || enrolmentState == null) {
        course = ctrl.createCourse(courseCode, courseTitle, courseDescription, enrolmentLimits);
      } else if (enrolmentState != null && courseState != null) {
        course = ctrl.createCourse(courseCode, courseTitle, courseDescription, enrolmentLimits,
            new CourseState(courseState), new CourseEnrolmentState(enrolmentState));
      }

      System.out.println("\nCourse successfully created");
    } else {
      System.out.println("Operation Cancelled!");
    }

    try (Scanner scanner = new Scanner(System.in)) {
      System.out.println("\nPress enter to continue...");
      scanner.nextLine();
    }

    return true;

  }

  private EnrolmentState selectEnrolmentState(EnrolmentState enrolmenState) {
    System.out.println("\n[1] - Open Enrolment");
    System.out.println("[2] - Close Enrolment");
    System.out.println("[0] - Exit");

    int option = Console.readOption(1, 2, 0);

    if (option == 0)
      return null;

    if (option == 1) {
      enrolmenState = CourseEnrolmentState.EnrolmentState.OPEN;
    } else {
      enrolmenState = CourseEnrolmentState.EnrolmentState.CLOSED;
    }

    return enrolmenState;

  }

  private State selectCourseState(State courseState) {
    System.out.println("\n[1] - Open Course");
    System.out.println("[2] - Close Course");
    System.out.println("[3] - In Progress");
    System.out.println("[4] - Finished");
    System.out.println("[0] - Exit");

    int option = Console.readOption(1, 4, 0);

    if (option == 0)
      return null;

    if (option == 1) {
      courseState = CourseState.State.OPEN;
    } else if (option == 2) {
      courseState = CourseState.State.CLOSED;
    } else if (option == 3) {
      courseState = CourseState.State.IN_PROGRESS;
    } else {
      courseState = CourseState.State.FINISHED;
    }

    return courseState;
  }

  @Override
  protected boolean doShow() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'doShow'");
  }

  @Override
  public String headline() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'headline'");
  }

}
