package com.working.pic.registration.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.working.pic.registration.exception.RegistrationErrorType;
import com.working.pic.registration.exception.RegistrationException;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Registration {

	private final String nickname;
	private final String githubUsername;
	private final String email;
	private final String description;
	private final LocalDate bestDate;
	private final LocalDateTime postedAt;

	@Builder
	public Registration(String nickname, String githubUsername, String email, String description, LocalDate bestDate) {
		validateIsNotBefore(bestDate);
		this.nickname = nickname;
		this.githubUsername = githubUsername;
		this.email = email;
		this.description = description;
		this.bestDate = bestDate;
		this.postedAt = LocalDateTime.now();
	}

	private void validateIsNotBefore(LocalDate bestDate) {
		if (bestDate == null) {
			return;
		}
		if (bestDate.isBefore(LocalDate.now())) {
			throw new RegistrationException(RegistrationErrorType.BEFORE_BEST_DATE, bestDate);
		}
	}
}
