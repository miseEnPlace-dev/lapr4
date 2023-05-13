package eapli.ecourse.infrastructure.bootstrapers.demo;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.coursemanagement.application.CreateCourseController;
import eapli.ecourse.coursemanagement.application.ToggleCourseStatusController;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.dto.TeacherDTO;
import eapli.ecourse.infrastructure.bootstrapers.UsersBootstrapperBase;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.teachermanagement.domain.TaxPayerNumber;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class CoursesBootstrapper extends UsersBootstrapperBase implements Action {
  private static final Logger LOGGER = LogManager.getLogger(CoursesBootstrapper.class);
  private final CreateCourseController createCourseCtrl = new CreateCourseController(
      PersistenceContext.repositories().courses(), AuthzRegistry.authorizationService(),
      PersistenceContext.repositories().teachers());
  private final ToggleCourseStatusController toggleCourseStatusCtrl = new ToggleCourseStatusController(
      PersistenceContext.repositories().courses());

  private TeacherDTO registerDummyTeacher(final String username, final String password, final String firstName,
      final String lastName, final String email) {
    final Set<Role> roles = new HashSet<>();
    roles.add(ClientRoles.TEACHER);

    SystemUser u = registerUser(username, password, firstName, lastName, email, roles);

    final Teacher teacher = new Teacher(u, TaxPayerNumber.valueOf("123456789"));

    return teacher.toDto();
  }

  @Override
  public boolean execute() {
    TeacherDTO t = registerDummyTeacher("teacher1", "Password1", "John", "Doe", "john@doe.com");
    createClosedCourse("1234", "Matemática", "Matemática simples", 1, 20, t);
    createClosedCourse("4321", "ESINF", "Estruturas de Informação ", 5, 80, t);
    createOpenCourse("2222", "EAPLI", "Engenharia de Aplicações", 10, 200, t);

    return true;
  }

  private void createClosedCourse(final String acronym, final String name, final String description, final int min,
      final int max, final TeacherDTO teacher) {
    try {
      createCourseCtrl.createCourse(acronym, name, description, min, max, teacher);
    } catch (final Exception e) {
      LOGGER.warn("Assuming {} already exists (activate trace log for details)", acronym);
      LOGGER.trace("Assuming existing record", e);
    }
  }

  private void createOpenCourse(final String acronym, final String name, final String description, final int min,
      final int max, final TeacherDTO teacher) {
    try {
      Course c = createCourseCtrl.createCourse(acronym, name, description, min, max, teacher);
      toggleCourseStatusCtrl.toggleCourseStatus(c);
    } catch (final Exception e) {
      LOGGER.warn("Assuming {} already exists (activate trace log for details)", acronym);
      LOGGER.trace("Assuming existing record", e);
      System.out.println(e);
    }
  }
}
