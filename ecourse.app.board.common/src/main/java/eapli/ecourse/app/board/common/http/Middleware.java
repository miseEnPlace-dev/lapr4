package eapli.ecourse.app.board.common.http;

public interface Middleware {
  void handle(Request req, Response res, NextFunction next);
}
