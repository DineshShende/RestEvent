package com.projectx.rest.handlers.completeregister;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.data.domain.completeregister.UpdateMobileVerificationStatusUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.completeregister.VendorDetailsNotFoundException;
import com.projectx.rest.exception.repository.completeregister.VendorDetailsTransactionalUpdateFailedException;
import com.projectx.rest.exception.repository.quickregister.DeleteQuickCreateDetailsEntityFailedException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.repository.completeregister.CustomerDetailsRepository;
import com.projectx.rest.repository.completeregister.VendorDetailsRepository;
import com.projectx.rest.services.completeregister.TransactionalUpdatesService;
import com.projectx.rest.services.completeregister.VendorDetailsService;
import com.projectx.rest.services.quickregister.EmailVerificationService;
import com.projectx.rest.services.quickregister.MobileVerificationService;
import com.projectx.rest.utils.MessagerSender;

@Component

public class VendorDetailsHandler implements VendorDetailsService {

	
	@Autowired
	VendorDetailsRepository vendorDetailsRepository;
	
	@Autowired
	TransactionalUpdatesService transactionalUpdatesService;

	
	@Override
	public VendorDetails createCustomerDetailsFromQuickRegisterEntity(
			QuickRegisterEntity quickRegisterEntity)throws DeleteQuickCreateDetailsEntityFailedException {

		VendorDetails savedEntity=transactionalUpdatesService.deleteQuickRegisterEntityCreateDetails(quickRegisterEntity).getVendorDetails();
		
		return savedEntity;
		
	}

	@Override
	public VendorDetails updateVendorDetails(VendorDetails vendorDetails) throws VendorDetailsTransactionalUpdateFailedException{
		
		VendorDetails updatedEntity=vendorDetailsRepository.save(vendorDetails);
		
		return updatedEntity;
	}

	@Override
	public VendorDetails findById(Long vendorId) throws VendorDetailsNotFoundException{

		VendorDetails fetchedEntity=vendorDetailsRepository.findOne(vendorId);
		
		return fetchedEntity;
		
	}


	@Override
	public void clearTestData() {


		vendorDetailsRepository.clearTestData();

	}

	@Override
	public Integer count() {
		

		Integer count=vendorDetailsRepository.count();
		
		return count;
	}


	
}	