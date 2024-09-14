package com.working.pic.registration.business;

import org.springframework.stereotype.Service;

import com.working.pic.registration.domain.Registration;
import com.working.pic.registration.implement.writer.RegistrationWriter;
import com.working.pic.registration.presentation.request.RegisterRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationService {

	private final RegistrationWriter registrationWriter;

	public void register(RegisterRequest registerRequest, String userName, String email) {
		Registration registration = registerRequest.toDomain(userName, email);

		registrationWriter.save(registration);
	}
}
