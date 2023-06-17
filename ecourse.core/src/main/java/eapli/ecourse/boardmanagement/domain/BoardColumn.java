package eapli.ecourse.boardmanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"board_id", "columnNumber"})})
public class BoardColumn implements DomainEntity<Integer> {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Long pk;

  @Version
  private Long version;

  @Column
  private BoardTitle title;

  @Column
  private Integer columnNumber;

  @Column(name = "board_id")
  private BoardID boardID;

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

  public Integer columnNumber() {
    return this.columnNumber;
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

    return identity().equals(otherBoardColumn.identity())
        && title().equals(otherBoardColumn.title());
  }


  public int getHash() {
    HashCodeBuilder hashBuilder =
        new HashCodeBuilder().append(title).append(columnNumber).append(boardID);

    return hashBuilder.toHashCode();
  }
}
