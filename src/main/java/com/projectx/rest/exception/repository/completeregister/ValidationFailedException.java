package com.projectx.rest.exception.repository.completeregister;

public class ValidationFailedException extends RuntimeException{
	
	 public ValidationFailedException(Throwable cause) {
	        super(cause);
	    }

	    public ValidationFailedException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public ValidationFailedException(String message) {
	        super(message);
	    }

	    public ValidationFailedException() {
	    }


}
