package eapli.ecourse.exammanagement.domain;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import eapli.ecourse.questionmanagement.domain.Identifier;

@Entity
/**
 * A section of an exam.
 */
public class Section {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  private Identifier identifier;

  @Column(nullable = false)
  private Title title;

  @Column(nullable = false)
  private Description description;

  @Column(nullable = false)
  private Score score;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<SectionQuestion> questions;

  protected Section() {
    // For ORM only
  }

  public Section(final Identifier identifier, final Title title, final Description description, final Score score) {
    this.identifier = identifier;
    this.title = title;
    this.description = description;
    this.score = score;
    this.questions = new LinkedList<SectionQuestion>();
  }

  public void addQuestion(SectionQuestion question) {
    this.questions.add(question);
  }

  public void addQuestions(Collection<SectionQuestion> questions) {
    this.questions.addAll(questions);
  }
}
