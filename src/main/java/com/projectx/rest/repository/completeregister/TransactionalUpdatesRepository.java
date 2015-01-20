package com.projectx.rest.repository.completeregister;

import org.springframework.stereotype.Repository;


import com.projectx.data.domain.completeregister.CustomerOrVendorDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterEmailMobileVerificationEntity;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;

@Repository

public interface TransactionalUpdatesRepository {
	
	CustomerDetails updateCustomerDetails( CustomerDetails customerDetails);
	
	VendorDetails updateVendorDetails( VendorDetails vendorDetails);
	
	Boolean updateMobileInDetailsEnityAndAuthenticationDetails(Long entityId,Integer entityType,Integer mobileType);

	Boolean updateEmailInDetailsEnityAndAuthenticationDetails(Long entityId,Integer entityType,Integer emailType);
	
	CustomerQuickRegisterEmailMobileVerificationEntity saveNewQuickRegisterEntity( QuickRegisterEntity quickRegisterEntity);

	CustomerOrVendorDetailsDTO deleteQuickRegisterEntityCreateDetails( QuickRegisterEntity quickRegisterEntity);
	
}
