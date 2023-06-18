package eapli.ecourse.app.board.application;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import eapli.ecourse.app.board.lib.BoardBackend;
import eapli.ecourse.app.board.lib.MessageListener;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.common.board.protocol.UnsupportedVersionException;
import eapli.ecourse.postitmanagement.dto.PostItDTO;

public class UndoPostItChangeController {
  private MessageListener listener;

  public UndoPostItChangeController() {
    this.listener = BoardBackend.getInstance().getListener();
  }

  public Iterable<BoardDTO> listUserWritableBoards() throws IOException,
      UnsupportedVersionException, ClassNotFoundException, UnsuccessfulRequestException {
    ProtocolMessage response = listener.sendRecv(
        new ProtocolMessage(MessageCode.GET_WRITABLE_BOARDS), MessageCode.GET_WRITABLE_BOARDS);

    if (response.getCode().equals(MessageCode.ERR))
      throw new UnsuccessfulRequestException(response);
    Iterable<?> obj = (Iterable<?>) response.getPayloadAsObject();

    List<BoardDTO> result = StreamSupport.stream(obj.spliterator(), true).map(BoardDTO.class::cast)
        .collect(Collectors.toUnmodifiableList());

    return result;
  }

  public Iterable<PostItDTO> listLatestBoardPostItsByUser(BoardDTO board) throws IOException,
      UnsupportedVersionException, UnsuccessfulRequestException, ClassNotFoundException {
    ProtocolMessage response = listener.sendRecv(
        new ProtocolMessage(MessageCode.GET_POSTITS_BOARD, board.getId().toString()),
        MessageCode.GET_POSTITS_BOARD);

    if (response.getCode().equals(MessageCode.ERR))
      throw new UnsuccessfulRequestException(response);

    Iterable<?> obj = (Iterable<?>) response.getPayloadAsObject();

    List<PostItDTO> result = StreamSupport.stream(obj.spliterator(), true)
        .map(PostItDTO.class::cast).collect(Collectors.toUnmodifiableList());

    return result;
  }

  public void undoPostItChange(PostItDTO postIt) throws IOException, UnsupportedVersionException,
      UnsuccessfulRequestException, ClassNotFoundException {
    ProtocolMessage response =
        listener.sendRecv(new ProtocolMessage(MessageCode.UNDO_POSTIT, postIt.getId().toString()),
            MessageCode.UNDO_POSTIT);

    if (response.getCode().equals(MessageCode.ERR))
      throw new UnsuccessfulRequestException(response);
  }
}
