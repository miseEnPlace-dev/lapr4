package eapli.ecourse.studentmanagement.repositories;

import eapli.ecourse.studentmanagement.domain.SignupRequest;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

/**
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public interface SignupRequestRepository extends DomainRepository<Username, SignupRequest> {

  Iterable<SignupRequest> pendingSignupRequests();
}
