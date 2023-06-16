package eapli.ecourse.app.student.console.presentation.courses;

import eapli.ecourse.app.common.console.presentation.course.CoursePrinter;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.enrolmentmanagement.application.RequestEnrolmentController;
import eapli.ecourse.enrolmentmanagement.repositories.EnrolmentRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class RequestEnrolmentUI extends AbstractUI {
  private final CourseRepository courseRepository = PersistenceContext.repositories().courses();
  private final EnrolmentRepository enrolmentRepository = PersistenceContext.repositories().enrollments();
  private final StudentRepository studentRepository = PersistenceContext.repositories().students();
  private final AuthorizationService authz = AuthzRegistry.authorizationService();

  private final RequestEnrolmentController controller = new RequestEnrolmentController(courseRepository,
      enrolmentRepository, studentRepository, authz);

  @Override
  protected boolean doShow() {
    Iterable<CourseDTO> courses = controller.listOpenForEnrolmentCourses();

    if (courses.spliterator().getExactSizeIfKnown() == 0) {
      System.out.println("No courses available for enrolment");
      return false;
    }

    final SelectWidget<CourseDTO> selector = new SelectWidget<>(new CoursePrinter().header(), courses,
        new CoursePrinter());
    selector.show();
    final CourseDTO selected = selector.selectedElement();

    if (selected == null) {
      System.out.println("No course selected");
      return false;
    }

    try {
      controller.requestEnrolment(selected);
    } catch (IllegalStateException e) {
      System.out.println("\n\n" + e.getMessage());
      return false;
    }

    System.out.println("\n\nEnrolment requested successfully");

    return true;
  }

  @Override
  public String headline() {
    return "Request Enrolment";
  }

}
