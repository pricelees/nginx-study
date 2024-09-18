package com.working.pic.auth.infrastructure.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.working.pic.auth.infrastructure.dto.GithubUserDto;
import com.working.pic.auth.infrastructure.dto.GithubUserEmailDto;

@EnableConfigurationProperties(GithubOAuthProperties.class)
@Component
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
			.body(new ParameterizedTypeReference<>() {
			});

		return getPrimaryEmail(results);
	}

	private GithubUserEmailDto getPrimaryEmail(List<GithubUserEmailDto> results) {
		return results.stream()
			.filter(GithubUserEmailDto::primary)
			.findFirst()
			.orElseThrow(() -> new IllegalStateException("Primary email not found"));
	}
}
