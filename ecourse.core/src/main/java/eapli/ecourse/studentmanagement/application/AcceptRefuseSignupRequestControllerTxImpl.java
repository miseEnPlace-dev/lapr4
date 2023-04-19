package eapli.ecourse.studentmanagement.application;

import java.util.HashSet;
import java.util.Set;

import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.studentmanagement.domain.SignupRequest;
import eapli.ecourse.studentmanagement.domain.StudentBuilder;
import eapli.ecourse.studentmanagement.repositories.SignupRequestRepository;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

/**
 * The transactional controller for the use case "accept/refuse a signup
 * request".
 * <p>
 * following the guideline that a controller should only change one Aggregate,
 * we shouldn't be
 * changing all these entities here, but should instead use asynchronous events.
 * However in this
 * case we will take advantage of TransactionalContext
 *
 * @todo handle the scenario where in the meantime the username is already used
 *       by some other user
 *
 * @author AJS on 08/04/2016.
 */
@UseCaseController
public class AcceptRefuseSignupRequestControllerTxImpl
    implements AcceptRefuseSignupRequestController {

  private final AuthorizationService authz = AuthzRegistry.authorizationService();
  private final UserManagementService userService = AuthzRegistry.userService();

  private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();
  private final StudentRepository clientUserRepository = PersistenceContext.repositories().students(txCtx);
  private final SignupRequestRepository signupRequestsRepository = PersistenceContext.repositories()
      .signupRequests(txCtx);

  /*
   * (non-Javadoc)
   *
   * @see eapli.base.clientusermanagement.application.
   * AcceptRefuseSignupRequestController#acceptSignupRequest(eapli.base.
   * clientusermanagement.domain.SignupRequest)
   */
  @Override
  public SignupRequest acceptSignupRequest(SignupRequest theSignupRequest) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);

    if (theSignupRequest == null) {
      throw new IllegalArgumentException();
    }

    // explicitly begin a transaction
    txCtx.beginTransaction();

    final SystemUser newUser = createSystemUserForStudent(theSignupRequest);
    createClientUser(theSignupRequest, newUser);
    theSignupRequest = acceptTheSignupRequest(theSignupRequest);

    // explicitly commit the transaction
    txCtx.commit();

    return theSignupRequest;
  }

  private SignupRequest acceptTheSignupRequest(final SignupRequest theSignupRequest) {
    theSignupRequest.accept();
    return this.signupRequestsRepository.save(theSignupRequest);
  }

  private void createClientUser(final SignupRequest theSignupRequest, final SystemUser newUser) {
    final var clientUserBuilder = new StudentBuilder();
    clientUserBuilder.withMecanographicNumber(theSignupRequest.mecanographicNumber())
        .withSystemUser(newUser);
    clientUserRepository.save(clientUserBuilder.build());
  }

  //
  // add system user
  //
  private SystemUser createSystemUserForStudent(final SignupRequest theSignupRequest) {
    final Set<Role> roles = new HashSet<>();
    roles.add(ClientRoles.STUDENT);
    return userService.registerUser(theSignupRequest.username(), theSignupRequest.password(),
        theSignupRequest.name(), theSignupRequest.email(), roles);
  }

  /*
   * (non-Javadoc)
   *
   * @see eapli.ecourse.clientusermanagement.application.
   * AcceptRefuseSignupRequestController#refuseSignupRequest(eapli.ecourse.
   * clientusermanagement.domain.SignupRequest)
   */
  @Override
  public SignupRequest refuseSignupRequest(SignupRequest theSignupRequest) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);

    if (theSignupRequest == null) {
      throw new IllegalArgumentException();
    }

    // explicitly begin a transaction
    txCtx.beginTransaction();

    theSignupRequest.refuse();
    theSignupRequest = signupRequestsRepository.save(theSignupRequest);

    // explicitly commit the transaction
    txCtx.commit();

    return theSignupRequest;
  }

  /*
   * (non-Javadoc)
   *
   * @see eapli.base.clientusermanagement.application.
   * AcceptRefuseSignupRequestController#listPendingSignupRequests()
   */
  @Override
  public Iterable<SignupRequest> listPendingSignupRequests() {
    return signupRequestsRepository.pendingSignupRequests();
  }
}
