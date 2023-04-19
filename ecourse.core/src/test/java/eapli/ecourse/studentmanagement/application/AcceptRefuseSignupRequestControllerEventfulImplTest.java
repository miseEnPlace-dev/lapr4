package eapli.ecourse.studentmanagement.application;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import eapli.ecourse.studentmanagement.domain.SignupRequest;
import eapli.ecourse.studentmanagement.domain.SignupRequestBuilder;
import eapli.ecourse.studentmanagement.domain.events.SignupAcceptedEvent;
import eapli.ecourse.studentmanagement.repositories.SignupRequestRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.pubsub.EventPublisher;
import eapli.framework.time.util.CurrentTimeCalendars;

/**
 * @author Paulo Gandra de Sousa 13/05/2022
 */
class AcceptRefuseSignupRequestControllerEventfulImplTest {

  private AcceptRefuseSignupRequestControllerEventfulImpl subject;
  private SignupRequestRepository signupRequestsRepository;
  private AuthorizationService authorizationService;
  private EventPublisher dispatcher;

  @BeforeEach
  void setUp() {
    signupRequestsRepository = mock(SignupRequestRepository.class);
    authorizationService = mock(AuthorizationService.class);
    dispatcher = mock(EventPublisher.class);

    subject = new AcceptRefuseSignupRequestControllerEventfulImpl(signupRequestsRepository,
        authorizationService, dispatcher);
  }

  @Test
  void ensureSignupRequestIsAcceptedAndSignupEventIsPublished() {
    final SignupRequest req = new SignupRequestBuilder(new NilPasswordPolicy(), new PlainTextEncoder())
        .withData("user", "pass", "a@b.com", "1234567").withName("Mary", "Smith")
        .createdOn(CurrentTimeCalendars.now()).build();

    when(signupRequestsRepository.save(req)).thenReturn(req);

    final var ret = subject.acceptSignupRequest(req);

    assertNotNull(ret);
    assertTrue(ret.isAccepted());
    verify(dispatcher).publish(ArgumentMatchers.any(SignupAcceptedEvent.class));
  }
}
