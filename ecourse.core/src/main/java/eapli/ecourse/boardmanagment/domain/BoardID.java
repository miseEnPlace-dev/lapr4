package eapli.ecourse.boardmanagment.domain;

import java.util.UUID;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class BoardID implements ValueObject, Comparable<BoardID> {
  private static final long serialVersionUID = 1L;

  String id;

  public BoardID(final String boardId) {
    Preconditions.nonEmpty(boardId);

    this.id = boardId;
  }

  protected BoardID() {
    // for ORM
  }

  public static BoardID valueOf(final String boardId) {
    return new BoardID(boardId);
  }

  public static BoardID newID() {
    return valueOf(UUID.randomUUID().toString());
  }

  @Override
  public String toString() {
    return this.id;
  }

  @Override
  public int compareTo(final BoardID arg0) {
    return id.compareTo(arg0.id);
  }
}
