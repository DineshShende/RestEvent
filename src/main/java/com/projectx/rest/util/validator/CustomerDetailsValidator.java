package com.projectx.rest.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.util.annotation.CustomerDetailsValid;





public class CustomerDetailsValidator implements ConstraintValidator<CustomerDetailsValid, CustomerDetails>{

	@Override
	public void initialize(CustomerDetailsValid constraintAnnotation) {

		
	}

	@Override
	public boolean isValid(CustomerDetails value, ConstraintValidatorContext context) {
		
		if(value.getMobile()==null && value.getEmail()==null)
			return false;
		else
			return true;
	}

	
}
