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
		String requestTime = LocalDateTime.now()
			.format(DateTimeFormatter.ofPattern("dd/MM/yyyy:HH:mm:ss"));
		log.info("Shutdown request received. Delay: {} seconds", delay);
		try {
			Thread.sleep(delay * 1000L);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		model.addAttribute("delay", delay);
		model.addAttribute("requestTime", requestTime);
		return "success-shutdown";
	}
}
