package eapli.ecourse.exammanagement.domain;

import java.util.List;

import eapli.ecourse.questionmanagement.domain.Identifier;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.validations.Preconditions;

public class SectionBuilder implements DomainFactory<Section> {
  private Section section;

  private Identifier identifier;
  private Title title;
  private Description description;
  private Score score;
  private List<SectionQuestion> questions;

  public SectionBuilder withIdentifier(Identifier identifier) {
    this.identifier = identifier;
    return this;
  }

  public SectionBuilder withTitle(Title title) {
    this.title = title;
    return this;
  }

  public SectionBuilder withDescription(Description description) {
    this.description = description;
    return this;
  }

  public SectionBuilder withScore(Score score) {
    this.score = score;
    return this;
  }

  public SectionBuilder withQuestions(List<SectionQuestion> questions) {
    this.questions = questions;
    return this;
  }

  private Section buildOrThrow() {
    if (section != null)
      return section;

    Preconditions.noneNull(identifier, title, description, score, questions);

    section = new Section(identifier, title, description, score, questions);

    return section;
  }

  @Override
  public Section build() {
    final Section ret = buildOrThrow();
    section = null;
    return ret;
  }

}
