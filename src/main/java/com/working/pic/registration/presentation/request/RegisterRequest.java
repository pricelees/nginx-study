package com.working.pic.registration.presentation.request;

import com.working.pic.registration.domain.Registration;

import jakarta.validation.constraints.NotEmpty;

public record RegisterRequest(
	@NotEmpty
	String nickname,
	@NotEmpty
	String email,
	String description
) {

	public Registration toRegistrationInfo() {
		return Registration.builder()
			.email(email)
			.nickname(nickname)
			.description(description)
			.build();
	}
}
