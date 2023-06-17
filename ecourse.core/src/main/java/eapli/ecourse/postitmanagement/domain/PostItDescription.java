package eapli.ecourse.postitmanagement.domain;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class PostItDescription implements ValueObject {
  private static final long serialVersionUID = 1L;

  @Lob
  private String description;

  private PostItDescription(final String courseDescription) {
    if (StringPredicates.isNullOrEmpty(courseDescription))
      throw new IllegalArgumentException("Course Description should neither be null nor empty");

    this.description = courseDescription;
  }

  protected PostItDescription() {
    // for ORM
  }

  public static PostItDescription valueOf(final String courseDescription) {
    return new PostItDescription(courseDescription);
  }

  @Override
  public String toString() {
    return this.description;
  }

  public int getHash() {
    return this.description.hashCode();
  }
}
