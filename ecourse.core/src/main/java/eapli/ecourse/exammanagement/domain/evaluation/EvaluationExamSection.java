package eapli.ecourse.exammanagement.domain.evaluation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

import eapli.ecourse.exammanagement.domain.SectionDescription;
import eapli.ecourse.exammanagement.domain.SectionIdentifier;
import eapli.ecourse.exammanagement.domain.SectionTitle;
import eapli.ecourse.questionmanagement.domain.Question;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;

@Entity
public class EvaluationExamSection implements DomainEntity<SectionIdentifier> {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @EmbeddedId
  private SectionIdentifier identifier;

  @Column(nullable = false)
  private SectionTitle title;

  @Column(nullable = false)
  private SectionDescription description;

  @Column(nullable = false)
  private ExamScore score;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Question> questions;

  public EvaluationExamSection(SectionIdentifier identifier, SectionTitle title, SectionDescription description,
      ExamScore score,
      Collection<Question> questions) {
    Preconditions.noneNull(identifier, title, description, score);
    this.identifier = identifier;
    this.title = title;
    this.description = description;
    this.score = score;
    this.questions = new ArrayList<>(questions);
  }

  protected EvaluationExamSection() {
    // for ORM only
  }

  public SectionIdentifier identity() {
    return identifier;
  }

  public SectionTitle title() {
    return title;
  }

  public SectionDescription description() {
    return description;
  }

  public ExamScore score() {
    return score;
  }

  public List<Question> questions() {
    return questions;
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
    if (!(o instanceof EvaluationExamSection))
      return false;

    final EvaluationExamSection that = (EvaluationExamSection) o;
    return this.identifier.equals(that.identifier) && this.title.equals(that.title)
        && this.description.equals(that.description) && this.score.equals(that.score)
        && this.questions.equals(that.questions);
  }
}