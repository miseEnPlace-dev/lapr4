package eapli.ecourse.app.teacher.console.presentation.questions;

import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.questionmanagement.application.AddQuestionsController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class AddQuestionsUI extends AbstractUI {
  AddQuestionsController ctrl = new AddQuestionsController(PersistenceContext.repositories().questions());

  @Override
  protected boolean doShow() {
    String fileName = Console.readLine("Enter the path of the file with the questions:");

    try {
      ctrl.addQuestionsFromFile(fileName);
    } catch (Exception e) {
      System.out.println("Unexpected error!");
    }

    return false;
  }

  @Override
  public String headline() {
    return "Add Questions";
  }
}
