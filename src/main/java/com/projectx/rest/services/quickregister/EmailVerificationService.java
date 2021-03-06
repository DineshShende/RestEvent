package com.projectx.rest.services.quickregister;

import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;

public interface EmailVerificationService {

    
	EmailVerificationDetails getByEntityIdTypeAndEmailType(Long customerId,Integer customerType,Integer emailType) throws ResourceNotFoundException;
    
    EmailVerificationDetails getByEmail(String email) throws ResourceNotFoundException;
    
    Boolean sendOrResendOrResetEmailHash(Long customerId,Integer customerType,Integer emailType,Boolean resetFlag,
    		Boolean resendFlag,Integer requestedBy,Long requestedById)throws ResourceNotFoundException;
    
    Boolean sendEmailHash(Long customerId,Integer customerType,Integer emailType,Integer requestedBy,Long requestedById) throws ResourceNotFoundException;
    
    Boolean reSendEmailHash(Long customerId,Integer customerType,Integer emailType,Integer requestedBy,Long requestedById) throws ResourceNotFoundException;
	
	Boolean reSetEmailHash(Long customerId,Integer customerType,Integer emailType,Integer requestedBy,Long requestedById) throws ResourceNotFoundException;
	
	Boolean verifyEmailHash(Long customerId,Integer customerType,Integer emailType,String emailHash);
	
	Boolean verifyEmailHashUpdateStatusAndSendPassword(Long customerId,Integer customerType,Integer emailType,
			String emailHash,Integer requestedBy,Long requestedById) 
															throws ResourceNotFoundException;
	
	Integer updateEmailHash(Long customerId,Integer customerType,Integer emailType,Integer requestedBy,Long requestedById);
	
	EmailVerificationDetails createEntity(Long customerId,Integer customerType,String email,Integer emailType,Integer requestedBy,Long requestedById);
	
	Boolean deleteByKey(EmailVerificationDetailsKey key);
	
	Integer count();
	
	Boolean clearTestData();
	
}
