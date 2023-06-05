package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.daemon.board.clientstate.ClientState;

/**
 * Get boards which the user has permission to access.
 */
public class GetBoardsMessage extends Message {
  public GetBoardsMessage(ProtocolMessage protocolMessage, DataOutputStream output, Socket socket) {
    super(protocolMessage, output, socket);
  }

  @Override
  public void handle() throws IOException {
    ClientState clientState = ClientState.getInstance();

    // ignore requests from unauthenticated clients
    if (!clientState.getCredentialStore().isAuthenticated())
      return;

    send(new ProtocolMessage(MessageCode.ERR, "Not Implemented"));
  }
}
