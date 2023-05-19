package eapli.ecourse.app.student.console.presentation;

import eapli.ecourse.app.common.console.presentation.course.CoursePrinter;
import eapli.ecourse.app.common.console.presentation.exam.ExamPrinter;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.exammanagement.application.ListFutureExamsController;
import eapli.ecourse.exammanagement.domain.dto.ExamDTO;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.presentation.console.SelectWidget;


public class ListFutureExamsUI extends AbstractUI {

  private final ListFutureExamsController ctrl = new ListFutureExamsController(AuthzRegistry.authorizationService(),
    PersistenceContext.repositories().exams(), PersistenceContext.repositories().enrollments(),
    PersistenceContext.repositories().students(), PersistenceContext.repositories().courses());

  @Override
  protected boolean doShow() {

    Iterable<CourseDTO> studentCourses = ctrl.listStudentCourses();
    if (!studentCourses.iterator().hasNext()) {
      System.out.println("Not enrolled in any course");
      return false;
    }

    final SelectWidget<CourseDTO> selector = new SelectWidget<>("Courses:", studentCourses, new CoursePrinter());
    selector.show();
    final CourseDTO selected = selector.selectedElement();
    if (selected == null)
      return false;


    Iterable<ExamDTO> courseFutureExams = ctrl.futureExams(selected);
    if (!courseFutureExams.iterator().hasNext()) {
      System.out.println("\nThere are no future exams");
      return false;
    }

    ListWidget<ExamDTO> list = new ListWidget<>("Exams of " + selected.getTitle(), courseFutureExams, new ExamPrinter());
    list.show();

    return false;
  }

  @Override
  public String headline() {
    return "Future Exams";
  }
}
