package com.projectx.rest.exception.repository.quickregister;

public class QuickRegisterEntityNotSavedException extends RuntimeException {
	
	
	public QuickRegisterEntityNotSavedException(Throwable cause) {
        super(cause);
    }

    public QuickRegisterEntityNotSavedException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuickRegisterEntityNotSavedException(String message) {
        super(message);
    }

    public QuickRegisterEntityNotSavedException() {
    }


}
