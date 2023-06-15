package eapli.ecourse.app.student.console.presentation.exams;

import eapli.ecourse.app.common.console.presentation.course.CourseHeader;
import eapli.ecourse.app.common.console.presentation.course.CoursePrinter;
import eapli.ecourse.app.common.console.presentation.exam.EvaluationExamHeader;
import eapli.ecourse.app.common.console.presentation.exam.EvaluationExamPrinter;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.exammanagement.application.TakeEvaluationExamController;
import eapli.ecourse.exammanagement.application.exceptions.ParseException;
import eapli.ecourse.exammanagement.dto.EvaluationExamDTO;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class TakeEvaluationExamUI extends AbstractUI {
  private TakeEvaluationExamController ctrl = new TakeEvaluationExamController(AuthzRegistry.authorizationService(),
      PersistenceContext.repositories().students(), PersistenceContext.repositories().evaluationExams(),
      PersistenceContext.repositories().courses(), PersistenceContext.repositories().answers());

  @Override
  protected boolean doShow() {
    CourseDTO course;
    if ((course = selectCourse()) == null)
      return false;

    EvaluationExamDTO exam;
    if ((exam = selectExam(course)) == null)
      return false;

    if (this.ctrl.hasTakenExam(exam)) {
      System.out.println("\nYou have already taken this exam! Only one attempt is allowed.");
      Console.readLine("Press Enter to go back to main menu...");
      return false;
    }

    try {
      this.ctrl.parseExam(exam.getFileContent(), new ConsoleExamPrinter());
    } catch (ParseException ex) {
      System.out.println(ex.getMessage());
      Console.readLine("Press Enter to go back to main menu...");
      return false;
    }

    this.ctrl.saveAnswer(exam);

    System.out.println("\n\nExam taken successfully.");
    Console.readLine("Press Enter to go back to main menu...");

    return false;
  }

  @Override
  public String headline() {
    return "Take Evaluation Exam";
  }

  private CourseDTO selectCourse() {
    final Iterable<CourseDTO> courses = this.ctrl.listInProgressCoursesOfAuthenticatedStudent();

    if (!courses.iterator().hasNext()) {
      System.out.println("\nThere are no courses in progress available to you. Please contact the administrator");
      return null;
    }

    System.out.println("Select the course of the exam you want to take:");
    final SelectWidget<CourseDTO> selector = new SelectWidget<>(new CourseHeader().header(), courses,
        new CoursePrinter());
    selector.show();
    final CourseDTO selectedCourse = selector.selectedElement();

    if (selectedCourse == null)
      return null;

    return selectedCourse;
  }

  private EvaluationExamDTO selectExam(CourseDTO course) {
    final Iterable<EvaluationExamDTO> exams = this.ctrl.listOpenExamsForCourse(course);

    if (!exams.iterator().hasNext()) {
      System.out.println("\nThere are no currently open exams for the selected course.");
      return null;
    }

    System.out.println("\nSelect the exam you want to take:");
    final SelectWidget<EvaluationExamDTO> selector = new SelectWidget<>(new EvaluationExamHeader().header(), exams,
        new EvaluationExamPrinter());
    selector.show();
    final EvaluationExamDTO selectedExam = selector.selectedElement();
    System.out.println("");

    if (selectedExam == null)
      return null;

    return selectedExam;
  }
}
