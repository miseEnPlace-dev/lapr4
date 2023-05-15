package eapli.ecourse.usermanagement.domain;

import eapli.framework.infrastructure.authz.domain.model.Role;

/**
 * @author Paulo Gandra Sousa
 *
 */
public final class ClientRoles {
  // Power User (technical role)
  public static final Role POWER_USER = Role.valueOf("POWER_USER");

  // Administrator
  public static final Role MANAGER = Role.valueOf("MANAGER");

  // Teacher
  public static final Role TEACHER = Role.valueOf("TEACHER");

  // Student
  public static final Role STUDENT = Role.valueOf("STUDENT");

  /**
   * Get available role types for collaborators.
   *
   * @return
   */
  public static Role[] nonUserValues() {
    return new Role[] { MANAGER, TEACHER, STUDENT };
  }
}
