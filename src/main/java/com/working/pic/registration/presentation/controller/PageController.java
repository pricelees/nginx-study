package com.working.pic.registration.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PageController {

	@GetMapping("/register")
	public String register() {
		return "registration";
	}
}
