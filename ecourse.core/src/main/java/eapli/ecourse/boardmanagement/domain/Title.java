package eapli.ecourse.boardmanagement.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class Title implements ValueObject {
  private static final long serialVersionUID = 1L;

  private String title;

  public Title(final String title) {
    validate(title);

    this.title = title;
  }

  protected Title() {
    // for ORM
  }

  private void validate(final String title) {
    if (title == null || title.isEmpty()) {
      throw new IllegalArgumentException("Title should neither be null nor empty");
    }
  }

}
