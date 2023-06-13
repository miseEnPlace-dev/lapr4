package eapli.ecourse.exammanagement.domain.formative;

import eapli.ecourse.exammanagement.domain.SectionDescription;
import eapli.ecourse.exammanagement.domain.SectionIdentifier;
import eapli.ecourse.exammanagement.domain.SectionTitle;
import eapli.framework.validations.Preconditions;

public class FormativeExamSectionRequest {
  private SectionIdentifier identifier;
  private SectionTitle title;
  private SectionDescription description;
  private Integer numberOfQuestions;
  private String questionsType;

  public FormativeExamSectionRequest() {
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

  public SectionIdentifier identifier() {
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
}
