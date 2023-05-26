package eapli.ecourse.app.board.backend.http;

import java.util.ArrayList;
import java.util.List;

public class Router {
  public List<Route> routes;
  public List<Middleware> middlewares;

  public Router() {
    this.routes = new ArrayList<>();
    this.middlewares = new ArrayList<>();
  }

  public void get(String path, RouteHandler handler) {
    routes.add(new Route("GET", path, handler));
  }

  public void post(String path, RouteHandler handler) {
    routes.add(new Route("POST", path, handler));
  }

  public void put(String path, RouteHandler handler) {
    routes.add(new Route("PUT", path, handler));
  }

  public void patch(String path, RouteHandler handler) {
    routes.add(new Route("PATCH", path, handler));
  }

  public void delete(String path, RouteHandler handler) {
    routes.add(new Route("DELETE", path, handler));
  }

  // TODO
  public void use(String path, Middleware middleware) {
    // middlewares.add(middleware);
  }

  // TODO
  public void use(Middleware middleware) {
    // path is "/" by default
    middlewares.add(middleware);
  }

  public void handle(Request req, Response res) {
    // for (Middleware middleware : middlewares) {
    // middleware.handle(req, res, this);
    // }

    handleRoute(req, res);
  }

  private void handleRoute(Request req, Response res) {
    for (Route route : routes) {
      if (route.matches(req)) {
        route.handle(req, res);
        return;
      }
    }

    // 404
    res.status(404).send("Not found");
  }
}
