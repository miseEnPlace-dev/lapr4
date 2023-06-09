package eapli.ecourse.exammanagement.domain.formative;

import java.util.List;

import eapli.ecourse.exammanagement.domain.ExamDescription;
import eapli.ecourse.exammanagement.domain.ExamIdentifier;
import eapli.ecourse.exammanagement.domain.ExamTitle;
import eapli.ecourse.exammanagement.domain.evaluation.ExamScore;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.validations.Preconditions;

public class FormativeExamRequestBuilder implements DomainFactory<FormativeExamRequest> {
  private FormativeExamRequest examRequest;

  private ExamIdentifier identifier;
  private ExamTitle title;
  private ExamDescription description;
  private List<FormativeExamSectionRequest> sections;
  private ExamScore score;

  public FormativeExamRequestBuilder withIdentifier(ExamIdentifier identifier) {
    this.identifier = identifier;
    return this;
  }

  public FormativeExamRequestBuilder withTitle(ExamTitle title) {
    this.title = title;
    return this;
  }

  public FormativeExamRequestBuilder withScore(ExamScore score) {
    this.score = score;
    return this;
  }

  public FormativeExamRequestBuilder withDescription(ExamDescription description) {
    this.description = description;
    return this;
  }

  public FormativeExamRequestBuilder withSections(List<FormativeExamSectionRequest> sections) {
    this.sections = sections;
    return this;
  }

  private FormativeExamRequest buildOrThrow() {
    if (examRequest != null)
      return examRequest;

    Preconditions.noneNull(identifier, title, description, sections);

    examRequest = new FormativeExamRequest(identifier, title, description, score, sections);
    return examRequest;
  }

  @Override
  public FormativeExamRequest build() {
    final FormativeExamRequest ret = buildOrThrow();
    examRequest = null;
    return ret;
  }
}
