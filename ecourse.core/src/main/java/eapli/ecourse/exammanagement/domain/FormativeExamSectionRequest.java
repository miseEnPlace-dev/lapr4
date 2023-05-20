package eapli.ecourse.exammanagement.domain;

import eapli.ecourse.questionmanagement.domain.QuestionIdentifier;
import eapli.framework.validations.Preconditions;

public class FormativeExamSectionRequest {
  private QuestionIdentifier identifier;
  private ExamTitle title;
  private ExamDescription description;
  private Integer numberOfQuestions;
  private String questionsType;

  public FormativeExamSectionRequest() {
  }

  public FormativeExamSectionRequest(QuestionIdentifier identifier, ExamTitle title, ExamDescription description,
      Integer numberOfQuestions,
      String questionsType) {
    Preconditions.noneNull(identifier, title, description, numberOfQuestions, questionsType);

    this.identifier = identifier;
    this.title = title;
    this.description = description;
    this.numberOfQuestions = numberOfQuestions;
    this.questionsType = questionsType;
  }

  public QuestionIdentifier identifier() {
    return identifier;
  }

  public void changeIdentifier(QuestionIdentifier identifier) {
    Preconditions.noneNull(identifier);
    this.identifier = identifier;
  }

  public ExamTitle title() {
    return title;
  }

  public void changeTitle(ExamTitle title) {
    Preconditions.noneNull(title);
    this.title = title;
  }

  public ExamDescription description() {
    return description;
  }

  public void changeDescription(ExamDescription description) {
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
