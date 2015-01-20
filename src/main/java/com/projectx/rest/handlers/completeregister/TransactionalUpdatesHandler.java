package com.projectx.rest.handlers.completeregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.data.domain.completeregister.CustomerOrVendorDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterEmailMobileVerificationEntity;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.repository.completeregister.TransactionalUpdatesRepository;
import com.projectx.rest.services.completeregister.TransactionalUpdatesService;

@Component
@Profile(value="Dev")
public class TransactionalUpdatesHandler implements
		TransactionalUpdatesService {

	@Autowired
	TransactionalUpdatesRepository transactionalUpdatesRepository;
	
	@Override
	public CustomerDetails updateCustomerDetails(CustomerDetails customerDetails) {

		return transactionalUpdatesRepository.updateCustomerDetails(customerDetails);
	}

	@Override
	public VendorDetails updateVendorDetails(VendorDetails vendorDetails) {

		return transactionalUpdatesRepository.updateVendorDetails(vendorDetails);
	}

	@Override
	public Boolean updateMobileInDetailsEnityAndAuthenticationDetails(
			Long entityId, Integer entityType, Integer mobileType) {

		return transactionalUpdatesRepository.updateMobileInDetailsEnityAndAuthenticationDetails(entityId, entityType, mobileType);
	}

	@Override
	public Boolean updateEmailInDetailsEnityAndAuthenticationDetails(
			Long entityId, Integer entityType, Integer emailType) {

		return transactionalUpdatesRepository.updateEmailInDetailsEnityAndAuthenticationDetails(entityId, entityType, emailType);
	}

	@Override
	public CustomerQuickRegisterEmailMobileVerificationEntity saveNewQuickRegisterEntity(
			QuickRegisterEntity quickRegisterEntity) {
		
		return transactionalUpdatesRepository.saveNewQuickRegisterEntity(quickRegisterEntity);
		
	}

	@Override
	public CustomerOrVendorDetailsDTO deleteQuickRegisterEntityCreateDetails(
			QuickRegisterEntity quickRegisterEntity) {
		
		return transactionalUpdatesRepository.deleteQuickRegisterEntityCreateDetails(quickRegisterEntity);
	}

}
