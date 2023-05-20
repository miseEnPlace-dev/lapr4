package eapli.ecourse.exammanagement.domain;

import java.util.List;

import eapli.ecourse.questionmanagement.domain.QuestionIdentifier;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.validations.Preconditions;

public class SectionBuilder implements DomainFactory<ExamSection> {
  private ExamSection section;

  private QuestionIdentifier identifier;
  private ExamTitle title;
  private ExamDescription description;
  private ExamScore score;
  private List<SectionQuestion> questions;

  public SectionBuilder withIdentifier(QuestionIdentifier identifier) {
    this.identifier = identifier;
    return this;
  }

  public SectionBuilder withTitle(ExamTitle title) {
    this.title = title;
    return this;
  }

  public SectionBuilder withDescription(ExamDescription description) {
    this.description = description;
    return this;
  }

  public SectionBuilder withScore(ExamScore score) {
    this.score = score;
    return this;
  }

  public SectionBuilder withQuestions(List<SectionQuestion> questions) {
    this.questions = questions;
    return this;
  }

  private ExamSection buildOrThrow() {
    if (section != null)
      return section;

    Preconditions.noneNull(identifier, title, description, score, questions);

    section = new ExamSection(identifier, title, description, score, questions);

    return section;
  }

  @Override
  public ExamSection build() {
    final ExamSection ret = buildOrThrow();
    section = null;
    return ret;
  }

}
