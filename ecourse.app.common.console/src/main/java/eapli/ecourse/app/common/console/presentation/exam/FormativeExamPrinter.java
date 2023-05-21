package eapli.ecourse.app.common.console.presentation.exam;
import eapli.ecourse.exammanagement.dto.FormativeExamDTO;
import eapli.framework.visitor.Visitor;

public class FormativeExamPrinter implements Visitor<FormativeExamDTO> {
  @Override
  public void visit(final FormativeExamDTO visitee) {
    new FormativeExamHeader().printHeader();

    System.out.printf("%-7s%-20s%-30s%-5s%-5s%-16s%-16s", visitee.getCode(), visitee.getTitle(),
      visitee.getCourse().title(), visitee.getTeacher().acronym(),
      visitee.getIdentifier(), visitee.getDescription(), visitee.getState());
  }
}
