package eapli.ecourse.app.user.console.presentation.myuser;

import eapli.framework.actions.Action;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
public class SignupRequestAction implements Action {

  @Override
  public boolean execute() {
    return new SignupRequestUI().show();
  }
}
