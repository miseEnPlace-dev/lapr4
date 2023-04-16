package eapli.ecourse.app.student.console.presentation.myuser;

import eapli.framework.io.util.Console;

/**
 * widget for reading user data Jorge Santos ajs@isp.ipp.pt
 */
class UserDataWidget {

  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String email;
  private String mecanographicNumber;

  public void show() {
    username = Console.readLine("Username");
    password = Console.readLine("Password");
    firstName = Console.readLine("First Name");
    lastName = Console.readLine("Last Name");
    email = Console.readLine("E-Mail");
    mecanographicNumber = Console.readLine("Mecanographic Number");
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

  public String mecanographicNumber() {
    return mecanographicNumber;
  }
}
