package com.projectx.rest.services.completeregister;

import org.springframework.stereotype.Service;

import com.projectx.mvc.domain.completeregister.CustomerDetailsAndUpdateStatusDTO;
import com.projectx.rest.domain.completeregister.Address;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.repository.completeregister.CustomerDetailsTransactionalUpdateFailedException;
import com.projectx.rest.exception.repository.quickregister.DeleteQuickCreateDetailsEntityFailedException;

@Service
public interface CustomerDetailsService {

	CustomerDetails createCustomerDetailsFromQuickRegisterEntity(QuickRegisterEntity quickRegisterEntity)
		throws DeleteQuickCreateDetailsEntityFailedException;
	
	CustomerDetails setMetaData(CustomerDetails customerDetails,CustomerDetails oldEntity);
	
	CustomerDetails mergeCustomerDetails(CustomerDetails customerDetails);
	
	CustomerDetails findById(Long customerId);
	
	Boolean verifyMobileDetails(Long customerId,Integer customerType,Integer mobileType,Integer mobilePin,String updatedBy);
	
	Boolean verifyEmailDetails(Long customerId,Integer customerType,Integer emailType,String emailHash,String requestedBy);
	
	Boolean sendMobileVerificationDetails(Long customerId,Integer customerType,Integer mobileType,String updatedBy);
	
	Boolean sendEmailVerificationDetails(Long customerId,Integer customerType,Integer emailType,String requestedBy);
	
	void clearTestData();
	
	Integer count();
	
	
}
