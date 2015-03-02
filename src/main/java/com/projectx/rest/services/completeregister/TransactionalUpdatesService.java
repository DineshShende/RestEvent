package com.projectx.rest.services.completeregister;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.projectx.data.domain.completeregister.CustomerOrVendorDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterEmailMobileVerificationEntity;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;

@Service
public interface TransactionalUpdatesService {

	CustomerDetails updateCustomerDetails( CustomerDetails customerDetails);
	
	VendorDetails updateVendorDetails( VendorDetails vendorDetails);
	
	Boolean updateMobileInDetailsEnityAndAuthenticationDetails(Long entityId,Integer entityType,Integer mobileType,String updatedBy);

	Boolean updateEmailInDetailsEnityAndAuthenticationDetails(Long entityId,Integer entityType,Integer emailType,String updatedBy);

	CustomerQuickRegisterEmailMobileVerificationEntity saveNewQuickRegisterEntity( QuickRegisterEntity quickRegisterEntity);
	
	CustomerOrVendorDetailsDTO deleteQuickRegisterEntityCreateDetails( QuickRegisterEntity quickRegisterEntity);

}
