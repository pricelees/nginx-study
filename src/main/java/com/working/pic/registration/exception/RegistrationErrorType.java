package com.working.pic.registration.exception;

import java.util.function.Function;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RegistrationErrorType {

	BLANK_NICKNAME("닉네임을 입력해 주세요!", HttpStatus.BAD_REQUEST, input -> "nickname: " + input),
	BLANK_EMAIL("이메일을 입력해 주세요!", HttpStatus.BAD_REQUEST, input -> "email: " + input),

	REGISTRATION_NOT_FOUND("등록 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND, input -> "id: " + input),
	;


	private final String message;
	private final HttpStatusCode httpStatus;
	private final Function<Object, String> invalidValueFunction;

	public String getErrorcode() {
		return this.name();
	}

	public String createInvalidValue(Object input) {
		return invalidValueFunction.apply(input);
	}
}
