package eapli.ecourse.app.backoffice.console.presentation.authz;

import java.util.HashSet;
import java.util.Set;

import eapli.ecourse.usermanagement.application.AddUserController;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * UI for adding a user to the application.
 *
 * Created by nuno on 22/03/16.
 */
@SuppressWarnings("java:S106")
public class AddUserUI extends AbstractUI {
  private final AddUserController theController = new AddUserController();

  @Override
  protected boolean doShow() {
    final SystemUserDataWidget userData = new SystemUserDataWidget();
    userData.show();

    final Set<Role> roleTypes = new HashSet<>();
    boolean show;
    do {
      show = showRoles(roleTypes);
    } while (!show);

    try {
      this.theController.addUser(userData.username(), userData.password(), userData.firstName(),
          userData.lastName(),
          userData.email(), roleTypes);
    } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
      System.out.println("That username is already in use.");
    } catch (IllegalArgumentException e) {
      System.out.println("Error creating the account: " + e.getMessage() + ". Please try again.\n");
      return true;
    }

    return false;
  }

  private boolean showRoles(final Set<Role> roleTypes) {
    // TODO we could also use the "widget" classes from the framework...
    final Menu rolesMenu = buildRolesMenu(roleTypes);
    final MenuRenderer renderer = new VerticalMenuRenderer(rolesMenu, MenuItemRenderer.DEFAULT);
    return renderer.render();
  }

  private Menu buildRolesMenu(final Set<Role> roleTypes) {
    final Menu rolesMenu = new Menu();
    int counter = 0;
    rolesMenu.addItem(MenuItem.of(counter++, "No Role", Actions.SUCCESS));
    for (final Role roleType : theController.getRoleTypes()) {
      rolesMenu.addItem(MenuItem.of(counter++, roleType.toString(), () -> roleTypes.add(roleType)));
    }
    return rolesMenu;
  }

  @Override
  public String headline() {
    return "Add User";
  }
}
