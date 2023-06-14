package eapli.ecourse.exammanagement.domain.formative;

import java.util.List;

import eapli.ecourse.exammanagement.domain.ExamDescription;
import eapli.ecourse.exammanagement.domain.ExamIdentifier;
import eapli.ecourse.exammanagement.domain.ExamInfo;
import eapli.ecourse.exammanagement.domain.ExamTitle;
import eapli.ecourse.exammanagement.domain.evaluation.ExamScore;
import eapli.framework.domain.model.DomainEntityBase;

public class FormativeExamRequest extends DomainEntityBase<ExamIdentifier> {
  private ExamIdentifier identifier;
  private ExamTitle title;
  private ExamDescription description;
  private ExamScore score;
  private List<FormativeExamSectionRequest> sections;
  private ExamInfo gradeInfo;

  public FormativeExamRequest(ExamIdentifier identifier, ExamTitle title, ExamDescription description, ExamScore score,
      List<FormativeExamSectionRequest> sections, ExamInfo gradExamInfo) {
    this.identifier = identifier;
    this.title = title;
    this.description = description;
    this.sections = sections;
    this.gradeInfo = gradExamInfo;
    this.score = score;
  }

  public ExamIdentifier identifier() {
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

  public ExamScore score() {
    return this.score;
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
        && sections.equals(that.sections) && score.equals(that.score) && gradeInfo.equals(that.gradeInfo);
  }

  @Override
  public ExamIdentifier identity() {
    return identifier;
  }
}
