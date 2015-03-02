package com.projectx.rest.exception.repository.quickregister;

public class QuickRegisterDetailsAlreadyPresentException extends
											ResourceAlreadyPresentException {

	public QuickRegisterDetailsAlreadyPresentException(Throwable cause) {
        super(cause);
    }

    public QuickRegisterDetailsAlreadyPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuickRegisterDetailsAlreadyPresentException(String message) {
        super(message);
    }

    public QuickRegisterDetailsAlreadyPresentException() {
    }

	
}
