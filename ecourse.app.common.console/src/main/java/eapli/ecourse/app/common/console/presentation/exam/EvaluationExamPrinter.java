package eapli.ecourse.app.common.console.presentation.exam;

import eapli.ecourse.app.common.console.util.TableFormatPrinter;
import eapli.ecourse.exammanagement.dto.EvaluationExamDTO;
import eapli.framework.visitor.Visitor;

public class EvaluationExamPrinter implements Visitor<EvaluationExamDTO> {
  public String header() {
    return String.format("\n+= Evaluation Exams =======================================================+\n"
        + "#  %-30s%-8s%-10s%-18s%-18s%-45s%-10s", "Title", "Course", "Teacher", "StartTime",
        "EndTime", "Description", "State");
  }

  public void printHeader() {
    System.out.println(header());
  }

  @Override
  public void visit(final EvaluationExamDTO visitee) {
    TableFormatPrinter printer = new TableFormatPrinter();

    printer.addColumn(visitee.getTitle().toString(), 30);
    printer.addColumn(visitee.getCourse().title().toString(), 8);
    printer.addColumn(visitee.getTeacher().acronym().toString(), 10);
    printer.addColumn(visitee.getStartTime().toString(), 18);
    printer.addColumn(visitee.getEndTime().toString(), 18);
    printer.addColumn(visitee.getDescription().toString(), 45);
    printer.addColumn(visitee.getState().toString(), 10);

    System.out.print(printer.format());
  }
}
