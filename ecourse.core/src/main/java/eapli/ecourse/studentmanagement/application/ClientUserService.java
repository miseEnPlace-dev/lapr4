package eapli.ecourse.studentmanagement.application;

import java.util.Optional;

import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Username;

/**
 * @author mcn
 */
@ApplicationService
public class ClientUserService {

  private final AuthorizationService authz = AuthzRegistry.authorizationService();
  private final StudentRepository repo = PersistenceContext.repositories().students();

  public Optional<Student> findClientUserByMecNumber(final String mecNumber) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);
    return repo.ofIdentity(MecanographicNumber.valueOf(mecNumber));
  }

  public Optional<Student> findClientUserByUsername(final Username user) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);
    return repo.findByUsername(user);
  }
}
