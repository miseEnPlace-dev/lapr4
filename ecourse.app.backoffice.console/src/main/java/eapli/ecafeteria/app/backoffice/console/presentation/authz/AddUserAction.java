package eapli.ecourse.app.backoffice.console.presentation.authz;

import eapli.framework.actions.Action;

/**
 * Menu action for adding a new user to the application. Created by nuno on 22/03/16.
 */
public class AddUserAction implements Action {

  @Override
  public boolean execute() {
    return new AddUserUI().show();
  }
}
