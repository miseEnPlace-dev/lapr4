package eapli.ecourse.infrastructure.authz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SimplePasswordHashEncoderTest {
  private PasswordEncoder encoder;

  @Before
  public void setup() {
    encoder = new SimplePasswordHashEncoder();
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
  public void ensurePasswordIsNotOneWay() {
    final String password = "password";
    final String encodedPassword = encoder.encode(password);
    final String encodedPassword2 = encoder.encode(password);
    assertEquals(encodedPassword, encodedPassword2);
    assertTrue(encoder.matches(password, encodedPassword));
    assertTrue(encoder.matches(password, encodedPassword2));
  }
}
