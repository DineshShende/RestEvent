package com.projectx.rest.exception.repository.completeregister;

import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;

public class VendorDetailsNotFoundException extends ResourceNotFoundException {

	public VendorDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public VendorDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VendorDetailsNotFoundException(String message) {
        super(message);
    }

    public VendorDetailsNotFoundException() {
    }


	
}
