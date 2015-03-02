package com.projectx.rest.exception.AuthenticationService;

public class LoginVerificationFailedException extends RuntimeException {

	public LoginVerificationFailedException(Throwable cause) {
        super(cause);
    }

    public LoginVerificationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginVerificationFailedException(String message) {
        super(message);
    }

    public LoginVerificationFailedException() {
    }

	
}
