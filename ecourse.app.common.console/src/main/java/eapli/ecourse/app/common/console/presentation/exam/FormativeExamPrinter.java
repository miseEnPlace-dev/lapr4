package eapli.ecourse.app.common.console.presentation.exam;
import eapli.ecourse.exammanagement.dto.FormativeExamDTO;
import eapli.framework.visitor.Visitor;

public class FormativeExamPrinter implements Visitor<FormativeExamDTO> {
  @Override
  public void visit(final FormativeExamDTO visitee) {
    new FormativeExamHeader().printHeader();

    System.out.printf("%-37s %-22s%-8s%-10s%-18s%-35s%-8s", visitee.getCode(), visitee.getTitle(),
      visitee.getCourse().title(), visitee.getTeacher().acronym(), visitee.getIdentifier(),
      visitee.getDescription(), visitee.getState());
  }
}
