package eapli.ecourse.exammanagement.domain.formative;

import java.util.List;

import eapli.ecourse.exammanagement.domain.ExamDescription;
import eapli.ecourse.exammanagement.domain.ExamTitle;
import eapli.ecourse.questionmanagement.domain.QuestionIdentifier;
import eapli.framework.domain.model.DomainEntityBase;

public class FormativeExamRequest extends DomainEntityBase<QuestionIdentifier> {
  private QuestionIdentifier identifier;
  private ExamTitle title;
  private ExamDescription description;
  private List<FormativeExamSectionRequest> sections;

  public FormativeExamRequest(QuestionIdentifier identifier, ExamTitle title, ExamDescription description,
      List<FormativeExamSectionRequest> sections) {
    this.identifier = identifier;
    this.title = title;
    this.description = description;
    this.sections = sections;
  }

  public QuestionIdentifier identifier() {
    return identifier;
  }

  public ExamTitle title() {
    return title;
  }

  public ExamDescription description() {
    return description;
  }

  public List<FormativeExamSectionRequest> sections() {
    return sections;
  }

  @Override
  public boolean sameAs(Object other) {
    if (!(other instanceof FormativeExamRequest)) {
      return false;
    }

    final FormativeExamRequest that = (FormativeExamRequest) other;
    if (this == that) {
      return true;
    }

    return identifier.equals(that.identifier) && title.equals(that.title) && description.equals(that.description)
        && sections.equals(that.sections);
  }

  @Override
  public QuestionIdentifier identity() {
    return identifier;
  }
}
