package com.projectx.rest.util.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.projectx.rest.util.validator.VehicleDetailsValidator;


@Documented
@Constraint(validatedBy = VehicleDetailsValidator.class)
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface VehicleDetailsValid {

	String message() default "{Insurance Company Name and Insurance Number cann't null if Vehicle is Insured}";
    
    Class<?>[] groups() default {};
      
    Class<? extends Payload>[] payload() default {};
	
}
