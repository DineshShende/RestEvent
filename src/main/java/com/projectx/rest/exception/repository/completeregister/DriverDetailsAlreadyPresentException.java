package com.projectx.rest.exception.repository.completeregister;

import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;

public class DriverDetailsAlreadyPresentException extends ResourceAlreadyPresentException {
	
	public DriverDetailsAlreadyPresentException(Throwable cause) {
        super(cause);
    }

    public DriverDetailsAlreadyPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public DriverDetailsAlreadyPresentException(String message) {
        super(message);
    }

    public DriverDetailsAlreadyPresentException() {
    }


}
