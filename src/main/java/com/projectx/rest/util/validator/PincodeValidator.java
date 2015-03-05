package com.projectx.rest.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.projectx.rest.util.annotation.Pincode;

public class PincodeValidator implements ConstraintValidator<Pincode, Integer> {

	@Override
	public void initialize(Pincode constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}
//apache commons email validation
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if(value >100000 && value<999999)
			return true;
		
		return false;
	}

}
