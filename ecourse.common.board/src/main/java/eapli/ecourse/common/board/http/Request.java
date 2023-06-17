package eapli.ecourse.common.board.http;

import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Request {
  private String method;
  private Path path;
  private String address;
  private List<String> headers;
  private DataInputStream input;

  public Request(String method, String path, String address, List<String> headers,
      DataInputStream input) {
    this.method = method;
    this.path = new Path(path);
    this.address = address;
    this.headers = headers;
    this.input = input;
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

  public Optional<String> getQuery(String name) {
    return Optional.of(path.getQuery(name));
  }

  public List<String> getArrayQuery(String name) {
    return path.getArrayQuery(name);
  }

  public DataInputStream getInput() {
    return input;
  }
}
