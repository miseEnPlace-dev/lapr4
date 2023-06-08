package eapli.ecourse.exammanagement.service;

import eapli.framework.io.util.Console;

/**
 * Format Verifier with all the rules for the application.
 *
 * @author André Barros <1211299@isep.ipp.pt>
 */
public final class FormatVerifier {
  /**
   * Private constructor to avoid instantiation.
   */
  private FormatVerifier() {
  }

  public static String validateNonEmptyString(String message) {
    String input = "";
    while (input.isEmpty()) {
      input = Console.readLine("\n" + message);
    }
    return input;
  }

  public static int readNonNegativeInteger(String message) {
    int input = -1;
    while (input < 0) {
      input = Console.readInteger("\n" + message);
      if (input < 0) {
        System.out.println("Invalid input. Please enter a non-negative number.");
      }
    }
    return input;
  }

  public static int readPositiveInteger(String message) {
    int input = -1;
    while (input <= 0) {
      input = Console.readInteger("\n" + message);
      if (input <= 0) {
        System.out.println("Invalid input. Please enter a positive number.");
      }
    }
    return input;
  }
}
