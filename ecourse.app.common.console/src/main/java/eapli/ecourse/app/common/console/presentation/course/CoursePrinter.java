package eapli.ecourse.app.common.console.presentation.course;

import java.text.SimpleDateFormat;

import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.framework.visitor.Visitor;

public class CoursePrinter implements Visitor<CourseDTO> {

  @Override
  public void visit(final CourseDTO visitee) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    System.out.printf("%-7s%-20s%-30s%-4s%-4s%-8s%-8s%-15s", visitee.getCode(), visitee.getTitle(),
        visitee.getDescription(), visitee.getEnrolmentLimits().minLimit(), visitee.getEnrolmentLimits().maxLimit(),
        visitee.getCourseState(),
        visitee.getEnrolmentState(), formatter.format(visitee.getCreatedAt().getTime()));
  }
}
