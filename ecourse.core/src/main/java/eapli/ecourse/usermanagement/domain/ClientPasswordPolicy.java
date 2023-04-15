package eapli.ecourse.usermanagement.domain;

import eapli.framework.infrastructure.authz.application.PasswordPolicy;
import eapli.framework.strings.util.StringPredicates;

/**
 * Enforces that passwords must be at least 6 characters long and have at least one digit and one
 * capital letter.
 * <p>
 * Look into
 * <a href="https://documentation.cpanel.net/display/CKB/How+to+Determine+Password+Strength">cpanel
 * passowrd strength</a> for example rules of password strength
 *
 * @author Paulo Gandra de Sousa 24/05/2019
 */
public class ClientPasswordPolicy implements PasswordPolicy {

  /*
   * (non-Javadoc)
   *
   * @see eapli.framework.infrastructure.authz.domain.model.PasswordPolicy#
   * meetsRequeriments(java.lang.String)
   */
  @Override
  public boolean isSatisfiedBy(final String rawPassword) {
    // sanity check
    if (StringPredicates.isNullOrEmpty(rawPassword)) {
      return false;
    }

    // at least 6 characters long
    if (rawPassword.length() < 6) {
      return false;
    }

    // at least one digit
    if (!StringPredicates.containsDigit(rawPassword)) {
      return false;
    }

    // at least one capital letter
    return StringPredicates.containsCapital(rawPassword);
  }

  /**
   * Check how strong a password is. just for demo purposes.
   * <p>
   * look into https://documentation.cpanel.net/display/CKB/How+to+Determine+Password+Strength for
   * example rules of password strength
   *
   * @param rawPassword the string to check
   *
   * @return how strong a password is
   */
  public PasswordStrength strength(final String rawPassword) {
    PasswordStrength passwordStrength;
    if (rawPassword.length() >= 12
        || (rawPassword.length() >= 8 && StringPredicates.containsAny(rawPassword, "$#!%&?"))) {
      passwordStrength = PasswordStrength.EXCELENT;
    } else if (rawPassword.length() >= 8) {
      passwordStrength = PasswordStrength.GOOD;
    } else if (rawPassword.length() >= 6) {
      passwordStrength = PasswordStrength.WEAK;
    } else {
      passwordStrength = PasswordStrength.WEAK;
    }
    return passwordStrength;
  }

  public enum PasswordStrength {
    WEAK, GOOD, EXCELENT,
  }
}
