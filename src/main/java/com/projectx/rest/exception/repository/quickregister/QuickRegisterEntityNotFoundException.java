package com.projectx.rest.exception.repository.quickregister;

public class QuickRegisterEntityNotFoundException extends ResourceNotFoundException {
	
	public QuickRegisterEntityNotFoundException(Throwable cause) {
        super(cause);
    }

    public QuickRegisterEntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuickRegisterEntityNotFoundException(String message) {
        super(message);
    }

    public QuickRegisterEntityNotFoundException() {
    }


}
