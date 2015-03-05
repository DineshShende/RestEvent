package com.projectx.rest.services.quickregister;

import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;

public interface EmailVerificationService {

    
	EmailVerificationDetails saveDetails(EmailVerificationDetails emailVerificationDetails) 
			throws ResourceAlreadyPresentException,ValidationFailedException;
	
	EmailVerificationDetails getByEntityIdTypeAndEmailType(Long customerId,Integer customerType,Integer emailType) throws ResourceNotFoundException;
    
    EmailVerificationDetails getByEmail(String email) throws ResourceNotFoundException;
    
    Boolean sendOrResendOrResetEmailHash(Long customerId,Integer customerType,Integer emailType,Boolean resetFlag,
    		Boolean resendFlag,String requestedBy)throws ResourceNotFoundException;
    
    Boolean sendEmailHash(Long customerId,Integer customerType,Integer emailType,String requestedBy) throws ResourceNotFoundException;
    
    Boolean reSendEmailHash(Long customerId,Integer customerType,Integer emailType,String requestedBy) throws ResourceNotFoundException;
	
	Boolean reSetEmailHash(Long customerId,Integer customerType,Integer emailType,String requestedBy) throws ResourceNotFoundException;
	
	Boolean verifyEmailHash(Long customerId,Integer customerType,Integer emailType,String emailHash);
	
	Boolean verifyEmailHashUpdateStatusAndSendPassword(Long customerId,Integer customerType,Integer emailType, String emailHash,String requestBy) 
															throws ResourceNotFoundException;
	
	Integer updateEmailHash(Long customerId,Integer customerType,Integer emailType,String requestedBy);
	
	EmailVerificationDetails createEntity(Long customerId,Integer customerType,String email,Integer emailType,String updatedBy);
	
	Boolean deleteByKey(EmailVerificationDetailsKey key);
	
	Integer count();
	
	Boolean clearTestData();
	
}
