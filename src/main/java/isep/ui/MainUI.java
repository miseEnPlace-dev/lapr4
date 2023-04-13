package isep.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import isep.util.Console;

public class MainUI {
  public MainUI() {
  }

  public void run() throws IOException {
    List<MenuItem> options = new ArrayList<MenuItem>();
    options.add(new MenuItem("register user", new RegisterUserUI()));
    options.add(new MenuItem("Login", new AuthUI()));
    options.add(new MenuItem("Know the Development Team", new DevTeamUI()));

    int option = 0;

    do {
      option = Console.showAndSelectIndex(options, "\n\nMain Menu");

      if ((option >= 0) && (option < options.size()))
        options.get(option).run();
    } while (option != -1);
  }
}
