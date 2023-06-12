package eapli.ecourse.app.common.console.presentation.exam;

import eapli.ecourse.exammanagement.dto.FormativeExamRequestDTO;
import eapli.framework.visitor.Visitor;

public class FormativeExamRequestPrinter implements Visitor<FormativeExamRequestDTO> {
  @Override
  public void visit(final FormativeExamRequestDTO visitee) {

    System.out.printf(" %-22s%-8s%-10s%-10s", visitee.getIdentifier(), visitee.getTitle(),
        visitee.getDescription());
  }
}
