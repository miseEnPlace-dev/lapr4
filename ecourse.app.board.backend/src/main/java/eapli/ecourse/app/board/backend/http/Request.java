package eapli.ecourse.app.board.backend.http;

import java.util.ArrayList;
import java.util.List;

public class Request {
  private String method;
  private Path path;
  private String address;
  private List<String> headers;

  public Request(String method, String path, String address, List<String> headers) {
    this.method = method;
    this.path = new Path(path);
    this.address = address;
    this.headers = headers;
  }

  public String getMethod() {
    return method;
  }

  public Path getPath() {
    return path;
  }

  public String getAddress() {
    return address;
  }

  public List<String> getHeaders() {
    return new ArrayList<>(headers);
  }

  public String getParam(String name) {
    return path.getParam(name);
  }
}
