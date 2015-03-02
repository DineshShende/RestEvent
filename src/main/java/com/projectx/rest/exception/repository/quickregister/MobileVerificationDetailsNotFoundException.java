package com.projectx.rest.exception.repository.quickregister;

public class MobileVerificationDetailsNotFoundException extends
		ResourceNotFoundException {
	
	public MobileVerificationDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public MobileVerificationDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MobileVerificationDetailsNotFoundException(String message) {
        super(message);
    }

    public MobileVerificationDetailsNotFoundException() {
    }


}
