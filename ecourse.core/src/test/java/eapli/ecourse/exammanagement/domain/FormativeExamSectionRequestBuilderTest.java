package eapli.ecourse.exammanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import eapli.ecourse.exammanagement.domain.formative.FormativeExamSectionRequest;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamSectionRequestBuilder;

public class FormativeExamSectionRequestBuilderTest {
  @Test
  public void testBuild() {
    SectionIdentifier identifier = SectionIdentifier.valueOf("1");
    SectionTitle title = SectionTitle.valueOf("Test Section");
    SectionDescription description = SectionDescription.valueOf("This is a test section");
    Integer numberOfQuestions = 10;
    String questionsType = "Multiple Choice";
    FormativeExamSectionRequest request = new FormativeExamSectionRequest(identifier, title, description,
        numberOfQuestions, questionsType);
    FormativeExamSectionRequestBuilder builder = new FormativeExamSectionRequestBuilder();
    builder.withIdentifier(identifier);
    builder.withTitle(title);
    builder.withDescription(description);
    builder.withNumberOfQuestions(numberOfQuestions);
    builder.withQuestionsType(questionsType);

    FormativeExamSectionRequest builtRequest = builder.build();

    assertEquals(request.identifier(), builtRequest.identifier());
    assertEquals(request.title(), builtRequest.title());
    assertEquals(request.description(), builtRequest.description());
    assertEquals(request.numberOfQuestions(), builtRequest.numberOfQuestions());
    assertEquals(request.questionsType(), builtRequest.questionsType());
  }

  @Test
  public void ensureIdentifierIsMandatory() {
    SectionTitle title = SectionTitle.valueOf("Test Section");
    SectionDescription description = SectionDescription.valueOf("This is a test section");
    Integer numberOfQuestions = 10;
    String questionsType = "Multiple Choice";
    FormativeExamSectionRequestBuilder builder = new FormativeExamSectionRequestBuilder();
    builder.withTitle(title);
    builder.withDescription(description);
    builder.withNumberOfQuestions(numberOfQuestions);
    builder.withQuestionsType(questionsType);

    assertThrows(IllegalArgumentException.class, () -> builder.build());
  }

  @Test
  public void ensureTitleIsMandatory() {
    SectionIdentifier identifier = SectionIdentifier.valueOf("1");
    SectionDescription description = SectionDescription.valueOf("This is a test section");
    Integer numberOfQuestions = 10;
    String questionsType = "Multiple Choice";
    FormativeExamSectionRequestBuilder builder = new FormativeExamSectionRequestBuilder();
    builder.withIdentifier(identifier);
    builder.withDescription(description);
    builder.withNumberOfQuestions(numberOfQuestions);
    builder.withQuestionsType(questionsType);

    assertThrows(IllegalArgumentException.class, () -> builder.build());
  }

  @Test
  public void ensureDescriptionIsMandatory() {
    SectionIdentifier identifier = SectionIdentifier.valueOf("1");
    SectionTitle title = SectionTitle.valueOf("Test Section");
    Integer numberOfQuestions = 10;
    String questionsType = "Multiple Choice";
    FormativeExamSectionRequestBuilder builder = new FormativeExamSectionRequestBuilder();
    builder.withIdentifier(identifier);
    builder.withTitle(title);
    builder.withNumberOfQuestions(numberOfQuestions);
    builder.withQuestionsType(questionsType);

    assertThrows(IllegalArgumentException.class, () -> builder.build());
  }

  @Test
  public void ensureNumberOfQuestionsIsMandatory() {
    SectionIdentifier identifier = SectionIdentifier.valueOf("1");
    SectionTitle title = SectionTitle.valueOf("Test Section");
    SectionDescription description = SectionDescription.valueOf("This is a test section");
    String questionsType = "Multiple Choice";
    FormativeExamSectionRequestBuilder builder = new FormativeExamSectionRequestBuilder();
    builder.withIdentifier(identifier);
    builder.withTitle(title);
    builder.withDescription(description);
    builder.withQuestionsType(questionsType);

    assertThrows(IllegalArgumentException.class, () -> builder.build());
  }

  @Test
  public void ensureQuestionsTypeIsMandatory() {
    SectionIdentifier identifier = SectionIdentifier.valueOf("1");
    SectionTitle title = SectionTitle.valueOf("Test Section");
    SectionDescription description = SectionDescription.valueOf("This is a test section");
    Integer numberOfQuestions = 10;
    FormativeExamSectionRequestBuilder builder = new FormativeExamSectionRequestBuilder();
    builder.withIdentifier(identifier);
    builder.withTitle(title);
    builder.withDescription(description);
    builder.withNumberOfQuestions(numberOfQuestions);

    assertThrows(IllegalArgumentException.class, () -> builder.build());
  }
}
