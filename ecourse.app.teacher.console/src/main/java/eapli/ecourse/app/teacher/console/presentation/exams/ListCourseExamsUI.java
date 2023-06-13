package eapli.ecourse.app.teacher.console.presentation.exams;

import java.util.ArrayList;
import java.util.List;

import eapli.ecourse.app.common.console.presentation.course.CourseHeader;
import eapli.ecourse.app.common.console.presentation.course.CoursePrinter;
import eapli.ecourse.app.common.console.presentation.exam.EvaluationExamHeader;
import eapli.ecourse.app.common.console.presentation.exam.EvaluationExamPrinter;
import eapli.ecourse.app.common.console.presentation.exam.FormativeExamHeader;
import eapli.ecourse.app.common.console.presentation.exam.FormativeExamPrinter;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.exammanagement.application.ListCourseExamsController;
import eapli.ecourse.exammanagement.dto.EvaluationExamDTO;
import eapli.ecourse.exammanagement.dto.FormativeExamDTO;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.presentation.console.SelectWidget;

public class ListCourseExamsUI extends AbstractUI {
  private final ListCourseExamsController ctrl = new ListCourseExamsController(AuthzRegistry.authorizationService(),
      PersistenceContext.repositories().courses(), PersistenceContext.repositories().evaluationExams(),
      PersistenceContext.repositories().formativeExams());

  @Override
  protected boolean doShow() {
    Iterable<CourseDTO> courses = ctrl.listOpenInProgressCourses();
    List<CourseDTO> courseList = new ArrayList<>();
    courses.forEach(courseList::add);

    if (courseList.isEmpty()) {
      System.out.println("There are no courses available.");
      return false;
    }

    new CourseHeader().printHeader();
    final SelectWidget<CourseDTO> selector = new SelectWidget<>("Courses:", courseList, new CoursePrinter());
    selector.show();
    final CourseDTO selected = selector.selectedElement();
    if (selected == null)
      return false;

    Iterable<EvaluationExamDTO> evaluationExams = ctrl.listCourseEvaluationExams(selected);
    Iterable<FormativeExamDTO> formativeExams = ctrl.listCourseFormativeExams(selected);
    if (!evaluationExams.iterator().hasNext() && !formativeExams.iterator().hasNext()) {
      System.out.println("There are no exams in " + selected.getTitle());
      return false;
    }

    if (evaluationExams.iterator().hasNext()) {
      new EvaluationExamHeader().printHeader();
      ListWidget<EvaluationExamDTO> evaluationExamsList = new ListWidget<>("", evaluationExams,
          new EvaluationExamPrinter());
      evaluationExamsList.show();
    } else {
      System.out.println("No evaluation exams in " + selected.getTitle() + "\n");
    }

    if (formativeExams.iterator().hasNext()) {
      new FormativeExamHeader().printHeader();
      ListWidget<FormativeExamDTO> formativeExamsList = new ListWidget<>("", formativeExams,
          new FormativeExamPrinter());
      formativeExamsList.show();
    } else {
      System.out.println("\nNo formative exams in " + selected.getTitle());
    }

    return true;
  }

  @Override
  public String headline() {
    return "List Exams In Course";
  }
}
