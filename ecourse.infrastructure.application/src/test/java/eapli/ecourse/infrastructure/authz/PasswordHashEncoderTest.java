package eapli.ecourse.infrastructure.authz;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordHashEncoderTest {
  private PasswordEncoder encoder;

  @Before
  public void setUp() {
    encoder = new PasswordHashEncoder();
  }

  @Test
  public void ensurePasswordIsEncoded() {
    final String password = "password";
    final String encodedPassword = encoder.encode(password);
    assertNotEquals(password, encodedPassword);
  }

  @Test
  public void ensurePasswordIsEncodedAndMatches() {
    final String password = "password";
    final String encodedPassword = encoder.encode(password);
    assertNotEquals(password, encodedPassword);
    assertTrue(encoder.matches(password, encodedPassword));
  }

  @Test
  public void ensurePasswordIsEncodedAndDoesNotMatch() {
    final String password = "password";
    final String encodedPassword = encoder.encode(password);
    assertNotEquals(password, encodedPassword);
    assertFalse(encoder.matches("wrongPassword", encodedPassword));
  }

  @Test
  public void ensureHashingIsOneWay() {
    final String password = "password";
    final String encodedPassword = encoder.encode(password);
    final String encodedPassword2 = encoder.encode(password);
    assertNotEquals(password, encodedPassword);
    assertNotEquals(encodedPassword, encodedPassword2);
    assertTrue(encoder.matches(password, encodedPassword));
    assertTrue(encoder.matches(password, encodedPassword2));
  }
}
