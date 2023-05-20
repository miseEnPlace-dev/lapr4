package eapli.ecourse.boardmanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;

@Entity
public class BoardColumn implements DomainEntity<Integer> {

  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @Column
  private BoardTitle title;

  @Id
  private Integer columnNumber;

  protected BoardColumn() {
    // for ORM
  }

  public BoardColumn(final BoardTitle title, final Integer number) {

    Preconditions.noneNull(title, number);

    this.title = title;
    this.columnNumber = number;
  }

  public BoardTitle title() {
    return this.title;
  }

  @Override
  public Integer identity() {
    return this.columnNumber;
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
