package com.working.pic.auth.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.working.pic.auth.business.AuthService;
import com.working.pic.auth.presentation.response.GithubLoginSucceedResponse;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/login/github")
	public String loginGithub() {
		String githubLoginUrl = authService.getLoginUrl();

		return "redirect:" + githubLoginUrl;
	}

	@GetMapping("/oauth/callback")
	public String oauthLogin(@RequestParam String code, HttpSession session) {
		GithubLoginSucceedResponse response = authService.login(code);
		String userName = response.userName();
		String email = response.email();

		session.setAttribute("userName", userName);
		session.setAttribute("email", email);

		log.info("github login success. username: {}, email: {}", userName, email);

		return "redirect:/register";
	}
}
