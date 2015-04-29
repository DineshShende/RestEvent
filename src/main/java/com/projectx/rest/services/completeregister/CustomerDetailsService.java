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
	
	void clearTestData();
	
	Integer count();
	
	
}
