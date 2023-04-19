package eapli.ecourse.studentmanagement.application;

import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

/**
 *
 * @author losa
 */
public class ListClientUsersController {
  private final AuthorizationService authz = AuthzRegistry.authorizationService();

  private final StudentRepository repo = PersistenceContext.repositories().students();

  public Iterable<Student> activeClientUsers() {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);

    return this.repo.findAllActive();
  }
}
