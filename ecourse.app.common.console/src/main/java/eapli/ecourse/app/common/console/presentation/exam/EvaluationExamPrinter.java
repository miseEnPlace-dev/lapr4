package eapli.ecourse.app.common.console.presentation.exam;

import eapli.ecourse.exammanagement.dto.EvaluationExamDTO;
import eapli.framework.visitor.Visitor;

public class EvaluationExamPrinter implements Visitor<EvaluationExamDTO> {

  @Override
  public void visit(final EvaluationExamDTO visitee) {
    System.out.printf("%-7s%-20s%-30s%-5s%-5s%-16s%-16s%-20s%-20s", visitee.getCode(), visitee.getTitle(),
      visitee.getCourse().title(), visitee.getTeacher().acronym(), visitee.getStartTime(), visitee.getEndTime(),
      visitee.getIdentifier(), visitee.getDescription(), visitee.getState());
  }
}
