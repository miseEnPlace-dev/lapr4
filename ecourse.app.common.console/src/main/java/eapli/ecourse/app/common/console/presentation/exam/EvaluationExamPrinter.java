package eapli.ecourse.app.common.console.presentation.exam;

import eapli.ecourse.exammanagement.dto.EvaluationExamDTO;
import eapli.framework.visitor.Visitor;

public class EvaluationExamPrinter implements Visitor<EvaluationExamDTO> {

  @Override
  public void visit(final EvaluationExamDTO visitee) {

    System.out.printf(" %-22s%-8s%-10s%-18s%-18s%-18s%-35s%-10s",
        visitee.getTitle().toString().substring(0, 18) + "...",
        visitee.getCourse().title(), visitee.getTeacher().acronym(), visitee.getStartTime(), visitee.getEndTime(),
        visitee.getIdentifier().toString().substring(0, 14) + "...",
        visitee.getDescription().toString().substring(0, 31) + "...", visitee.getState());
  }
}
