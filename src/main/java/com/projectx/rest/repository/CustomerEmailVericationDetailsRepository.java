package com.projectx.rest.repository;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.CustomerEmailVerificationDetails;


@Repository
public interface CustomerEmailVericationDetailsRepository {

	CustomerEmailVerificationDetails save(CustomerEmailVerificationDetails mobileVerificationDetails);
	
	CustomerEmailVerificationDetails getEmailVerificationDetailsByCustomerIdAndEmail(Long customerId,String email);
	
	Integer resetEmailHashAndEmailHashSentTime(Long customerId,String email,String emailHash,Date emailHashSentTime,Integer resetCount);
	
	Integer updateResendCountByCustomerIdAndEmail(Long customerId,String email,Integer resendCount);
	
	Long count();
	
	Boolean clearTestData();
	
}
