package com.projectx.rest.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.projectx.rest.domain.completeregister.VehicleDetails;
import com.projectx.rest.util.annotation.VehicleDetailsValid;


public class VehicleDetailsValidator implements ConstraintValidator<VehicleDetailsValid, VehicleDetails>{

	@Override
	public void initialize(VehicleDetailsValid constraintAnnotation) {

		
	}

	@Override
	public boolean isValid(VehicleDetails value, ConstraintValidatorContext context) {
		
		if(value.getInsuranceStatus() && (value.getInsuranceCompany()==null || value.getInsuranceNumber()==null))
			return false;
		else
			return true;
	}

	
}

