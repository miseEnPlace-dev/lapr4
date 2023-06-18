package eapli.ecourse.postitmanagement.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class PostItTitle implements ValueObject, Comparable<PostItTitle> {

  String title;

  private PostItTitle(String title) {
    this.title = title;
  }

  protected PostItTitle() {
    // for ORM
  }

  public static PostItTitle valueOf(String title) {
    if (StringPredicates.isNullOrEmpty(title))
      throw new IllegalArgumentException("Title should neither be null nor empty");

    return new PostItTitle(title);
  }

  @Override
  public String toString() {
    return this.title;
  }

  @Override
  public int compareTo(PostItTitle o) {
    return title.compareTo(o.title);
  }
}
