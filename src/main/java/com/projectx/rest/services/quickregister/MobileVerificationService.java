package com.projectx.rest.services.quickregister;

import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;


public interface MobileVerificationService {
	
	MobileVerificationDetails saveDetails(MobileVerificationDetails mobileVerificationDetails) 
			throws ResourceAlreadyPresentException,ValidationFailedException;
	
	MobileVerificationDetails getByEntityIdTypeAndMobileType(Long customerId,Integer customerType,Integer mobileType)
				throws ResourceAlreadyPresentException;
	
	MobileVerificationDetails getByMobile(Long mobile) throws ResourceAlreadyPresentException;
	
	Boolean sendOrResendOrResetMobilePin(Long entityId,Integer entityType,Integer mobileType,String updatedBy,Boolean resetFlag,
			Boolean resendFlag)throws ResourceNotFoundException;
	
	Boolean sendMobilePin(Long entityId,Integer entityType,Integer mobileType,String updatedBy) throws ResourceNotFoundException;

	Boolean reSendMobilePin(Long customerId,Integer customerType,Integer mobileType,String updatedBy) throws ResourceNotFoundException;
	
	Boolean reSetMobilePin(Long customerId,Integer customerType,Integer mobileType,String updatedBy) throws ResourceNotFoundException;
	
	Boolean verifyMobilePin(Long customerId,Integer customerType,Integer mobileType,Integer mobilePin);
	
	Boolean verifyMobilePinUpdateStatusAndSendPassword(Long customerId,Integer customerType,Integer mobileType,Integer mobilePin,String updatedBy);
	
	Integer updateMobilePin(Long customerId,Integer customerType,Integer mobileType,String updatedBy);

	MobileVerificationDetails createEntity(Long customerId,Integer customerType,Long mobile,Integer mobileType,String updatedBy);
	
	

	Boolean deleteByKey(MobileVerificationDetailsKey key);
	
	Integer count();
	
	Boolean clearTestData();
	

}
