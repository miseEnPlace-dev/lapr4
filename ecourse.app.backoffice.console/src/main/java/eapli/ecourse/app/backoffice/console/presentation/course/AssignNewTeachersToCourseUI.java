package eapli.ecourse.app.backoffice.console.presentation.course;

import eapli.ecourse.app.common.console.presentation.course.CoursePrinter;
import eapli.ecourse.app.common.console.presentation.teacher.TeacherPrinter;
import eapli.ecourse.app.common.console.util.MultipleSelectorWidget;
import eapli.ecourse.coursemanagement.application.AssignTeacherToCourseController;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.teachermanagement.dto.TeacherDTO;
import eapli.ecourse.teachermanagement.repositories.TeacherRepository;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class AssignNewTeachersToCourseUI extends AbstractUI {
  private final TeacherRepository teacherRepository = PersistenceContext.repositories().teachers();
  private final CourseRepository courseRepository = PersistenceContext.repositories().courses();

  private final AssignTeacherToCourseController controller = new AssignTeacherToCourseController(teacherRepository,
      courseRepository);

  @Override
  protected boolean doShow() {
    final Iterable<TeacherDTO> teachers = controller.allTeachers();

    if (!teachers.iterator().hasNext()) {
      System.out.println("There are no teachers to assign.");
      return false;
    }

    final MultipleSelectorWidget<TeacherDTO> teachersSelector = new MultipleSelectorWidget<>("Teachers", teachers,
        new TeacherPrinter());

    Iterable<TeacherDTO> selectedTeachers = teachersSelector.selectElements();

    final Iterable<CourseDTO> courses = controller.allNotClosedCourses();

    if (!courses.iterator().hasNext()) {
      System.out.println("There are no courses to assign.");
      return false;
    }

    final SelectWidget<CourseDTO> courseSelector = new SelectWidget<>("Courses", courses, new CoursePrinter());
    CourseDTO selectedCourse = courseSelector.selectedElement();
    try {
      controller.assignTeachersToCourse(selectedTeachers, selectedCourse);
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }

    return false;
  }

  @Override
  public String headline() {
    return "Add Teachers";
  }
}
