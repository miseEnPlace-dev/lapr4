package isep.ui;

import isep.util.Console;

public class RegisterUserUI implements Runnable {
  // RegisterUserController ctrl;

  public RegisterUserUI() {
    // this.ctrl = new RegisterUserController();
  }

  public void run() {
    String email = Console.readLineFromConsole("Email: ");
    String fullName = Console.readLineFromConsole("Full name: ");
    String shortName = Console.readLineFromConsole("Short name: ");
    String password = Console.readLineFromConsole("Password: ");

    // SystemUser u = ctrl.addUser(email, fullName, shortName, password);
  }
}
