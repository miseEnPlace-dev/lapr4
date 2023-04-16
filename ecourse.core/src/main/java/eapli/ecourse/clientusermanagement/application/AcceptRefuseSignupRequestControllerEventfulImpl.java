package eapli.ecourse.clientusermanagement.application;

import eapli.ecourse.clientusermanagement.domain.SignupRequest;
import eapli.ecourse.clientusermanagement.domain.events.SignupAcceptedEvent;
import eapli.ecourse.clientusermanagement.repositories.SignupRequestRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.domain.events.DomainEvent;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.pubsub.EventPublisher;
import eapli.framework.validations.Preconditions;
import lombok.RequiredArgsConstructor;

/**
 * The controller for the use case "Accept or refuse signup request".
 * <p>
 * This implementation makes use of domain events to (1) follow the rule that one controller should
 * only modify one aggregate, and (2) notify other parts of the system to react accordingly. For an
 * alternative transactional approach see {@link AcceptRefuseSignupRequestControllerTxImpl}.
 * </p>
 * <p>
 * In order to follow an eventful approach, first it is necessary to register the event handlers
 * ("watch dogs").
 * </p>
 * <p>
 * <img src="seq-init.svg">
 * </p>
 * <p>
 * Afterwards, during the use case execution, the actual three steps are divided and executed in
 * separate transactions (and eventually threads) according to the publishing of events. The first
 * step is "Accept the signup".
 * </p>
 * <p>
 * <img src="seq-accept-sign-up.svg">
 * </p>
 * <p>
 * The second step is "Register System User".
 * </p>
 * <p>
 * <img src="seq-register-system-user.svg">
 * </p>
 * <p>
 * The third step is "Create Client User".
 * </p>
 * <p>
 * <img src="seq-create-client-user.svg">
 * </p>
 * <p>
 * All the steps are shown in the following sequence diagram.
 * </p>
 * <p>
 * <img src="seq-use-case.svg">
 * </p>
 * <!--
 *
 * @startuml seq-init.svg title Subscribe event handlers participant App participant dispatcher
 *           participant SignupAcceptedWatchDog create SignupAcceptedWatchDog App ->
 *           SignupAcceptedWatchDog : new App -> dispatcher: subscribe(signupWatchDog) create
 *           NewUserRegisteredFromSignupWatchDog App -> NewUserRegisteredFromSignupWatchDog:new App
 *           -> dispatcher: subscribe(newUserWatchDog)
 *
 * @enduml --> <!--
 *
 * @startuml seq-use-case.svg title Full Use case execution participant dispatcher participant UI
 *           participant AcceptRefuseSignupRequestController participant SignupRepository
 *           participant SignupAcceptedEvent participant SignupAcceptedWatchDog participant
 *           AddUserOnSignupAcceptedController participant UserRepository participant
 *           NewUserRegisteredFromSignupEvent participant NewUserRegisteredFromSignupWatchDog
 *           participant AddClientUserOnSignupAcceptedController participant ClientUserRepository
 *           group Accept the signup UI -> AcceptRefuseSignupRequestController:acceptSignupRequest
 *           activate AcceptRefuseSignupRequestController AcceptRefuseSignupRequestController ->
 *           SignupRepository:save create SignupAcceptedEvent AcceptRefuseSignupRequestController ->
 *           "SignupAcceptedEvent":new AcceptRefuseSignupRequestController -> dispatcher:publish
 *           deactivate AcceptRefuseSignupRequestController end group Register system user
 *           dispatcher -> SignupAcceptedWatchDog: onEvent activate SignupAcceptedWatchDog
 *           SignupAcceptedWatchDog -> AddUserOnSignupAcceptedController:addUser activate
 *           AddUserOnSignupAcceptedController AddUserOnSignupAcceptedController ->
 *           UserRepository:save create NewUserRegisteredFromSignupEvent
 *           AddUserOnSignupAcceptedController -> NewUserRegisteredFromSignupEvent:new
 *           AddUserOnSignupAcceptedController -> dispatcher:publish deactivate
 *           AddUserOnSignupAcceptedController deactivate SignupAcceptedWatchDog end group Create
 *           client user dispatcher -> NewUserRegisteredFromSignupWatchDog:onEvent activate
 *           NewUserRegisteredFromSignupWatchDog NewUserRegisteredFromSignupWatchDog ->
 *           AddClientUserOnSignupAcceptedController:addClientUser activate
 *           AddClientUserOnSignupAcceptedController AddClientUserOnSignupAcceptedController ->
 *           ClientUserRepository:save deactivate AddClientUserOnSignupAcceptedController deactivate
 *           NewUserRegisteredFromSignupWatchDog end
 *
 * @enduml --> <!--
 *
 * @startuml seq-accept-sign-up.svg title Accept the Signup participant dispatcher participant UI
 *           participant AcceptRefuseSignupRequestController participant SignupRepository
 *           participant SignupAcceptedEvent group Accept the signup UI ->
 *           AcceptRefuseSignupRequestController:acceptSignupRequest activate
 *           AcceptRefuseSignupRequestController AcceptRefuseSignupRequestController ->
 *           SignupRepository:save create SignupAcceptedEvent AcceptRefuseSignupRequestController ->
 *           "SignupAcceptedEvent":new AcceptRefuseSignupRequestController -> dispatcher:publish
 *           deactivate AcceptRefuseSignupRequestController end
 *
 * @enduml --> <!--
 *
 * @startuml seq-register-system-user.svg title Register System User participant dispatcher
 *           participant SignupAcceptedWatchDog participant AddUserOnSignupAcceptedController
 *           participant UserRepository participant NewUserRegisteredFromSignupEvent group Register
 *           system user dispatcher -> SignupAcceptedWatchDog: onEvent activate
 *           SignupAcceptedWatchDog SignupAcceptedWatchDog ->
 *           AddUserOnSignupAcceptedController:addUser activate AddUserOnSignupAcceptedController
 *           AddUserOnSignupAcceptedController -> UserRepository:save create
 *           NewUserRegisteredFromSignupEvent AddUserOnSignupAcceptedController ->
 *           NewUserRegisteredFromSignupEvent:new AddUserOnSignupAcceptedController ->
 *           dispatcher:publish deactivate AddUserOnSignupAcceptedController deactivate
 *           SignupAcceptedWatchDog end
 *
 * @enduml --> <!--
 *
 * @startuml seq-create-client-user.svg title Create Client User participant dispatcher participant
 *           NewUserRegisteredFromSignupWatchDog participant AddClientUserOnSignupAcceptedController
 *           participant ClientUserRepository group Create Client User dispatcher ->
 *           NewUserRegisteredFromSignupWatchDog:onEvent activate
 *           NewUserRegisteredFromSignupWatchDog NewUserRegisteredFromSignupWatchDog ->
 *           AddClientUserOnSignupAcceptedController:addClientUser activate
 *           AddClientUserOnSignupAcceptedController AddClientUserOnSignupAcceptedController ->
 *           ClientUserRepository:save deactivate AddClientUserOnSignupAcceptedController deactivate
 *           NewUserRegisteredFromSignupWatchDog end
 *
 * @enduml -->
 *
 * @author Paulo Gandra de Sousa
 */
