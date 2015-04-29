package com.projectx.rest.services.quickregister;

import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;


public interface MobileVerificationService {
	
	MobileVerificationDetails getByEntityIdTypeAndMobileType(Long customerId,Integer customerType,Integer mobileType)
				throws ResourceAlreadyPresentException;
	
	MobileVerificationDetails getByMobile(Long mobile) throws ResourceAlreadyPresentException;
	
	Boolean sendOrResendOrResetMobilePin(Long entityId,Integer entityType,Integer mobileType,Integer requestedBy,Long requestedById,Boolean resetFlag,
			Boolean resendFlag)throws ResourceNotFoundException;
	
	Boolean sendMobilePin(Long entityId,Integer entityType,Integer mobileType,Integer requestedBy,Long requestedById) throws ResourceNotFoundException;

	Boolean reSendMobilePin(Long customerId,Integer customerType,Integer mobileType,Integer requestedBy,Long requestedById) throws ResourceNotFoundException;
	
	Boolean reSetMobilePin(Long customerId,Integer customerType,Integer mobileType,Integer requestedBy,Long requestedById) throws ResourceNotFoundException;
	
	Boolean verifyMobilePin(Long customerId,Integer customerType,Integer mobileType,Integer mobilePin,Integer requestedBy,Long requestedById);
	
	Boolean verifyMobilePinUpdateStatusAndSendPassword(Long customerId,Integer customerType,Integer mobileType,Integer mobilePin,
			Integer requestedBy,Long requestedById);
	
	Integer updateMobilePin(Long customerId,Integer customerType,Integer mobileType,
			Integer requestedBy,Long requestedById);

	MobileVerificationDetails createEntity(Long customerId,Integer customerType,Long mobile,Integer mobileType,
			Integer requestedBy,Long requestedById);
	
	

	Boolean deleteByKey(MobileVerificationDetailsKey key);
	
	Integer count();
	
	Boolean clearTestData();
	

}
