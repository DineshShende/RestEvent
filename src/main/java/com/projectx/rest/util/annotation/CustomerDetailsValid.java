package com.projectx.rest.util.annotation;

import java.lang.annotation.Documented;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.projectx.rest.util.validator.CustomerDetailsValidator;



@Documented
@Constraint(validatedBy = CustomerDetailsValidator.class)
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomerDetailsValid {

	String message() default "{Email And Mobile Both cann't be NULL}";
    
    Class<?>[] groups() default {};
      
    Class<? extends Payload>[] payload() default {};
	
}
