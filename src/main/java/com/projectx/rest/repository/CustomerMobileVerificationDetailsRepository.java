package com.projectx.rest.repository;

import org.springframework.stereotype.Repository;

import com.projectx.data.domain.CustomerMobileVerificationDetailsByCustomerIdAndMobileDTO;
import com.projectx.rest.domain.CustomerMobileVerificationDetails;
import com.projectx.web.domain.VerifyMobileDTO;

@Repository
public interface CustomerMobileVerificationDetailsRepository {

	CustomerMobileVerificationDetails save(CustomerMobileVerificationDetails mobileVerificationDetails);
	
	CustomerMobileVerificationDetails getMobileVerificationDetailsByCustomerIdAndMobile(Long customerId,Long mobile);
	
	Integer updateMobilePinAndMobileVerificationAttemptsAndResendCount(Long customerId,Long mobile,Integer mobilePin,Integer mobileVerificationAttempts,Integer resendCount);
	
	Integer incrementMobileVerificationAttempts(Long customerId,Long mobile);
	
	Integer incrementResendCount(Long customerId,Long mobile);
	
	Long count();
	
	Boolean clearTestData();
	
}
