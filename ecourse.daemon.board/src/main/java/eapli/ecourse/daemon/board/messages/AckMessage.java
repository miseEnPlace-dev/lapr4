package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.net.Socket;
import eapli.ecourse.common.board.EventListener;
import eapli.ecourse.common.board.SafeBoardUpdatesCounter;
import eapli.ecourse.common.board.SafeOnlineCounter;
import eapli.ecourse.common.board.protocol.ProtocolMessage;

public class AckMessage extends Message {
  public AckMessage(ProtocolMessage protocolMessage, DataOutputStream output, Socket socket,
      SafeOnlineCounter onlineCounter, SafeBoardUpdatesCounter boardUpdatesCounter,
      EventListener eventListener) {
    super(protocolMessage, output, socket, onlineCounter, boardUpdatesCounter, eventListener);
  }

  @Override
  public void handle() {
    // ignore for now
  }
}
