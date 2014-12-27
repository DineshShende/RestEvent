package com.projectx.rest.services.quickregister;

import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;


public interface MobileVerificationService {
	
	MobileVerificationDetails getCustomerMobileVerificationDetailsByCustomerIdTypeAndMobile(Long customerId,Integer customerType,Long mobile);

	Boolean reSendMobilePin(Long customerId,Integer customerType,Long mobile);
	
	Boolean reSetMobilePin(Long customerId,Integer customerType,Long mobile);
	
	Boolean verifyMobilePin(Long customerId,Integer customerType,Long mobile,Integer mobilePin);
	
	Integer updateMobilePin(Long customerId,Integer customerType,Long mobile);

	MobileVerificationDetails createCustomerMobileVerificationEntity(Long customerId,Integer customerType,Long mobile,Integer mobileType,String updatedBy);
	
	MobileVerificationDetails saveCustomerMobileVerificationDetails(MobileVerificationDetails mobileVerificationDetails);

	Boolean deleteByKey(MobileVerificationDetailsKey key);
	
	Integer count();
	
	Boolean clearTestData();

}
