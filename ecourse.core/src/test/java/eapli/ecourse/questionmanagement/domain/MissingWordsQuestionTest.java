package eapli.ecourse.questionmanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MissingWordsQuestionTest {
  private MissingWordsQuestion question;

  @Before
  public void setUp() {
    question = new MissingWordsQuestion(
        new QuestionBody("The capital of Portugal is ___. The currency used in Portugal is the ___."),
        QuestionType.FORMATIVE);
  }

  @Test
  public void testAddMissingWord() {
    String missingWord = "Lisbon";
    question.addMissingWord(missingWord);
    List<String> expected = Arrays.asList(missingWord);
    assertEquals(expected, question.missingWords());
  }

  @Test
  public void testAddOption() {
    String option = "Euro";
    question.addOption(option);
    List<String> expected = Arrays.asList(option);
    assertEquals(expected, question.options());
  }

  @Test
  public void testSameAs() {
    MissingWordsQuestion other = new MissingWordsQuestion(
        new QuestionBody("The capital of Portugal is ___. The currency used in Portugal is the ___."),
        QuestionType.FORMATIVE);
    assertTrue(question.sameAs(other));
  }
}
