package eapli.ecourse.app.teacher.console.presentation.exams;


import eapli.ecourse.app.common.console.presentation.course.CoursePrinter;
// import eapli.ecourse.app.common.console.presentation.exam.ExamPrinter;
// import eapli.ecourse.coursemanagement.application.ListCourseExamsController;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.eventsmanagement.classmanagement.application.ScheduleClassController;
// import eapli.ecourse.eventsmanagement.classmanagement.repositories.ClassRepository;
// import eapli.ecourse.exammanagement.domain.dto.ExamDTO;
// import eapli.ecourse.exammanagement.domain.repositories.ExamRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.presentation.console.SelectWidget;

import java.util.ArrayList;
import java.util.List;

public class ScheduleClassUI extends AbstractUI {

  // private final CourseRepository courseRepository = PersistenceContext.repositories().courses();
  // private final ClassRepository classRepository = PersistenceContext.repositories().classes();

  // private final ScheduleClassController ctrl = new ScheduleClassController(
  // AuthzRegistry.authorizationService(), courseRepository, classRepository);

  @Override
  protected boolean doShow() {
    // Iterable<CourseDTO> courses = ctrl.listOpenInProgressCourses();
    // List<CourseDTO> courseList = new ArrayList<>();
    // courses.forEach(courseList::add);

    // if (courseList.isEmpty()) {
    // System.out.println("There are no courses available.");
    // return false;
    // }

    // final SelectWidget<CourseDTO> selector =
    // new SelectWidget<>("Courses:", courseList, new CoursePrinter());
    // selector.show();
    // final CourseDTO selected = selector.selectedElement();
    // if (selected == null)
    // return false;

    // Iterable<ExamDTO> exams = ctrl.listCourseExams(selected);
    // System.out.println(exams.iterator().hasNext());

    // ListWidget<ExamDTO> list =
    // new ListWidget<>("Exams of " + selected.getTitle(), exams, new ExamPrinter());
    // list.show();


    return true;
  }

  @Override
  public String headline() {
    return "Schedule a Class";
  }
}
