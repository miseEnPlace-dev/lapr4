package eapli.ecourse.myclientuser.application;

import java.util.Calendar;

import eapli.ecourse.clientusermanagement.domain.SignupRequest;
import eapli.ecourse.clientusermanagement.domain.SignupRequestBuilder;
import eapli.ecourse.clientusermanagement.repositories.SignupRequestRepository;
import eapli.ecourse.infrastructure.authz.SimplePasswordHashEncoder;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.ClientPasswordPolicy;
import eapli.framework.application.UseCaseController;
import eapli.framework.time.util.CurrentTimeCalendars;

/**
 * This allows users to signup.
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
@UseCaseController
public class SignupController {

  private final SignupRequestRepository signupRequestRepository = PersistenceContext.repositories().signupRequests();

  public SignupRequest signup(final String username, final String password, final String firstName,
      final String lastName, final String email, final String mecanographicNumber,
      final Calendar createdOn) {

    // there is no need for authorization check in this method as even
    // unauthenticated users may request a signup

    final SignupRequestBuilder signupRequestBuilder = new SignupRequestBuilder(new ClientPasswordPolicy(),
        new SimplePasswordHashEncoder());

    signupRequestBuilder.withUsername(username).withPassword(password).withName(firstName, lastName)
        .withEmail(email).createdOn(createdOn).withMecanographicNumber(mecanographicNumber);

    final SignupRequest newSignupRequest = signupRequestBuilder.build();
    return this.signupRequestRepository.save(newSignupRequest);
  }

  public SignupRequest signup(final String username, final String password, final String firstName,
      final String lastName, final String email, final String mecanographicNumber) {

    return signup(username, password, firstName, lastName, email, mecanographicNumber,
        CurrentTimeCalendars.now());
  }
}
