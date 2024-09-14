package com.working.pic.auth.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GithubUser {

	private final String userName;
	private final String email;
	private final String profileUrl;
}
