package eapli.ecourse.app.common.console.presentation.course;

import java.text.SimpleDateFormat;

import eapli.ecourse.app.common.console.util.TableFormatPrinter;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.framework.visitor.Visitor;

public class CoursePrinter implements Visitor<CourseDTO> {
  public String header() {
    return String.format("\n#  %-7s%-20s%-30s%-5s%-5s%-16s%-16s%-20s", "Code", "Title", "Description", "Min", "Max",
        "State",
        "Enrolment State", "Created At");
  }

  public void printHeader() {
    System.out.println(header());
  }

  @Override
  public void visit(final CourseDTO visitee) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    TableFormatPrinter printer = new TableFormatPrinter();

    printer.addColumn(visitee.getCode().toString(), 7);
    printer.addColumn(visitee.getTitle().toString(), 20);
    printer.addColumn(visitee.getDescription().toString(), 30);
    printer.addColumn(visitee.getEnrolmentLimits().minLimit().toString(), 5);
    printer.addColumn(visitee.getEnrolmentLimits().maxLimit().toString(), 5);
    printer.addColumn(visitee.getCourseState().toString(), 16);
    printer.addColumn(visitee.getEnrolmentState().toString(), 16);
    printer.addColumn(formatter.format(visitee.getCreatedAt().getTime()), 20);

    System.out.print(printer.format());
  }
}
