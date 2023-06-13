package eapli.ecourse.app.student.console.presentation.exams;

import eapli.ecourse.app.common.console.presentation.course.CourseHeader;
import eapli.ecourse.app.common.console.presentation.course.CoursePrinter;
import eapli.ecourse.app.common.console.presentation.exam.FormativeExamRequestHeader;
import eapli.ecourse.app.common.console.presentation.exam.FormativeExamRequestPrinter;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.exammanagement.application.TakeFormativeExamController;
import eapli.ecourse.exammanagement.dto.FormativeExamRequestDTO;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class TakeFormativeExamUI extends AbstractUI {

  private TakeFormativeExamController ctrl = new TakeFormativeExamController(AuthzRegistry.authorizationService(),
      PersistenceContext.repositories().students(), PersistenceContext.repositories().courses(),
      PersistenceContext.repositories().formativeExamRequests());

  @Override
  public boolean doShow() {
    final Iterable<CourseDTO> courses = this.ctrl.listInProgressCoursesOfAuthenticatedStudent();

    if (!courses.iterator().hasNext()) {
      System.out.println("There are no courses in progress available to you. Please contact the administrator");
      return false;
    }

    System.out.println("Select the course of the exam you want to take:");
    final SelectWidget<CourseDTO> selectorC = new SelectWidget<>(new CourseHeader().header(), courses,
        new CoursePrinter());
    selectorC.show();
    final CourseDTO selectedCourse = selectorC.selectedElement();

    if (selectedCourse == null)
      return false;

    final Iterable<FormativeExamRequestDTO> formativeRequests = this.ctrl.listFormativeExamsRequest(selectedCourse);

    if (!formativeRequests.iterator().hasNext()) {
      System.out.println("There are no defined structures of formative exams in this Course.");
      return false;
    }

    System.out.println("Select a formative exam you want to take:");
    final SelectWidget<FormativeExamRequestDTO> selectorF = new SelectWidget<>(
        new FormativeExamRequestHeader().header(),
        formativeRequests, new FormativeExamRequestPrinter());
    selectorF.show();
    final FormativeExamRequestDTO selectedFormativeExamRequest = selectorF.selectedElement();

    if (selectedFormativeExamRequest == null)
      return false;

    return false;
  }

  @Override
  public String headline() {
    return "Take Formative Exam";
  }

}