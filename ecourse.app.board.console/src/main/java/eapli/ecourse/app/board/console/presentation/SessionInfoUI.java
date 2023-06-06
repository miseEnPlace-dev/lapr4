package eapli.ecourse.app.board.console.presentation;

import eapli.ecourse.app.board.lib.BoardBackend;
import eapli.ecourse.common.board.dto.UserDTO;
import eapli.framework.presentation.console.AbstractUI;

public class SessionInfoUI extends AbstractUI {
  @Override
  protected boolean doShow() {
    UserDTO user = BoardBackend.getInstance().getCredentialStore().getUser();
    System.out.println(user.toString());
    return false;
  }

  @Override
  public String headline() {
    return "Session Info";
  }
}

