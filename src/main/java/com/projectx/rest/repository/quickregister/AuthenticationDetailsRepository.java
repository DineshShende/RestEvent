package com.projectx.rest.repository.quickregister;

import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.AuthenticationDetailsNotFoundException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;




@Repository
public interface AuthenticationDetailsRepository  {

	AuthenticationDetails save(AuthenticationDetails authenticationDetails) throws ResourceAlreadyPresentException,ValidationFailedException;
	
	AuthenticationDetails getByCustomerIdType(Long customerId,Integer customerType) throws AuthenticationDetailsNotFoundException;
	
	AuthenticationDetails getByEmail(String email) throws AuthenticationDetailsNotFoundException;
	
	AuthenticationDetails getByMobile(Long mobile) throws AuthenticationDetailsNotFoundException;
	
	Integer updatePasswordEmailPasswordAndPasswordTypeAndCounts(Long customerId,Integer customerType,String password,String emailPassword,String passwordType,String updatedBy);
	
	Integer incrementResendCount(Long customerId,Integer customerType,String updatedBy);
	
	Integer incrementLastUnsucessfullAttempts(Long customerId,Integer customerType,String updatedBy);
	
	Integer count();
	
	Boolean clearLoginDetailsForTesting();
	
	//Integer updateEmailPasswordAndPasswordTypeAndCounts(Long customerId,Integer customerType,String emailPassword);

	//CustomerAuthenticationDetails loginVerification(String email,Long mobile,String password);
	
}
