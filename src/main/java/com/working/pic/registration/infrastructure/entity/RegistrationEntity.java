package com.working.pic.registration.infrastructure.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "registration")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegistrationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String nickname;

	@Column(nullable = false)
	private String githubUsername;

	@Column(nullable = false)
	private String email;

	private String description;

	private LocalDate bestDate;

	@Column(nullable = false)
	private LocalDateTime postedAt;

	@Builder
	public RegistrationEntity(String nickname, String githubUsername, String email, String description,
		LocalDate bestDate) {
		this.nickname = nickname;
		this.githubUsername = githubUsername;
		this.email = email;
		this.description = description;
		this.bestDate = bestDate;
		this.postedAt = LocalDateTime.now();
	}
}
