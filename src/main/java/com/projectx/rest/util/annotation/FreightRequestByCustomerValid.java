package com.projectx.rest.util.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.projectx.rest.util.validator.FreightRequestByCustomerValidator;



@Documented
@Constraint(validatedBy = FreightRequestByCustomerValidator.class)
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FreightRequestByCustomerValid {
	
	String message() default "{Parameter missing for full/lessthan truckload request}";
    
    Class<?>[] groups() default {};
      
    Class<? extends Payload>[] payload() default {};


}
