package eapli.ecourse.studentmanagement.domain.events;

import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.framework.domain.events.DomainEventBase;
import eapli.framework.infrastructure.authz.domain.model.Username;

/**
 * @author Paulo Gandra de Sousa
 *
 */
public class NewUserRegisteredFromSignupEvent extends DomainEventBase {

  private static final long serialVersionUID = 1L;

  private final MecanographicNumber mecanographicNumber;
  private final Username newUser;

  public NewUserRegisteredFromSignupEvent(final MecanographicNumber mecanographicNumber,
      final Username newUser) {
    this.mecanographicNumber = mecanographicNumber;
    this.newUser = newUser;
  }

  public MecanographicNumber mecanographicNumber() {
    return mecanographicNumber;
  }

  public Username username() {
    return newUser;
  }

  @Override
  public String toString() {
    return "NewUserFromsignup(" + username() + ")";
  }
}
