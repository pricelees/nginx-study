package com.working.pic.registration.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PageController {

	@GetMapping("/register")
	public String register() {
		return "registration";
	}

	@GetMapping("/v1/headers")
	public String v1Headers(HttpServletRequest request, Model model) {
		return getHeaderPage(request, model);
	}

	@GetMapping("/v2/headers")
	public String v2Headers(HttpServletRequest request, Model model) {
		return getHeaderPage(request, model);
	}

	private String getHeaderPage(HttpServletRequest request, Model model) {
		String scheme = request.getScheme();
		String remoteHost = request.getRemoteHost();
		String requestURL = request.getRequestURL().toString();
		String remoteAddr = request.getRemoteAddr();
		model.addAttribute("scheme", scheme);
		model.addAttribute("remoteHost", remoteHost);
		model.addAttribute("requestUrl", requestURL);
		model.addAttribute("remoteAddr", remoteAddr);

		return "headers";
	}
}
