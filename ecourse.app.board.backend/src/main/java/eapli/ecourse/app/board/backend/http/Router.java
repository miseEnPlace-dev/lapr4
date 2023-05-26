package eapli.ecourse.app.board.backend.http;

import java.util.ArrayList;
import java.util.List;

public class Router {
  public List<Route> routes;
  // public List<Pair<String, Middleware>> middlewares;
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

  public void on(String path, String method, RouteHandler handler) {
    routes.add(new Route(method, path, handler));
  }

  public void use(Middleware middleware) {
    // path is "/" by default
    // use("/", middleware);
    middlewares.add(middleware);
  }

  // public void use(String path, Middleware middleware) {
  // middlewares.add(new Pair<>(path, middleware));
  // }

  public void handle(Request req, Response res) {
    // for (Pair<String, Middleware> entry : middlewares) {

    // NextFunction nextFunction = () -> {

    // handleRoute(req, res);
    // };

    // if (req.getPath().startsWith(path))
    // middleware.handle(req, res, this);
    // }

    for (Middleware middleware : middlewares) {
      middleware.handle(req, res, null);
    }

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
