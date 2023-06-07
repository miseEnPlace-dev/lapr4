package eapli.ecourse.app.common.console.presentation.board;

import java.text.SimpleDateFormat;
import eapli.ecourse.boardmanagement.dto.UserPermissionDTO;
import eapli.framework.visitor.Visitor;

public class UserPermissionPrinter implements Visitor<UserPermissionDTO> {

  @Override
  public void visit(final UserPermissionDTO visitee) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    System.out.printf("%-38s%-12s%-12s%-18s%-18s", visitee.getId(), visitee.getUser().identity(),
        visitee.getPermissionType(), formatter.format(visitee.getCreatedAt().getTime()),
        formatter.format(visitee.getUpdatedAt().getTime()));
  }
}
