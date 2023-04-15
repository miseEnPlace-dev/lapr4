package eapli.ecourse.clientusermanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

/**
 * A factory for User entities.
 *
 * This class demonstrates the use of the factory (DDD) pattern using a fluent interface. it acts as
 * a Builder (GoF).
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public class ClientUserBuilder implements DomainFactory<ClientUser> {

  private SystemUser systemUser;
  private MecanographicNumber mecanographicNumber;

  public ClientUserBuilder withSystemUser(final SystemUser systemUser) {
    this.systemUser = systemUser;
    return this;
  }

  public ClientUserBuilder withMecanographicNumber(final MecanographicNumber mecanographicNumber) {
    this.mecanographicNumber = mecanographicNumber;
    return this;
  }

  public ClientUserBuilder withMecanographicNumber(final String mecanographicNumber) {
    this.mecanographicNumber = new MecanographicNumber(mecanographicNumber);
    return this;
  }

  @Override
  public ClientUser build() {
    // since the factory knows that all the parts are needed it could throw
    // an exception. however, we will leave that to the constructor
    return new ClientUser(this.systemUser, this.mecanographicNumber);
  }
}
