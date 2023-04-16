package eapli.ecourse.app.student.console.presentation.myuser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.myclientuser.application.SignupController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
@SuppressWarnings("squid:S106")
public class SignupRequestUI extends AbstractUI {
  private static final Logger LOGGER = LogManager.getLogger(SignupRequestUI.class);

  private final SignupController theController = new SignupController();

  @Override
  protected boolean doShow() {
    final var userData = new UserDataWidget();
    userData.show();

    try {
      this.theController.signup(userData.username(), userData.password(), userData.firstName(),
          userData.lastName(), userData.email(), userData.mecanographicNumber());
    } catch (final IllegalArgumentException e) {
      System.out.println("Error creating the account: " + e.getMessage() + ". Please try again.\n");
      return true;
    } catch (final IntegrityViolationException | ConcurrencyException e) {
      LOGGER.error("Error performing the operation", e);
      System.out.println(
          "Unfortunatelly there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
      return true;
    }

    System.out.println("\nAccount created! Please wait for the administrator to activate your account.\n");

    return true;
  }

  @Override
  public String headline() {
    return "Sign Up";
  }
}
