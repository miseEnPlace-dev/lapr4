package eapli.ecourse.app.backoffice.console.presentation.authz;

import eapli.framework.io.util.Console;

public class SystemUserDataWidget {
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String email;

  public void show() {
    username = Console.readLine("Username");
    password = Console.readLine("Password");
    firstName = Console.readLine("First Name");
    lastName = Console.readLine("Last Name");
    email = Console.readLine("E-Mail");
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
}
