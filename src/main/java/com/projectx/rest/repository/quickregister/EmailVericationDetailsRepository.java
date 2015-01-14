package com.projectx.rest.repository.quickregister;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsKey;


@Repository
public interface EmailVericationDetailsRepository {

	EmailVerificationDetails save(EmailVerificationDetails mobileVerificationDetails);
	
	EmailVerificationDetails getByEntityIdTypeAndEmailType(Long customerId,Integer customerType,Integer emailType);
	
	EmailVerificationDetails getByEmail(String email);
	
	Integer resetEmailHashAndEmailHashSentTime(Long customerId,Integer customerType,Integer emailType,String emailHash,Date emailHashSentTime,Integer resetCount);
	
	Integer incrementResendCountByCustomerIdAndEmail(Long customerId,Integer customerType,Integer emailType);
	
	Long count();
	
	Boolean delete(EmailVerificationDetailsKey key);
	
	Boolean clearTestData();
	
}
