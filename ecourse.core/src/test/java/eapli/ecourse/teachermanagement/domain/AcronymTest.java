package eapli.ecourse.teachermanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class AcronymTest {
  @Test
  public void ensureItIsNotPossibleToCreateAnAcronymWithNullValue() {
    assertThrows(IllegalArgumentException.class, () -> Acronym.valueOf(null));
  }

  @Test
  public void ensureItIsNotPossibleToCreateAnAcronymWithBlankValue() {
    assertThrows(IllegalArgumentException.class, () -> Acronym.valueOf(""));
  }

  @Test
  public void ensureToStringReturnsTheAcronymValue() {
    Acronym acronym = Acronym.valueOf("ABC");
    assertEquals(acronym.toString(), "ABC");
  }

  @Test
  public void ensureTwoAcronymsAreEqualIfTheyHaveTheSameValue() {
    Acronym acronym1 = Acronym.valueOf("ABC");
    Acronym acronym2 = Acronym.valueOf("ABC");
    assertEquals(acronym1, acronym2);
  }

  @Test
  public void ensureTwoAcronymsAreNotEqualIfTheyHaveDifferentValues() {
    Acronym acronym1 = Acronym.valueOf("ABC");
    Acronym acronym2 = Acronym.valueOf("DEF");
    assertNotEquals(acronym1, acronym2);
  }

  @Test
  public void ensureHashCodeIsTheSameForTwoAcronymsWithTheSameValue() {
    Acronym acronym1 = Acronym.valueOf("ABC");
    Acronym acronym2 = Acronym.valueOf("ABC");
    assertEquals(acronym1.hashCode(), acronym2.hashCode());
  }
}
