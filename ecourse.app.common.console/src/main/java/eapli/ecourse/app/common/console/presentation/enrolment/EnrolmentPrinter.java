package eapli.ecourse.app.common.console.presentation.enrolment;

import java.text.SimpleDateFormat;

import eapli.ecourse.enrolmentmanagement.dto.EnrolmentDTO;
import eapli.framework.visitor.Visitor;

public class EnrolmentPrinter implements Visitor<EnrolmentDTO> {

  @Override
  public void visit(final EnrolmentDTO visitee) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    System.out.printf("%-10s%-20s%-30s%-5s%-5s%-8s%-16s%-20s", visitee.getCourseCode(), visitee.getStudentNumber(),
        visitee.getStudentName(), formatter.format(visitee.getCreatedAt().getTime()), visitee.getState());
  }

}
