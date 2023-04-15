package eapli.ecourse.clientusermanagement.domain;

import java.util.Calendar;

import org.springframework.security.crypto.password.PasswordEncoder;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.application.PasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.Name;
import eapli.framework.infrastructure.authz.domain.model.Password;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.time.util.CurrentTimeCalendars;

/**
 * A factory for Signup Requests.
 * <p>
 * This class demonstrates the use of the factory (DDD) pattern using a fluent interface. it acts as
 * a Builder (GoF).
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
@SuppressWarnings("squid:S106")
public class SignupRequestBuilder implements DomainFactory<SignupRequest> {

  private final PasswordPolicy policy;
  private final PasswordEncoder encoder;

  private String username;
  private Password password;
  private String firstName;
  private String lastName;
  private String email;
  private String mecanographicNumber;
  private Calendar createdOn;

  public SignupRequestBuilder(final PasswordPolicy policy, final PasswordEncoder encoder) {
    this.policy = policy;
    this.encoder = encoder;
  }

  public SignupRequestBuilder withData(final String username, final String rawPassword,
      final String email, final String number) {
    withUsername(username);
    withPassword(rawPassword);
    withEmail(email);
    withMecanographicNumber(number);
    return this;
  }

  public SignupRequestBuilder withUsername(final String username) {
    this.username = username;
    return this;
  }

  public SignupRequestBuilder withPassword(final String rawPassword) {
    password = Password.encodedAndValid(rawPassword, policy, encoder)
        .orElseThrow(IllegalArgumentException::new);
    return this;
  }

  public SignupRequestBuilder withName(final String firstName, final String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
    return this;
  }

  public SignupRequestBuilder withEmail(final String email) {
    this.email = email;
    return this;
  }

  public SignupRequestBuilder withMecanographicNumber(final String mecanographicNumber) {
    this.mecanographicNumber = mecanographicNumber;
    return this;
  }

  public SignupRequestBuilder createdOn(final Calendar createdOn) {
    this.createdOn = createdOn;
    return this;
  }

  @Override
  public SignupRequest build() {
    // since the factory knows that all the parts are needed it could throw
    // an exception. however, we will leave that to the constructor
    if (createdOn != null) {
      createdOn = CurrentTimeCalendars.now();
    }
    return new SignupRequest(Username.valueOf(username), password,
        Name.valueOf(firstName, lastName), EmailAddress.valueOf(email),
        MecanographicNumber.valueOf(mecanographicNumber), createdOn);
  }
}
