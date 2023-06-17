package eapli.ecourse.app.board.controllers;

import java.io.IOException;
import javax.json.Json;
import javax.json.JsonObjectBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.app.board.application.UnsuccessfulRequestException;
import eapli.ecourse.app.board.authz.CredentialStore;
import eapli.ecourse.app.board.lib.BoardBackend;
import eapli.ecourse.common.board.TcpClient;
import eapli.ecourse.common.board.dto.BoardWithPostItsDTO;
import eapli.ecourse.common.board.http.Request;
import eapli.ecourse.common.board.http.Response;
import eapli.ecourse.common.board.http.RouteController;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.common.board.protocol.UnsupportedVersionException;

public class ViewBoardController implements RouteController {
  private final static Logger LOGGER = LogManager.getLogger(ViewBoardController.class);

  private CredentialStore auth;
  private TcpClient client;

  public ViewBoardController() {
    this.auth = BoardBackend.getInstance().getCredentialStore();
    this.client = BoardBackend.getInstance().getTcpClient();
  }

  @Override
  public void handle(Request req, Response res) {
    if (auth.getUser().isEmpty()) {
      JsonObjectBuilder response = Json.createObjectBuilder().add("message", "Unauthorized");
      res.status(401).json(response.build());
      return;
    }

    String boardId = req.getParam("id");

    try {
      JsonObjectBuilder request = Json.createObjectBuilder().add("boardId", boardId);

      req.getQuery("hash").ifPresent(hash -> request.add("hash", hash));

      ProtocolMessage response =
          client.sendRecv(new ProtocolMessage(MessageCode.GET_BOARD, request.build()));

      // response has not been modified
      if (response.getCode().equals(MessageCode.NOT_MODIFIED)) {
        res.status(304).send();
        return;
      }

      if (response.getCode().equals(MessageCode.ERR))
        throw new UnsuccessfulRequestException(response);

      BoardWithPostItsDTO board = (BoardWithPostItsDTO) response.getPayloadAsObject();

      JsonObjectBuilder builder = board.toJsonBuilder().add("hash", board.hashCode());

      res.json(builder.build());
    } catch (UnsuccessfulRequestException e) {
      JsonObjectBuilder json = Json.createObjectBuilder().add("message", e.getMessage());
      res.status(400).json(json.build());
    } catch (IOException | UnsupportedVersionException | ClassNotFoundException e) {
      LOGGER.error("Error fetching boards", e);
      res.status(500).send("error");
    }
  }
}
