package com.working.pic.registration.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.working.pic.registration.business.RegistrationService;
import com.working.pic.registration.presentation.request.RegisterRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

	private final RegistrationService registrationService;

	@PostMapping
	public ResponseEntity<Void> register(@RequestBody RegisterRequest request) {
		registrationService.register(request);

		return ResponseEntity.ok().build();
	}
}
