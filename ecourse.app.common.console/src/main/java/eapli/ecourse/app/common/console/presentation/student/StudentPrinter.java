package eapli.ecourse.app.common.console.presentation.student;

import eapli.ecourse.app.common.console.util.TableFormatPrinter;
import eapli.ecourse.studentmanagement.dto.StudentDTO;
import eapli.framework.visitor.Visitor;

public class StudentPrinter implements Visitor<StudentDTO> {
  @Override
  public void visit(final StudentDTO visitee) {
    TableFormatPrinter printer = new TableFormatPrinter();

    printer.addColumn(visitee.getMecanographicNumber().toString(), 15);
    printer.addColumn(visitee.getUsername().toString(), 20);
    printer.addColumn(visitee.getName().firstName().toString(), 30);
    printer.addColumn(visitee.getName().lastName().toString(), 30);

    System.out.print(printer.format());
  }
}
