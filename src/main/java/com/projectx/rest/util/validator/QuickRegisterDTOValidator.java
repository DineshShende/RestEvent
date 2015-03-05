package com.projectx.rest.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.projectx.mvc.domain.quickregister.CustomerQuickRegisterEntityDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.util.annotation.QuickRegisterDTOValid;
import com.projectx.rest.util.annotation.QuickRegisterEntityValid;

public class QuickRegisterDTOValidator implements ConstraintValidator<QuickRegisterDTOValid, CustomerQuickRegisterEntityDTO>{

	@Override
	public void initialize(QuickRegisterDTOValid constraintAnnotation) {
		
		
	}

	@Override
	public boolean isValid(CustomerQuickRegisterEntityDTO value,
			ConstraintValidatorContext context) {
		
		if(value.getMobile()==null && value.getEmail()==null)
			return false;
		else
			return true;
			
	}

}
