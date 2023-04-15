package eapli.ecourse.app.backoffice.console.presentation.authz;

import java.util.ArrayList;
import java.util.List;

import eapli.ecourse.usermanagement.application.DeactivateUserController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

/**
 * @author Fernando
 */
@SuppressWarnings("squid:S106")
public class DeactivateUserUI extends AbstractUI {
  private final DeactivateUserController theController = new DeactivateUserController();

  @Override
  protected boolean doShow() {
    final List<SystemUser> list = new ArrayList<>();
    final Iterable<SystemUser> iterable = this.theController.activeUsers();
    if (!iterable.iterator().hasNext()) {
      System.out.println("There is no registered User");
    } else {
      var cont = 1;
      System.out.println("SELECT User to deactivate\n");
      // TODO use select widget, see, ChangeDishTypeUI
      System.out.printf("%-6s%-10s%-30s%-30s%n", "Nº:", "Username", "Firstname", "Lastname");
      for (final SystemUser user : iterable) {
        list.add(user);
        System.out.printf("%-6d%-10s%-30s%-30s%n", cont, user.username(), user.name().firstName(),
            user.name().lastName());
        cont++;
      }
      final var option = Console.readInteger("Enter user nº to deactivate or 0 to finish ");
      if (option == 0) {
        System.out.println("No user selected");
      } else {
        try {
          this.theController.deactivateUser(list.get(option - 1));
        } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
          System.out.println(
              "WARNING: That entity has already been changed or deleted since you last read it");
        }
      }
    }
    return true;
  }

  @Override
  public String headline() {
    return "Deactivate User";
  }
}
