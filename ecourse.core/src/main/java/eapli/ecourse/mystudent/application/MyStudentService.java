package eapli.ecourse.mystudent.application;

import java.util.Optional;

import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

/**
 *
 * @author Paulo Gandra de Sousa
 */
@ApplicationService
public class MyStudentService {

  private final AuthorizationService authz = AuthzRegistry.authorizationService();
  private final StudentRepository clientUsersRepo = PersistenceContext.repositories().students();

  public Student me() {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.STUDENT);

    // TODO cache the client user object
    final Optional<Student> me = clientUsersRepo.findByUsername(myUser().identity());

    return me.orElseThrow(IllegalStateException::new);
  }

  private SystemUser myUser() {
    return authz.session().map(UserSession::authenticatedUser)
        .orElseThrow(IllegalStateException::new);
  }
}
