package eapli.ecourse.app.backoffice.console.presentation.users;

import eapli.ecourse.app.backoffice.console.presentation.authz.ListUsersUI;
import eapli.ecourse.usermanagement.application.ListUsersController;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class ListManagersUI extends ListUsersUI {
  private final ListUsersController controller = new ListUsersController();

  @Override
  public String headline() {
    return "List Users";
  }

  @Override
  protected String elementName() {
    return "Manager";
  }

  @Override
  protected Iterable<SystemUser> elements() {
    return controller.allManagers();
  }
}
