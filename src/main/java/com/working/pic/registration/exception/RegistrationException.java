package com.working.pic.registration.exception;

import com.working.pic.common.exception.PicException;

public class RegistrationException extends PicException {

	public RegistrationException(RegistrationErrorType error, Object input) {
		super(error.getErrorcode(), error.createInvalidValue(input), error.getHttpStatus());
	}
}
