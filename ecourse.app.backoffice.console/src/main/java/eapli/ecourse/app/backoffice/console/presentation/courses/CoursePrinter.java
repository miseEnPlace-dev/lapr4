package eapli.ecourse.app.backoffice.console.presentation.courses;

import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.framework.visitor.Visitor;

public class CoursePrinter implements Visitor<CourseDTO> {

  @Override
  public void visit(final CourseDTO visitee) {
    System.out.printf("%-5s%-10s%-30s%-30s%-30s%-30s%-30s", visitee.getCode(), visitee.getTitle(),
        visitee.getDescription(), visitee.getEnrolmentLimits(), visitee.getCourseState(),
        visitee.getEnrolmentState(), visitee.getCreatedAt());
  }
}
