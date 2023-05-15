package eapli.ecourse.app.backoffice.console.presentation.student;

import eapli.ecourse.studentmanagement.domain.Student;
import eapli.framework.visitor.Visitor;

public class StudentPrinter implements Visitor<Student> {
  @Override
  public void visit(final Student visitee) {
    System.out.printf("%-15s%-20s%-30s%-30s", visitee.mecanographicNumber(), visitee.user().username(),
        visitee.user().name().firstName(),
        visitee.user().name().lastName());
  }
}
