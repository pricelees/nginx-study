package com.working.pic.auth.infrastructure.dto;

public record GithubUserEmailDto(
	String email,
	Boolean primary,
	Boolean verified,
	String visibility
) {
}
