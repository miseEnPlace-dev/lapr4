package eapli.ecourse.boardmanagement.application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import eapli.ecourse.boardmanagement.domain.Archived;
import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardColumn;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.domain.BoardRow;
import eapli.ecourse.boardmanagement.domain.BoardTitle;
import eapli.ecourse.boardmanagement.domain.UserPermission;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;

public class BoardControllerBaseTest {
  public static SystemUser dummyUser(final String username, final Role... roles) {
    final SystemUserBuilder userBuilder =
        new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
    return userBuilder.with(username, "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles)
        .build();
  }

  public SystemUser getNewDummyUser() {
    return dummyUser("dummy", ClientRoles.MANAGER);
  }

  public SystemUser getNewDummyUser2() {
    return dummyUser("dummy2", ClientRoles.POWER_USER);
  }

  public Board getDummyBoard() {
    BoardRow row = new BoardRow(new BoardTitle("row1"), 1);
    List<BoardRow> rows = new ArrayList<BoardRow>();
    rows.add(row);

    BoardColumn column = new BoardColumn(new BoardTitle("column1"), 1);
    List<BoardColumn> columns = new ArrayList<BoardColumn>();
    columns.add(column);

    List<UserPermission> permissions = new ArrayList<UserPermission>();

    return new Board(new BoardTitle("B01"), permissions, columns, rows, BoardID.newID(),
        getNewDummyUser());
  }

  public Board getDummyBoard2() {
    BoardRow row = new BoardRow(new BoardTitle("row2"), 2);
    List<BoardRow> rows = new ArrayList<BoardRow>();
    rows.add(row);

    BoardColumn column = new BoardColumn(new BoardTitle("column2"), 2);
    List<BoardColumn> columns = new ArrayList<BoardColumn>();
    columns.add(column);

    List<UserPermission> permissions = new ArrayList<UserPermission>();

    return new Board(new BoardTitle("B2"), permissions, columns, rows, BoardID.newID(),
        getNewDummyUser2());
  }

  public Board getDummyArchivedBoard() {
    BoardRow row = new BoardRow(new BoardTitle("1512"), 3);
    List<BoardRow> rows = new ArrayList<BoardRow>();
    rows.add(row);

    BoardColumn column = new BoardColumn(new BoardTitle("column3"), 3);
    List<BoardColumn> columns = new ArrayList<BoardColumn>();
    columns.add(column);

    List<UserPermission> permissions = new ArrayList<UserPermission>();

    return new Board(new BoardTitle("B03"), permissions, columns, rows, BoardID.newID(),
        getNewDummyUser(), new Archived(Calendar.getInstance()));
  }
}
