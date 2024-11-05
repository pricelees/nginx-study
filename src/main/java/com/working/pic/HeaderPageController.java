package com.working.pic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HeaderPageController {

	private static final String DOCKER_NETWORK_SUBNET = "172.18";
	private static final String LOCALHOST_IP_PREFIX = "127.0";
	private static final String X_FORWARDED_FOR_HEADER_DELIMITER = ", ";
	private static final String IPv4_SEPARATOR_REGEX = "\\.";
	private static final String IPv4_SEPARATOR = ".";
	private static final String IPv6_SEPARATOR = ":";

	@GetMapping("/v1/headers")
	public String v1Headers(HttpServletRequest request, Model model) {
		return getHeaderPage(request, model);
	}

	@GetMapping("/v2/headers")
	public String v2Headers(HttpServletRequest request, Model model) {

		return getHeaderPage(request, model);
	}

	@GetMapping("/v1/all-headers")
	public String allV1Headers(HttpServletRequest request, Model model) {
		return getAllHeadersPage(request, model);
	}

	@GetMapping("/v2/all-headers")
	public String allV2Headers(HttpServletRequest request, Model model) {
		return getAllHeadersPage(request, model);
	}

	private String getAllHeadersPage(HttpServletRequest request, Model model) {
		Map<String, String> headers = new HashMap<>();
		request.getHeaderNames()
			.asIterator()
			.forEachRemaining(headerName -> headers.put(headerName, request.getHeader(headerName)));
		model.addAttribute("headers", headers);

		return "all-headers";
	}

	/**
	 * 클라이언트 IP 정보를 받아 모델에 추가한 후, headers.html 페이지를 반환합니다.
	 *
	 * @param request HttpServletRequest 객체로, 클라이언트의 요청 정보를 담고 있습니다.
	 * @param model Model 객체로, 뷰에 전달할 데이터를 담습니다.
	 * @return 클라이언트의 IP 주소(request.getRemoteAddr())와
	 *         X-Forwarded-For 헤더(request.getHeader("X-Forwarded-For")) 값을
	 *         모델에 추가하고, headers.html 페이지를 반환합니다.
	 */
	private String getHeaderPage(HttpServletRequest request, Model model) {
		model.addAttribute("remoteAddr", parseIpAddress(request.getRemoteAddr()));
		model.addAttribute("remoteHost", request.getRemoteHost());
		model.addAttribute("remotePort", request.getRemotePort());
		model.addAttribute("requestUrl", request.getRequestURL().toString());

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

	private String parseXForwardedForHeader(String header) {
		String[] parts = header.split(X_FORWARDED_FOR_HEADER_DELIMITER);
		return Arrays.stream(parts)
			.map(this::parseIpAddress)
			.collect(Collectors.joining(X_FORWARDED_FOR_HEADER_DELIMITER));
	}

	private String parseIpAddress(String ipAddress) {
		if (isDockerOrLocalhostIPAddress(ipAddress)) {
			return ipAddress;
		}

		return getFirstTwoSegments(ipAddress);
	}

	private boolean isDockerOrLocalhostIPAddress(String ipAddress) {
		return ipAddress.startsWith(DOCKER_NETWORK_SUBNET) || ipAddress.startsWith(LOCALHOST_IP_PREFIX);
	}

	private String getFirstTwoSegments(String ipAddress) {
		if (isIpv6Address(ipAddress)) {
			String[] segments = ipAddress.split(IPv6_SEPARATOR);
			return segments[0] + IPv6_SEPARATOR + segments[1];
		}

		String[] segments = ipAddress.split(IPv4_SEPARATOR_REGEX);
		return segments[0] + IPv4_SEPARATOR + segments[1];
	}

	private boolean isIpv6Address(String ipAddress) {
		return ipAddress.contains(IPv6_SEPARATOR);
	}
}
