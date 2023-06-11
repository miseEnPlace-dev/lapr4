package eapli.ecourse.exammanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import eapli.ecourse.exammanagement.domain.evaluation.EvaluationExamSection;
import eapli.ecourse.questionmanagement.domain.Question;

public class EvaluationExamSectionTest {

  private SectionIdentifier identifier;
  private SectionTitle title;
  private SectionDescription description;
  private List<Question> questions;

  @Before
  public void setup() {
    identifier = SectionIdentifier.valueOf("SEC001");
    title = SectionTitle.valueOf("Section Title");
    description = SectionDescription.valueOf("Section Description");
    questions = new ArrayList<>();
  }

  @Test
  public void testEvaluationExamSectionCreation() {
    EvaluationExamSection section = new EvaluationExamSection(identifier, title, description, questions);
    assertEquals(identifier, section.identity());
    assertEquals(title, section.title());
    assertEquals(description, section.description());
    assertEquals(questions, section.questions());
  }

  @Test
  public void testEvaluationExamSectionEquality() {
    EvaluationExamSection section1 = new EvaluationExamSection(identifier, title, description, questions);
    EvaluationExamSection section2 = new EvaluationExamSection(identifier, title, description, questions);
    EvaluationExamSection section3 = new EvaluationExamSection(SectionIdentifier.valueOf("SEC002"), title, description,
        questions);
    assertEquals(section1, section2);
    assertNotEquals(section1, section3);
  }

  @Test
  public void ensureSameAsWorks() {
    EvaluationExamSection section1 = new EvaluationExamSection(identifier, title, description, questions);
    EvaluationExamSection section2 = new EvaluationExamSection(identifier, title, description, questions);
    EvaluationExamSection section3 = new EvaluationExamSection(SectionIdentifier.valueOf("SEC002"), title, description,
        questions);
    assertTrue(section1.sameAs(section2));
    assertFalse(section1.sameAs(section3));
  }
}
