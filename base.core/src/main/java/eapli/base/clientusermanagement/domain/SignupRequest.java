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
package eapli.base.clientusermanagement.domain;

import java.util.Calendar;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Name;
import eapli.framework.infrastructure.authz.domain.model.Password;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.validations.Preconditions;

/**
 * A Signup Request. This class represents the Signup Request created right
 * after a person applies for a Client User account.
 *
 * <p>
 * It follows a DDD approach where all of its properties are instances of value
 * objects. This approach may seem a little more complex than just having String
 * or native type attributes but provides for real semantic of the domain and
 * follows the Single Responsibility Pattern.
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 *
 */
@Entity
public class SignupRequest implements AggregateRoot<Username> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @EmbeddedId
    private Username username;
    private Password password;
    private Name name;
    private EmailAddress email;

    private MecanographicNumber mecanographicNumber;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;
    @Temporal(TemporalType.DATE)
    private Calendar createdOn;

    /* package */ SignupRequest(final Username username, final Password password, final Name name,
            final EmailAddress email, final MecanographicNumber mecanographicNumber,
            final Calendar createdOn) {
        Preconditions.noneNull(username, password, name, email, mecanographicNumber);

        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.mecanographicNumber = mecanographicNumber;
        // by default
        approvalStatus = ApprovalStatus.PENDING;
        this.createdOn = createdOn;
    }

    protected SignupRequest() {
        // for ORM only
    }

    public void accept() {
        approvalStatus = ApprovalStatus.ACCEPTED;
    }

    public void refuse() {
        approvalStatus = ApprovalStatus.REFUSED;
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(final Object other) {
        if (!(other instanceof SignupRequest)) {
            return false;
        }

        final SignupRequest that = (SignupRequest) other;
        if (this == that) {
            return true;
        }

        return username.equals(that.username) && password.equals(that.password)
                && name.equals(that.name) && email.equals(that.email)
                && mecanographicNumber.equals(that.mecanographicNumber);
    }

    public MecanographicNumber mecanographicNumber() {
        return mecanographicNumber;
    }

    @Override
    public Username identity() {
        return username;
    }

    public Username username() {
        return username;
    }

    public Name name() {
        return name;
    }

    public boolean isPending() {
        return approvalStatus == ApprovalStatus.PENDING;
    }

    public EmailAddress email() {
        return email;
    }

    public Password password() {
        return password;
    }
}
