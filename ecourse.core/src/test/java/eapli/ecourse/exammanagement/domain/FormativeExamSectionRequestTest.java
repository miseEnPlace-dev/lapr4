package eapli.ecourse.exammanagement.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import eapli.ecourse.exammanagement.domain.formative.FormativeExamSectionRequest;

public class FormativeExamSectionRequestTest {

  private FormativeExamSectionRequest request;

  @Before
  public void setUp() {
    SectionIdentifier identifier = SectionIdentifier.valueOf("1");
    SectionTitle title = SectionTitle.valueOf("Test Section");
    SectionDescription description = SectionDescription.valueOf("This is a test section");
    Integer numberOfQuestions = 10;
    String questionsType = "Multiple Choice";
    request = new FormativeExamSectionRequest(identifier, title, description, numberOfQuestions, questionsType);
  }

  @Test
  public void testChangeIdentifier() {
    SectionIdentifier newIdentifier = SectionIdentifier.valueOf("2");
    request.changeIdentifier(newIdentifier);
    assertEquals(newIdentifier, request.identifier());
  }

  @Test
  public void testChangeTitle() {
    SectionTitle newTitle = SectionTitle.valueOf("New Test Section");
    request.changeTitle(newTitle);
    assertEquals(newTitle, request.title());
  }

  @Test
  public void testChangeDescription() {
    SectionDescription newDescription = SectionDescription.valueOf("This is a new test section");
    request.changeDescription(newDescription);
    assertEquals(newDescription, request.description());
  }

  @Test
  public void testChangeNumberOfQuestions() {
    Integer newNumberOfQuestions = 20;
    request.changeNumberOfQuestions(newNumberOfQuestions);
    assertEquals(newNumberOfQuestions, request.numberOfQuestions());
  }

  @Test
  public void testChangeQuestionsType() {
    String newQuestionsType = "True/False";
    request.changeQuestionsType(newQuestionsType);
    assertEquals(newQuestionsType, request.questionsType());
  }
}
