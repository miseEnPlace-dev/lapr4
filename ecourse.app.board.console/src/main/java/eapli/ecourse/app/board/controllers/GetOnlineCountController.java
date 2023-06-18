package eapli.ecourse.app.board.controllers;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.app.board.application.UnsuccessfulRequestException;
import eapli.ecourse.app.board.lib.BoardBackend;
import eapli.ecourse.app.board.lib.MessageListener;
import eapli.ecourse.common.board.http.Request;
import eapli.ecourse.common.board.http.Response;
import eapli.ecourse.common.board.http.RouteController;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;

public class GetOnlineCountController implements RouteController {
  private final static Logger LOGGER = LogManager.getLogger(GetOnlineCountController.class);

  private MessageListener listener;

  public GetOnlineCountController() {
    this.listener = BoardBackend.getInstance().getListener();
  }

  @Override
  public void handle(Request req, Response res) {
    try {
      ProtocolMessage response = listener.sendRecv(
          new ProtocolMessage(MessageCode.GET_ONLINE_COUNT), MessageCode.GET_ONLINE_COUNT);

      if (response.getCode().equals(MessageCode.ERR))
        throw new UnsuccessfulRequestException(response);

      JsonObjectBuilder json =
          Json.createObjectBuilder().add("online", (Long) response.getPayloadAsObject());

      res.status(200).json(json.build());
    } catch (UnsuccessfulRequestException e) {
      JsonObjectBuilder json = Json.createObjectBuilder().add("message", e.getMessage());
      res.status(400).json(json.build());
    } catch (IOException | ClassNotFoundException e) {
      LOGGER.error("Error fetching online count", e);
      res.status(500).send("error");
    }
  }
}
