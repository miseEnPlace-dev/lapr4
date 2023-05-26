package eapli.ecourse.app.board.backend.http;

public interface Middleware {
  void handle(Request req, Response res, NextFunction next);
}
