package com.projectx.rest.exception.repository.completeregister;

import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;

public class VehicleDetailsAlreadyPresentException extends ResourceAlreadyPresentException{
	
	public VehicleDetailsAlreadyPresentException(Throwable cause) {
        super(cause);
    }

    public VehicleDetailsAlreadyPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public VehicleDetailsAlreadyPresentException(String message) {
        super(message);
    }

    public VehicleDetailsAlreadyPresentException() {
    }


}
