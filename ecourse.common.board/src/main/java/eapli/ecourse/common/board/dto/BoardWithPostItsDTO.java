package eapli.ecourse.common.board.dto;

import java.io.Serializable;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.common.board.mapper.BoardMapper;
import eapli.ecourse.common.board.mapper.PostItMapper;
import eapli.ecourse.postitmanagement.dto.PostItDTO;

public class BoardWithPostItsDTO implements Serializable {
  private static final long serialVersionUID = 1L;

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

  public JsonObjectBuilder toJsonBuilder() {
    JsonObjectBuilder json = Json.createObjectBuilder(BoardMapper.toJson(board));
    JsonArrayBuilder postItsJson = Json.createArrayBuilder();

    postIts.forEach(postIt -> postItsJson.add(PostItMapper.toJson(postIt)));
    json.add("postIts", postItsJson);

    return json;
  }

  public int hashCode() {
    HashCodeBuilder hashBuilder = new HashCodeBuilder().appendSuper(board.hashCode());
    postIts.forEach(postIt -> hashBuilder.appendSuper(postIt.hashCode()));
    return hashBuilder.toHashCode();
  }
}
