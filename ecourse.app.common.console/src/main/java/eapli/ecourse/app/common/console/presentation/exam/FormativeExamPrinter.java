package eapli.ecourse.app.common.console.presentation.exam;

import eapli.ecourse.app.common.console.util.TableFormatPrinter;
import eapli.ecourse.exammanagement.dto.FormativeExamDTO;
import eapli.framework.visitor.Visitor;

public class FormativeExamPrinter implements Visitor<FormativeExamDTO> {
  public String header() {
    return String.format("+= Formative Exams =======================================================+\n" +
        "#   %-22s%-8s%-10s%-35s%-10s", "Title", "Course", "Teacher", "Description", "State");
  }

  public void printHeader() {
    System.out.println(header());
  }

  @Override
  public void visit(final FormativeExamDTO visitee) {
    TableFormatPrinter printer = new TableFormatPrinter();

    printer.addColumn(visitee.getTitle().toString(), 22);
    printer.addColumn(visitee.getCourse().title().toString(), 8);
    printer.addColumn(visitee.getTeacher().acronym().toString(), 10);
    printer.addColumn(visitee.getDescription().toString(), 35);
    printer.addColumn(visitee.getState().toString(), 10);

    System.out.print(printer.format());
  }
}
