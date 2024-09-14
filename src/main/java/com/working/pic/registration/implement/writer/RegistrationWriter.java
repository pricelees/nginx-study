package com.working.pic.registration.implement.writer;

import org.springframework.stereotype.Component;

import com.working.pic.registration.domain.Registration;
import com.working.pic.registration.infrastructure.entity.RegistrationEntity;
import com.working.pic.registration.infrastructure.repository.RegistrationRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RegistrationWriter {

	private final RegistrationRepository registrationRepository;

	public void save(Registration registration) {
		RegistrationEntity registrationEntity = RegistrationEntity.builder()
			.nickname(registration.getNickname())
			.githubUsername(registration.getGithubUsername())
			.email(registration.getEmail())
			.nickname(registration.getNickname())
			.description(registration.getDescription())
			.build();

		registrationRepository.save(registrationEntity);
	}
}
