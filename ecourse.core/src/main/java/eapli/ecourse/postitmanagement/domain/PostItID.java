package eapli.ecourse.postitmanagement.domain;

import java.util.UUID;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class PostItID implements ValueObject, Comparable<PostItID> {
  private static final long serialVersionUID = 1L;

  private String postItId;

  private PostItID(final String postItId) {
    Preconditions.nonEmpty(postItId);

    this.postItId = UUID.fromString(postItId).toString();
  }

  protected PostItID() {
    // for ORM
    this.postItId = null;
  }

  public static PostItID valueOf(final String postItId) {
    return new PostItID(postItId);
  }

  public static PostItID newID() {
    return valueOf(UUID.randomUUID().toString());
  }

  @Override
  public String toString() {
    return this.postItId;
  }

  @Override
  public int compareTo(final PostItID arg0) {
    return postItId.compareTo(arg0.postItId);
  }
}
