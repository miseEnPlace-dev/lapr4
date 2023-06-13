package eapli.ecourse.exammanagement.domain.formative;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import eapli.ecourse.exammanagement.domain.SectionDescription;
import eapli.ecourse.exammanagement.domain.SectionIdentifier;
import eapli.ecourse.exammanagement.domain.SectionTitle;
import eapli.framework.domain.model.DomainEntity;
import eapli.framework.validations.Preconditions;

@Entity
public class FormativeExamSectionRequest implements DomainEntity<SectionIdentifier> {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @Id
  private SectionIdentifier identifier;

  @Column(nullable = false)
  private SectionTitle title;

  @Column(nullable = false)
  private SectionDescription description;

  @Column(nullable = false)
  private Integer numberOfQuestions;

  @Column(nullable = false)
  private String questionsType;

  protected FormativeExamSectionRequest() {
    // for ORM only
  }

  public FormativeExamSectionRequest(SectionIdentifier identifier, SectionTitle title, SectionDescription description,
      Integer numberOfQuestions,
      String questionsType) {
    Preconditions.noneNull(identifier, title, description, numberOfQuestions, questionsType);

    this.identifier = identifier;
    this.title = title;
    this.description = description;
    this.numberOfQuestions = numberOfQuestions;
    this.questionsType = questionsType;
  }

  public SectionIdentifier identity() {
    return identifier;
  }

  public void changeIdentifier(SectionIdentifier identifier) {
    Preconditions.noneNull(identifier);
    this.identifier = identifier;
  }

  public SectionTitle title() {
    return title;
  }

  public void changeTitle(SectionTitle title) {
    Preconditions.noneNull(title);
    this.title = title;
  }

  public SectionDescription description() {
    return description;
  }

  public void changeDescription(SectionDescription description) {
    Preconditions.noneNull(description);
    this.description = description;
  }

  public Integer numberOfQuestions() {
    return numberOfQuestions;
  }

  public void changeNumberOfQuestions(Integer numberOfQuestions) {
    Preconditions.noneNull(numberOfQuestions);
    this.numberOfQuestions = numberOfQuestions;
  }

  public String questionsType() {
    return questionsType;
  }

  public void changeQuestionsType(String questionsType) {
    Preconditions.noneNull(questionsType);
    this.questionsType = questionsType;
  }

  @Override
  public boolean sameAs(Object other) {
    if (!(other instanceof FormativeExamSectionRequest)) {
      return false;
    }

    final FormativeExamSectionRequest that = (FormativeExamSectionRequest) other;
    if (this == that) {
      return true;
    }

    return identity().equals(that.identity()) && title().equals(that.title())
        && description().equals(that.description()) && numberOfQuestions().equals(that.numberOfQuestions())
        && questionsType().equals(that.questionsType());
  }
}
