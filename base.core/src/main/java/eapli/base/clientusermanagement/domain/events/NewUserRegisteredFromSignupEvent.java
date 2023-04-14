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
package eapli.base.clientusermanagement.domain.events;

import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.framework.domain.events.DomainEvent;
import eapli.framework.domain.events.DomainEventBase;
import eapli.framework.infrastructure.authz.domain.model.Username;

/**
 * @author Paulo Gandra de Sousa
 *
 */
public class NewUserRegisteredFromSignupEvent extends DomainEventBase implements DomainEvent {

    private static final long serialVersionUID = 1L;

    private final MecanographicNumber mecanographicNumber;
    private final Username newUser;

    public NewUserRegisteredFromSignupEvent(final MecanographicNumber mecanographicNumber,
            final Username newUser) {
        this.mecanographicNumber = mecanographicNumber;
        this.newUser = newUser;
    }

    public MecanographicNumber mecanographicNumber() {
        return mecanographicNumber;
    }

    public Username username() {
        return newUser;
    }

    @Override
    public String toString() {
        return "NewUserFromsignup(" + username() + ")";
    }
}
