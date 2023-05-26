package eapli.ecourse.app.board.backend.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Path {
  private String path;
  private Map<String, List<String>> query;
  private Map<String, String> params;

  public Path(String path) {
    this.query = new HashMap<>();
    this.params = new HashMap<>();

    String[] parts = path.split("\\?");

    this.path = parts[0];
    parseQuery(parts[1]);
  }

  public String getPath() {
    return path;
  }

  public String getQuery(String name) {
    return query.get(name) == null ? null : query.get(name).get(0);
  }

  public List<String> getArrayQuery(String name) {
    return query.get(name);
  }

  public boolean matches(String path) {
    // path = /test/:id/aa
    // this.path = /test/1/aa
    // this should match
    String[] pathParts = path.split("/");
    String[] thisPathParts = this.path.split("/");

    if (pathParts.length != thisPathParts.length)
      return false;

    for (int i = 0; i < pathParts.length; i++) {
      String pathPart = pathParts[i];
      String thisPathPart = thisPathParts[i];

      if (thisPathPart.startsWith(":"))
        continue;

      if (!pathPart.equals(thisPathPart))
        return false;
    }

    return true;
  }

  public String getParam(String name) {
    return params.get(name);
  }

  public void parseParams(String path) {
    String[] pathParts = path.split("/");
    String[] thisPathParts = this.path.split("/");

    for (int i = 0; i < pathParts.length; i++) {
      String pathPart = pathParts[i];
      String thisPathPart = thisPathParts[i];

      if (thisPathPart.startsWith(":")) {
        String paramName = thisPathPart.substring(1);
        params.put(paramName, pathPart);
      }
    }
  }

  private void parseQuery(String query) {
    String[] parts = query.split("&");

    for (String part : parts) {
      String[] keyValue = part.split("=");

      // ?name[]=test&name[]=test2
      String key = keyValue[0];

      if (key.endsWith("[]")) {
        key = key.substring(0, key.length() - 2);
      }

      if (!this.query.containsKey(key)) {
        this.query.put(key, new ArrayList<>());
      }

      List<String> queryList = this.query.get(key);

      queryList.add(keyValue[1]);
    }
  }
}
