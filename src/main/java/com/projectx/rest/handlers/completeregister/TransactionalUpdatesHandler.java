package com.projectx.rest.handlers.completeregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.data.domain.completeregister.CustomerOrVendorDetailsDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterEmailMobileVerificationEntity;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.repository.completeregister.CustomerDetailsTransactionalUpdateFailedException;
import com.projectx.rest.exception.repository.completeregister.UpdateEmailInDetailsAndAuthenticationDetailsFailedException;
import com.projectx.rest.exception.repository.completeregister.UpdateMobileInDetailsAndAuthentionDetailsFailedException;
import com.projectx.rest.exception.repository.completeregister.VendorDetailsTransactionalUpdateFailedException;
import com.projectx.rest.exception.repository.quickregister.DeleteQuickCreateDetailsEntityFailedException;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterDetailsAlreadyPresentException;
import com.projectx.rest.repository.completeregister.TransactionalUpdatesRepository;
import com.projectx.rest.services.completeregister.TransactionalUpdatesService;

@Component

public class TransactionalUpdatesHandler implements
		TransactionalUpdatesService {

	@Autowired
	TransactionalUpdatesRepository transactionalUpdatesRepository;
	
	@Override
	public CustomerDetails updateCustomerDetails(CustomerDetails customerDetails) throws CustomerDetailsTransactionalUpdateFailedException{

		return transactionalUpdatesRepository.updateCustomerDetails(customerDetails);
	}

	@Override
	public VendorDetails updateVendorDetails(VendorDetails vendorDetails) throws VendorDetailsTransactionalUpdateFailedException{

		return transactionalUpdatesRepository.updateVendorDetails(vendorDetails);
	}

	@Override
	public Boolean updateMobileInDetailsEnityAndAuthenticationDetails(
			Long entityId, Integer entityType, Integer mobileType,String updatedBy) throws UpdateMobileInDetailsAndAuthentionDetailsFailedException{

		return transactionalUpdatesRepository.updateMobileInDetailsEnityAndAuthenticationDetails(entityId, entityType, mobileType,updatedBy);
	}

	@Override
	public Boolean updateEmailInDetailsEnityAndAuthenticationDetails(
			Long entityId, Integer entityType, Integer emailType,String updatedBy) throws UpdateEmailInDetailsAndAuthenticationDetailsFailedException {

		return transactionalUpdatesRepository.updateEmailInDetailsEnityAndAuthenticationDetails(entityId, entityType, emailType,updatedBy);
	}

	@Override
	public CustomerQuickRegisterEmailMobileVerificationEntity saveNewQuickRegisterEntity(
			QuickRegisterEntity quickRegisterEntity)throws QuickRegisterDetailsAlreadyPresentException {
		
		return transactionalUpdatesRepository.saveNewQuickRegisterEntity(quickRegisterEntity);
		
	}

	@Override
	public CustomerOrVendorDetailsDTO deleteQuickRegisterEntityCreateDetails(
			QuickRegisterEntity quickRegisterEntity) throws DeleteQuickCreateDetailsEntityFailedException {
		
		return transactionalUpdatesRepository.deleteQuickRegisterEntityCreateDetails(quickRegisterEntity);
	}

}
