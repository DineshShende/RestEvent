package com.projectx.rest.services.quickregister;

import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;

public interface EmailVerificationService {

EmailVerificationDetails getCustomerEmailVerificationDetailsByCustomerIdTypeAndEmail(Long customerId,Integer customerType,String email);
	
	Boolean reSendEmailHash(Long customerId,Integer customerType,String email);
	
	Boolean reSetEmailHash(Long customerId,Integer customerType,String email);
	
	Boolean verifyEmailHash(Long customerId,Integer customerType,String email,String emailHash);
	
	Integer updateEmailHash(Long customerId,Integer customerType,String email);
	
	EmailVerificationDetails createCustomerEmailVerificationEntity(QuickRegisterEntity customerQuickRegisterEntity);
	
	EmailVerificationDetails saveCustomerEmailVerificationDetails(EmailVerificationDetails emailVerificationDetails);
	
	Boolean clearTestData();
}
