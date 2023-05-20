package eapli.ecourse.teachermanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Calendar;

import org.junit.Test;

public class BirthDateTest {
  @Test
  public void ensureItIsNotPossibleToCreateAnBirthDateWithNullValue() {
    assertThrows(IllegalArgumentException.class, () -> BirthDate.valueOf(null));
  }

  @Test
  public void testBirthDateCreation() {
    Calendar birthDate = Calendar.getInstance();
    birthDate.set(1990, Calendar.JANUARY, 1);
    BirthDate bd = BirthDate.valueOf(birthDate);
    assertEquals(BirthDate.valueOf(birthDate), bd);
  }

  @Test
  public void testBirthDateCreationWithNull() {
    assertThrows(IllegalArgumentException.class, () -> {
      BirthDate.valueOf(null);
    });
  }

  @Test
  public void testBirthDateCreationWithFutureDate() {
    Calendar birthDate = Calendar.getInstance();
    birthDate.add(Calendar.DAY_OF_MONTH, 1);
    assertThrows(IllegalArgumentException.class, () -> {
      BirthDate.valueOf(birthDate);
    });
  }

  @Test
  public void ensureToStringPrintsInCorrectFormat() {
    Calendar birthDate = Calendar.getInstance();
    birthDate.set(1990, Calendar.JANUARY, 1);
    BirthDate bd = BirthDate.valueOf(birthDate);
    assertEquals(bd.toString(), "01/01/1990");
  }
}
