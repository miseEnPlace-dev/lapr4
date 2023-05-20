package eapli.ecourse.exammanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class SectionTitleTest {

  @Test
  public void testSectionTitleCreation() {
    String title = "Section 1";
    SectionTitle sectionTitle = SectionTitle.valueOf(title);
    assertEquals(title, sectionTitle.toString());
  }

  @Test
  public void testSectionTitleCreationWithEmptyTitle() {
    assertThrows(IllegalArgumentException.class, () -> {
      SectionTitle.valueOf("");
    });
  }

  @Test
  public void testSectionTitleCreationWithLongTitle() {
    String longTitle = "This is a very long title that exceeds the maximum length of 255 characters. This is a very long title that exceeds the maximum length of 255 characters. This is a very long title that exceeds the maximum length of 255 characters. This is a very long title that exceeds the maximum length of 255 characters. This is a very long title that exceeds the maximum length of 255 characters. This is a very long title that exceeds the maximum length of 255 characters. This is a very long title that exceeds the maximum length of 255 characters. This is a very long title that exceeds the maximum length of 255 characters. This is a very long title that exceeds the maximum length of 255 characters. This is a very long title that exceeds the maximum length of 255 characters.";
    assertThrows(IllegalArgumentException.class, () -> {
      SectionTitle.valueOf(longTitle);
    });
  }

  @Test
  public void testSectionTitleEquality() {
    String title1 = "Section 1";
    String title2 = "Section 2";
    SectionTitle sectionTitle1 = SectionTitle.valueOf(title1);
    SectionTitle sectionTitle2 = SectionTitle.valueOf(title2);
    SectionTitle sectionTitle3 = SectionTitle.valueOf(title1);
    assertNotEquals(sectionTitle1, sectionTitle2);
    assertEquals(sectionTitle1, sectionTitle3);
  }

  @Test
  public void ensureHashCodeIsCorrect() {
    String title = "Section 1";
    SectionTitle sectionTitle = SectionTitle.valueOf(title);
    SectionTitle sectionTitle2 = SectionTitle.valueOf(title);
    assertEquals(sectionTitle2.hashCode(), sectionTitle.hashCode());
  }
}
