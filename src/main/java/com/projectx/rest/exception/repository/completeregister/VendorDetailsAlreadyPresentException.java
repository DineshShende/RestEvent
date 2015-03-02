package com.projectx.rest.exception.repository.completeregister;

import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;

public class VendorDetailsAlreadyPresentException extends ResourceAlreadyPresentException{

	public VendorDetailsAlreadyPresentException(Throwable cause) {
        super(cause);
    }

    public VendorDetailsAlreadyPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public VendorDetailsAlreadyPresentException(String message) {
        super(message);
    }

    public VendorDetailsAlreadyPresentException() {
    }

	
}
