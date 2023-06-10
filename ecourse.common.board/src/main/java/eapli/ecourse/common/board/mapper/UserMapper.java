package eapli.ecourse.common.board.mapper;

import java.util.Collection;
import java.util.HashSet;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue.ValueType;
import eapli.ecourse.usermanagement.dto.UserDTO;
import eapli.framework.infrastructure.authz.domain.model.Role;

public class UserMapper {
  public static JsonObject toJson(UserDTO user) {
    JsonArrayBuilder rolesJson = Json.createArrayBuilder();

    for (Role role : user.getRoles()) {
      rolesJson.add(role.toString());
    }

    JsonObject json = Json.createObjectBuilder().add("username", user.getUsername())
        .add("name", user.getFullName()).add("email", user.getEmail())
        .add("roles", rolesJson.build()).build();

    return json;
  }

  public static UserDTO fromJson(JsonObject jsonObject) {
    String username = jsonObject.getString("username");
    String name = jsonObject.getString("name");
    String email = jsonObject.getString("email");

    Collection<Role> roles = new HashSet<>();

    jsonObject.getJsonArray("roles").forEach(role -> {
      if (role.getValueType().equals(ValueType.STRING))
        roles.add(Role.valueOf(((JsonString) role).getString()));
    });

    return new UserDTO(username, email, name, roles);
  }
}
