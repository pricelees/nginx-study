package com.working.pic.auth.infrastructure.dto;

public record GithubUserDto(
	String login,
	String name,
	String htmlUrl
) {
}
