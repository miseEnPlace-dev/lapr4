package eapli.ecourse.exammanagement.domain;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import eapli.ecourse.exammanagement.domain.formative.FormativeExamSection;
import eapli.ecourse.questionmanagement.domain.Question;

public class FormativeExamSectionTest {

  private FormativeExamSection section;

  @Before
  public void setUp() {
    SectionIdentifier identifier = SectionIdentifier.valueOf("1");
    SectionTitle title = SectionTitle.valueOf("Section 1");
    SectionDescription description = SectionDescription.valueOf("This is section 1");
    List<Question> questions = new ArrayList<>();
    section = new FormativeExamSection(identifier, title, description, questions);
  }

  @Test
  public void testSameAs() {
    FormativeExamSection other = new FormativeExamSection(SectionIdentifier.valueOf("1"),
        SectionTitle.valueOf("Section 1"),
        SectionDescription.valueOf("This is section 1"), new ArrayList<>());
    assertTrue(section.sameAs(other));
  }
}
