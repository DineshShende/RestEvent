package com.projectx.rest.repository.quickregister;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.quickregister.EmailVerificationDetails;


@Repository
public interface EmailVericationDetailsRepository {

	EmailVerificationDetails save(EmailVerificationDetails mobileVerificationDetails);
	
	EmailVerificationDetails getEmailVerificationDetailsByCustomerIdTypeAndEmail(Long customerId,Integer customerType,String email);
	
	Integer resetEmailHashAndEmailHashSentTime(Long customerId,Integer customerType,String email,String emailHash,Date emailHashSentTime,Integer resetCount);
	
	Integer incrementResendCountByCustomerIdAndEmail(Long customerId,Integer customerType,String email);
	
	Long count();
	
	Boolean clearTestData();
	
}
