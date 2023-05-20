package eapli.ecourse.exammanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class SectionDescriptionTest {

  @Test
  public void testSectionDescriptionCreation() {
    String description = "This is a section description.";
    SectionDescription sectionDescription = SectionDescription.valueOf(description);
    assertEquals(description, sectionDescription.toString());
  }

  @Test
  public void testSectionDescriptionCreationWithEmptyDescription() {
    assertThrows(IllegalArgumentException.class, () -> {
      SectionDescription.valueOf("");
    });
  }

  @Test
  public void testSectionDescriptionCreationWithLongDescription() {
    String longDescription = "This is a very long description that exceeds the maximum length of 255 characters. This is a very long description that exceeds the maximum length of 255 characters. This is a very long description that exceeds the maximum length of 255 characters. This is a very long description that exceeds the maximum length of 255 characters. This is a very long description that exceeds the maximum length of 255 characters. This is a very long description that exceeds the maximum length of 255 characters. This is a very long description that exceeds the maximum length of 255 characters. This is a very long description that exceeds the maximum length of 255 characters. This is a very long description that exceeds the maximum length of 255 characters. This is a very long description that exceeds the maximum length of 255 characters.";
    assertThrows(IllegalArgumentException.class, () -> {
      SectionDescription.valueOf(longDescription);
    });
  }

  @Test
  public void testSectionDescriptionEquality() {
    String description1 = "This is a section description.";
    String description2 = "This is another section description.";
    SectionDescription sectionDescription1 = SectionDescription.valueOf(description1);
    SectionDescription sectionDescription2 = SectionDescription.valueOf(description2);
    SectionDescription sectionDescription3 = SectionDescription.valueOf(description1);
    assertNotEquals(sectionDescription1, sectionDescription2);
    assertEquals(sectionDescription1, sectionDescription3);
  }

  @Test
  public void ensureHashCodeIsWorking() {
    String description1 = "This is a section description.";
    String description2 = "This is another section description.";
    SectionDescription sectionDescription1 = SectionDescription.valueOf(description1);
    SectionDescription sectionDescription2 = SectionDescription.valueOf(description2);
    SectionDescription sectionDescription3 = SectionDescription.valueOf(description1);
    assertNotEquals(sectionDescription1.hashCode(), sectionDescription2.hashCode());
    assertEquals(sectionDescription1.hashCode(), sectionDescription3.hashCode());
  }
}
