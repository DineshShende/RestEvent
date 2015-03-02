package com.projectx.rest.exception.repository.completeregister;

import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;

public class CustomerDetailsAlreadyPresentException extends ResourceAlreadyPresentException {
	
	 public CustomerDetailsAlreadyPresentException(Throwable cause) {
	        super(cause);
	    }

	    public CustomerDetailsAlreadyPresentException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public CustomerDetailsAlreadyPresentException(String message) {
	        super(message);
	    }

	    public CustomerDetailsAlreadyPresentException() {
	    }

}
