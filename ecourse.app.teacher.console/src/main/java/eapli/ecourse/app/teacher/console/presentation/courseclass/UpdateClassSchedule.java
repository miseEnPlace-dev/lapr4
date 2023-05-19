package eapli.ecourse.app.teacher.console.presentation.courseclass;

import eapli.ecourse.app.common.console.presentation.courseclass.CourseClassPrinter;
import eapli.ecourse.eventsmanagement.courseclassmanagement.application.UpdateClassScheduleController;
import eapli.ecourse.eventsmanagement.courseclassmanagement.dto.ClassDTO;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class UpdateClassSchedule extends AbstractUI {

  private UpdateClassScheduleController controller = new UpdateClassScheduleController(
      PersistenceContext.repositories().classes(), AuthzRegistry.authorizationService(),
      PersistenceContext.repositories().meetings(), PersistenceContext.repositories().enrollments(),
      PersistenceContext.repositories().students());

  @Override
  protected boolean doShow() {

    final Iterable<ClassDTO> classes = this.controller.listAllClassesForTeacher();
    if (!classes.iterator().hasNext()) {
      System.out.println("There are no registered classes");
      return false;
    }

    final SelectWidget<ClassDTO> selector = new SelectWidget<>("\nTeachers:", classes, new CourseClassPrinter());
    selector.show();
    final ClassDTO selected = selector.selectedElement();
    return false;
  }

  @Override
  public String headline() {
    return "Update Class Schedule";
  }

}
