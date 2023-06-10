package eapli.ecourse.app.board.controllers;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import eapli.ecourse.app.board.authz.CredentialStore;
import eapli.ecourse.app.board.lib.BoardBackend;
import eapli.ecourse.common.board.http.Request;
import eapli.ecourse.common.board.http.Response;
import eapli.ecourse.common.board.http.RouteController;
import eapli.ecourse.common.board.mapper.UserMapper;

public class GetSessionController implements RouteController {
  private CredentialStore auth = BoardBackend.getInstance().getCredentialStore();

  @Override
  public void handle(Request req, Response res) {
    if (auth.getUser().isPresent()) {
      res.json(UserMapper.toJson(auth.getUser().get()));
    } else {
      JsonObjectBuilder json = Json.createObjectBuilder().add("message", "Unauthorized");
      res.status(401).json(json.build());
    }
  }
}
