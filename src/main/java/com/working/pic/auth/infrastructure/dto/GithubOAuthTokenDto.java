package com.working.pic.auth.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GithubOAuthTokenDto(
	@JsonProperty("access_token") String accessToken,
	@JsonProperty("scope") String scope,
	@JsonProperty("token_type") String tokenType
) {
}
