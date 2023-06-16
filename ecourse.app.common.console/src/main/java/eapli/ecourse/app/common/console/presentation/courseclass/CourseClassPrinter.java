package eapli.ecourse.app.common.console.presentation.courseclass;

import eapli.ecourse.app.common.console.util.TableFormatPrinter;
import eapli.ecourse.eventsmanagement.courseclassmanagement.dto.ClassDTO;
import eapli.framework.visitor.Visitor;

public class CourseClassPrinter implements Visitor<ClassDTO> {
  @Override
  public void visit(final ClassDTO visitee) {
    TableFormatPrinter printer = new TableFormatPrinter();

    printer.addColumn(visitee.getDayInWeek().toString(), 15);
    printer.addColumn(visitee.getDuration().toString(), 8);
    printer.addColumn(visitee.getHours().toString(), 10);
    printer.addColumn(visitee.getCourse().title().toString(), 15);

    System.out.print(printer.format());
  }

}
