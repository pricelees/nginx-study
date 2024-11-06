package com.working.pic.common.controller;

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
		log.info("Shutdown request received. Delay: {} seconds", delay);
		try {
			Thread.sleep(delay * 1000L);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

		model.addAttribute("delay", delay);
		return "success-shutdown";
	}
}