@UseCaseController
public class AcceptRefuseSignupRequestControllerEventfulImpl
    implements AcceptRefuseSignupRequestController {

  private final SignupRequestRepository signupRequestsRepository;
  private final AuthorizationService authorizationService;
  private final EventPublisher dispatcher;

  /**
   * Constructor.
   * <p>
   * We are using constructor dependency injection to facilitate testing of this controller.
   * <p>
   * This boilerplate code could be avoided by leveraging Lombok's {@link RequiredArgsConstructor}
   *
   * @param signupRequestsRepository
   * @param authorizationService
   * @param dispatcher
   */
  public AcceptRefuseSignupRequestControllerEventfulImpl(
      final SignupRequestRepository signupRequestsRepository,
      final AuthorizationService authorizationService, final EventPublisher dispatcher) {
    // dependency injection - to make this object more testable we don't create the
    // infrastructure objects to avoid coupling to the implementation. This way, the controller
    // can be used in different scenarios with different implementations of the repository. for
    // instance, unit testing.
    this.signupRequestsRepository = signupRequestsRepository;
    this.authorizationService = authorizationService;
    this.dispatcher = dispatcher;
  }

  @Override
  @SuppressWarnings("squid:S1226")
  public SignupRequest acceptSignupRequest(SignupRequest theSignupRequest) {
    authorizationService.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER,
        ClientRoles.MANAGER);

    Preconditions.nonNull(theSignupRequest);

    theSignupRequest = markSignupRequestAsAccepted(theSignupRequest);
    return theSignupRequest;
  }

  /**
   * Modify Signup Request to accepted.
   *
   * @param theSignupRequest
   *
   * @return
   *
   * @throws ConcurrencyException
   * @throws IntegrityViolationException
   */
  @SuppressWarnings("squid:S1226")
  private SignupRequest markSignupRequestAsAccepted(SignupRequest theSignupRequest) {
    // do just what is needed in the scope of this use case
    theSignupRequest.accept();
    theSignupRequest = signupRequestsRepository.save(theSignupRequest);

    // notify interested parties (if any)
    final DomainEvent event = new SignupAcceptedEvent(theSignupRequest);
    dispatcher.publish(event);

    return theSignupRequest;
  }

  @Override
  public SignupRequest refuseSignupRequest(final SignupRequest theSignupRequest) {
    authorizationService.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER,
        ClientRoles.MANAGER);

    Preconditions.nonNull(theSignupRequest);

    theSignupRequest.refuse();
    return signupRequestsRepository.save(theSignupRequest);
  }

  /**
   * @return
   */
  @Override
  public Iterable<SignupRequest> listPendingSignupRequests() {
    return signupRequestsRepository.pendingSignupRequests();
  }
}
