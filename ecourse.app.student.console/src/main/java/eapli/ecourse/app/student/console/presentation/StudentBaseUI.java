package eapli.ecourse.app.student.console.presentation;

import eapli.ecourse.mystudent.application.MyStudentService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author mcn
 */
@SuppressWarnings("squid:S106")
public abstract class StudentBaseUI extends AbstractUI {

  private final AuthorizationService authz = AuthzRegistry.authorizationService();

  protected MyStudentService svc = new MyStudentService();

  @Override
  public String headline() {

    return authz.session().map(s -> "eCOURSE [ @" + s.authenticatedUser().identity() + " ] ")
        .orElse("eCOURSE [ ==Anonymous== ]");
  }

  @Override
  protected void drawFormTitle(final String title) {
    final String titleBorder = BORDER.substring(0, 2) + " " + title;
    System.out.println(titleBorder);
    drawFormBorder();
  }
}
