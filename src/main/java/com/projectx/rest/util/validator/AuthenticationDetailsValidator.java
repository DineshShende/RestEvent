package com.projectx.rest.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.util.annotation.AuthenticationDetailsValid;




public class AuthenticationDetailsValidator implements ConstraintValidator<AuthenticationDetailsValid, AuthenticationDetails>{

	@Override
	public void initialize(AuthenticationDetailsValid constraintAnnotation) {

		
	}

	@Override
	public boolean isValid(AuthenticationDetails value, ConstraintValidatorContext context) {
		
		if(value.getMobile()==null && value.getEmail()==null)
			return false;
		else
			return true;
	}

	
}
