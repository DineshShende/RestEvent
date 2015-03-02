package com.projectx.rest.exception.repository.completeregister;

public class VendorDetailsTransactionalUpdateFailedException extends
		RuntimeException {
	
	public VendorDetailsTransactionalUpdateFailedException(Throwable cause) {
        super(cause);
    }

    public VendorDetailsTransactionalUpdateFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public VendorDetailsTransactionalUpdateFailedException(String message) {
        super(message);
    }

    public VendorDetailsTransactionalUpdateFailedException() {
    }


}
