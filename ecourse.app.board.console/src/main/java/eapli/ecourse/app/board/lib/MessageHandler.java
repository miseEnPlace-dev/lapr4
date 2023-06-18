package eapli.ecourse.app.board.lib;

import eapli.ecourse.common.board.protocol.ProtocolMessage;

public interface MessageHandler {
  public void handle(ProtocolMessage message);
}
