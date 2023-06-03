package eapli.ecourse.common.board.http;

public interface Middleware {
  void handle(Request req, Response res, NextFunction next);
}
