package isep;

import java.sql.SQLException;
import org.h2.tools.Server;

public class App {
    public static void main(String[] args) throws SQLException {
        startDatabase();
        System.out.println("Hello World!");
    }

    private static void startDatabase() throws SQLException {
        new Server().runTool("-tcp", "-web", "-ifNotExists");
    }
}
