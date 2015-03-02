package com.projectx.rest.exception.repository.completeregister;

import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;

public class VehicleDetailsNotFoundException extends ResourceNotFoundException {
	
	public VehicleDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public VehicleDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VehicleDetailsNotFoundException(String message) {
        super(message);
    }

    public VehicleDetailsNotFoundException() {
    }



}
