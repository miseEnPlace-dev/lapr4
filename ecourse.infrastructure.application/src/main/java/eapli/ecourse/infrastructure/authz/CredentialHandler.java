package eapli.ecourse.infrastructure.authz;

import eapli.framework.infrastructure.authz.domain.model.Role;

/**
 *
 * @author Paulo Gandra de Sousa 2022.11.24
 */
public interface CredentialHandler {
  boolean authenticated(String username, String password, Role onlyWithThis);
}
