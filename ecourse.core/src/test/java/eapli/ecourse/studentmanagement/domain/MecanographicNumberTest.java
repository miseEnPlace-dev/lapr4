package eapli.ecourse.studentmanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class MecanographicNumberTest {

  @Test
  public void testMecanographicNumberCreation() {
    String validMecanographicNumber = "12345";
    MecanographicNumber mecanographicNumber = new MecanographicNumber(validMecanographicNumber);
    assertEquals(validMecanographicNumber, mecanographicNumber.toString());
  }

  @Test
  public void testMecanographicNumberCreationWithInvalidNumber() {
    String invalidMecanographicNumber = "";
    assertThrows(IllegalArgumentException.class, () -> {
      new MecanographicNumber(invalidMecanographicNumber);
    });
  }
}
