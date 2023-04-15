package eapli.ecourse.app.common.console.presentation.authz;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author mcn
 */
public class LogoutUI extends AbstractUI {
  private final AuthorizationService authz = AuthzRegistry.authorizationService();

  @Override
  public String headline() {
    return "Logout sucessful!!\n Make a new Login";
  }

  @Override
  protected boolean doShow() {
    authz.clearSession();
    return true;
  }
}
