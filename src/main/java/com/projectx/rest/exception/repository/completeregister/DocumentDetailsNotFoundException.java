package com.projectx.rest.exception.repository.completeregister;

import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;

public class DocumentDetailsNotFoundException extends ResourceNotFoundException{

	public DocumentDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public DocumentDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocumentDetailsNotFoundException(String message) {
        super(message);
    }

    public DocumentDetailsNotFoundException() {
    }

	
}
