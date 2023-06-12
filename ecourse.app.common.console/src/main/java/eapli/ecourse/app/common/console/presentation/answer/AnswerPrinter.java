package eapli.ecourse.app.common.console.presentation.answer;

import java.text.SimpleDateFormat;

import eapli.ecourse.answermanagement.dto.AnswerDTO;
import eapli.framework.visitor.Visitor;

public class AnswerPrinter implements Visitor<AnswerDTO> {
  @Override
  public void visit(final AnswerDTO visitee) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    System.out.printf("%-12s%-20s%-20s%-20s%-6s%-16s", visitee.getStudentNumber(), visitee.getStudentName(),
        visitee.getExamTitle(), visitee.getClass(), visitee.getScore(), visitee.getTakenAt() == null ? "N/a"
            : formatter.format(visitee.getTakenAt().getTime()));
  }
}
