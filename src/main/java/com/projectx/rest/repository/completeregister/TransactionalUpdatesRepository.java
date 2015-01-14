package com.projectx.rest.repository.completeregister;

import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;

@Repository

public interface TransactionalUpdatesRepository {
	
	CustomerDetails updateCustomerDetails( CustomerDetails customerDetails);
	
	VendorDetails updateVendorDetails( VendorDetails vendorDetails);
	
	Boolean updateMobileInDetailsEnityAndAuthenticationDetails(Long entityId,Integer entityType,Integer mobileType);

	Boolean updateEmailInDetailsEnityAndAuthenticationDetails(Long entityId,Integer entityType,Integer emailType);
	
	

}
