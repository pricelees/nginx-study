package com.working.pic.common.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ShutdownController {

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy:HH:mm:ss");
	private static final String DEFAULT_SHUTDOWN_DELAY = "30";

	@GetMapping("/shutdown-test")
	public String shutdownPage() {
		return "shutdown";
	}

	@PostMapping("/shutdown-test")
	public String shutdown(
		@RequestParam(value = "delay", defaultValue = DEFAULT_SHUTDOWN_DELAY) int delay,
		Model model
	) {
		LocalDateTime requestTime = LocalDateTime.now();
		log.info("Shutdown request received. Delay: {} seconds", delay);
		try {
			Thread.sleep(delay * 1000L);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		LocalDateTime completeTime = LocalDateTime.now();
		model.addAttribute("requestTime", requestTime.format(DATE_TIME_FORMATTER));
		model.addAttribute("completeTime", completeTime.format(DATE_TIME_FORMATTER));
		log.info("Shutdown request completed. requestTime: {}, completeTime: {}"
			, requestTime.format(DATE_TIME_FORMATTER), completeTime.format(DATE_TIME_FORMATTER));
		return "success-shutdown";
	}
}
