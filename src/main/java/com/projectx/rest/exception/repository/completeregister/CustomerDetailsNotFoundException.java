package com.projectx.rest.exception.repository.completeregister;

import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;

public class CustomerDetailsNotFoundException extends ResourceNotFoundException {

	public CustomerDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public CustomerDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerDetailsNotFoundException(String message) {
        super(message);
    }

    public CustomerDetailsNotFoundException() {
    }

	
}
