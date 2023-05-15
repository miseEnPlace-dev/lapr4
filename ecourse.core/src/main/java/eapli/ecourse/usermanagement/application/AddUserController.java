package eapli.ecourse.usermanagement.application;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.studentmanagement.domain.StudentBuilder;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.teachermanagement.domain.TeacherBuilder;
import eapli.ecourse.teachermanagement.repositories.TeacherRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.time.util.CurrentTimeCalendars;

@UseCaseController
public class AddUserController {
  private final AuthorizationService authz = AuthzRegistry.authorizationService();
  private final UserManagementService userSvc = AuthzRegistry.userService();
  private final StudentRepository studentRepository = PersistenceContext.repositories().students();
  private final TeacherRepository teacherRepository = PersistenceContext.repositories().teachers();

  /**
   * Get existing RoleTypes available to the user.
   *
   * @return a list of RoleTypes
   */
  public Role[] getRoleTypes() {
    return ClientRoles.nonUserValues();
  }

  public SystemUser addUser(final String username, final String password, final String firstName,
      final String lastName, final String email, final Set<Role> roles, final Calendar createdOn) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);

    return userSvc.registerNewUser(username, password, firstName, lastName, email, roles,
        createdOn);
  }

  public SystemUser addUser(final String username, final String password, final String firstName,
      final String lastName, final String email, final Set<Role> roles) {
    return addUser(username, password, firstName, lastName, email, roles,
        CurrentTimeCalendars.now());
  }

  public Student addStudent(final String username, final String password, final String firstName,
      final String lastName, final String email, final String mecanographicNumber) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);
    Set<Role> roles = new HashSet<Role>();
    roles.add(ClientRoles.STUDENT);

    SystemUser u = addUser(username, password, firstName, lastName, email, roles, CurrentTimeCalendars.now());
    Student s = new StudentBuilder().withSystemUser(u).withMecanographicNumber(mecanographicNumber).build();

    return studentRepository.save(s);
  }

  public Teacher addTeacher(final String username, final String password, final String firstName,
      final String lastName, final String email, final String taxPayerNumber, final String birthDate,
      final String acronym) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);
    Set<Role> roles = new HashSet<Role>();
    roles.add(ClientRoles.TEACHER);

    SystemUser u = addUser(username, password, firstName, lastName, email, roles, CurrentTimeCalendars.now());
    Teacher s = new TeacherBuilder().withSystemUser(u).withTaxPayerNumber(taxPayerNumber).withBirthDate(birthDate)
        .withAcronym(acronym).build();

    return teacherRepository.save(s);

  }
}
