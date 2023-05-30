package eapli.ecourse.app.board.console;

import eapli.ecourse.app.board.common.HttpServer;
import eapli.ecourse.app.board.common.TcpClient;
import eapli.ecourse.app.board.common.http.Router;
import eapli.ecourse.app.board.common.http.StaticMiddleware;
import eapli.ecourse.app.board.console.controllers.ApiController;

public class App {
  // move to properties
  private static final String BOARD_SERVER_HOST = "localhost";
  private static final int BOARD_SERVER_PORT = 9999;

  private static final int HTTP_SERVER_PORT = 8080;
  private static final String WWW_PATH = "www";

  public static void main(String[] args) {
    // create the router
    Router router = new Router();

    // retrieve static files middleware
    router.use(new StaticMiddleware(WWW_PATH));

    // add your routes here
    router.get("/api", new ApiController());

    // ...

    // create the http server
    HttpServer httpServer = new HttpServer(HTTP_SERVER_PORT, router);
    Thread httpServerThread = new Thread(httpServer);

    httpServerThread.start();

    // connect to the shared board server
    TcpClient tcpClient = new TcpClient(BOARD_SERVER_HOST, BOARD_SERVER_PORT);
    tcpClient.connect();
  }
}
