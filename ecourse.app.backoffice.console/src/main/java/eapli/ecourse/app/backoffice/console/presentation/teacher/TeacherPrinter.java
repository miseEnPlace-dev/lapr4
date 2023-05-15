package eapli.ecourse.app.backoffice.console.presentation.teacher;

import eapli.ecourse.coursemanagement.dto.TeacherDTO;
import eapli.framework.visitor.Visitor;

/**
 * TODO: incomplete
 */
public class TeacherPrinter implements Visitor<TeacherDTO> {

  @Override
  public void visit(final TeacherDTO visitee) {

    System.out.printf("%s", visitee.getNumber());
  }
}
