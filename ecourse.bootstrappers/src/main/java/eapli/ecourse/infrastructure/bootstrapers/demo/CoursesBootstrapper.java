package eapli.ecourse.infrastructure.bootstrapers.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.coursemanagement.application.CreateCourseController;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class CoursesBootstrapper implements Action {
  private static final Logger LOGGER = LogManager.getLogger(CoursesBootstrapper.class);
  private final CreateCourseController controller = new CreateCourseController(
      PersistenceContext.repositories().courses(), AuthzRegistry.authorizationService());

  @Override
  public boolean execute() {
    controller.createCourse("1234", "Matemática", "Matemática simples", 1, 20);
    controller.createCourse("4321", "ESINF", "Estruturas de Informação ", 5, 80);
    controller.createCourse("2222", "EAPLI", "Engenharia de Aplicações", 10, 200);

    return true;
  }
}
