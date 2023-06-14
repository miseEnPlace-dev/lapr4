package eapli.ecourse.exammanagement.domain.formative;

import eapli.ecourse.exammanagement.domain.SectionDescription;
import eapli.ecourse.exammanagement.domain.SectionIdentifier;
import eapli.ecourse.exammanagement.domain.SectionTitle;

public class FormativeExamSectionRequestBuilder {
  private FormativeExamSectionRequest section;

  private SectionIdentifier identifier;
  private SectionTitle title;
  private SectionDescription description;
  private Integer numberOfQuestions;
  private String questionsType;

  public FormativeExamSectionRequestBuilder withIdentifier(SectionIdentifier identifier) {
    this.identifier = identifier;
    return this;
  }

  public FormativeExamSectionRequestBuilder withTitle(SectionTitle title) {
    this.title = title;
    return this;
  }

  public FormativeExamSectionRequestBuilder withDescription(SectionDescription description) {
    this.description = description;
    return this;
  }

  public FormativeExamSectionRequestBuilder withNumberOfQuestions(Integer numberOfQuestions) {
    this.numberOfQuestions = numberOfQuestions;
    return this;
  }

  public FormativeExamSectionRequestBuilder withQuestionsType(String questionsType) {
    this.questionsType = questionsType;
    return this;
  }

  private FormativeExamSectionRequest buildOrThrow() {
    if (section != null)
      return section;

    section = new FormativeExamSectionRequest(identifier, title, description, numberOfQuestions, questionsType);

    return section;
  }

  public FormativeExamSectionRequest build() {
    final FormativeExamSectionRequest ret = buildOrThrow();
    section = null;
    return ret;
  }

}
