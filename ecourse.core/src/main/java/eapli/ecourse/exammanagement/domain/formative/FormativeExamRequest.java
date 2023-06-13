package eapli.ecourse.exammanagement.domain.formative;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.ExamDescription;
import eapli.ecourse.exammanagement.domain.ExamIdentifier;
import eapli.ecourse.exammanagement.domain.ExamTitle;
import eapli.ecourse.exammanagement.domain.evaluation.ExamScore;
import eapli.ecourse.exammanagement.dto.FormativeExamRequestDTO;
import eapli.framework.domain.model.AggregateRoot;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode
public class FormativeExamRequest implements AggregateRoot<ExamIdentifier> {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @EmbeddedId
  private ExamIdentifier identifier;

  @Column(nullable = false)
  private ExamTitle title;

  @Column(nullable = false)
  private ExamDescription description;

  @Column(nullable = false)
  private ExamScore score;

  @Column(nullable = false)
  private Course course;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<FormativeExamSectionRequest> sections;

  public FormativeExamRequest(ExamIdentifier identifier, ExamTitle title, ExamDescription description, ExamScore score,
      List<FormativeExamSectionRequest> sections, Course course) {
    this.identifier = identifier;
    this.title = title;
    this.description = description;
    this.sections = sections;
    this.score = score;
    this.course = course;
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
        && sections.equals(that.sections);
  }

  @Override
  public ExamIdentifier identity() {
    return identifier;
  }

  public FormativeExamRequestDTO toDto() {
    return new FormativeExamRequestDTO(identifier, title, description);
  }
}
