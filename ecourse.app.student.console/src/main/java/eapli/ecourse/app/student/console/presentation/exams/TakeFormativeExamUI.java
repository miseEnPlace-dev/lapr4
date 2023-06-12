package eapli.ecourse.app.student.console.presentation.exams;

import eapli.ecourse.exammanagement.application.TakeFormativeExamController;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;

public class TakeFormativeExamUI extends AbstractUI {

  private TakeFormativeExamController ctrl = new TakeFormativeExamController(AuthzRegistry.authorizationService(),
      PersistenceContext.repositories().students(), PersistenceContext.repositories().courses());

  @Override
  public boolean doShow() {

    return false;
  }

  @Override
  public String headline() {
    return "Take Formative Exam";
  }

}
