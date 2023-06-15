package eapli.ecourse.app.teacher.console.presentation.questions;

import eapli.ecourse.app.common.console.presentation.course.CourseHeader;
import eapli.ecourse.app.common.console.presentation.course.CoursePrinter;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.questionmanagement.application.AddQuestionsController;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class AddQuestionsUI extends AbstractUI {
  AddQuestionsController ctrl = new AddQuestionsController(PersistenceContext.repositories().questions(),
      PersistenceContext.repositories().courses(), AuthzRegistry.authorizationService(),
      PersistenceContext.repositories().teachers());

  @Override
  protected boolean doShow() {
    String fileName = Console.readLine("Enter the path of the file with the questions:");

    final Iterable<CourseDTO> courses = this.ctrl.listAvailableCourses();

    new CourseHeader().printHeader();
    final SelectWidget<CourseDTO> selector = new SelectWidget<>("Courses:", courses, new CoursePrinter());
    selector.show();
    final CourseDTO selected = selector.selectedElement();

    if (selected == null) {
      System.out.println("\n\nNo questions added!");
      return false;
    }

    try {
      ctrl.addQuestionsFromFile(fileName, selected);
    } catch (IllegalArgumentException e) {
      System.out.println("\nInvalid file path!");
    } catch (Exception e) {
      System.out.println("\nUnexpected error!");
    }

    System.out.println("\n\nQuestions added successfully!");

    return false;
  }

  @Override
  public String headline() {
    return "Add Questions";
  }
}
