package eapli.ecourse.postitmanagement.domain;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class PostItImage implements ValueObject {
  private static final long serialVersionUID = 1L;

  @Lob
  private String encodedImage;

  private PostItImage(final String encodedImage) {
    if (StringPredicates.isNullOrEmpty(encodedImage))
      throw new IllegalArgumentException("Encoded Image should neither be null nor empty");

    this.encodedImage = encodedImage;
  }

  protected PostItImage() {
    // for ORM
  }

  public static PostItImage valueOf(final String encodedImage) {
    return new PostItImage(encodedImage);
  }

  @Override
  public String toString() {
    return this.encodedImage;
  }
}
