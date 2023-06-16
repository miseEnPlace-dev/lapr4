package eapli.ecourse.app.student.console.presentation.exams;

import eapli.ecourse.answermanagement.application.ListStudentGradesController;
import eapli.ecourse.answermanagement.dto.AnswerDTO;
import eapli.ecourse.app.common.console.presentation.answer.AnswerPrinter;
import eapli.ecourse.app.common.console.presentation.course.CoursePrinter;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.presentation.console.SelectWidget;

public class ListStudentGradesUI extends AbstractUI {
  private ListStudentGradesController controller = new ListStudentGradesController(
      PersistenceContext.repositories().enrollments(), PersistenceContext.repositories().answers(),
      PersistenceContext.repositories().students(),
      PersistenceContext.repositories().evaluationExams(), AuthzRegistry.authorizationService());

  @Override
  protected boolean doShow() {
    Iterable<CourseDTO> studentCourses = controller.listStudentCourses();
    if (!studentCourses.iterator().hasNext()) {
      System.out.println("Not enrolled in any course");
      return false;
    }

    final SelectWidget<CourseDTO> selector = new SelectWidget<>(new CoursePrinter().header(), studentCourses,
        new CoursePrinter());
    selector.show();
    final CourseDTO selected = selector.selectedElement();
    if (selected == null)
      return false;

    Iterable<AnswerDTO> studentAnswers = controller.listStudentGrades(selected);
    if (!studentAnswers.iterator().hasNext()) {
      System.out.println("\nThere are no grades available for " + selected.getTitle());
      return false;
    }

    System.out.println("\n");
    ListWidget<AnswerDTO> list = new ListWidget<>(new AnswerPrinter().header(), studentAnswers,
        new AnswerPrinter());
    list.show();
    System.out.println("\n");

    return false;
  }

  @Override
  public String headline() {
    return "List Student Grades";
  }
}
