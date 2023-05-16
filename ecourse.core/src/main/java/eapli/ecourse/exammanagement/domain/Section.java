package eapli.ecourse.exammanagement.domain;

import java.util.ArrayList;
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

  public Section() {
    this.identifier = null;
    this.title = null;
    this.description = null;
    this.score = null;
    this.questions = new ArrayList<SectionQuestion>();
  }

  public Section(final Identifier identifier, final Title title, final Description description, final Score score) {
    this.identifier = identifier;
    this.title = title;
    this.description = description;
    this.score = score;
    this.questions = new ArrayList<SectionQuestion>();
  }

  public void addQuestion(SectionQuestion question) {
    this.questions.add(question);
  }

  public void addQuestions(Collection<SectionQuestion> questions) {
    this.questions.addAll(questions);
  }

  public void changeIdentifier(Identifier identifier) {
    this.identifier = identifier;
  }

  public void changeTitle(Title title) {
    this.title = title;
  }

  public void changeDescription(Description description) {
    this.description = description;
  }

  public void changeScore(Score score) {
    this.score = score;
  }
}
