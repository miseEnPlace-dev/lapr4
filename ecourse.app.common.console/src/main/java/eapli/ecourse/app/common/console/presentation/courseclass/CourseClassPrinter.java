package eapli.ecourse.app.common.console.presentation.courseclass;

import eapli.ecourse.eventsmanagement.courseclassmanagement.dto.ClassDTO;
import eapli.framework.visitor.Visitor;

public class CourseClassPrinter implements Visitor<ClassDTO> {
  @Override
  public void visit(final ClassDTO visitee) {
    System.out.printf("%-7s%-20s%-30s%-5s%-5s%-8s%-16s", visitee.getId(), visitee.getDayInWeek(), visitee.getDuration(),
        visitee.getHours(), visitee.getCourse().title());
  }

}
