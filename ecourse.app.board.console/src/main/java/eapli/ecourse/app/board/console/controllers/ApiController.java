package eapli.ecourse.app.board.console.controllers;

import javax.json.Json;
import javax.json.JsonObject;
import eapli.ecourse.common.board.http.Request;
import eapli.ecourse.common.board.http.Response;
import eapli.ecourse.common.board.http.RouteController;

public class ApiController implements RouteController {
  @Override
  public void handle(Request req, Response res) {
    JsonObject json = Json.createObjectBuilder().add("message", "Hello World!").build();
    res.json(json);
  }
}
