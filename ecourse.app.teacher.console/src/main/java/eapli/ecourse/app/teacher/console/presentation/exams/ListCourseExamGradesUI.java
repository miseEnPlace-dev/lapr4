package eapli.ecourse.app.teacher.console.presentation.exams;

import eapli.ecourse.answermanagement.dto.AnswerDTO;
import eapli.ecourse.app.common.console.presentation.answer.AnswerHeader;
import eapli.ecourse.app.common.console.presentation.answer.AnswerPrinter;
import eapli.ecourse.app.common.console.presentation.course.CourseHeader;
import eapli.ecourse.app.common.console.presentation.course.CoursePrinter;
import eapli.ecourse.app.common.console.presentation.exam.EvaluationExamHeader;
import eapli.ecourse.app.common.console.presentation.exam.EvaluationExamPrinter;
import eapli.ecourse.app.common.console.presentation.exam.FormativeExamHeader;
import eapli.ecourse.app.common.console.presentation.exam.FormativeExamPrinter;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.exammanagement.application.ListCourseExamGradesController;
import eapli.ecourse.exammanagement.dto.EvaluationExamDTO;
import eapli.ecourse.exammanagement.dto.FormativeExamDTO;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.presentation.console.SelectWidget;

public class ListCourseExamGradesUI extends AbstractUI {

  private final ListCourseExamGradesController ctrl = new ListCourseExamGradesController(AuthzRegistry.authorizationService(),
    PersistenceContext.repositories().evaluationExams(), PersistenceContext.repositories().formativeExams(), PersistenceContext.repositories().courses(),
    PersistenceContext.repositories().teachers(), PersistenceContext.repositories().answers(), PersistenceContext.repositories().enrollments());

  @Override
  protected boolean doShow() {

    Iterable<CourseDTO> courses = ctrl.teacherCourses();
    if (!courses.iterator().hasNext()) {
      System.out.println("No courses available to you.");
      return false;
    }

    new CourseHeader().printHeader();
    SelectWidget<CourseDTO> selectCourse = new SelectWidget<>("", courses, new CoursePrinter());
    selectCourse.show();
    final CourseDTO selected = selectCourse.selectedElement();

    if (selected == null)
      return false;

    System.out.println("[1] - Evaluation Exams");
    System.out.println("[2] - Formative Exams");
    System.out.println("[0] - Exit");

    int option = Console.readOption(1, 2, 0);

    if (option == 0)
      return true;


    Iterable<AnswerDTO> grades;

    if (option == 1) {
      Iterable<EvaluationExamDTO> exams = ctrl.courseEvaluationExams(selected);
      if (!exams.iterator().hasNext()) {
        System.out.println("There are no evaluation exams with grades");
        return false;
      }

      SelectWidget<EvaluationExamDTO> selectExam = new SelectWidget<>(new EvaluationExamHeader().header(), exams, new EvaluationExamPrinter());
      selectExam.show();
      final EvaluationExamDTO exam = selectExam.selectedElement();

      grades = ctrl.evaluationExamGrades(exam);

    } else {
      Iterable<FormativeExamDTO> exams = ctrl.courseFormativeExams(selected);
      if (!exams.iterator().hasNext()) {
        System.out.println("There are no formative exams with grades");
        return false;
      }

      SelectWidget<FormativeExamDTO> selectExam = new SelectWidget<>(new FormativeExamHeader().header(), exams, new FormativeExamPrinter());
      selectExam.show();
      final FormativeExamDTO exam = selectExam.selectedElement();

      grades = ctrl.formativeExamGrades(exam);
    }

    ListWidget<AnswerDTO> gradesList = new ListWidget<>(new AnswerHeader().header(), grades, new AnswerPrinter());
    gradesList.show();

    return false;
  }

  @Override
  public String headline() {
    return "List Course Exam Grades";
  }
}
