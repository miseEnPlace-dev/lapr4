package eapli.ecourse.app.board.controllers;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import eapli.ecourse.app.board.authz.CredentialStore;
import eapli.ecourse.app.board.lib.BoardBackend;
import eapli.ecourse.common.board.dto.UserDTO;
import eapli.ecourse.common.board.http.Request;
import eapli.ecourse.common.board.http.Response;
import eapli.ecourse.common.board.http.RouteController;

public class ViewBoardController implements RouteController {
  private CredentialStore auth;

  public ViewBoardController() {
    this.auth = BoardBackend.getInstance().getCredentialStore();
  }

  @Override
  public void handle(Request req, Response res) {
    String boardId = req.getParam("id");
    System.out.println(boardId);

    if (auth.getUser().isPresent()) {
      UserDTO authenticatedUser = auth.getUser().get();

      // ...
    } else {
      JsonObjectBuilder json = Json.createObjectBuilder().add("message", "Unauthorized");
      res.status(401).json(json.build());
    }
  }
}
