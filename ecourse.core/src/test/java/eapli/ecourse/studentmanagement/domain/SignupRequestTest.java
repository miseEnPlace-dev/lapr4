package eapli.ecourse.studentmanagement.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.time.util.CurrentTimeCalendars;

/**
 * @author Paulo Gandra de Sousa 13/05/2022
 */
public class SignupRequestTest {

  private SignupRequest subject;

  @Before
  public void setUp() {
    subject = new SignupRequestBuilder(new NilPasswordPolicy(), new PlainTextEncoder())
        .withData("user", "pass", "a@b.com", "1234567").withName("Mary", "Smith")
        .createdOn(CurrentTimeCalendars.now()).build();
  }

  @Test
  public void ensureNewlyCreatedSignupRequestIsPending() {
    assertTrue(subject.isPending());
  }

  @Test
  public void ensureAceptedSignupRequestIsInProperState() {
    subject.accept();

    assertTrue(subject.isAccepted());
    assertFalse(subject.isPending());
    assertFalse(subject.isRefused());
  }

  @Test
  public void ensureREfusedSignupRequestIsInProperState() {
    subject.refuse();

    assertTrue(subject.isRefused());
    assertFalse(subject.isAccepted());
    assertFalse(subject.isPending());
  }
}
