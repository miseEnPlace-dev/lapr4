package eapli.ecourse.clientusermanagement.application;

import eapli.ecourse.clientusermanagement.domain.SignupRequest;

/**
 * The controller for the use case "Accept or refuse signup request".
 * <p>
 * <p/>
 * Approving a signup request needs to create/change more than one aggregate instance, as such it
 * violates the rule that one controller should only create/change one aggregate instance.
 * <p>
 * <p/>
 * We provide two implementations of this controller for comparison of different approaches. The
 * (traditional) approach {@link AcceptRefuseSignupRequestControllerTxImpl} creates a wrapping
 * transaction and performs the needed steps inside that transaction. It makes it simple to develop
 * and understand but hardcodes the process and fails the Single Responsibility Principle (SRP) as
 * now this class will have more than one reason to change.
 * <p>
 * <p/>
 * The other approach uses events {@link AcceptRefuseSignupRequestControllerEventfulImpl} and
 * decouples the different steps of the process. This makes the system more flexible if there is the
 * need to change the actual process, enforces the SRP, but increases the complexity, e.g., there is
 * the need to have an event bus, there is no global status, if something fails in the middle of the
 * process it is not easy to compensate (rollback) and there is no temporal coupling so the system
 * needs to handle "waiting" for something to happen before proceeding (see
 * {@link AddClientUserOnSignupAcceptedController})
 *
 * @author Paulo Gandra de Sousa
 */
public interface AcceptRefuseSignupRequestController {

  SignupRequest acceptSignupRequest(SignupRequest theSignupRequest);

  SignupRequest refuseSignupRequest(SignupRequest theSignupRequest);

  Iterable<SignupRequest> listPendingSignupRequests();
}
