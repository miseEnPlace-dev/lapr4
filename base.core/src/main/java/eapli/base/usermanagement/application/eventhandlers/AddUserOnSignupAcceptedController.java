/*
 * Copyright (c) 2013-2022 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.usermanagement.application.eventhandlers;

import eapli.base.clientusermanagement.domain.events.NewUserRegisteredFromSignupEvent;
import eapli.base.clientusermanagement.domain.events.SignupAcceptedEvent;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.application.UseCaseController;
import eapli.framework.domain.events.DomainEvent;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.pubsub.EventPublisher;
import eapli.framework.infrastructure.pubsub.impl.inprocess.service.InProcessPubSub;

/**
 *
 * @author Paulo Gandra de Sousa
 */
@UseCaseController
/* package */ class AddUserOnSignupAcceptedController {
  private final UserRepository userRepository = PersistenceContext.repositories().users();
  private final EventPublisher dispatcher = InProcessPubSub.publisher();

  /**
   *
   * @param theSignupRequest
   * @return
   * @throws ConcurrencyException
   * @throws IntegrityViolationException
   */
  public SystemUser addUser(final SignupAcceptedEvent theSignupRequest) {

    final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
    userBuilder.withUsername(theSignupRequest.username()).withPassword(theSignupRequest.password())
        .withName(theSignupRequest.name()).withEmail(theSignupRequest.email()).withRoles(BaseRoles.CLIENT_USER);
    final SystemUser newUser = userRepository.save(userBuilder.build());

    // notify interested parties
    final DomainEvent event = new NewUserRegisteredFromSignupEvent(theSignupRequest.mecanographicNumber(),
        newUser.username());
    dispatcher.publish(event);

    return newUser;
  }
}
