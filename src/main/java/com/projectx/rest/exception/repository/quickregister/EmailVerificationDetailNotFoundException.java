package com.projectx.rest.exception.repository.quickregister;

public class EmailVerificationDetailNotFoundException extends ResourceNotFoundException {

	public EmailVerificationDetailNotFoundException(Throwable cause) {
        super(cause);
    }

    public EmailVerificationDetailNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailVerificationDetailNotFoundException(String message) {
        super(message);
    }

    public EmailVerificationDetailNotFoundException() {
    }


	
}
