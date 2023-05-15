package eapli.ecourse.app.common.console.presentation.teacher;

import eapli.framework.io.util.Console;

public class TeacherDataWidget {
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String email;
  private String taxPayerNumber;
  private String birthDate;
  private String acronym;

  public void show() {
    username = Console.readLine("Username");
    password = Console.readLine("Password");
    firstName = Console.readLine("First Name");
    lastName = Console.readLine("Last Name");
    email = Console.readLine("E-Mail");
    taxPayerNumber = Console.readLine("Tax Payer Number");
    birthDate = Console.readLine("Birth Date");
    acronym = Console.readLine("Acronym");
  }

  public String username() {
    return this.username;
  }

  public String password() {
    return this.password;
  }

  public String firstName() {
    return this.firstName;
  }

  public String lastName() {
    return this.lastName;
  }

  public String email() {
    return this.email;
  }

  public String taxPayerNumber() {
    return this.taxPayerNumber;
  }

  public String birthDate() {
    return this.birthDate;
  }

  public String acronym() {
    return this.acronym;
  }
}
