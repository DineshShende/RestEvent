package com.projectx.rest.services.completeregister;

import org.springframework.stereotype.Service;

import com.projectx.mvc.domain.completeregister.CustomerDetailsAndUpdateStatusDTO;
import com.projectx.rest.domain.completeregister.Address;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;

@Service
public interface CustomerDetailsService {

	CustomerDetails createCustomerDetailsFromQuickRegisterEntity(QuickRegisterEntity quickRegisterEntity);
	
	CustomerDetails setMetaData(CustomerDetails customerDetails,CustomerDetails oldEntity);
	
	CustomerDetails mergeCustomerDetails(CustomerDetails customerDetails);
	
	CustomerDetails findById(Long customerId);
	
	Boolean verifyMobileDetails(Long customerId,Integer customerType,Integer mobileType,Integer mobilePin);
	
	Boolean verifyEmailDetails(Long customerId,Integer customerType,Integer emailType,String emailHash);
	
	Boolean sendMobileVerificationDetails(Long customerId,Integer customerType,Integer mobileType);
	
	Boolean sendEmailVerificationDetails(Long customerId,Integer customerType,Integer emailType);
	
	void clearTestData();
	
	Integer count();
	
	//CustomerDetails updateHomeAddress(Long customerId,Address address);
	
	//CustomerDetails updateFirmAddress(Long customerId,Address address);
	
}
