package eapli.ecourse.app.backoffice.console.presentation.courses;

import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.framework.visitor.Visitor;

public class CoursePrinter implements Visitor<CourseDTO> {

  @Override
  public void visit(final CourseDTO visitee) {
    // TODO implement
    System.out.println("");
  }
}
