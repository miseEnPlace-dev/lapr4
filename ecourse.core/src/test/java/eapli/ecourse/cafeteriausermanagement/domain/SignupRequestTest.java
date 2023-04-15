package eapli.ecourse.clientusermanagement.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.time.util.CurrentTimeCalendars;

/**
 * @author Paulo Gandra de Sousa 13/05/2022
 */
class SignupRequestTest {

  private SignupRequest subject;

  @BeforeEach
  void setUp() {
    subject = new SignupRequestBuilder(new NilPasswordPolicy(), new PlainTextEncoder())
        .withData("user", "pass", "a@b.com", "1234567").withName("Mary", "Smith")
        .createdOn(CurrentTimeCalendars.now()).build();
  }

  @Test
  void ensureNewlyCreatedSignupRequestIsPending() {
    assertTrue(subject.isPending());
  }

  @Test
  void ensureAceptedSignupRequestIsInProperState() {
    subject.accept();

    assertTrue(subject.isAccepted());
    assertFalse(subject.isPending());
    assertFalse(subject.isRefused());
  }

  @Test
  void ensureREfusedSignupRequestIsInProperState() {
    subject.refuse();

    assertTrue(subject.isRefused());
    assertFalse(subject.isAccepted());
    assertFalse(subject.isPending());
  }
}
