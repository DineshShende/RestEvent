package com.projectx.rest.repository.quickregister;

import org.springframework.stereotype.Repository;

import com.projectx.data.quickregister.domain.CustomerMobileVerificationDetailsByCustomerIdTypeAndMobileDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.web.domain.quickregister.VerifyMobileDTO;

@Repository
public interface MobileVerificationDetailsRepository {

	MobileVerificationDetails save(MobileVerificationDetails mobileVerificationDetails);
	
	MobileVerificationDetails getMobileVerificationDetailsByCustomerIdTypeAndMobile(Long customerId,Integer customerType,Long mobile);
	
	Integer updateMobilePinAndMobileVerificationAttemptsAndResendCount(Long customerId,Integer customerType,Long mobile,Integer mobilePin,Integer mobileVerificationAttempts,Integer resendCount);
	
	Integer incrementMobileVerificationAttempts(Long customerId,Integer customerType,Long mobile);
	
	Integer incrementResendCount(Long customerId,Integer customerType,Long mobile);
	
	Long count();
	
	Boolean clearTestData();
	
}
