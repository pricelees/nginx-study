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
		String protocol = request.getProtocol();
		String remoteHost = request.getRemoteHost();
		StringBuffer requestURL = request.getRequestURL();
		String remoteAddr = request.getRemoteAddr();
		model.addAttribute("remoteHost", remoteHost);
		model.addAttribute("protocol", protocol);
		model.addAttribute("requestUrl", requestURL.toString());
		model.addAttribute("remoteAddr", remoteAddr);

		return "headers";
	}
}
