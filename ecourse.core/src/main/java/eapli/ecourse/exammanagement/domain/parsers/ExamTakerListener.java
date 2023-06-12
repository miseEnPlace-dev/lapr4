package eapli.ecourse.exammanagement.domain.parsers;

import eapli.ecourse.answermanagement.domain.Answer;
import eapli.ecourse.exammanagement.application.ExamPrinter;
import eapli.ecourse.exammanagement.domain.parsers.ExamParser.PropertiesContext;

public class ExamTakerListener extends ExamBaseListener {
  private final ExamPrinter printer;

  public ExamTakerListener(ExamPrinter printer) {
    if (printer == null)
      throw new IllegalArgumentException("Printer cannot be null!");

    this.printer = printer;
  }

  @Override
  public void enterHeader(ExamParser.HeaderContext ctx) {
    String title = null, description = null;

    for (PropertiesContext prop : ctx.properties()) {
      if (prop.title() != null) {
        title = extractString(prop.title().STRING().getText());
      }
      if (prop.description() != null) {
        description = extractString(prop.description().STRING().getText());
      }
    }

    if (ctx.getParent().getStart().getText().equals("@start-exam"))
      // Entering in the Exam Header...
      printer.printExamHeader(title, description);
    else
      // Entering in the Section Header...
      printer.printSectionHeader(title, description);
  }

  public Answer getAnswer() {
    return null;
  }

  private String extractString(String s) {
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i < s.length() - 1; i++)
      sb.append(s.charAt(i));
    return sb.toString();
  }
}
