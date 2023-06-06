package eapli.ecourse.app.board.application;

import eapli.ecourse.common.board.protocol.ProtocolMessage;

public class UnsuccessfulRequestException extends Exception {
  public UnsuccessfulRequestException(String message) {
    super(message);
  }

  public UnsuccessfulRequestException(ProtocolMessage response) {
    super(response.getStringifiedPayload());
  }
}
