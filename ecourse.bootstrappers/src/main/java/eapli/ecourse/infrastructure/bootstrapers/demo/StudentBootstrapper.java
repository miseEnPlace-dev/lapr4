package eapli.ecourse.infrastructure.bootstrapers.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.infrastructure.bootstrapers.TestDataConstants;
import eapli.ecourse.mystudent.application.SignupController;
import eapli.ecourse.studentmanagement.application.AcceptRefuseSignupFactory;
import eapli.ecourse.studentmanagement.application.AcceptRefuseSignupRequestController;
import eapli.ecourse.studentmanagement.application.AcceptRefuseSignupRequestControllerEventfulImpl;
import eapli.ecourse.studentmanagement.application.AcceptRefuseSignupRequestControllerTxImpl;
import eapli.ecourse.studentmanagement.domain.SignupRequest;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;

/**
 * Bootstrapper that demonstrates how a Client User can be added to the system.
 * <p>
 * If application property {@code UseEventfulControllers} is set to
 * {@code false}, than the
 * {@link AcceptRefuseSignupRequestController} uses an fully transactional
 * (eventless) controller
 * {@link AcceptRefuseSignupRequestControllerTxImpl}.
 * <p>
 * If application property {@code UseEventfulControllers} is set to
 * {@code true}, than the
 * {@link AcceptRefuseSignupRequestController} uses an eventful controller
 * {@link AcceptRefuseSignupRequestControllerEventfulImpl}.
 *
 * @author Paulo Sousa
 */
public class StudentBootstrapper implements Action {
  private static final Logger LOGGER = LogManager.getLogger(StudentBootstrapper.class);

  private final SignupController signupController = new SignupController();
  private final AcceptRefuseSignupRequestController acceptController = AcceptRefuseSignupFactory.build();

  @Override
  public boolean execute() {
    // signup a couple of users and accept them so they can be used imediately in
    // the User app
    signupAndApprove(TestDataConstants.USER_TEST1, TestDataConstants.PASSWORD1, "John", "Smith",
        "john@smith.com", TestDataConstants.USER_TEST1);
    signupAndApprove(TestDataConstants.USER_TEST2, TestDataConstants.PASSWORD1, "Mary", "Smith",
        "mary@smith.com", TestDataConstants.USER_TEST2);
    // make a bunch of signups but leave them for approval so the backoffice user
    // will aprove them
    signup("isep111", TestDataConstants.PASSWORD1, "John", "Smith One", "john1@smith.com",
        "isep111");
    signup("isep222", TestDataConstants.PASSWORD1, "Mary", "Smith Two", "mary2@smith.com",
        "isep222");
    signup("isep333", TestDataConstants.PASSWORD1, "Mary", "Smith Three", "mary3@smith.com",
        "isep333");

    return true;
  }

  private SignupRequest signup(final String username, final String password, final String firstName,
      final String lastName, final String email, final String mecanographicNumber) {
    return doSignupAndApprove(username, password, firstName, lastName, email, mecanographicNumber,
        false);
  }

  private SignupRequest signupAndApprove(final String username, final String password,
      final String firstName, final String lastName, final String email,
      final String mecanographicNumber) {
    return doSignupAndApprove(username, password, firstName, lastName, email, mecanographicNumber,
        true);
  }

  private SignupRequest doSignupAndApprove(final String username, final String password,
      final String firstName, final String lastName, final String email,
      final String mecanographicNumber, boolean approve) {
    SignupRequest request = null;
    try {
      request = signupController.signup(username, password, firstName, lastName, email,
          mecanographicNumber);
      if (approve) {
        acceptController.acceptSignupRequest(request);
      }
    } catch (final ConcurrencyException | IntegrityViolationException e) {
      // ignoring exception. assuming it is just a primary key violation
      // due to the tentative of inserting a duplicated user
      LOGGER.warn("Assuming {} already exists (activate trace log for details)", username);
      LOGGER.trace("Assuming existing record", e);
    }
    return request;
  }
}
