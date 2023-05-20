package eapli.ecourse.exammanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SectionIdentifierTest {
  @Test
  public void testSectionIdentifierCreation() {
    String identifier = "Exam 1";
    SectionIdentifier sectionIdentifier = SectionIdentifier.valueOf(identifier);
    assertEquals(identifier, sectionIdentifier.toString());
  }

  @Test
  public void testSectionIdentifierCreationWithEmptyIdentifier() {
    assertThrows(IllegalArgumentException.class, () -> {
      SectionIdentifier.valueOf("");
    });
  }

  @Test
  public void testSectionIdentifierCreationWithLongIdentifier() {
    String longIdentifier = "This is a very long identifier that exceeds the maximum length of 255 characters. This is a very long identifier that exceeds the maximum length of 255 characters. This is a very long identifier that exceeds the maximum length of 255 characters. This is a very long identifier that exceeds the maximum length of 255 characters. This is a very long identifier that exceeds the maximum length of 255 characters. This is a very long identifier that exceeds the maximum length of 255 characters. This is a very long identifier that exceeds the maximum length of 255 characters. This is a very long identifier that exceeds the maximum length of 255 characters. This is a very long identifier that exceeds the maximum length of 255 characters. This is a very long identifier that exceeds the maximum length of 255 characters.";
    assertThrows(IllegalArgumentException.class, () -> {
      SectionIdentifier.valueOf(longIdentifier);
    });
  }

  @Test
  public void testSectionIdentifierEquality() {
    String identifier1 = "Exam 1";
    String identifier2 = "Exam 2";
    SectionIdentifier sectionIdentifier1 = SectionIdentifier.valueOf(identifier1);
    SectionIdentifier sectionIdentifier2 = SectionIdentifier.valueOf(identifier2);
    SectionIdentifier sectionIdentifier3 = SectionIdentifier.valueOf(identifier1);
    assertNotEquals(sectionIdentifier1, sectionIdentifier2);
    assertEquals(sectionIdentifier1, sectionIdentifier3);
  }

  @Test
  public void ensureHashCodeIsCorrect() {
    String identifier = "Exam 1";
    SectionIdentifier sectionIdentifier = SectionIdentifier.valueOf(identifier);
    SectionIdentifier sectionIdentifier2 = SectionIdentifier.valueOf(identifier);
    assertEquals(sectionIdentifier2.hashCode(), sectionIdentifier.hashCode());
  }

  @Test
  public void ensureCompareToIsWorking() {
    String identifier1 = "Exam 1";
    String identifier2 = "Exam 2";
    SectionIdentifier sectionIdentifier1 = SectionIdentifier.valueOf(identifier1);
    SectionIdentifier sectionIdentifier2 = SectionIdentifier.valueOf(identifier2);
    assertTrue(sectionIdentifier1.compareTo(sectionIdentifier2) < 0);
    assertTrue(sectionIdentifier2.compareTo(sectionIdentifier1) > 0);
    assertEquals(0, sectionIdentifier1.compareTo(sectionIdentifier1));
  }

  @Test
  public void ensureToStringReturnsTheIdentifier() {
    String identifier = "Exam 1";
    SectionIdentifier sectionIdentifier = SectionIdentifier.valueOf(identifier);
    assertEquals(identifier, sectionIdentifier.toString());
  }
}
