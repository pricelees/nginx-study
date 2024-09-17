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
		model.addAttribute("scheme", request.getScheme());
		model.addAttribute("requestUrl", request.getRequestURL().toString());
		model.addAttribute("remoteAddr", parseIpAddress(request.getRemoteAddr()));

		return "headers";
	}

	private String parseIpAddress(String ipAddress) {
		String[] parts = ipAddress.split("\\.");
		if (parts.length == 1) {
			parts = ipAddress.split(":");
		}
		return parts[0] + "." + parts[1];
	}
}
