package eapli.ecourse.app.teacher.console.presentation.exams;

import java.util.ArrayList;
import java.util.List;

import eapli.ecourse.app.common.console.presentation.course.CoursePrinter;
import eapli.ecourse.app.common.console.presentation.exam.EvaluationExamPrinter;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.exammanagement.application.ListCourseExamsController;
import eapli.ecourse.exammanagement.dto.EvaluationExamDTO;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.presentation.console.SelectWidget;

public class ListCourseExamsUI extends AbstractUI {
  private final ListCourseExamsController ctrl = new ListCourseExamsController(AuthzRegistry.authorizationService(),
      PersistenceContext.repositories().courses(), PersistenceContext.repositories().evaluationExams());

  @Override
  protected boolean doShow() {
    Iterable<CourseDTO> courses = ctrl.listOpenInProgressCourses();
    List<CourseDTO> courseList = new ArrayList<>();
    courses.forEach(courseList::add);

    if (courseList.isEmpty()) {
      System.out.println("There are no courses available.");
      return false;
    }

    final SelectWidget<CourseDTO> selector = new SelectWidget<>(new CoursePrinter().header(), courseList,
        new CoursePrinter());
    selector.show();
    final CourseDTO selected = selector.selectedElement();
    if (selected == null)
      return false;

    Iterable<EvaluationExamDTO> evaluationExams = ctrl.listCourseEvaluationExams(selected);
    if (!evaluationExams.iterator().hasNext()) {
      System.out.println("There are no exams in " + selected.getTitle());
      return false;
    }

    if (evaluationExams.iterator().hasNext()) {
      ListWidget<EvaluationExamDTO> evaluationExamsList = new ListWidget<>(new EvaluationExamPrinter().header(),
          evaluationExams, new EvaluationExamPrinter());
      evaluationExamsList.show();
    } else {
      System.out.println("No evaluation exams in " + selected.getTitle() + "\n");
    }

    return false;
  }

  @Override
  public String headline() {
    return "List Exams In Course";
  }
}
