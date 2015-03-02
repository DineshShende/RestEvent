package com.projectx.rest.exception.repository.quickregister;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException() {
    }


}
