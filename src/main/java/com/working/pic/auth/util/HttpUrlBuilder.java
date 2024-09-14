package com.working.pic.auth.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpUrlBuilder {

	private static final String QUERY_PARAM_DELIMITER = "?";
	private static final String QUERY_DELIMITER = "&";
	private static final String QUERY_KEY_VALUE_DELIMITER = "=";

	private String url;

	public static HttpUrlBuilder fromBaseUrl(String baseUrl) {
		return new HttpUrlBuilder(baseUrl);
	}

	public HttpUrlBuilder addQueryParam(String key, String value) {
		if (url.contains(QUERY_PARAM_DELIMITER)) {
			url += QUERY_DELIMITER;
		} else {
			url += QUERY_PARAM_DELIMITER;
		}

		url += key + QUERY_KEY_VALUE_DELIMITER + value;
		return this;
	}

	public String build() {
		return url;
	}
}
