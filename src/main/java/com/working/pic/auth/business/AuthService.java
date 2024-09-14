package com.working.pic.auth.business;

import org.springframework.stereotype.Service;

import com.working.pic.auth.domain.GithubUser;
import com.working.pic.auth.implement.finder.AuthFinder;
import com.working.pic.auth.presentation.response.GithubLoginSucceedResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final AuthFinder authFinder;

	public String getLoginUrl() {
		return authFinder.getLoginUrl();
	}

	public GithubLoginSucceedResponse login(String code) {
		GithubUser user = authFinder.login(code);

		return GithubLoginSucceedResponse.toResponse(user);
	}
}
