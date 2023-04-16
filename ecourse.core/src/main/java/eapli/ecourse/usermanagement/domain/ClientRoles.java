package eapli.ecourse.usermanagement.domain;

import eapli.framework.infrastructure.authz.domain.model.Role;

/**
 * @author Paulo Gandra Sousa
 *
 */
public final class ClientRoles {
  /**
   * poweruser
   */
  public static final Role POWER_USER = Role.valueOf("POWER_USER");
  /**
   * Student
   */
  public static final Role CLIENT_USER = Role.valueOf("CLIENT_USER");
  /**
   * Administrator
   */
  public static final Role ADMIN = Role.valueOf("ADMIN");
  /**
   *
   */
  public static final Role MANAGER = Role.valueOf("MANAGER");
  /**
   *
   */
  public static final Role TEACHER = Role.valueOf("TEACHER");

  /**
   * get available role types for adding new users
   *
   * @return
   */
  public static Role[] nonUserValues() {
    return new Role[] { ADMIN, MANAGER, TEACHER };
  }
}
