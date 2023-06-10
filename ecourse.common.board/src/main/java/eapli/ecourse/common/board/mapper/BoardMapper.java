package eapli.ecourse.common.board.mapper;

import java.text.SimpleDateFormat;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.usermanagement.dto.UserDTO;

public class BoardMapper {
  public static JsonObject toJson(BoardDTO board) {
    JsonArrayBuilder rowJson = Json.createArrayBuilder(), colJson = Json.createArrayBuilder(),
        permsJson = Json.createArrayBuilder();

    board.getRows().forEach(r -> {
      JsonObjectBuilder row = Json.createObjectBuilder();
      row.add("number", r.identity());
      row.add("title", r.title().toString());
      rowJson.add(row.build());
    });

    board.getColumns().forEach(c -> {
      JsonObjectBuilder col = Json.createObjectBuilder();
      col.add("number", c.identity());
      col.add("title", c.title().toString());
      colJson.add(col.build());
    });

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    board.getPermissions().forEach(p -> {
      JsonObjectBuilder perm = Json.createObjectBuilder();
      perm.add("user", UserMapper.toJson(UserDTO.from(p.user())));
      perm.add("type", p.permissionType().toString());
      perm.add("createdAt", formatter.format(p.createdAt().getTime()));
      perm.add("updatedAt", formatter.format(p.updatedAt().getTime()));
      permsJson.add(perm.build());
    });

    UserDTO owner = UserDTO.from(board.getOwner());

    JsonObjectBuilder json = Json.createObjectBuilder();

    json.add("id", board.getId().toString());
    json.add("title", board.getTitle().toString());

    if (board.getArchived() == null)
      json.add("archived", JsonValue.NULL);
    else
      json.add("archived", board.getArchived().toString());

    json.add("owner", UserMapper.toJson(owner));
    json.add("permissions", permsJson.build());
    json.add("rows", rowJson.build());
    json.add("columns", colJson.build());

    return json.build();
  }
}
