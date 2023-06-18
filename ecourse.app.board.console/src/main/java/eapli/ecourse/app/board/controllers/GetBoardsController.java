package eapli.ecourse.app.board.controllers;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.ecourse.app.board.application.UnsuccessfulRequestException;
import eapli.ecourse.app.board.authz.CredentialStore;
import eapli.ecourse.app.board.lib.BoardBackend;
import eapli.ecourse.app.board.lib.MessageListener;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.common.board.http.Request;
import eapli.ecourse.common.board.http.Response;
import eapli.ecourse.common.board.http.RouteController;
import eapli.ecourse.common.board.mapper.BoardMapper;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;

public class GetBoardsController implements RouteController {
  private final static Logger LOGGER = LogManager.getLogger(GetBoardsController.class);

  private CredentialStore auth;
  private MessageListener listener;

  public GetBoardsController() {
    this.auth = BoardBackend.getInstance().getCredentialStore();
    this.listener = BoardBackend.getInstance().getListener();
  }

  @Override
  public void handle(Request req, Response res) {
    if (auth.getUser().isEmpty()) {
      JsonObjectBuilder response = Json.createObjectBuilder().add("message", "Unauthorized");
      res.status(401).json(response.build());
      return;
    }

    try {
      ProtocolMessage response =
          listener.sendRecv(new ProtocolMessage(MessageCode.GET_BOARDS), MessageCode.GET_BOARDS);

      if (response.getCode().equals(MessageCode.ERR))
        throw new UnsuccessfulRequestException(response);

      Iterable<?> obj = (Iterable<?>) response.getPayloadAsObject();

      JsonArrayBuilder json = Json.createArrayBuilder();

      // convert to dto and add to json obj
      StreamSupport.stream(obj.spliterator(), true).map(BoardDTO.class::cast)
          .collect(Collectors.toUnmodifiableList()).forEach(b -> json.add(BoardMapper.toJson(b)));

      res.json(json.build());

    } catch (IOException | UnsuccessfulRequestException | ClassNotFoundException e) {
      LOGGER.error("Error fetching boards", e);
      res.status(500).send("error");
    }
  }
}
