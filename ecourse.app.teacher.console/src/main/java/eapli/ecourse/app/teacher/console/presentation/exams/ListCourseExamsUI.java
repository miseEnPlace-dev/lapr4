package eapli.ecourse.app.teacher.console.presentation.exams;

import java.util.ArrayList;
import java.util.List;

import eapli.ecourse.app.common.console.presentation.course.CoursePrinter;
import eapli.ecourse.app.common.console.presentation.exam.ExamPrinter;
import eapli.ecourse.exammanagement.application.ListCourseExamsController;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.exammanagement.dto.ExamDTO;
import eapli.ecourse.exammanagement.repositories.ExamRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.presentation.console.SelectWidget;

public class ListCourseExamsUI extends AbstractUI {

  private final ListCourseExamsController ctrl = new ListCourseExamsController(AuthzRegistry.authorizationService(),
      PersistenceContext.repositories().courses(), PersistenceContext.repositories().exams());

  @Override
  protected boolean doShow() {
    Iterable<CourseDTO> courses = ctrl.listOpenInProgressCourses();
    List<CourseDTO> courseList = new ArrayList<>();
    courses.forEach(courseList::add);

    if (courseList.isEmpty()) {
      System.out.println("There are no courses available.");
      return false;
    }

    final SelectWidget<CourseDTO> selector = new SelectWidget<>("Courses:", courseList, new CoursePrinter());
    selector.show();
    final CourseDTO selected = selector.selectedElement();
    if (selected == null)
      return false;

    Iterable<ExamDTO> exams = ctrl.listCourseExams(selected);
    if (!exams.iterator().hasNext()) {
      System.out.println("There are no exams in " + selected.getTitle());
      return false;
    }

    ListWidget<ExamDTO> list = new ListWidget<>("Exams of " + selected.getTitle(), exams, new ExamPrinter());
    list.show();

    return true;
  }

  @Override
  public String headline() {
    return "List Exams In Course";
  }
}
