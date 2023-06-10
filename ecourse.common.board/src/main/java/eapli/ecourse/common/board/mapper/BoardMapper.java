package eapli.ecourse.common.board.mapper;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.common.board.dto.UserDTO;

public class BoardMapper {
  public static JsonObject toJson(BoardDTO board) {
    JsonArrayBuilder rolesJson = Json.createArrayBuilder();

    // for (Role role : roles) {
    // rolesJson.add(role.toString());
    // }

    // perms, rows, cols

    UserDTO owner = UserDTO.from(board.getOwner());

    JsonObjectBuilder json = Json.createObjectBuilder();

    json.add("id", board.getId().toString());
    json.add("title", board.getTitle().toString());

    if (board.getArchived() == null)
      json.add("archived", JsonValue.NULL);
    else
      json.add("archived", board.getArchived().toString());

    json.add("owner", UserMapper.toJson(owner));
    json.add("permissions", rolesJson.build());
    json.add("rows", rolesJson.build());
    json.add("columns", rolesJson.build());

    return json.build();
  }
}
