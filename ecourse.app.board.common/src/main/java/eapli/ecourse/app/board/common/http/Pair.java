package eapli.ecourse.app.board.common.http;

public class Pair<T, V> {
  public T key;
  public V value;

  public Pair(T key, V value) {
    this.key = key;
    this.value = value;
  }

  public T getKey() {
    return key;
  }

  public V getValue() {
    return value;
  }
}
