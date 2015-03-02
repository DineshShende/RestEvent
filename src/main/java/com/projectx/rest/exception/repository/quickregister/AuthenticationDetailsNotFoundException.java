package com.projectx.rest.exception.repository.quickregister;

public class AuthenticationDetailsNotFoundException extends ResourceNotFoundException {

	public AuthenticationDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public AuthenticationDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationDetailsNotFoundException(String message) {
        super(message);
    }

    public AuthenticationDetailsNotFoundException() {
    }

	
}
