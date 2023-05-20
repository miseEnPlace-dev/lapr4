package eapli.ecourse.app.backoffice.console.presentation.authz;

import eapli.ecourse.app.common.console.presentation.authz.SystemUserPrinter;
import eapli.ecourse.usermanagement.application.DeactivateUserController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

/**
 * @author Fernando
 */
@SuppressWarnings("squid:S106")
public class DeactivateUserUI extends AbstractUI {
  private final DeactivateUserController theController = new DeactivateUserController();

  @Override
  protected boolean doShow() {
    final Iterable<SystemUser> iterable = this.theController.activeUsers();
    if (!iterable.iterator().hasNext()) {
      System.out.println("There is no registered User");
      return false;
    }

    SelectWidget<SystemUser> selector = new SelectWidget<>("Select a User to deactivate", iterable,
        new SystemUserPrinter());
    selector.show();

    final SystemUser user = selector.selectedElement();

    if (user == null) {
      System.out.println("No user selected");
      return false;
    }

    try {
      this.theController.deactivateUser(user);
    } catch (final ConcurrencyException ex) {
      System.out.println(
          "WARNING: That entity has already been changed or deleted since you last read it");
    }

    return false;

  }

  @Override
  public String headline() {
    return "Deactivate User";
  }
}
