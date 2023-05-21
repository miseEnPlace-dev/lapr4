package eapli.ecourse.app.common.console.presentation.exam;

import eapli.ecourse.exammanagement.dto.EvaluationExamDTO;
import eapli.framework.visitor.Visitor;

import java.util.Calendar;

public class EvaluationExamPrinter implements Visitor<EvaluationExamDTO> {

  @Override
  public void visit(final EvaluationExamDTO visitee) {
    new EvaluationExamHeader().printHeader();


    System.out.printf("%-39s %-22s%-8s%-10s%-18s%-18s%-18s%-40s%-8s", visitee.getCode(), visitee.getTitle(),
      visitee.getCourse().title(), visitee.getTeacher().acronym(), visitee.getStartTime().dayInWeek() + " " +
        visitee.getStartTime().hour() +":" + visitee.getStartTime().minute(), visitee.getEndTime().dayInWeek() + " " +
        visitee.getEndTime().hour() +":" + visitee.getEndTime().minute(), visitee.getIdentifier(),
      visitee.getDescription(), visitee.getState());
  }
}
