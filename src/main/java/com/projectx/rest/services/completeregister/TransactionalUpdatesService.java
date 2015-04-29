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

	Boolean updateMobileInDetailsEnityAndAuthenticationDetails(Long entityId,Integer entityType,Integer mobileType,
			Integer updatedBy,Long updatedById);

	Boolean updateEmailInDetailsEnityAndAuthenticationDetails(Long entityId,Integer entityType,Integer emailType,
			Integer updatedBy,Long updatedById);

	CustomerQuickRegisterEmailMobileVerificationEntity saveNewQuickRegisterEntity( QuickRegisterEntity quickRegisterEntity);
	
	CustomerOrVendorDetailsDTO deleteQuickRegisterEntityCreateDetails( QuickRegisterEntity quickRegisterEntity);

}
