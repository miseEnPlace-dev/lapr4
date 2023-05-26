package eapli.ecourse.app.board.backend.http;

public class Route {
  private String method;
  private String path;
  private RouteHandler handler;

  public Route(String method, String path, RouteHandler handler) {
    this.method = method;
    this.path = path;
    this.handler = handler;
  }

  public boolean matches(Request req) {
    return req.getMethod().equals(method) && req.getPath().equals(path);
  }

  public void handle(Request req, Response res) {
    handler.handle(req, res);
  }
}
