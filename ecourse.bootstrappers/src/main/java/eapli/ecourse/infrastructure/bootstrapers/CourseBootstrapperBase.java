package eapli.ecourse.infrastructure.bootstrapers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.coursemanagement.application.CreateCourseController;
import eapli.ecourse.coursemanagement.application.ToggleCourseStatusController;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.dto.TeacherDTO;
import eapli.ecourse.infrastructure.bootstrapers.demo.CoursesBootstrapper;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class CourseBootstrapperBase {
  private static final Logger LOGGER = LogManager.getLogger(CoursesBootstrapper.class);

  private final CreateCourseController createCourseCtrl = new CreateCourseController(
      PersistenceContext.repositories().courses(), AuthzRegistry.authorizationService(),
      PersistenceContext.repositories().teachers());
  private final ToggleCourseStatusController toggleCourseStatusCtrl = new ToggleCourseStatusController(
      PersistenceContext.repositories().courses(), AuthzRegistry.authorizationService());

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
      toggleCourseStatusCtrl.toggleCourseStatus(c.toDto());
    } catch (final Exception e) {
      LOGGER.warn("Assuming {} already exists (activate trace log for details)", code);
      LOGGER.trace("Assuming existing record", e);
      System.out.println(e);
    }
  }
}
