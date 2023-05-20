package eapli.ecourse.boardmanagement.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;

public class BoardBuilderTest {

  private BoardBuilder boardBuilder;
  private SystemUser user;

  @Before
  public void setUp() {
    final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
    this.user = userBuilder.with("username", "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(ClientRoles.STUDENT)
        .build();

    boardBuilder = new BoardBuilder();

    boardBuilder.withColumn("title", 1);
    boardBuilder.withRow("title", 2);
    boardBuilder.withPermissions(new ArrayList<>());
    boardBuilder.withTitle("title");
    boardBuilder.withUser(user);
  }

  @Test
  public void ensureBoardBuilderWorks() {
    BoardBuilder boardBuilder = new BoardBuilder();
    assert (boardBuilder != null);
  }

  @Test
  public void ensureBoardBuilderWithTitleWorks() {
    boardBuilder.withTitle(new BoardTitle("title"));
    Board b = boardBuilder.build();

    assertEquals("title", b.title().toString());
  }

  @Test
  public void ensureBoardBuilderWithTitleStringWorks() {
    boardBuilder.withTitle("title");
    Board b = boardBuilder.build();

    assertEquals("title", b.title().toString());
  }

  @Test
  public void ensureBoardBuilderWorksWithListOfPermissions() {
    List<UserPermission> lst = new ArrayList<>();

    lst.add(new UserPermission(new UserPermissionID(), Calendar.getInstance(), Calendar.getInstance(),
        new PermissionType(), user));

    boardBuilder.withPermissions(lst);

    Board b = boardBuilder.build();

    assertEquals(1, b.permissions().size());
  }

  @Test
  public void ensureBoardBuilderWorksWithMapOfPermissions() {
    Map<SystemUser, PermissionType> map = new HashMap<>();

    map.put(user, PermissionType.read());

    boardBuilder.withPermissions(map);

    Board b = boardBuilder.build();

    assertEquals(1, b.permissions().size());
    assertEquals(user, b.permissions().get(0).user());
  }

  @Test
  public void ensureBoardBuilderWorksWithUserPermission() {
    boardBuilder.withPermission(new UserPermission(new UserPermissionID(), Calendar.getInstance(),
        Calendar.getInstance(), new PermissionType(), user));

    Board b = boardBuilder.build();

    assertEquals(1, b.permissions().size());
    assertEquals(user, b.permissions().get(0).user());
  }

  @Test
  public void ensureBoardBuilderWorksWithUserAndPermissionType() {
    boardBuilder.withPermission(user, PermissionType.read());

    Board b = boardBuilder.build();

    assertEquals(1, b.permissions().size());
    assertEquals(user, b.permissions().get(0).user());
  }

  @Test
  public void ensureBoardBuilderWorksWithListOfColumns() {
    List<BoardColumn> lst = new ArrayList<>();
    lst.add(new BoardColumn(new BoardTitle("title"), 1));

    boardBuilder.withColumns(lst);

    Board b = boardBuilder.build();

    assertEquals(1, b.columns().size());
    assertEquals("title", b.columns().get(0).title().toString());
  }

  @Test
  public void ensureBoardBuilderWorksWithListOfRows() {
    List<BoardRow> lst = new ArrayList<>();
    lst.add(new BoardRow(new BoardTitle("title"), 1));

    boardBuilder.withRows(lst);

    Board b = boardBuilder.build();

    assertEquals(1, b.rows().size());
    assertEquals("title", b.rows().get(0).title().toString());
  }

  @Test
  public void ensureBoardBuilderWorksWithMapOfColumns() {
    Map<String, Integer> map = new HashMap<>();
    map.put("title", 1);

    boardBuilder.withColumns(new ArrayList<>());
    boardBuilder.withColumns(map);

    Board b = boardBuilder.build();

    assertEquals(1, b.columns().size());
    assertEquals("title", b.columns().get(0).title().toString());
  }

  @Test
  public void ensureBoardBuilderWorksWithMapOfRows() {
    Map<String, Integer> map = new HashMap<>();
    map.put("title", 1);

    boardBuilder.withRows(new ArrayList<>());
    boardBuilder.withRows(map);

    Board b = boardBuilder.build();

    assertEquals(1, b.rows().size());
    assertEquals("title", b.rows().get(0).title().toString());
  }

  @Test
  public void ensureBoardBuilderWorksWithColumn() {
    boardBuilder.withColumns(new ArrayList<>());
    boardBuilder.withColumn(new BoardColumn(new BoardTitle("title"), 1));

    Board b = boardBuilder.build();

    assertEquals(1, b.rows().size());
    assertEquals("title", b.rows().get(0).title().toString());
  }

  @Test
  public void ensureBoardBuilderWorksWithRow() {
    boardBuilder.withRows(new ArrayList<>());
    boardBuilder.withRow(new BoardRow(new BoardTitle("title"), 1));

    Board b = boardBuilder.build();

    assertEquals(1, b.rows().size());
    assertEquals("title", b.rows().get(0).title().toString());
  }

  @Test
  public void ensureBoardBuilderWorksWithColumnTitleAndInt() {
    boardBuilder.withColumns(new ArrayList<>());
    boardBuilder.withColumn(new BoardTitle("title"), 1);

    Board b = boardBuilder.build();

    assertEquals(1, b.rows().size());
    assertEquals("title", b.rows().get(0).title().toString());
  }

  @Test
  public void ensureBoardBuilderWorksWithRowTitleAndInt() {
    boardBuilder.withRows(new ArrayList<>());
    boardBuilder.withRow(new BoardTitle("title"), 1);

    Board b = boardBuilder.build();

    assertEquals(1, b.rows().size());
    assertEquals("title", b.rows().get(0).title().toString());
  }

  @Test
  public void ensureBoardBuilderWorksWithColumnTitleStringAndInt() {
    boardBuilder.withColumns(new ArrayList<>());
    boardBuilder.withColumn("title", 1);

    Board b = boardBuilder.build();

    assertEquals(1, b.rows().size());
    assertEquals("title", b.rows().get(0).title().toString());
  }

  @Test
  public void ensureBoardBuilderWorksWithRowTitleStringAndInt() {
    boardBuilder.withRows(new ArrayList<>());
    boardBuilder.withRow("title", 1);

    Board b = boardBuilder.build();

    assertEquals(1, b.rows().size());
    assertEquals("title", b.rows().get(0).title().toString());
  }

  @Test
  public void ensureBoardBuilderWithUser() {
    boardBuilder.withUser(user);

    Board b = boardBuilder.build();

    assertEquals(user, b.owner());
  }

}
