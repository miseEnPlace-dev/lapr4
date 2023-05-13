package eapli.ecourse.infrastructure.bootstrapers.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.coursemanagement.application.CreateCourseController;
import eapli.ecourse.coursemanagement.application.ToggleCourseStatusController;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class CoursesBootstrapper implements Action {
  private static final Logger LOGGER = LogManager.getLogger(CoursesBootstrapper.class);
  private final CreateCourseController createCourseCtrl = new CreateCourseController(
      PersistenceContext.repositories().courses(), AuthzRegistry.authorizationService());
  private final ToggleCourseStatusController toggleCourseStatusCtrl = new ToggleCourseStatusController(
      PersistenceContext.repositories().courses());

  @Override
  public boolean execute() {
    createClosedCourse("1234", "Matemática", "Matemática simples", 1, 20);
    createClosedCourse("4321", "ESINF", "Estruturas de Informação ", 5, 80);
    createOpenCourse("2222", "EAPLI", "Engenharia de Aplicações", 10, 200);

    return true;
  }

  private void createClosedCourse(final String acronym, final String name, final String description, final int min,
      final int max) {
    try {
      createCourseCtrl.createCourse(acronym, name, description, min, max);
    } catch (final Exception e) {
      LOGGER.warn("Assuming {} already exists (activate trace log for details)", acronym);
      LOGGER.trace("Assuming existing record", e);
    }
  }

  private void createOpenCourse(final String acronym, final String name, final String description, final int min,
      final int max) {
    try {
      Course c = createCourseCtrl.createCourse(acronym, name, description, min, max);
      toggleCourseStatusCtrl.toggleCourseStatus(c);
    } catch (final Exception e) {
      LOGGER.warn("Assuming {} already exists (activate trace log for details)", acronym);
      LOGGER.trace("Assuming existing record", e);
      System.out.println(e);
    }
  }
}
