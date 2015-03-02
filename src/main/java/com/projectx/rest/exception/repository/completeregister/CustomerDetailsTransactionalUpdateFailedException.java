package com.projectx.rest.exception.repository.completeregister;

public class CustomerDetailsTransactionalUpdateFailedException extends RuntimeException {
	
	public CustomerDetailsTransactionalUpdateFailedException(Throwable cause) {
        super(cause);
    }

    public CustomerDetailsTransactionalUpdateFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerDetailsTransactionalUpdateFailedException(String message) {
        super(message);
    }

    public CustomerDetailsTransactionalUpdateFailedException() {
    }


}
