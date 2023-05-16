package eapli.ecourse.boardmanagment.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

@Entity
public class Board implements AggregateRoot<BoardID> {

  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @Column(nullable = false)
  private Title title;

  @Column
  private Archived archived;

  @EmbeddedId
  private BoardID id;

  @OneToMany
  private List<UserPermission> permissions;

  @OneToMany
  private List<BoardColumn> columns;

  @OneToMany
  private List<BoardRow> rows;

  protected Board() {
    // for ORM
  }

  public Board(final Title title, final Archived archived, final List<UserPermission> permissions,
      final List<BoardColumn> column, final List<BoardRow> row, final BoardID id) {

    Preconditions.noneNull(title, id, column, row);

    this.title = title;
    this.archived = archived;
    this.permissions = permissions;
    this.id = id;
    this.columns = column;
    this.rows = row;
  }

  public Title title() {
    return this.title;
  }

  public Archived archived() {
    return this.archived;
  }

  public List<BoardColumn> columns() {
    return this.columns;
  }

  public List<BoardRow> rows() {
    return this.rows;
  }

  public List<UserPermission> permissions() {
    return this.permissions;
  }

  @Override
  public boolean equals(Object other) {
    return DomainEntities.areEqual(this, other);
  }

  @Override
  public BoardID identity() {
    return this.id;
  }

  @Override
  public boolean sameAs(Object other) {
    if (!(other instanceof Board))
      return false;

    final Board otherBoard = (Board) other;

    if (this == otherBoard)
      return true;

    for (BoardColumn column : columns) {
      if (!otherBoard.columns().contains(column))
        return false;
    }

    for (BoardRow row : rows) {
      if (!otherBoard.rows().contains(row))
        return false;
    }

    for (UserPermission permission : permissions) {
      if (!otherBoard.permissions().contains(permission))
        return false;
    }

    return this.identity().equals(otherBoard.identity()) && this.title.equals(otherBoard.title())
        && this.archived.equals(otherBoard.archived());

  }

}
