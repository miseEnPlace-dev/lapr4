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
package eapli.base.app.common.console.presentation.authz;

import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

/**
 * UI for user login action. Created by nuno on 21/03/16.
 */
@SuppressWarnings("squid:S106")
public class LoginUI extends AbstractUI {

    private final AuthenticationService authenticationService = AuthzRegistry.authenticationService();

    private Role onlyWithThis;
    private static final int DEFAULT_MAX_ATTEMPTS = 3;
    private final int maxAttempts;

    public LoginUI() {
        maxAttempts = DEFAULT_MAX_ATTEMPTS;
    }

    public LoginUI(final Role onlyWithThis) {
        this.onlyWithThis = onlyWithThis;
        maxAttempts = DEFAULT_MAX_ATTEMPTS;
    }

    public LoginUI(final Role onlyWithThis, final int maxAttempts) {
        this.onlyWithThis = onlyWithThis;
        this.maxAttempts = maxAttempts;
    }

    public LoginUI(final int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    @Override
    protected boolean doShow() {
        int attempt = 1;
        while (attempt <= maxAttempts) {
            final String userName = Console.readLine("Username:");
            final String password = Console.readLine("Password:");

            if (authenticationService.authenticate(userName, password, onlyWithThis).isPresent()) {
                return true;
            } else {
                System.out.printf("Wrong username or password. You have %d attempts left.%n%n»»»»»»»»»%n",
                        maxAttempts - attempt);
            }
            attempt++;
        }
        System.out.println("Sorry, we are unable to authenticate you. Please contact your system admnistrator.");
        return false;
    }

    @Override
    public String headline() {
        return "Login";
    }
}
