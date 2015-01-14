package com.projectx.rest.services.completeregister;

import org.springframework.stereotype.Service;

import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;

@Service
public interface TransactionalUpdatesService {

	CustomerDetails updateCustomerDetails( CustomerDetails customerDetails);
	
	VendorDetails updateVendorDetails( VendorDetails vendorDetails);
	
	Boolean updateMobileInDetailsEnityAndAuthenticationDetails(Long entityId,Integer entityType,Integer mobileType);

	Boolean updateEmailInDetailsEnityAndAuthenticationDetails(Long entityId,Integer entityType,Integer emailType);
	

}
