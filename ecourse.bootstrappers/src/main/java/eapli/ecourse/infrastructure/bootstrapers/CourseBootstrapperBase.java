package eapli.ecourse.infrastructure.bootstrapers;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.coursemanagement.application.AssignTeacherToCourseController;
import eapli.ecourse.coursemanagement.application.CreateCourseController;
import eapli.ecourse.coursemanagement.application.ToggleCourseStatusController;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.infrastructure.bootstrapers.demo.CoursesBootstrapper;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.teachermanagement.dto.TeacherDTO;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class CourseBootstrapperBase {
  private static final Logger LOGGER = LogManager.getLogger(CoursesBootstrapper.class);

  private final CreateCourseController createCourseCtrl = new CreateCourseController(
      PersistenceContext.repositories().courses(), AuthzRegistry.authorizationService(),
      PersistenceContext.repositories().teachers());
  private final ToggleCourseStatusController toggleCourseStatusCtrl = new ToggleCourseStatusController(
      PersistenceContext.repositories().courses(), AuthzRegistry.authorizationService());
  private final AssignTeacherToCourseController assignTeacherToCourseController = new AssignTeacherToCourseController(
      PersistenceContext.repositories().teachers(), PersistenceContext.repositories().courses());

  public void createClosedCourse(final String code, final String title, final String description, final int min,
      final int max, final TeacherDTO teacher) {
    try {
      createCourseCtrl.createCourse(code, title, description, min, max, teacher);
    } catch (final Exception e) {
      LOGGER.warn("Assuming {} already exists (activate trace log for details)", code);
      LOGGER.trace("Assuming existing record", e);
    }
  }

  public void createOpenCourse(final String code, final String title, final String description, final int min,
      final int max, final TeacherDTO teacher) {
    try {
      Course c = createCourseCtrl.createCourse(code, title, description, min, max, teacher);
      toggleCourseStatusCtrl.toggleToNextCourseStatus(c.toDto());
    } catch (final Exception e) {
      LOGGER.warn("Assuming {} already exists (activate trace log for details)", code);
      LOGGER.trace("Assuming existing record", e);
      System.out.println(e);
    }
  }

  public CourseDTO createInProgressCourse(final String code, final String title, final String description,
      final int min,
      final int max, final TeacherDTO teacher) {
    try {
      Course c = createCourseCtrl.createCourse(code, title, description, min, max, teacher);
      toggleCourseStatusCtrl.toggleToNextCourseStatus(c.toDto());
      toggleCourseStatusCtrl.toggleToNextCourseStatus(c.toDto());
      return c.toDto();
    } catch (final Exception e) {
      LOGGER.warn("Assuming {} already exists (activate trace log for details)", code);
      LOGGER.trace("Assuming existing record", e);
      System.out.println(e);
    }
    return null;
  }

  public void assignTeacherToCourse(final CourseDTO course, final TeacherDTO teacher) {
    try {
      ArrayList<TeacherDTO> list = new ArrayList<TeacherDTO>();
      list.add(teacher);
      assignTeacherToCourseController.assignTeachersToCourse(list, course);
    } catch (final Exception e) {
      LOGGER.warn("Assuming {} already exists (activate trace log for details)", course.getCode());
      LOGGER.trace("Assuming existing record", e);
      System.out.println(e);
    }
  }
}
