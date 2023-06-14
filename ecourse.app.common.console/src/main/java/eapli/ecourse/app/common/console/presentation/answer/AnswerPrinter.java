package eapli.ecourse.app.common.console.presentation.answer;

import java.text.SimpleDateFormat;

import eapli.ecourse.answermanagement.dto.AnswerDTO;
import eapli.framework.visitor.Visitor;

public class AnswerPrinter implements Visitor<AnswerDTO> {
  @Override
  public void visit(final AnswerDTO visitee) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    System.out.printf("%-14s%-15s%-15s%-10s%-10s%-16s", visitee.getStudentNumber(), visitee.getStudentName(),
        visitee.getExamTitle(), visitee.getCourse(), visitee.getScore() == null ? "N/a" : visitee.getScore(),
        visitee.getTakenAt() == null ? "N/a"
            : formatter.format(visitee.getTakenAt().getTime()));
  }
}
