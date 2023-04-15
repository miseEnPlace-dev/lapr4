package eapli.ecourse.infrastructure.authz;

import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * The problem with this implementation is that it is not a one-way function.
 * This means that if we have the encoded password, we can easily find the
 * original password. This is a problem because if an attacker gets access to
 * the database, he can easily find the passwords of all users.
 *
 * Other problem is that the hash function is not salted. This means that if two
 * users have the same password, they will have the same encoded password. This
 * is a problem because an attacker can easily find out if two users have the
 * same password.
 *
 * Collisions are also a problem. If two users have different passwords, but the
 * hash function returns the same value, then the attacker can easily find out
 * that the two users have different passwords.
 *
 * In simple terms, this is a very bad implementation of the PasswordEncoder
 */
public class SimplePasswordHashEncoder implements PasswordEncoder {
  @Override
  public String encode(CharSequence rawPassword) {
    return String.valueOf(rawPassword.hashCode());
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    return encode(rawPassword).equals(encodedPassword);
  }
}
