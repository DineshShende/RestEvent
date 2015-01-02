package com.projectx.rest.services.completeregister;

import org.springframework.stereotype.Service;

import com.projectx.rest.domain.completeregister.Address;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;

@Service
public interface CustomerDetailsService {

	CustomerDetails createCustomerDetailsFromQuickRegisterEntity(QuickRegisterEntity quickRegisterEntity);
	
	CustomerDetails mergeCustomerDetails(CustomerDetails customerDetails);
	
	CustomerDetails findById(Long customerId);
	
	Boolean checkIfMobileSaved(Long customerId,Integer customerType,Long mobile);
	
	MobileVerificationDetails saveMobileVerificationDetails(MobileVerificationDetails mobileVerificationDetails);
	
	Boolean checkIfEmailSaved(Long customerId,Integer customerType,String email);
	
	EmailVerificationDetails saveEmailVerificationDetails(EmailVerificationDetails emailVerificationDetails);
	
	Boolean verifyMobileDetails(Long customerId,Integer customerType,Long mobile,Integer mobileType,Integer mobilePin);
	
	Boolean verifyEmailDetails(Long customerId,Integer customerType,String email,String emailHash);
	
	Boolean sendMobileVerificationDetails(Long customerId,Integer customerType,Long mobile);
	
	Boolean sendEmailVerificationDetails(Long customerId,Integer customerType,String email);
	
	//CustomerDetails updateHomeAddress(Long customerId,Address address);
	
	//CustomerDetails updateFirmAddress(Long customerId,Address address);
	
	void clearTestData();
	
	Integer count();
	
}
