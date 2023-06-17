package eapli.ecourse.common.board.dto;

import javax.json.JsonStructure;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.postitmanagement.dto.PostItDTO;

public class BoardWithPostItsDTO {
  private BoardDTO board;
  private Iterable<PostItDTO> postIts;

  public BoardWithPostItsDTO(BoardDTO board, Iterable<PostItDTO> postIts) {
    this.board = board;
    this.postIts = postIts;
  }

  public BoardDTO getBoard() {
    return board;
  }

  public Iterable<PostItDTO> getPostIts() {
    return postIts;
  }

  public JsonStructure toJson() {
    return null;
  }

  public int hashCode() {
    HashCodeBuilder hashBuilder = new HashCodeBuilder().appendSuper(board.hashCode());
    postIts.forEach(postIt -> hashBuilder.appendSuper(postIt.hashCode()));
    return hashBuilder.toHashCode();
  }
}
