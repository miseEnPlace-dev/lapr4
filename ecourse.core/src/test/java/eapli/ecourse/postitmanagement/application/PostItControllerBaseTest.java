package eapli.ecourse.postitmanagement.application;

import java.util.ArrayList;
import java.util.List;
import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardColumn;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.domain.BoardRow;
import eapli.ecourse.boardmanagement.domain.BoardTitle;
import eapli.ecourse.boardmanagement.domain.UserPermission;
import eapli.ecourse.postitmanagement.domain.Coordinates;
import eapli.ecourse.postitmanagement.domain.PostIt;
import eapli.ecourse.postitmanagement.domain.PostItTitle;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;

public class PostItControllerBaseTest {
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
    BoardRow row = new BoardRow(new BoardTitle("row1"), 1);
    List<BoardRow> rows = new ArrayList<BoardRow>();
    rows.add(row);

    BoardColumn column = new BoardColumn(new BoardTitle("column1"), 1);
    List<BoardColumn> columns = new ArrayList<BoardColumn>();
    columns.add(column);

    List<UserPermission> permissions = new ArrayList<UserPermission>();

    return new Board(new BoardTitle("B02"), permissions, columns, rows, BoardID.newID(),
        getNewDummyUser2());
  }

  public PostIt getDummyPostIt() {
    return new PostIt(PostItTitle.valueOf("title"), Coordinates.valueOf(1, 2), getDummyBoard(),
        getNewDummyUser());
  }

  public PostIt getDummyPostIt2() {
    return new PostIt(PostItTitle.valueOf("title2"), Coordinates.valueOf(3, 4), getDummyBoard(),
        getNewDummyUser2());
  }

  public PostIt getDummyPostItWithPrevious() {
    return new PostIt(PostItTitle.valueOf("title4"), Coordinates.valueOf(5, 2), getDummyBoard(),
        getNewDummyUser(), true, getDummyPostItNotLatest());
  }

  public PostIt getDummyPostItNotLatest() {
    return new PostIt(PostItTitle.valueOf("title3"), Coordinates.valueOf(5, 4), getDummyBoard(),
        getNewDummyUser(), false);
  }

  public PostIt getDummyPostItNotLatestWithPrevious() {
    return new PostIt(PostItTitle.valueOf("title3"), Coordinates.valueOf(5, 4), getDummyBoard(),
        getNewDummyUser(), false, getDummyPostIt());
  }
}
