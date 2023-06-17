package eapli.ecourse.app.board.application;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import eapli.ecourse.app.board.lib.BoardBackend;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.common.board.TcpClient;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.common.board.protocol.UnsupportedVersionException;
import eapli.ecourse.postitmanagement.domain.PostItID;
import eapli.ecourse.postitmanagement.dto.PostItDTO;

public class ChangePostItController {
  private TcpClient server;

  public ChangePostItController() {
    this.server = BoardBackend.getInstance().getTcpClient();
  }

  public Iterable<BoardDTO> listUserWritableBoards() throws IOException,
      UnsupportedVersionException, ClassNotFoundException, UnsuccessfulRequestException {
    ProtocolMessage response =
        server.sendRecv(new ProtocolMessage(MessageCode.GET_WRITABLE_BOARDS));

    if (response.getCode().equals(MessageCode.ERR))
      throw new UnsuccessfulRequestException(response);
    Iterable<?> obj = (Iterable<?>) response.getPayloadAsObject();

    List<BoardDTO> result = StreamSupport.stream(obj.spliterator(), true).map(BoardDTO.class::cast)
        .collect(Collectors.toUnmodifiableList());

    return result;
  }

  public Iterable<PostItDTO> listUserUpdatablePostIts(BoardID boardID) throws IOException,
      UnsupportedVersionException, ClassNotFoundException, UnsuccessfulRequestException {
    ProtocolMessage response =
        server.sendRecv(new ProtocolMessage(MessageCode.GET_OWN_POSTITS_BOARD, boardID.toString()));

    if (response.getCode().equals(MessageCode.ERR))
      throw new UnsuccessfulRequestException(response);
    Iterable<?> obj = (Iterable<?>) response.getPayloadAsObject();

    List<PostItDTO> result = StreamSupport.stream(obj.spliterator(), true)
        .map(PostItDTO.class::cast).collect(Collectors.toUnmodifiableList());

    return result;
  }

  public boolean validateCoordinates(BoardID boardID, Integer x, Integer y) throws IOException,
      UnsupportedVersionException, UnsuccessfulRequestException, ClassNotFoundException {

    JsonObjectBuilder json = Json.createObjectBuilder();
    json.add("boardId", boardID.toString());
    json.add("x", x);
    json.add("y", y);

    ProtocolMessage response =
        server.sendRecv(new ProtocolMessage(MessageCode.IS_CELL_AVAILABLE, json.build()));

    if (response.getCode().equals(MessageCode.ERR))
      throw new UnsuccessfulRequestException(response);

    return (boolean) response.getPayloadAsObject();
  }

  public void changePostIt(PostItID postItID, Integer x, Integer y, String title,
      String description, String imagePath) throws IOException, UnsupportedVersionException,
      UnsuccessfulRequestException, ClassNotFoundException {

    JsonObjectBuilder json = Json.createObjectBuilder();
    json.add("postItId", postItID.toString());

    if (x != null)
      json.add("x", x);
    if (y != null)
      json.add("y", y);
    if (title != null)
      json.add("title", title);
    if (description != null)
      json.add("description", description);
    if (imagePath != null)
      json.add("imagePath", imagePath);

    ProtocolMessage response =
        server.sendRecv(new ProtocolMessage(MessageCode.CHANGE_POSTIT, json.build()));

    if (response.getCode().equals(MessageCode.ERR))
      throw new UnsuccessfulRequestException(response);

  }

  public void deletePostIt(PostItID postItID) throws IOException, UnsupportedVersionException,
      UnsuccessfulRequestException, ClassNotFoundException {

    ProtocolMessage response =
        server.sendRecv(new ProtocolMessage(MessageCode.DELETE_POSTIT, postItID.toString()));

    if (response.getCode().equals(MessageCode.ERR))
      throw new UnsuccessfulRequestException(response);

  }

}
