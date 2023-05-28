package eapli.ecourse.app.student.console.presentation;

import eapli.ecourse.app.common.console.presentation.course.CourseHeader;
import eapli.ecourse.app.common.console.presentation.course.CoursePrinter;
import eapli.ecourse.app.common.console.presentation.exam.EvaluationExamHeader;
import eapli.ecourse.app.common.console.presentation.exam.EvaluationExamPrinter;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.exammanagement.application.ListFutureExamsController;
import eapli.ecourse.exammanagement.dto.EvaluationExamDTO;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.presentation.console.SelectWidget;

public class ListFutureExamsUI extends AbstractUI {

  private final ListFutureExamsController ctrl = new ListFutureExamsController(AuthzRegistry.authorizationService(),
      PersistenceContext.repositories().evaluationExams(), PersistenceContext.repositories().enrollments(),
      PersistenceContext.repositories().courses(), PersistenceContext.repositories().students());

  @Override
  protected boolean doShow() {

    Iterable<CourseDTO> studentCourses = ctrl.listStudentCourses();
    if (!studentCourses.iterator().hasNext()) {
      System.out.println("Not enrolled in any course");
      return false;
    }

    new CourseHeader().printHeader();
    final SelectWidget<CourseDTO> selector = new SelectWidget<>("", studentCourses, new CoursePrinter());
    selector.show();
    final CourseDTO selected = selector.selectedElement();
    if (selected == null)
      return false;


    Iterable<EvaluationExamDTO> courseFutureExams = ctrl.futureExams(selected);
    if (!courseFutureExams.iterator().hasNext()) {
      System.out.println("\nThere are no future exams in " + selected.getTitle());
      return false;
    }


    new EvaluationExamHeader().printHeader();
    ListWidget<EvaluationExamDTO> list = new ListWidget<>("", courseFutureExams, new EvaluationExamPrinter());
    list.show();

    return false;
  }

  @Override
  public String headline() {
    return "Future Exams";
  }
}
