package eapli.ecourse.infrastructure.authz;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This is a better implementation of the PasswordEncoder interface. It uses a
 * one-way function to encode the password.
 *
 */
public class PasswordHashEncoder implements PasswordEncoder {
  private final int strength = 12;
  private final java.security.SecureRandom random = new java.security.SecureRandom();

  @Override
  public String encode(CharSequence rawPassword) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(strength, random);

    return encoder.encode(rawPassword);
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(strength, random);

    return encoder.matches(rawPassword, encodedPassword);
  }
}
