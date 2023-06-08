package eapli.ecourse.exammanagement.domain.formative;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

import eapli.ecourse.exammanagement.domain.SectionDescription;
import eapli.ecourse.exammanagement.domain.SectionIdentifier;
import eapli.ecourse.exammanagement.domain.SectionTitle;
import eapli.ecourse.questionmanagement.domain.Question;
import eapli.framework.domain.model.DomainEntityBase;
import eapli.framework.validations.Preconditions;

@Entity
public class FormativeExamSection extends DomainEntityBase<Long> {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  private SectionIdentifier identifier;

  @Column(nullable = false)
  private SectionTitle title;

  @Column(nullable = false)
  private SectionDescription description;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Question> questions;

  public FormativeExamSection(SectionIdentifier identifier, SectionTitle title, SectionDescription description,
      Collection<Question> questions) {
    Preconditions.noneNull(identifier, title, description);

    this.identifier = identifier;
    this.title = title;
    this.description = description;
    this.questions = new ArrayList<>(questions);
  }

  protected FormativeExamSection() {
    // for ORM only
  }

  public Long identity() {
    return id;
  }

  public SectionIdentifier identifier() {
    return identifier;
  }

  public SectionTitle title() {
    return title;
  }

  public SectionDescription description() {
    return description;
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
    if (!(o instanceof FormativeExamSection))
      return false;

    final FormativeExamSection that = (FormativeExamSection) o;
    return this.identifier.equals(that.identifier) && this.title.equals(that.title)
        && this.description.equals(that.description) && this.questions.equals(that.questions);
  }
}
