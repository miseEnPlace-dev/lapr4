package eapli.ecourse.app.common.console.presentation.answer;

import java.text.SimpleDateFormat;

import eapli.ecourse.answermanagement.dto.AnswerDTO;
import eapli.ecourse.app.common.console.util.TableFormatPrinter;
import eapli.framework.visitor.Visitor;

public class AnswerPrinter implements Visitor<AnswerDTO> {
  public String header() {
    return String.format("#  %-14s%-15s%-30s%-12s%-18s%-16s", "Student No.", "Name", "Exam", "Course", "Score", "Date");
  }

  public void printHeader() {
    System.out.println(header());
  }

  @Override
  public void visit(final AnswerDTO visitee) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    TableFormatPrinter printer = new TableFormatPrinter();

    printer.addColumn(visitee.getStudentNumber(), 14);
    printer.addColumn(visitee.getStudentName(), 15);
    printer.addColumn(visitee.getExamTitle(), 30);
    printer.addColumn(visitee.getCourse(), 12);
    printer.addColumn(visitee.getScore() == null ? "N/a" : visitee.getScore() + "/" + visitee.getExamScore(), 18);
    printer.addColumn(visitee.getTakenAt() == null ? "N/a" : formatter.format(visitee.getTakenAt().getTime()), 16);

    System.out.print(printer.format());
  }
}
