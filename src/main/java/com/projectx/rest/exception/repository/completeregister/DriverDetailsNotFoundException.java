package com.projectx.rest.exception.repository.completeregister;

import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;

public class DriverDetailsNotFoundException extends ResourceNotFoundException {

	
	public DriverDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public DriverDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DriverDetailsNotFoundException(String message) {
        super(message);
    }

    public DriverDetailsNotFoundException() {
    }
}
