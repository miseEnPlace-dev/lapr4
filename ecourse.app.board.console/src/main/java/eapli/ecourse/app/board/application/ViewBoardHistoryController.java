package eapli.ecourse.app.board.application;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import eapli.ecourse.app.board.lib.BoardBackend;
import eapli.ecourse.app.board.lib.MessageListener;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.boardmanagement.dto.BoardHistoryDTO;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.common.board.protocol.UnsupportedVersionException;
import eapli.ecourse.postitmanagement.dto.PostItDTO;

public class ViewBoardHistoryController {
  private MessageListener listener;

  public ViewBoardHistoryController() {
    this.listener = BoardBackend.getInstance().getListener();
  }

  public Iterable<BoardDTO> listUserAccessibleBoards() throws IOException,
      UnsupportedVersionException, ClassNotFoundException, UnsuccessfulRequestException {
    ProtocolMessage response = listener.sendRecv(new ProtocolMessage(MessageCode.GET_BOARDS), MessageCode.GET_BOARDS);

    if (response.getCode().equals(MessageCode.ERR))
      throw new UnsuccessfulRequestException(response);
    Iterable<?> obj = (Iterable<?>) response.getPayloadAsObject();

    List<BoardDTO> result = StreamSupport.stream(obj.spliterator(), true).map(BoardDTO.class::cast)
        .collect(Collectors.toUnmodifiableList());

    return result;
  }

  public Iterable<PostItDTO> listBoardHistory(BoardDTO board) throws IOException,
      UnsupportedVersionException, ClassNotFoundException, UnsuccessfulRequestException {
    ProtocolMessage response = listener.sendRecv(
        new ProtocolMessage(MessageCode.GET_BOARD_HISTORY, board.getId().toString()),
        MessageCode.GET_BOARD_HISTORY);

    if (response.getCode().equals(MessageCode.ERR))
      throw new UnsuccessfulRequestException(response);
    Iterable<?> obj = (Iterable<?>) response.getPayloadAsObject();

    List<PostItDTO> result = StreamSupport.stream(obj.spliterator(), true)
        .map(PostItDTO.class::cast).collect(Collectors.toUnmodifiableList());

    return result;
  }

  public Iterable<BoardHistoryDTO> getBoardPostItHistory(BoardDTO board) throws IOException,
      UnsupportedVersionException, ClassNotFoundException, UnsuccessfulRequestException {
    ProtocolMessage response = listener.sendRecv(
        new ProtocolMessage(MessageCode.GET_BOARD_POST_IT_HISTORY, board.getId().toString()),
        MessageCode.GET_BOARD_POST_IT_HISTORY);

    if (response.getCode().equals(MessageCode.ERR))
      throw new UnsuccessfulRequestException(response);
    Iterable<?> obj = (Iterable<?>) response.getPayloadAsObject();

    List<BoardHistoryDTO> result = StreamSupport.stream(obj.spliterator(), true)
        .map(BoardHistoryDTO.class::cast).collect(Collectors.toUnmodifiableList());

    return result;
  }
}
