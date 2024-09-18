package com.working.pic.auth.infrastructure.client;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.working.pic.auth.infrastructure.dto.GithubUserDto;
import com.working.pic.auth.infrastructure.dto.GithubUserEmailDto;

import lombok.extern.slf4j.Slf4j;

@EnableConfigurationProperties(GithubOAuthProperties.class)
@Component
@Slf4j
public class GithubOAuthAPIClient {

	private static final String DEFAULT_RESPONSE_CONTENT_TYPE = "application/vnd.github+json";
	private final GithubOAuthProperties properties;
	private final RestClient restClient;

	@Autowired
	public GithubOAuthAPIClient(GithubOAuthProperties properties) {
		this.properties = properties;
		this.restClient = RestClient.builder()
			.baseUrl(properties.getApiUrl().getBase())
			.defaultHeader("Accept", DEFAULT_RESPONSE_CONTENT_TYPE)
			.build();
	}

	public GithubUserDto getUser(String accessToken) {
		return restClient.get()
			.uri(properties.getApiUrl().getUser())
			.header("Authorization", "Bearer " + accessToken)
			.retrieve()
			.body(GithubUserDto.class);
	}

	public GithubUserEmailDto getEmail(String accessToken) {
		List<GithubUserEmailDto> results = restClient.get()
			.uri(properties.getApiUrl().getEmails())
			.header("Authorization", "Bearer " + accessToken)
			.retrieve()
			.onStatus(this::isGitHubApiError, this::handleError)
			.body(new ParameterizedTypeReference<>() {});

		assert results != null;
		return getPrimaryEmail(results);
	}

	private boolean isGitHubApiError(HttpStatusCode status) {
		return status.is4xxClientError() || status.is5xxServerError();
	}

	private void handleError(HttpRequest request, ClientHttpResponse response) throws IOException {
		log.error("Failed to get email From Github API. status: {}, body: {}", response.getStatusCode(),
			response.getBody());
		throw new IllegalStateException("Failed to get email from Github API");
	}

	private GithubUserEmailDto getPrimaryEmail(List<GithubUserEmailDto> results) {
		return results.stream()
			.filter(GithubUserEmailDto::primary)
			.findFirst()
			.orElseThrow(() -> new IllegalStateException("Primary email not found"));
	}
}
