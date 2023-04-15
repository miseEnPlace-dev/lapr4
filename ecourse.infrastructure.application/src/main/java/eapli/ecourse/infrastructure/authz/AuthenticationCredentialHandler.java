package eapli.ecourse.infrastructure.authz;

import eapli.framework.infrastructure.authz.application.Authenticator;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;

/**
 *
 * @author Paulo Gandra de Sousa 2022.11.24
 *
 */
public class AuthenticationCredentialHandler implements CredentialHandler {

  private final Authenticator authenticationService = AuthzRegistry.authenticationService();

  @Override
  public boolean authenticated(String username, String password, Role onlyWithThis) {
    return authenticationService.authenticate(username, password, onlyWithThis).isPresent();
  }

}
