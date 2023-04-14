/*
 * Copyright (c) 2013-2022 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.base.usermanagement.application.eventhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eapli.base.clientusermanagement.domain.events.SignupAcceptedEvent;
import eapli.framework.domain.events.DomainEvent;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.pubsub.EventHandler;

/**
 *
 */
public class SignupAcceptedWatchDog implements EventHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(SignupAcceptedWatchDog.class);

  @Override
  public void onEvent(final DomainEvent domainevent) {
    assert domainevent instanceof SignupAcceptedEvent;

    final SignupAcceptedEvent event = (SignupAcceptedEvent) domainevent;

    final AddUserOnSignupAcceptedController controller = new AddUserOnSignupAcceptedController();
    try {
      controller.addUser(event);
    } catch (final IntegrityViolationException e) {
      // TODO provably should send some warning email...
      LOGGER.error("Unable to register new user on signup event", e);
    }
  }
}
