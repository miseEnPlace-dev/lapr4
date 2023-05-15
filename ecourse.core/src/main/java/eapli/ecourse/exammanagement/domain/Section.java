package eapli.ecourse.exammanagement.domain;

import javax.persistence.Entity;
import javax.persistence.Version;

@Entity
/**
 * A section of an exam.
 */
public class Section {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;
}
