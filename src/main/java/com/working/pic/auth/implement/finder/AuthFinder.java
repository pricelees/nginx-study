package com.working.pic.auth.implement.finder;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.working.pic.auth.domain.GithubUser;
import com.working.pic.auth.infrastructure.client.GithubOAuthAPIClient;
import com.working.pic.auth.infrastructure.client.GithubOAuthLoginClient;
import com.working.pic.auth.infrastructure.client.GithubOAuthProperties;
import com.working.pic.auth.infrastructure.dto.GithubUserDto;
import com.working.pic.auth.infrastructure.dto.GithubUserEmailDto;
import com.working.pic.auth.util.HttpUrlBuilder;

import lombok.RequiredArgsConstructor;

@EnableConfigurationProperties(GithubOAuthProperties.class)
@RequiredArgsConstructor
@Component
public class AuthFinder {

	private final GithubOAuthProperties properties;
	private final GithubOAuthLoginClient loginClient;
	private final GithubOAuthAPIClient apiClient;

	public String getLoginUrl() {
		GithubOAuthProperties.LoginUrl loginUrl = properties.getLoginUrl();
		String baseUrl = loginUrl.getBase() + loginUrl.getAuthorize();
		String clientId = properties.getClientId();
		String redirectUri = properties.getRedirectUri();
		String scopes = properties.getScopes();

		return HttpUrlBuilder.fromBaseUrl(baseUrl)
			.addQueryParam("client_id", clientId)
			.addQueryParam("redirect_uri", redirectUri)
			.addQueryParam("scope", scopes)
			.build();
	}

	public GithubUser login(String code) {
		String accessToken = loginClient.getAccessToken(code).accessToken();
		GithubUserDto userDto = apiClient.getUser(accessToken);
		GithubUserEmailDto emailDto = apiClient.getEmail(accessToken);

		return new GithubUser(userDto.login(), emailDto.email(), userDto.htmlUrl());
	}
}
