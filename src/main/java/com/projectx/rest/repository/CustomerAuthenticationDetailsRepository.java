package com.projectx.rest.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.CustomerAuthenticationDetails;




@Repository
public interface CustomerAuthenticationDetailsRepository  {

	
	CustomerAuthenticationDetails save(CustomerAuthenticationDetails authenticationDetails);
	
	Integer updatePasswordAndPasswordType(Long customerId,String password,String passwordType);
		
	CustomerAuthenticationDetails loginVerification(String email,Long mobile,String password);
	
	Integer count();
	
	CustomerAuthenticationDetails getByCustomerId(Long customerId);
	
	Boolean clearLoginDetailsForTesting();
	
	
	
}
