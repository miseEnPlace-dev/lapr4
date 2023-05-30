package eapli.ecourse.app.board.common.http;

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

  public void get(String path, RouteController handler) {
    routes.add(new Route("GET", path, handler));
  }

  public void post(String path, RouteController handler) {
    routes.add(new Route("POST", path, handler));
  }

  public void put(String path, RouteController handler) {
    routes.add(new Route("PUT", path, handler));
  }

  public void patch(String path, RouteController handler) {
    routes.add(new Route("PATCH", path, handler));
  }

  public void delete(String path, RouteController handler) {
    routes.add(new Route("DELETE", path, handler));
  }

  public void on(String path, String method, RouteController handler) {
    routes.add(new Route(method, path, handler));
  }

  public void use(String path, Router router) {
    // middlewares.add(new Pair<>(path, middleware));
    router.handle(null, null);
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

    handleMiddleware(req, res, 0);
  }

  private void handleMiddleware(Request req, Response res, int index) {
    if (index >= middlewares.size()) {
      handleRoute(req, res);
      return;
    }

    Middleware middleware = middlewares.get(index);

    NextFunction nextFunction = () -> {
      handleMiddleware(req, res, index + 1);
    };

    middleware.handle(req, res, nextFunction);
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
