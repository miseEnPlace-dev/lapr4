package eapli.ecourse.infrastructure.bootstrapers.demo;

import java.util.HashSet;
import java.util.Set;

import eapli.ecourse.coursemanagement.dto.TeacherDTO;
import eapli.ecourse.infrastructure.bootstrapers.CourseBootstrapperBase;
import eapli.ecourse.infrastructure.bootstrapers.UsersBootstrapperBase;
import eapli.ecourse.teachermanagement.domain.TaxPayerNumber;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class CoursesBootstrapper extends UsersBootstrapperBase implements Action {
  CourseBootstrapperBase courseBootstrapperBase = new CourseBootstrapperBase();

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
    courseBootstrapperBase.createClosedCourse("1234", "Matemática", "Matemática simples", 1, 20, t);
    courseBootstrapperBase.createClosedCourse("4321", "ESINF", "Estruturas de Informação ", 5, 80, t);
    courseBootstrapperBase.createOpenCourse("2222", "EAPLI", "Engenharia de Aplicações", 10, 200, t);

    return true;
  }

}
