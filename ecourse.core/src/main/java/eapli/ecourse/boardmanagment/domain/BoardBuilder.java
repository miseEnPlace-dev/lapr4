package eapli.ecourse.boardmanagment.domain;

import java.security.Permission;
import java.util.Calendar;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.boot.BootstrapRegistry;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

public class BoardBuilder implements DomainFactory<Board> {
  private Board board;

  private Title title;
  private Archived archived;
  private BoardID id;
  private List<UserPermission> permissions;
  private List<BoardColumn> columns;
  private List<BoardRow> rows;

  public BoardBuilder withTitle(String title) {
    this.title = new Title(title);
    return this;
  }

  public BoardBuilder withTitle(Title title) {
    this.title = title;
    return this;
  }

  public BoardBuilder withArchived(Calendar archived) {
    this.archived = new Archived(archived);
    return this;
  }

  public BoardBuilder withArchived(Archived archived) {
    this.archived = archived;
    return this;
  }

  public BoardBuilder withId(String id) {
    this.id = new BoardID(id);
    return this;
  }

  public BoardBuilder withId(BoardID id) {
    this.id = id;
    return this;
  }

  public BoardBuilder withPermissions(List<UserPermission> permissions) {
    this.permissions = permissions;
    return this;
  }

  public BoardBuilder withPermission(UserPermission permission) {
    this.permissions.add(permission);
    return this;
  }

  public BoardBuilder withPermission(SystemUser user, PermissionType permission) {
    // TODO
    UserPermissionID id = new UserPermissionID("TODO");
    this.permissions.add(new UserPermission(id, Calendar.getInstance(), null, permission, user));

    return this;
  }

  public BoardBuilder withColumns(List<BoardColumn> columns) {
    this.columns = columns;
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

  private Board buildOrThrow() {
    if (board != null) {
      return board;
    }

    // Only fields that are mandatory are checked
    Preconditions.noneNull(title, id, columns, rows);

    board = new Board(title, archived, permissions, columns, rows, id);
    return board;

  }

  @Override
  public Board build() {
    final Board board = buildOrThrow();

    // reset builder state so it can be used again
    this.board = null;

    return board;
  }

}
