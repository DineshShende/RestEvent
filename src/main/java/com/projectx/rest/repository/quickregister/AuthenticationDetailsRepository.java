package com.projectx.rest.repository.quickregister;

import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.AuthenticationDetailsNotFoundException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;




@Repository
public interface AuthenticationDetailsRepository  {

	AuthenticationDetails getByCustomerIdType(Long customerId,Integer customerType) throws AuthenticationDetailsNotFoundException;
	
	AuthenticationDetails getByEmail(String email) throws AuthenticationDetailsNotFoundException;
	
	AuthenticationDetails getByMobile(Long mobile) throws AuthenticationDetailsNotFoundException;
	
	Integer updatePasswordEmailPasswordAndPasswordTypeAndCounts(Long customerId,Integer customerType,String password,String emailPassword,
			String passwordType,Integer updatedBy,Long updatedById);
	
	Integer incrementResendCount(Long customerId,Integer customerType,Integer updatedBy,Long updatedById);
	
	Integer incrementLastUnsucessfullAttempts(Long customerId,Integer customerType,Integer updatedBy,Long updatedById);
	
	Integer count();
	
	Boolean clearLoginDetailsForTesting();
	

}
