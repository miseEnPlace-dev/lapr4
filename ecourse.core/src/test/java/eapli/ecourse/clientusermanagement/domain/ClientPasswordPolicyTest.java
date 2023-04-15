package eapli.ecourse.clientusermanagement.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import eapli.ecourse.usermanagement.domain.ClientPasswordPolicy;
import eapli.ecourse.usermanagement.domain.ClientPasswordPolicy.PasswordStrength;

/**
 * @author Paulo Gandra de Sousa 18/04/2020
 */
class ClientPasswordPolicyTest {

  private final ClientPasswordPolicy subject = new ClientPasswordPolicy();

  @Test
  void ensurePasswordHasAtLeastOneDigitOneCapitalAnd6CharactersLong() {
    assertTrue(subject.isSatisfiedBy("abCfefgh1"));
  }

  @Test
  void ensurePasswordsSmallerThan6CharactersAreNotAllowed() {
    assertFalse(subject.isSatisfiedBy("ab1c"));
  }

  @Test
  void ensurePasswordsWithoutDigitsAreNotAllowed() {
    assertFalse(subject.isSatisfiedBy("abcefghi"));
  }

  @Test
  void ensurePasswordsWithoutCapitalLetterAreNotAllowed() {
    assertFalse(subject.isSatisfiedBy("abcefghi1"));
  }

  @Test
  void testWeakPassword1() {
    assertEquals(PasswordStrength.WEAK, subject.strength("A23456"));
  }

  @Test
  void testWeakPassword2() {
    assertEquals(PasswordStrength.WEAK, subject.strength("A234567"));
  }

  @Test
  void testGoodPassword1() {
    assertEquals(PasswordStrength.GOOD, subject.strength("A2345678"));
  }

  @Test
  void testGoodPassword2() {
    assertEquals(PasswordStrength.GOOD, subject.strength("A23456789"));
  }

  @Test
  void testExcelentPassword1() {
    assertEquals(PasswordStrength.EXCELENT, subject.strength("123456789ABC"));
  }

  @Test
  void testExcelentPassword2() {
    assertEquals(PasswordStrength.EXCELENT, subject.strength("123456789ABCD"));
  }

  @Test
  void testExcelentPassword3() {
    assertEquals(PasswordStrength.EXCELENT, subject.strength("A234$5678"));
  }

  @Test
  void testExcelentPassword4() {
    assertEquals(PasswordStrength.EXCELENT, subject.strength("A234#5678"));
  }

  @Test
  void testExcelentPassword5() {
    assertEquals(PasswordStrength.EXCELENT, subject.strength("A234!5678"));
  }

  @Test
  void testExcelentPassword6() {
    assertEquals(PasswordStrength.EXCELENT, subject.strength("A234?5678"));
  }
}
