package eapli.ecourse.app.common.console.presentation.student;

import eapli.ecourse.studentmanagement.dto.StudentDTO;
import eapli.framework.visitor.Visitor;

public class StudentPrinter implements Visitor<StudentDTO> {
  @Override
  public void visit(final StudentDTO visitee) {
    System.out.printf("%-15s%-20s%-30s%-30s", visitee.getMecanographicNumber(),
        visitee.getUsername(),
        visitee.getName().firstName(), visitee.getName().lastName());
  }
}
