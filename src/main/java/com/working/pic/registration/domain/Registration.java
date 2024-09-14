package com.working.pic.registration.domain;

import java.time.LocalDate;

import com.working.pic.registration.exception.RegistrationErrorType;
import com.working.pic.registration.exception.RegistrationException;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Registration {

	private final String nickname;
	private final String email;
	private final String description;
	private final LocalDate postedAt;

	@Builder
	public Registration(String nickname, String email, String description) {
		validateIsNotBlankOrNull(nickname, email);
		this.nickname = nickname;
		this.email = email;
		this.description = description;
		this.postedAt = LocalDate.now();
	}

	private void validateIsNotBlankOrNull(String nickname, String email) {
		if (nickname == null || nickname.isBlank()) {
			throw new RegistrationException(RegistrationErrorType.BLANK_NICKNAME, nickname);
		}
		if (email == null || email.isBlank()) {
			throw new RegistrationException(RegistrationErrorType.BLANK_EMAIL, email);
		}
	}
}
