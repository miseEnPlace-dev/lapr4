package eapli.ecourse.app.common.console.presentation.courseclass;

import eapli.ecourse.eventsmanagement.courseclassmanagement.dto.ClassDTO;
import eapli.framework.visitor.Visitor;

public class CourseClassPrinter implements Visitor<ClassDTO> {
  @Override
  public void visit(final ClassDTO visitee) {
    System.out.printf("%-15s%-8s%-10s%-15s", visitee.getDayInWeek(), visitee.getDuration(),
        visitee.getHours(), visitee.getCourse().title());
  }

}
