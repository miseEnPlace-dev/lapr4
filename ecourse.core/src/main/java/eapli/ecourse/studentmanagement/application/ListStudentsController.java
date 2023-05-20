package eapli.ecourse.studentmanagement.application;

import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.studentmanagement.dto.StudentDTO;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

/**
 *
 * @author losa
 */
public class ListStudentsController {
  private final AuthorizationService authz = AuthzRegistry.authorizationService();

  private final StudentRepository repo = PersistenceContext.repositories().students();
  private final StudentService service = new StudentService();

  public Iterable<Student> activeStudents() {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);

    return this.repo.findAllActive();
  }

  public Iterable<StudentDTO> allStudents() {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);

    return service.findAll();
  }
}
