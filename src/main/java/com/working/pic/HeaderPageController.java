package com.working.pic;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HeaderPageController {

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
		addXForwardedForHeaderIfExist(request, model);

		return "headers";
	}

	private void addXForwardedForHeaderIfExist(HttpServletRequest request, Model model) {
		String xForwardedForHeader = request.getHeader("X-Forwarded-For");
		log.info("request: {}, X-Forwarded-For: {}", request.getRequestURI(), xForwardedForHeader);
		if (xForwardedForHeader == null) {
			return;
		}

		model.addAttribute("xForwardedFor", parseXForwardedForHeader(xForwardedForHeader));
	}

	private String parseIpAddress(String ipAddress) {
		if (ipAddress.startsWith("172.18") || ipAddress.startsWith("127.0")) {
			return ipAddress;
		}

		return getFirstTwoOctet(ipAddress);
	}

	private static String getFirstTwoOctet(String ipAddress) {
		String[] parts = ipAddress.split("\\.");
		if (parts.length == 1) {
			parts = ipAddress.split(":");
		}
		return parts[0] + "." + parts[1];
	}

	private String parseXForwardedForHeader(String header) {
		String[] parts = header.split(", ");
		return Arrays.stream(parts)
			.map(this::parseIpAddress)
			.collect(Collectors.joining(", "));
	}
}
