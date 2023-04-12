package isep;

import java.sql.SQLException;
import org.h2.tools.Server;
import isep.ui.MainUI;

public class App {
  public static void main(String[] args) throws SQLException {
    try {
      startDatabase();
      MainUI menu = new MainUI();
      menu.run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void startDatabase() throws SQLException {
    new Server().runTool("-tcp", "-web", "-ifNotExists");
  }
}
