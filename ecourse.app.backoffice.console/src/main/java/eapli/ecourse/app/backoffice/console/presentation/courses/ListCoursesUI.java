package eapli.ecourse.app.backoffice.console.presentation.courses;

import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

public class ListCoursesUI extends AbstractListUI<CourseDTO> {

  @Override
  public String headline() {
    return "List Courses";
  }

  @Override
  protected String emptyMessage() {
    return "No data.";
  }

  @Override
  protected Iterable<CourseDTO> elements() {
    // TODO implement
    return null;
  }

  @Override
  protected Visitor<CourseDTO> elementPrinter() {
    return new CoursePrinter();
  }

  @Override
  protected String elementName() {
    return "Course";
  }

  @Override
  protected String listHeader() {
    // TODO implement
    return null;
  }
}
