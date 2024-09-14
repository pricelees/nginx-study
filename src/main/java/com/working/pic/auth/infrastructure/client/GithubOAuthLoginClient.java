package com.working.pic.auth.infrastructure.client;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.working.pic.auth.infrastructure.dto.GithubOAuthTokenDto;

@EnableConfigurationProperties(GithubOAuthProperties.class)
@Component
public class GithubOAuthLoginClient {

	private final GithubOAuthProperties properties;
	private final RestClient restClient;

	@Autowired
	public GithubOAuthLoginClient(GithubOAuthProperties properties) {
		this.properties = properties;
		this.restClient = RestClient.builder()
			.baseUrl(properties.getLoginUrl().getBase())
			.build();
	}

	public GithubOAuthTokenDto getAccessToken(String code) {
		Map<String, String> body = createBody(code);

		return restClient.post()
			.uri(properties.getLoginUrl().getToken())
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.body(body)
			.retrieve()
			.body(GithubOAuthTokenDto.class);
	}

	private Map<String, String> createBody(String code) {
		Map<String, String> body = new LinkedHashMap<>();
		body.put("client_id", properties.getClientId());
		body.put("client_secret", properties.getClientSecret());
		body.put("code", code);

		return body;
	}
}
