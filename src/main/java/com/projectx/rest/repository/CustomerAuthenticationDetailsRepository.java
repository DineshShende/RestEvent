package com.projectx.rest.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.CustomerAuthenticationDetails;




@Repository
public interface CustomerAuthenticationDetailsRepository  {

	
	CustomerAuthenticationDetails save(CustomerAuthenticationDetails authenticationDetails);
	
	CustomerAuthenticationDetails getCustomerAuthenticationDetailsByCustomerId(Long customerId);
	
	CustomerAuthenticationDetails getCustomerAuthenticationDetailsByEmail(String email);
	
	CustomerAuthenticationDetails getCustomerAuthenticationDetailsByMobile(Long mobile);
	
	
	Integer updatePasswordAndPasswordTypeAndCounts(Long customerId,String password,String passwordType);
	
	Integer updateEmailPasswordAndPasswordTypeAndCounts(Long customerId,String emailPassword);
	
	Integer incrementResendCount(Long customerId);
	
	Integer incrementLastUnsucessfullAttempts(Long customerId);
	
	//CustomerAuthenticationDetails loginVerification(String email,Long mobile,String password);
	
	Integer count();
	
	
	Boolean clearLoginDetailsForTesting();
	
	
	
}
