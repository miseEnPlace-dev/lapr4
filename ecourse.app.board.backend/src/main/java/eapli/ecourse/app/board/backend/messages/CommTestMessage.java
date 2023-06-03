package eapli.ecourse.app.board.backend.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import eapli.ecourse.app.board.common.protocol.MessageCode;
import eapli.ecourse.app.board.common.protocol.ProtocolMessage;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class CommTestMessage extends Message {
  public CommTestMessage(ProtocolMessage protocolMessage, DataOutputStream output) {
    super(protocolMessage, output);
  }

  @Override
  public void handle() throws IOException {
    AuthorizationService authz = AuthzRegistry.authorizationService();

    if (authz.hasSession()) {
      send(new ProtocolMessage(MessageCode.ACK,
          authz.session().get().authenticatedUser().email().toString()));
    } else {
      send(new ProtocolMessage(MessageCode.ERR, "Not Authenticated"));
    }
  }
}
