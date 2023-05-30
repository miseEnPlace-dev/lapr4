package eapli.ecourse.app.board.console.controllers;

import eapli.ecourse.app.board.common.http.Request;
import eapli.ecourse.app.board.common.http.Response;
import eapli.ecourse.app.board.common.http.RouteController;

public class ApiController implements RouteController {
  @Override
  public void handle(Request req, Response res) {
    res.send("Hello World!");
  }
}
