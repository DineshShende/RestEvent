package com.projectx.rest.repository.quickregister;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.quickregister.AuthenticationDetails;




@Repository
public interface AuthenticationDetailsRepository  {

	
	AuthenticationDetails save(AuthenticationDetails authenticationDetails);
	
	AuthenticationDetails getCustomerAuthenticationDetailsByCustomerIdType(Long customerId,Integer customerType);
	
	AuthenticationDetails getCustomerAuthenticationDetailsByEmail(String email);
	
	AuthenticationDetails getCustomerAuthenticationDetailsByMobile(Long mobile);
	
	
	Integer updatePasswordAndPasswordTypeAndCounts(Long customerId,Integer customerType,String password,String passwordType);
	
	Integer updateEmailPasswordAndPasswordTypeAndCounts(Long customerId,Integer customerType,String emailPassword);
	
	Integer incrementResendCount(Long customerId,Integer customerType);
	
	Integer incrementLastUnsucessfullAttempts(Long customerId,Integer customerType);
	
	//CustomerAuthenticationDetails loginVerification(String email,Long mobile,String password);
	
	Integer count();
	
	
	Boolean clearLoginDetailsForTesting();
	
	
	
}
