package eapli.ecourse.app.common.console.presentation.teacher;

import eapli.ecourse.teachermanagement.dto.TeacherDTO;
import eapli.framework.visitor.Visitor;

public class TeacherPrinter implements Visitor<TeacherDTO> {

  @Override
  public void visit(final TeacherDTO visitee) {
    System.out.printf("%-10s%-20s%-30s%-20s", visitee.getNumber(), visitee.getUsername(),
        visitee.getAcronym(), visitee.getBirthDate());
  }
}
