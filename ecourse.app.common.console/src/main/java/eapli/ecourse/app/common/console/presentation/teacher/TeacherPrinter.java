package eapli.ecourse.app.common.console.presentation.teacher;

import eapli.ecourse.app.common.console.util.TableFormatPrinter;
import eapli.ecourse.teachermanagement.dto.TeacherDTO;
import eapli.framework.visitor.Visitor;

public class TeacherPrinter implements Visitor<TeacherDTO> {
  @Override
  public void visit(final TeacherDTO visitee) {
    TableFormatPrinter printer = new TableFormatPrinter();

    printer.addColumn(visitee.getNumber().toString(), 20);
    printer.addColumn(visitee.getUsername().toString(), 20);
    printer.addColumn(visitee.getAcronym().toString(), 30);
    printer.addColumn(visitee.getBirthDate().toString(), 20);

    System.out.print(printer.format());
  }
}
