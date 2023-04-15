package eapli.ecourse.usermanagement.domain;

import eapli.ecourse.infrastructure.authz.SimplePasswordHashEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.util.Utility;

/**
 *
 * @author Paulo Gandra de Sousa 27/05/2019
 */
@Utility
public class UserBuilderHelper {
  private UserBuilderHelper() {
    // ensure utility
  }

  public static SystemUserBuilder builder() {
    return new SystemUserBuilder(new ClientPasswordPolicy(), new SimplePasswordHashEncoder());
  }
}
