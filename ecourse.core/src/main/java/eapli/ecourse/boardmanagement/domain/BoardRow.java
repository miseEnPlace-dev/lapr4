package eapli.ecourse.boardmanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;

@Entity
public class BoardRow implements DomainEntity<Integer> {

  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @Column
  private BoardTitle title;

  @Id
  private Integer rowNumber;

  protected BoardRow() {
    // for ORM
  }

  public BoardRow(final BoardTitle title, final Integer number) {

    Preconditions.noneNull(title, number);

    this.title = title;
    this.rowNumber = number;
  }

  public BoardTitle title() {
    return this.title;
  }

  @Override
  public Integer identity() {
    return this.rowNumber;
  }

  @Override
  public boolean sameAs(Object other) {
    if (!(other instanceof BoardRow))
      return false;

    final BoardRow otherBoardRow = (BoardRow) other;

    if (this == otherBoardRow)
      return true;

    return identity().equals(otherBoardRow.identity()) && title().equals(otherBoardRow.title());
  }

}
