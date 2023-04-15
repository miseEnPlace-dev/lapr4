package eapli.ecourse.clientusermanagement.application;

/**
 * A simple factory to obtain the desired implementation of the
 * {@link AcceptRefuseSignupRequestController}.
 *
 * @author Paulo Gandra de Sousa 16/05/2019
 *
 */
@Utility
public final class AcceptRefuseSignupFactory {
  private AcceptRefuseSignupFactory() {
    // ensure utility
  }

  public static AcceptRefuseSignupRequestController build() {
    // for pedagogical purposes: play around with the 2 approaches
    if (Application.settings().useEventfulControllers()) {
      // dependency injection - when constructing the object one must inject the dependencies
      // to infrastructure objects it needs. this should be handled by a DI/IoC container like
      // Spring Framework
      return new AcceptRefuseSignupRequestControllerEventfulImpl(
          PersistenceContext.repositories().signupRequests(), AuthzRegistry.authorizationService(),
          InProcessPubSub.publisher());
    } else {
      return new AcceptRefuseSignupRequestControllerTxImpl();
    }
  }
}
