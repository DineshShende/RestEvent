package com.projectx.rest.repository.quickregister;

import org.springframework.stereotype.Repository;

import com.projectx.data.domain.quickregister.CustomerMobileVerificationDetailsByCustomerIdTypeAndMobileTypeDTO;
import com.projectx.mvc.domain.quickregister.VerifyMobileDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsKey;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.MobileVerificationDetailsNotFoundException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;

@Repository
public interface MobileVerificationDetailsRepository {

	MobileVerificationDetails save(MobileVerificationDetails mobileVerificationDetails) throws ResourceAlreadyPresentException,ValidationFailedException;
	
	MobileVerificationDetails geByEntityIdTypeAndMobileType(Long customerId,Integer customerType,Integer mobileType) throws MobileVerificationDetailsNotFoundException;
	
	MobileVerificationDetails getByMobile(Long mobile) throws MobileVerificationDetailsNotFoundException;
	
	Integer updateMobilePinAndMobileVerificationAttemptsAndResendCount(Long customerId,Integer customerType,Integer mobileType,Integer mobilePin,Integer mobileVerificationAttempts,Integer resendCount);
	
	Integer incrementMobileVerificationAttempts(Long customerId,Integer customerType,Integer mobileType);
	
	Integer incrementResendCount(Long customerId,Integer customerType,Integer mobileType);
	
	Long count();
	
	Boolean delete(MobileVerificationDetailsKey key);
	
	Boolean clearTestData();
	
}
