package eapli.ecourse.app.backoffice.console.presentation.course;

import java.util.Collection;

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

  @Override
  protected boolean doShow() {
    TeacherRepository teacherRepository = PersistenceContext.repositories().teachers();
    CourseRepository courseRepository = PersistenceContext.repositories().courses();

    AssignTeacherToCourseController controller = new AssignTeacherToCourseController(teacherRepository,
        courseRepository);

    final Iterable<CourseDTO> courses = controller.allNotFinishedCourses();

    if (!courses.iterator().hasNext()) {
      System.out.println("There are no courses to assign.");
      return false;
    }

    final SelectWidget<CourseDTO> courseSelector = new SelectWidget<>("Courses", courses, new CoursePrinter());
    courseSelector.show();
    CourseDTO selectedCourse = courseSelector.selectedElement();
    final Collection<TeacherDTO> teachers = controller.allTeachersExceptFromCourse(selectedCourse);

    if (!teachers.iterator().hasNext()) {
      System.out.println("There are no teachers to assign.");
      return false;
    }

    final MultipleSelectorWidget<TeacherDTO> teachersSelector = new MultipleSelectorWidget<>("\nTeachers:", teachers,
        new TeacherPrinter());

    final Iterable<TeacherDTO> selectedTeachers = teachersSelector.selectElements();

    try {
      controller.assignTeachersToCourse(selectedTeachers, selectedCourse);
      System.out.println("\n\nTeachers assigned to course successfully.");
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
