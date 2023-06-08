package eapli.ecourse.exammanagement.domain.evaluation;

import java.util.List;

import eapli.ecourse.exammanagement.domain.SectionDescription;
import eapli.ecourse.exammanagement.domain.SectionIdentifier;
import eapli.ecourse.exammanagement.domain.SectionTitle;
import eapli.ecourse.questionmanagement.domain.Question;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.validations.Preconditions;

public class EvaluationExamSectionBuilder implements DomainFactory<EvaluationExamSection> {
  private EvaluationExamSection section;

  private SectionIdentifier identifier;
  private SectionTitle title;
  private SectionDescription description;
  private List<Question> questions;

  public EvaluationExamSectionBuilder withIdentifier(SectionIdentifier identifier) {
    this.identifier = identifier;
    return this;
  }

  public EvaluationExamSectionBuilder withTitle(SectionTitle title) {
    this.title = title;
    return this;
  }

  public EvaluationExamSectionBuilder withDescription(SectionDescription description) {
    this.description = description;
    return this;
  }

  public EvaluationExamSectionBuilder withQuestions(List<Question> questions) {
    this.questions = questions;
    return this;
  }

  private EvaluationExamSection buildOrThrow() {
    if (section != null)
      return section;

    Preconditions.noneNull(identifier, title, description, questions);

    section = new EvaluationExamSection(identifier, title, description, questions);

    return section;
  }

  @Override
  public EvaluationExamSection build() {
    final EvaluationExamSection ret = buildOrThrow();
    section = null;
    return ret;
  }

}
