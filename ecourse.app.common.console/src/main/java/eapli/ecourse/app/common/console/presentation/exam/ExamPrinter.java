package eapli.ecourse.app.common.console.presentation.exam;

import eapli.ecourse.exammanagement.domain.dto.ExamDTO;
import eapli.framework.visitor.Visitor;

public class ExamPrinter implements Visitor<ExamDTO> {

  @Override
  public void visit(final ExamDTO visitee) {
    System.out.printf("%-7s%-20s%-30s%-5s%-5s%-8s%-16s", visitee.getCode(), visitee.getCourse().title(),
        visitee.getTeacher().acronym(), visitee.getIdentifier(), visitee.getTitle(), visitee.getDescription(),
        visitee.getState());
  }
}
