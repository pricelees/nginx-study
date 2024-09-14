package com.working.common.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(value = PicException.class)
	public ErrorResponse handlePicException(PicException e, HttpServletResponse response) {
		String message = e.getMessage();
		log.error("{}{}", message, e.getInvalidValue(), e);
		response.setStatus(e.getStatusCode());

		return ErrorResponse.builder()
			.code(message)
			.message(e.getMessage())
			.build();
	}
}
