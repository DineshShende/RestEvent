package com.projectx.rest.exception.repository.completeregister;

public class DriverDetailsUpdateFailedException extends RuntimeException {

	public DriverDetailsUpdateFailedException(Throwable cause) {
        super(cause);
    }

    public DriverDetailsUpdateFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DriverDetailsUpdateFailedException(String message) {
        super(message);
    }

    public DriverDetailsUpdateFailedException() {
    }

	
}
