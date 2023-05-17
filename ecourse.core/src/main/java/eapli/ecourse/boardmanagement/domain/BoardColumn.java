package eapli.ecourse.boardmanagement.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Version;

import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;

@Entity
public class BoardColumn implements DomainEntity<Integer> {

  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @Column
  private Title title;

  @EmbeddedId
  private Integer number;

  protected BoardColumn() {
    // for ORM
  }

  public BoardColumn(final Title title, final Integer number) {

    Preconditions.noneNull(title, number);

    this.title = title;
    this.number = number;
  }

  public Title title() {
    return this.title;
  }

  @Override
  public Integer identity() {
    return this.number;
  }

  @Override
  public boolean sameAs(Object other) {
    if (!(other instanceof BoardColumn))
      return false;

    final BoardColumn otherBoardColumn = (BoardColumn) other;

    if (this == otherBoardColumn)
      return true;

    return identity().equals(otherBoardColumn.identity()) && title().equals(otherBoardColumn.title());
  }

}
