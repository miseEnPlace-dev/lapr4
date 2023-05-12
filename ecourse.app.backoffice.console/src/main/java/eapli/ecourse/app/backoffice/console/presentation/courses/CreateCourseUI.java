package eapli.ecourse.app.backoffice.console.presentation.courses;

import eapli.ecourse.coursemanagement.application.CreateCourseController;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class CreateCourseUI extends AbstractUI implements Action {

  private CreateCourseController ctrl = new CreateCourseController(PersistenceContext.repositories().courses(),
      AuthzRegistry.authorizationService());

  @Override
  public boolean execute() {
    System.out.println("Insert the following information: ");

    String code = Console.readLine("Code: ");
    String title = Console.readLine("Title: ");
    String description = Console.readLine("Description: ");
    int minLimit = Console.readInteger("Minimum Enrolment Limit: ");
    int maxLimit = Console.readInteger("Maximum Enrolment Limit: ");
    System.out.println("Do you want to submit the data? [Y/N]");

    String confirm = "";
    while (!confirm.equals("Y") && !confirm.equals("N")) {
      confirm = Console.readLine("Option: ").toUpperCase();
    }

    if (confirm.equals("N")) {
      System.out.println("Operation Cancelled!");
      return false;
    }

    try {
      ctrl.createCourse(code, title, description, minLimit, maxLimit);
    } catch (IllegalStateException e) {
      System.out.println("Error creating course.");
      return false;
    }

    System.out.println("\nCourse successfully created");
    Console.readLine("Press Enter to continue...");

    return true;
  }

  @Override
  protected boolean doShow() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'doShow'");
  }

  @Override
  public String headline() {
    return "Create Course";
  }
}
