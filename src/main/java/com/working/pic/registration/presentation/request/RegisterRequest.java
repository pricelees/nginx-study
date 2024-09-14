package com.working.pic.registration.presentation.request;

import java.time.LocalDate;

import com.working.pic.registration.domain.Registration;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
	@NotBlank
	String nickname,
	LocalDate bestDate,
	String description
) {

	public Registration toDomain(String githubUsername, String email) {
		return Registration.builder()
			.nickname(nickname)
			.githubUsername(githubUsername)
			.email(email)
			.description(description)
			.bestDate(bestDate)
			.build();
	}
}
