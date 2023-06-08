// package eapli.ecourse.app.student.console.presentation.exams;

// import java.io.IOException;

// import eapli.ecourse.app.common.console.presentation.course.CourseHeader;
// import eapli.ecourse.app.common.console.presentation.course.CoursePrinter;
// import eapli.ecourse.coursemanagement.dto.CourseDTO;
// import eapli.ecourse.eventsmanagement.domain.Time;
// import eapli.ecourse.exammanagement.application.TakeEvaluationExamController;
// import eapli.ecourse.exammanagement.application.exceptions.ParseException;
// import eapli.ecourse.infrastructure.persistence.PersistenceContext;
// import eapli.framework.infrastructure.authz.application.AuthzRegistry;
// import eapli.framework.io.util.Console;
// import eapli.framework.presentation.console.AbstractUI;
// import eapli.framework.presentation.console.SelectWidget;

// public class TakeEvaluationExamUI extends AbstractUI {
// private TakeEvaluationExamController ctrl = new
// TakeEvaluationExamController(AuthzRegistry.authorizationService(),
// PersistenceContext.repositories().teachers()

// @Override
// protected boolean doShow() {

// final Iterable<CourseDTO> courses =
// this.ctrl.listInProgressCoursesOfAuthenticatedTeacher();

// if (!courses.iterator().hasNext()) {
// System.out.println("There are no courses available to you. Please contact the
// administrator");
// return false;
// }

// System.out.println("Select the course where the exam will be created:");
// final SelectWidget<CourseDTO> selector = new SelectWidget<>(new
// CourseHeader().header(), courses,
// new CoursePrinter());
// selector.show();
// final CourseDTO selectedCourse = selector.selectedElement();

// if (selectedCourse == null)
// return false;

// String filePath = Console.readLine("Enter the file path where the exam is
// defined: ");

// try {
// this.ctrl.parseExam(filePath);
// } catch (IOException ex) {
// System.out.println("The specified file does not exist.");
// return false;
// } catch (ParseException ex) {
// System.out.println(ex.getMessage());
// return false;
// }

// Time startTime = Time.valueOf(
// Console.readCalendar("Enter the start date/time for the exam (dd/mm/yyyy
// hh:mm): ", "dd/MM/yyyy HH:mm"));

// Time endTime = Time.valueOf(
// Console.readCalendar("Enter the end date/time for the exam (dd/mm/yyyy
// hh:mm): ", "dd/MM/yyyy HH:mm"));

// try {
// this.ctrl.createExam(selectedCourse, startTime, endTime);
// } catch (Exception ex) {
// System.out.println("\n\nAn error occurred while creating the exam.");
// return false;
// }

// System.out.println("\n\nExam created successfully.");

// return false;
// }

// @Override
// public String headline() {
// return "Create Exam";
// }
// }