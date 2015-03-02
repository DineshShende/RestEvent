package com.projectx.rest.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.util.annotation.VendorDetailsValid;

public class VendorDetailsValidator implements ConstraintValidator<VendorDetailsValid, VendorDetails>{

	@Override
	public void initialize(VendorDetailsValid constraintAnnotation) {

		
	}

	@Override
	public boolean isValid(VendorDetails value, ConstraintValidatorContext context) {
		
		if(value.getMobile()==null && value.getEmail()==null)
			return false;
		else
			return true;
	}

	
}
