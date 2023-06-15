package eapli.ecourse.boardmanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "board_id", "rowNumber" }) })
public class BoardRow implements DomainEntity<Integer> {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Long pk;

  @Version
  private Long version;

  @Column
  private BoardTitle title;

  @Column
  private Integer rowNumber;

  @Column
  private BoardID boardID;

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

  public Integer rowNumber() {
    return this.rowNumber;
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
