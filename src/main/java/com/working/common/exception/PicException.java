package com.working.common.exception;

import java.util.Optional;

import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public class PicException extends RuntimeException {

	private final String errorCode;
	private final String invalidValue;
	private final HttpStatusCode httpStatus;

	public PicException(String errorCode, String invalidValue, HttpStatusCode httpStatus) {
		this.errorCode = errorCode;
		this.invalidValue = invalidValue;
		this.httpStatus = httpStatus;
	}

	public String getInvalidValue() {
		return Optional.ofNullable(invalidValue).orElse("");
	}

	public int getStatusCode() {
		return httpStatus.value();
	}
}
