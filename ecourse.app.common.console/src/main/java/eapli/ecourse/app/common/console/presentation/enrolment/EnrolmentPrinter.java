package eapli.ecourse.app.common.console.presentation.enrolment;

import java.text.SimpleDateFormat;

import eapli.ecourse.app.common.console.util.TableFormatPrinter;
import eapli.ecourse.enrolmentmanagement.dto.EnrolmentDTO;
import eapli.framework.visitor.Visitor;

public class EnrolmentPrinter implements Visitor<EnrolmentDTO> {
  public String header() {
    return String.format("\n#  %-16s%-20s%-20s%-20s%-16s", "Course Code", "Student Number", "Student Name",
        "Created At", "State");
  }

  public void printHeader() {
    System.out.println(header());
  }

  @Override
  public void visit(final EnrolmentDTO visitee) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    TableFormatPrinter printer = new TableFormatPrinter();

    printer.addColumn(visitee.getCourseCode().toString(), 16);
    printer.addColumn(visitee.getStudentNumber().toString(), 20);
    printer.addColumn(visitee.getStudentName(), 20);
    printer.addColumn(formatter.format(visitee.getCreatedAt().getTime()), 20);
    printer.addColumn(visitee.getState(), 16);

    System.out.print(printer.format());
  }
}
