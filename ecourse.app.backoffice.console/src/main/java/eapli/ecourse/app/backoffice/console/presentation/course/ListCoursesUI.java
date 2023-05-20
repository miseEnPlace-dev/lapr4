package eapli.ecourse.app.backoffice.console.presentation.course;

import eapli.ecourse.app.common.console.presentation.course.CourseHeader;
import eapli.ecourse.app.common.console.presentation.course.CoursePrinter;
import eapli.ecourse.coursemanagement.application.ListCoursesController;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

public class ListCoursesUI extends AbstractListUI<CourseDTO> {
  private final ListCoursesController controller = new ListCoursesController(AuthzRegistry.authorizationService(),
      PersistenceContext.repositories().courses(), PersistenceContext.repositories().teachers(),
      PersistenceContext.repositories().students(), PersistenceContext.repositories().enrollments());

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
    return controller.getForLoggedUser();
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
    return new CourseHeader().header();
  }
}
