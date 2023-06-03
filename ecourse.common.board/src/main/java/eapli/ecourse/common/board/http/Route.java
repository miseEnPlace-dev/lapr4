package eapli.ecourse.common.board.http;

public class Route {
  private String method;
  private String path;
  private RouteController handler;

  public Route(String method, String path, RouteController handler) {
    this.method = method;
    this.path = path;
    this.handler = handler;
  }

  public boolean matches(Request req) {
    return req.getMethod().equals(method) && req.getPath().matches(path);
  }

  public void handle(Request req, Response res) {
    req.getPath().parseParams(path);
    handler.handle(req, res);
  }
}
