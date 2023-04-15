package eapli.ecourse.infrastructure.authz;

import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * The problem with this implementation is that it is not a one-way function.
 * This means that if we have the encoded password, we can easily find the
 * original password. This is a problem because if an attacker gets access to
 * the database, he can easily find the passwords of all users.
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
