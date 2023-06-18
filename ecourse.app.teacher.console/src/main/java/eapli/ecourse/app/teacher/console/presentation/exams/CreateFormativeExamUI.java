package eapli.ecourse.app.teacher.console.presentation.exams;

import java.io.IOException;

import eapli.ecourse.app.common.console.presentation.course.CoursePrinter;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.exammanagement.application.CreateFormativeExamController;
import eapli.ecourse.exammanagement.application.exceptions.ParseException;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class CreateFormativeExamUI extends AbstractUI {

  @Override
  protected boolean doShow() {
    CreateFormativeExamController ctrl = new CreateFormativeExamController(AuthzRegistry.authorizationService(),
        PersistenceContext.repositories().teachers(), PersistenceContext.repositories().courses(),
        PersistenceContext.repositories().questions(), PersistenceContext.repositories().formativeExams());
    final Iterable<CourseDTO> courses = ctrl.listInProgressCoursesOfAuthenticatedTeacher();

    if (!courses.iterator().hasNext()) {
      System.out.println("There are no courses available to you. Please contact the administrator");
      return false;
    }

    System.out.println("Select the course where the exam will be created:");
    final SelectWidget<CourseDTO> selector = new SelectWidget<>(new CoursePrinter().header(), courses,
        new CoursePrinter());
    selector.show();
    final CourseDTO selectedCourse = selector.selectedElement();

    if (selectedCourse == null)
      return false;

    String filePath = Console.readLine("Enter the file path where the exam is defined: ");

    try {
      ctrl.parseExam(filePath);
    } catch (IOException ex) {
      System.out.println("\n\nThe specified file does not exist.");
      return false;
    } catch (ParseException ex) {
      System.out.println(ex.getMessage());
      return false;
    }

    System.out.println("\n\nChecked structure successfully!\n\n");

    try {
      ctrl.createExam(selectedCourse);
    } catch (IllegalArgumentException ex) {
      System.out.println(ex.getMessage());
      return false;
    }

    System.out.println("\n\nExam created successfully.");

    return false;
  }

  @Override
  public String headline() {
    return "Create Formative Exam";
  }

}
