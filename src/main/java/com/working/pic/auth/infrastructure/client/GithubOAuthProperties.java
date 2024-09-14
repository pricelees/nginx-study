package com.working.pic.auth.infrastructure.client;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@ConfigurationProperties(prefix = "oauth")
@RequiredArgsConstructor
@Getter
public class GithubOAuthProperties {

	private static final String SCOPE_DELIMITER = " ";

	private final String baseUrl;
	private final String clientId;
	private final String clientSecret;
	private final List<String> scopes;
	private final String redirectUri;
	private final LoginUrl loginUrl;
	private final ApiUrl apiUrl;

	public String getRedirectUri() {
		return baseUrl + redirectUri;
	}

	public String getScopes() {
		return String.join(SCOPE_DELIMITER, scopes);
	}

	@RequiredArgsConstructor
	@Getter
	public static class LoginUrl {

		private final String base;
		private final String authorize;
		private final String token;
	}

	@RequiredArgsConstructor
	@Getter
	public static class ApiUrl {

		private final String base;
		private final String user;
		private final String emails;
	}
}
