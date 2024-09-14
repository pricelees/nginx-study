package com.working.pic.registration.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.working.pic.registration.infrastructure.entity.RegistrationEntity;

public interface RegistrationRepository extends JpaRepository<RegistrationEntity, Long> {
}
