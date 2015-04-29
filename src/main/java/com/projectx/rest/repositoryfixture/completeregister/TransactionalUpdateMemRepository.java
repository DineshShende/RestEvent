package com.projectx.rest.repositoryfixture.completeregister;

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

@Component
@Profile(value="Test")
public class TransactionalUpdateMemRepository implements
		TransactionalUpdatesRepository {


	@Override
	public Boolean updateMobileInDetailsEnityAndAuthenticationDetails(
			Long entityId, Integer entityType, Integer mobileType,
			Integer updatedBy,Long updatedById)
			throws UpdateMobileInDetailsAndAuthentionDetailsFailedException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Boolean updateEmailInDetailsEnityAndAuthenticationDetails(
			Long entityId, Integer entityType, Integer emailType,
			Integer updatedBy,Long updatedById)
			throws UpdateEmailInDetailsAndAuthenticationDetailsFailedException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public CustomerQuickRegisterEmailMobileVerificationEntity saveNewQuickRegisterEntity(
			QuickRegisterEntity quickRegisterEntity)
			throws QuickRegisterDetailsAlreadyPresentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerOrVendorDetailsDTO deleteQuickRegisterEntityCreateDetails(
			QuickRegisterEntity quickRegisterEntity)
			throws DeleteQuickCreateDetailsEntityFailedException {
		// TODO Auto-generated method stub
		return null;
	}

}
