package eapli.ecourse.app.teacher.console.presentation.exams;

import eapli.ecourse.answermanagement.application.ListCourseExamGradesController;
import eapli.ecourse.answermanagement.dto.AnswerDTO;
import eapli.ecourse.app.common.console.presentation.answer.AnswerPrinter;
import eapli.ecourse.app.common.console.presentation.course.CoursePrinter;
import eapli.ecourse.app.common.console.presentation.exam.EvaluationExamPrinter;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.exammanagement.dto.EvaluationExamDTO;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.presentation.console.SelectWidget;

public class ListCourseExamGradesUI extends AbstractUI {

  @Override
  protected boolean doShow() {
    ListCourseExamGradesController ctrl = new ListCourseExamGradesController(
        AuthzRegistry.authorizationService(), PersistenceContext.repositories().evaluationExams(),
        PersistenceContext.repositories().courses(), PersistenceContext.repositories().teachers(),
        PersistenceContext.repositories().answers(), PersistenceContext.repositories().enrollments(),
        PersistenceContext.repositories().students());

    Iterable<CourseDTO> courses = ctrl.teacherCourses();
    if (!courses.iterator().hasNext()) {
      System.out.println("\nNo courses available to you.");
      return false;
    }

    SelectWidget<CourseDTO> selectCourse = new SelectWidget<>(new CoursePrinter().header(), courses,
        new CoursePrinter());
    selectCourse.show();
    final CourseDTO course = selectCourse.selectedElement();

    if (course == null)
      return false;

    Iterable<EvaluationExamDTO> exams = ctrl.courseEvaluationExams(course);
    if (!exams.iterator().hasNext()) {
      System.out.println("\nThere are no evaluated exams in " + course.getTitle().toString());
      return false;
    }

    SelectWidget<EvaluationExamDTO> selectExam = new SelectWidget<>("\n" + new EvaluationExamPrinter().header(), exams,
        new EvaluationExamPrinter());
    selectExam.show();
    final EvaluationExamDTO exam = selectExam.selectedElement();

    Iterable<AnswerDTO> grades = ctrl.evaluationExamGrades(exam);

    ListWidget<AnswerDTO> gradesList = new ListWidget<>("\n" + new AnswerPrinter().header(), grades,
        new AnswerPrinter());
    gradesList.show();

    return false;
  }

  @Override
  public String headline() {
    return "List Course Exam Grades";
  }
}
