package com.projectx.rest.exception.AuthenticationService;

public class ForceFulPasswordChangeNotAllowedException extends RuntimeException {
	
	
	public ForceFulPasswordChangeNotAllowedException(Throwable cause) {
        super(cause);
    }

    public ForceFulPasswordChangeNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForceFulPasswordChangeNotAllowedException(String message) {
        super(message);
    }

    public ForceFulPasswordChangeNotAllowedException() {
    }

}
