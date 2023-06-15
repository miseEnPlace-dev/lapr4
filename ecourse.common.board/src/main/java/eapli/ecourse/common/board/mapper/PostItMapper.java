package eapli.ecourse.common.board.mapper;

import java.text.SimpleDateFormat;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import eapli.ecourse.postitmanagement.domain.PostItDescription;
import eapli.ecourse.postitmanagement.domain.PostItImage;
import eapli.ecourse.postitmanagement.dto.PostItDTO;

public class PostItMapper {
  public static JsonObject toJson(PostItDTO p) {
    JsonObjectBuilder json = Json.createObjectBuilder();

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    JsonObjectBuilder coordinates = Json.createObjectBuilder();
    coordinates.add("x", p.getCoordinates().getX());
    coordinates.add("y", p.getCoordinates().getY());

    PostItImage image = p.getImage();
    PostItDTO previous = p.getPrevious();
    PostItDescription description = p.getDescription();

    json.add("id", p.getId().toString());
    json.add("title", p.getTitle().toString());
    json.add("coordinates", coordinates.build());
    json.add("state", p.getState().toString());
    json.add("boardId", p.getBoard().getId().toString());
    json.add("owner", UserMapper.toJson(p.getOwner()));
    json.add("createdAt", formatter.format(p.getCreatedAt().getTime()));
    json.add("isLatest", p.isLatest());

    // description
    if (description == null)
      json.add("description", JsonValue.NULL);
    else
      json.add("description", description.toString());

    // previous
    if (previous == null)
      json.add("previous", JsonValue.NULL);
    else
      json.add("previous", PostItMapper.toJson(p.getPrevious()));

    // img
    if (image == null)
      json.add("image", JsonValue.NULL);
    else
      json.add("image", image.toString());

    return json.build();
  }
}
