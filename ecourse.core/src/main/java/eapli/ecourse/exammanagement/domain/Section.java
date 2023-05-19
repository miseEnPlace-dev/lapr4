package eapli.ecourse.exammanagement.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import eapli.ecourse.questionmanagement.domain.Identifier;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;

@Entity
/**
 * A section of an exam.
 */
public class Section implements DomainEntity<Long> {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  private Identifier identifier;

  @Column(nullable = false)
  private Title title;

  @Column(nullable = false)
  private Description description;

  @Column(nullable = false)
  private Score score;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<SectionQuestion> questions;

  public Section(Identifier identifier, Title title, Description description, Score score,
      Collection<SectionQuestion> questions) {
    Preconditions.noneNull(identifier, title, description, score);
    this.identifier = identifier;
    this.title = title;
    this.description = description;
    this.score = score;
    this.questions = new ArrayList<>(questions);
  }

  protected Section() {
    // for ORM only
  }

  public Long identity() {
    return id;
  }

  public boolean sameAs(Object other) {
    return this.equals(other);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null)
      return false;
    if (this == o)
      return true;
    if (!(o instanceof Section))
      return false;

    final Section that = (Section) o;
    return this.identifier.equals(that.identifier) && this.title.equals(that.title)
        && this.description.equals(that.description) && this.score.equals(that.score)
        && this.questions.equals(that.questions);
  }
}
