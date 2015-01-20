package com.projectx.rest.services.quickregister;

import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;

public interface EmailVerificationService {

    EmailVerificationDetails getByEntityIdTypeAndEmailType(Long customerId,Integer customerType,Integer emailType);
    
    EmailVerificationDetails getByEmail(String email);
    
    Boolean sendOrResendOrResetEmailHash(Long customerId,Integer customerType,Integer emailType,Boolean resetFlag,Boolean resendFlag);
    
    Boolean sendEmailHash(Long customerId,Integer customerType,Integer emailType);
    
    Boolean reSendEmailHash(Long customerId,Integer customerType,Integer emailType);
	
	Boolean reSetEmailHash(Long customerId,Integer customerType,Integer emailType);
	
	Boolean verifyEmailHash(Long customerId,Integer customerType,Integer emailType,String emailHash);
	
	Boolean verifyEmailHashUpdateStatusAndSendPassword(Long customerId,Integer customerType,Integer emailType, String emailHash);
	
	Integer updateEmailHash(Long customerId,Integer customerType,Integer emailType);
	
	EmailVerificationDetails createEntity(Long customerId,Integer customerType,String email,Integer emailType,String updatedBy);
	
	EmailVerificationDetails saveDetails(EmailVerificationDetails emailVerificationDetails);
	
	Boolean deleteByKey(EmailVerificationDetailsKey key);
	
	Integer count();
	
	Boolean clearTestData();
	
	//String checkIfEmailAlreadyExist(Long customerId,Integer customerType,Integer emailType,String email);
}
