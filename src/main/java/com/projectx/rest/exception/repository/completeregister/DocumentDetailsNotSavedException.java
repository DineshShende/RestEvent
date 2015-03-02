package com.projectx.rest.exception.repository.completeregister;

public class DocumentDetailsNotSavedException extends RuntimeException{
	
	public DocumentDetailsNotSavedException(Throwable cause) {
        super(cause);
    }

    public DocumentDetailsNotSavedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocumentDetailsNotSavedException(String message) {
        super(message);
    }

    public DocumentDetailsNotSavedException() {
    }

	

}
