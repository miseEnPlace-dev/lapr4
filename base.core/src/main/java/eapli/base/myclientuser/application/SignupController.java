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
package eapli.base.myclientuser.application;

import java.util.Calendar;

import eapli.base.clientusermanagement.domain.SignupRequest;
import eapli.base.clientusermanagement.domain.SignupRequestBuilder;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.application.UseCaseController;
import eapli.framework.time.util.CurrentTimeCalendars;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
@UseCaseController
public class SignupController {

	private final SignupRequestRepository signupRequestRepository = PersistenceContext.repositories().signupRequests();

	public SignupRequest signup(final String username, final String password, final String firstName,
			final String lastName, final String email, String mecanographicNumber, final Calendar createdOn) {

		// there is no need for authorisation check in this method as even
		// unauthenticated users may request a signup

		final SignupRequestBuilder signupRequestBuilder = UserBuilderHelper.signupBuilder();
		signupRequestBuilder.withUsername(username).withPassword(password).withName(firstName, lastName)
				.withEmail(email).createdOn(createdOn).withMecanographicNumber(mecanographicNumber);

		final SignupRequest newSignupRequest = signupRequestBuilder.build();
		return this.signupRequestRepository.save(newSignupRequest);
	}

	public SignupRequest signup(final String username, final String password, final String firstName,
			final String lastName, final String email, String mecanographicNumber) {

		return signup(username, password, firstName, lastName, email, mecanographicNumber, CurrentTimeCalendars.now());
	}
}
