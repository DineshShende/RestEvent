package com.projectx.rest.services.quickregister;

import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;


public interface MobileVerificationService {
	
	MobileVerificationDetails getByEntityIdTypeAndMobileType(Long customerId,Integer customerType,Integer mobileType);
	
	MobileVerificationDetails getByMobile(Long mobile);
	
	Boolean sendOrResendOrResetMobilePin(Long entityId,Integer entityType,Integer mobileType,Boolean resetFlag,Boolean resendFlag);
	
	Boolean sendMobilePin(Long entityId,Integer entityType,Integer mobileType);

	Boolean reSendMobilePin(Long customerId,Integer customerType,Integer mobileType);
	
	Boolean reSetMobilePin(Long customerId,Integer customerType,Integer mobileType);
	
	Boolean verifyMobilePin(Long customerId,Integer customerType,Integer mobileType,Integer mobilePin);
	
	Boolean verifyMobilePinUpdateStatusAndSendPassword(Long customerId,Integer customerType,Integer mobileType,Integer mobilePin);
	
	Integer updateMobilePin(Long customerId,Integer customerType,Integer mobileType);

	MobileVerificationDetails createEntity(Long customerId,Integer customerType,Long mobile,Integer mobileType,String updatedBy);
	
	MobileVerificationDetails saveDetails(MobileVerificationDetails mobileVerificationDetails);

	Boolean deleteByKey(MobileVerificationDetailsKey key);
	
	Integer count();
	
	Boolean clearTestData();
	
	//String checkIfMobileAlreadyExist(Long customerId,Integer customerType,Integer mobileType,Long mobile);

}
