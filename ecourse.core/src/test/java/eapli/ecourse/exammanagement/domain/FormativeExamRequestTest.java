package eapli.ecourse.exammanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import eapli.ecourse.exammanagement.domain.evaluation.ExamScore;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamRequest;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamRequestBuilder;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamSectionRequest;

public class FormativeExamRequestTest {
  private FormativeExamRequest request;
  private ExamIdentifier identifier;
  private ExamTitle title;
  private ExamDescription description;
  private ExamScore score;
  private List<FormativeExamSectionRequest> sections;

  @Before
  public void setUp() {
    identifier = ExamIdentifier.valueOf("1");
    title = ExamTitle.valueOf("Test Exam");
    description = ExamDescription.valueOf("This is a test exam");
    sections = new ArrayList<>();
    SectionIdentifier sectionIdentifier = SectionIdentifier.valueOf("1");
    SectionTitle sectionTitle = SectionTitle.valueOf("Test Section");
    SectionDescription sectionDescription = SectionDescription.valueOf("This is a test section");
    Integer numberOfQuestions = 10;
    String questionsType = "Multiple Choice";
    score = ExamScore.valueOf(12d);
    FormativeExamSectionRequest sectionRequest = new FormativeExamSectionRequest(sectionIdentifier, sectionTitle,
        sectionDescription,
        numberOfQuestions, questionsType);
    sections.add(sectionRequest);
    request = new FormativeExamRequest(identifier, title, description, score, sections);
  }

  @Test
  public void testIdentifier() {
    assertEquals(identifier, request.identifier());
  }

  @Test
  public void testTitle() {
    assertEquals(title, request.title());
  }

  @Test
  public void testDescription() {
    assertEquals(description, request.description());
  }

  @Test
  public void testSections() {
    assertEquals(sections, request.sections());
  }

  @Test
  public void testSameAs() {
    FormativeExamRequest other = new FormativeExamRequest(identifier, title, description, score, sections);
    assertTrue(request.sameAs(other));
  }

  @Test
  public void testEquals() {
    FormativeExamRequest other = new FormativeExamRequest(identifier, title, description, score, sections);
    assertEquals(request, other);
  }

  @Test
  public void testHashCode() {
    FormativeExamRequest other = new FormativeExamRequest(identifier, title, description, score, sections);
    assertEquals(request.hashCode(), other.hashCode());
  }

  @Test
  public void testBuilder() {
    FormativeExamRequestBuilder builder = new FormativeExamRequestBuilder();
    builder.withIdentifier(identifier);
    builder.withTitle(title);
    builder.withDescription(description);
    builder.withScore(score);
    builder.withSections(sections);
    FormativeExamRequest other = builder.build();
    assertEquals(request, other);
  }
}
