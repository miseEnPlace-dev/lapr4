package eapli.ecourse.app.student.console.presentation.myuser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.app.common.console.presentation.student.StudentDataWidget;
import eapli.ecourse.mystudent.application.SignupController;
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

  @Override
  protected boolean doShow() {
    final SignupController theController = new SignupController();
    final StudentDataWidget studentData = new StudentDataWidget();
    studentData.show();

    try {
      theController.signup(studentData.username(), studentData.password(), studentData.firstName(),
          studentData.lastName(), studentData.email(), studentData.mecanographicNumber());
    } catch (final IllegalArgumentException e) {
      System.out.println("Error creating the account: " + e.getMessage() + ". Please try again.\n");
      return true;
    } catch (final IntegrityViolationException | ConcurrencyException e) {
      LOGGER.error("Error performing the operation", e);
      System.out.println(
          "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
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
