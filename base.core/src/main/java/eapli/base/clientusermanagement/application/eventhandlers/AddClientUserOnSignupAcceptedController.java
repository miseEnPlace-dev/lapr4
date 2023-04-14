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
package eapli.base.clientusermanagement.application.eventhandlers;

import java.util.Optional;

import eapli.base.clientusermanagement.domain.ClientUser;
import eapli.base.clientusermanagement.domain.ClientUserBuilder;
import eapli.base.clientusermanagement.domain.events.NewUserRegisteredFromSignupEvent;
import eapli.base.clientusermanagement.repositories.ClientUserRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.functional.Functions;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

/**
 *
 * @author Paulo Gandra de Sousa
 *
 */
/* package */ class AddClientUserOnSignupAcceptedController {

  private final UserRepository repo = PersistenceContext.repositories().users();
  private final ClientUserRepository clientUserRepository = PersistenceContext
      .repositories().clientUsers();

  public ClientUser addClientUser(final NewUserRegisteredFromSignupEvent event) {
    final Optional<SystemUser> newUser = findUser(event);

    return newUser.map(u -> {
      final ClientUserBuilder clientUserBuilder = new ClientUserBuilder();
      clientUserBuilder.withMecanographicNumber(event.mecanographicNumber())
          .withSystemUser(u);
      return clientUserRepository.save(clientUserBuilder.build());
    }).orElseThrow(IllegalStateException::new);
  }

  @SuppressWarnings("squid:S1488")
  private Optional<SystemUser> findUser(final NewUserRegisteredFromSignupEvent event) {
    // since we are using events, the actual user may not yet be
    // created, so lets give it a time and wait
    final Optional<SystemUser> newUser = Functions
        .retry(() -> repo.ofIdentity(event.username()), 1000, 3);
    return newUser;
  }
}
