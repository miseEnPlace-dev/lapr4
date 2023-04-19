package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.studentmanagement.domain.SignupRequest;
import eapli.ecourse.studentmanagement.repositories.SignupRequestRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaSignupRequestRepository extends JpaAutoTxRepository<SignupRequest, Username, Username>
    implements SignupRequestRepository {

  public JpaSignupRequestRepository(final TransactionalContext autoTx) {
    super(autoTx, "username");
  }

  public JpaSignupRequestRepository(final String puname) {
    super(puname, Application.settings().extendedPersistenceProperties(), "username");
  }

  @Override
  public Iterable<SignupRequest> pendingSignupRequests() {
    return match(
        "e.approvalStatus=eapli.ecourse.student.domain.ApprovalStatus.PENDING");
  }
}
