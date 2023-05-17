package eapli.ecourse.boardmanagement.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

public class BoardBuilder implements DomainFactory<Board> {
  private Board board;

  private Title title;
  private BoardID id;
  private List<UserPermission> permissions;
  private List<BoardColumn> columns;
  private List<BoardRow> rows;
  private SystemUser user;

  public BoardBuilder() {
    this.permissions = new ArrayList<>();
    this.columns = new ArrayList<>();
    this.rows = new ArrayList<>();
  }

  public BoardBuilder withTitle(String title) {
    this.title = new Title(title);
    return this;
  }

  public BoardBuilder withTitle(Title title) {
    this.title = title;
    return this;
  }

  public BoardBuilder withId(BoardID id) {
    this.id = id;
    return this;
  }

  public BoardBuilder withId(String id) {
    this.id = BoardID.valueOf(id);
    return this;
  }

  public BoardBuilder withPermissions(List<UserPermission> permissions) {
    this.permissions = permissions;
    return this;
  }

  public BoardBuilder withPermissions(Map<SystemUser, PermissionType> permissions) {
    for (Map.Entry<SystemUser, PermissionType> entry : permissions.entrySet()) {
      this.withPermission(entry.getKey(), entry.getValue());
    }
    return this;
  }

  public BoardBuilder withPermission(UserPermission permission) {
    this.permissions.add(permission);
    return this;
  }

  public BoardBuilder withPermission(SystemUser user, PermissionType permission) {
    UserPermissionID id = UserPermissionID.newID();
    Calendar createdAt = Calendar.getInstance();
    this.permissions.add(new UserPermission(id, createdAt, createdAt, permission, user));

    return this;
  }

  public BoardBuilder withColumns(List<BoardColumn> columns) {
    this.columns = columns;
    return this;
  }

  public BoardBuilder withColumns(Map<String, Integer> columns) {
    for (Map.Entry<String, Integer> entry : columns.entrySet()) {
      this.withColumn(entry.getKey(), entry.getValue());
    }
    return this;
  }

  public BoardBuilder withColumn(BoardColumn column) {
    this.columns.add(column);
    return this;
  }

  public BoardBuilder withColumn(Title title, Integer number) {
    this.columns.add(new BoardColumn(title, number));
    return this;
  }

  public BoardBuilder withColumn(String title, Integer number) {
    Title t = new Title(title);
    this.columns.add(new BoardColumn(t, number));
    return this;
  }

  public BoardBuilder withRows(List<BoardRow> rows) {
    this.rows = rows;
    return this;
  }

  public BoardBuilder withRows(Map<String, Integer> rows) {
    for (Map.Entry<String, Integer> entry : rows.entrySet()) {
      this.withRow(entry.getKey(), entry.getValue());
    }
    return this;
  }

  public BoardBuilder withRow(BoardRow row) {
    this.rows.add(row);
    return this;
  }

  public BoardBuilder withRow(Title title, Integer number) {
    this.rows.add(new BoardRow(title, number));
    return this;
  }

  public BoardBuilder withRow(String title, Integer number) {
    Title t = new Title(title);
    this.rows.add(new BoardRow(t, number));
    return this;
  }

  public BoardBuilder withUser(SystemUser user) {
    this.user = user;
    return this;
  }

  private Board buildOrThrow() {
    if (board != null) {
      return board;
    }

    // Only fields that are mandatory are checked
    Preconditions.noneNull(title, id, columns, rows);

    if (id != null) {
      board = new Board(title, permissions, columns, rows, id, user);
    } else {
      board = new Board(title, permissions, columns, rows, user);
    }

    return board;

  }

  @Override
  public Board build() {
    final Board b = buildOrThrow();

    // reset builder state so it can be used again
    this.board = null;

    return b;
  }

}
