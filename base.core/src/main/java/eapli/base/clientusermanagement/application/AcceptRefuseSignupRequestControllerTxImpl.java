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
package eapli.base.clientusermanagement.application;

import java.util.HashSet;
import java.util.Set;

import eapli.base.clientusermanagement.domain.ClientUserBuilder;
import eapli.base.clientusermanagement.domain.SignupRequest;
import eapli.base.clientusermanagement.repositories.ClientUserRepository;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

/**
 * The transactional controller for the use case "accept/refuse a signup
 * request".
 * <p>
 * following the guideline that a controller should only change one Aggregate,
 * we shouldn't be changing all these entities here, but should instead use
 * asynchronous events. However in this case we will take advantage of
 * TransactionalContext
 *
 * @todo handle the scenario where in the meantime the username is already used
 *       by some other user
 *
 * @author AJS on 08/04/2016.
 */
@UseCaseController
public class AcceptRefuseSignupRequestControllerTxImpl
        implements AcceptRefuseSignupRequestController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final UserManagementService userService = AuthzRegistry.userService();

    private final TransactionalContext txCtx = PersistenceContext.repositories()
            .newTransactionalContext();
    private final ClientUserRepository clientUserRepository = PersistenceContext
            .repositories().clientUsers(txCtx);
    private final SignupRequestRepository signupRequestsRepository = PersistenceContext
            .repositories().signupRequests(txCtx);

    /*
     * (non-Javadoc)
     *
     * @see eapli.base.clientusermanagement.application.
     * AcceptRefuseSignupRequestController#acceptSignupRequest(eapli.base.
     * clientusermanagement.domain.SignupRequest)
     */
    @Override
    public SignupRequest acceptSignupRequest(SignupRequest theSignupRequest) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);

        if (theSignupRequest == null) {
            throw new IllegalArgumentException();
        }

        // explicitly begin a transaction
        txCtx.beginTransaction();

        final SystemUser newUser = createSystemUserForClientUser(theSignupRequest);
        createClientUser(theSignupRequest, newUser);
        theSignupRequest = acceptTheSignupRequest(theSignupRequest);

        // explicitly commit the transaction
        txCtx.commit();

        return theSignupRequest;
    }

    private SignupRequest acceptTheSignupRequest(final SignupRequest theSignupRequest) {
        theSignupRequest.accept();
        return this.signupRequestsRepository.save(theSignupRequest);
    }

    private void createClientUser(final SignupRequest theSignupRequest,
                                  final SystemUser newUser) {
        final ClientUserBuilder clientUserBuilder = new ClientUserBuilder();
        clientUserBuilder.withMecanographicNumber(theSignupRequest.mecanographicNumber())
                .withSystemUser(newUser);
        this.clientUserRepository.save(clientUserBuilder.build());
    }

    //
    // add system user
    //
    private SystemUser createSystemUserForClientUser(final SignupRequest theSignupRequest) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.CLIENT_USER);
        return userService.registerUser(theSignupRequest.username(), theSignupRequest.password(),
                theSignupRequest.name(), theSignupRequest.email(), roles);
    }

    /*
     * (non-Javadoc)
     *
     * @see eapli.base.clientusermanagement.application.
     * AcceptRefuseSignupRequestController#refuseSignupRequest(eapli.base.
     * clientusermanagement.domain.SignupRequest)
     */
    @Override
    public SignupRequest refuseSignupRequest(SignupRequest theSignupRequest) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);

        if (theSignupRequest == null) {
            throw new IllegalArgumentException();
        }

        // explicitly begin a transaction
        txCtx.beginTransaction();

        theSignupRequest.refuse();
        theSignupRequest = signupRequestsRepository.save(theSignupRequest);

        // explicitly commit the transaction
        txCtx.commit();

        return theSignupRequest;
    }

    /*
     * (non-Javadoc)
     *
     * @see eapli.base.clientusermanagement.application.
     * AcceptRefuseSignupRequestController#listPendingSignupRequests()
     */
    @Override
    public Iterable<SignupRequest> listPendingSignupRequests() {
        return signupRequestsRepository.pendingSignupRequests();
    }
}
