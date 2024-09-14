package com.working.pic.auth.presentation.response;

import com.working.pic.auth.domain.GithubUser;

import lombok.Builder;

@Builder
public record GithubLoginSucceedResponse(
	String userName,
	String email
) {

	public static GithubLoginSucceedResponse toResponse(GithubUser user) {
		return GithubLoginSucceedResponse.builder()
			.userName(user.getUserName())
			.email(user.getEmail())
			.build();
	}
}
