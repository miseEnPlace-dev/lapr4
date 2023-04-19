package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.studentmanagement.domain.SignupRequest;
import eapli.ecourse.studentmanagement.repositories.SignupRequestRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public class InMemorySignupRequestRepository
    extends InMemoryDomainRepository<SignupRequest, Username> implements SignupRequestRepository {

  static {
    InMemoryInitializer.init();
  }

  @Override
  public Iterable<SignupRequest> pendingSignupRequests() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
