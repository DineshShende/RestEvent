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
import com.projectx.rest.exception.repository.completeregister.VendorDetailsNotFoundException;
import com.projectx.rest.exception.repository.completeregister.VendorDetailsTransactionalUpdateFailedException;
import com.projectx.rest.exception.repository.quickregister.DeleteQuickCreateDetailsEntityFailedException;
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
	MobileVerificationService mobileVerificationService;
	
	@Autowired
	EmailVerificationService emailVerificationService;
	
	@Autowired
	TransactionalUpdatesService transactionalUpdatesService;
	
	@Autowired
	MessagerSender messagerSender;
	
	private static Integer UPDATE_SUCESS=new Integer(1);

	
	@Override
	public VendorDetails createCustomerDetailsFromQuickRegisterEntity(
			QuickRegisterEntity quickRegisterEntity)throws DeleteQuickCreateDetailsEntityFailedException {

		VendorDetails savedEntity=transactionalUpdatesService.deleteQuickRegisterEntityCreateDetails(quickRegisterEntity).getVendorDetails();
		
		return savedEntity;
		
	}

	@Override
	public VendorDetails updateVendorDetails(VendorDetails vendorDetails) throws VendorDetailsTransactionalUpdateFailedException{
		
		VendorDetails updatedEntity=transactionalUpdatesService.updateVendorDetails(vendorDetails);
		
		return updatedEntity;
	}

	@Override
	public VendorDetails findById(Long vendorId) throws VendorDetailsNotFoundException{

		VendorDetails fetchedEntity=vendorDetailsRepository.findOne(vendorId);
		
		return fetchedEntity;
		
	}

	@Override
	public Boolean verifyMobileDetails(Long customerId, Integer customerType,
			 Integer mobileType,Integer mobilePin,String updatedBy) {
		
		Boolean verificationStatus=mobileVerificationService
				.verifyMobilePinUpdateStatusAndSendPassword(customerId, customerType, mobileType, mobilePin,updatedBy);
		
		return verificationStatus;
	}

	
	@Override
	public Boolean verifyEmailDetails(Long customerId, Integer customerType,
			Integer emailType,  String emailHash,String requestedBy) {
		
		Boolean verificationStatus=emailVerificationService
				.verifyEmailHashUpdateStatusAndSendPassword(customerId, customerType, emailType, emailHash,requestedBy);
				
		return verificationStatus;
	}
	@Override
	public Boolean sendMobileVerificationDetails(Long vendorId,
			Integer entityType, Integer mobileType,String updatedBy) {

		Boolean sendStatus=mobileVerificationService.sendMobilePin(vendorId, entityType, mobileType,updatedBy);
		
		return sendStatus;		
	}

	@Override
	public Boolean sendEmailVerificationDetails(Long vendorId,
			Integer entityType, Integer emailType) {

		Boolean sendStatus=emailVerificationService.sendEmailHash(vendorId, entityType, emailType);
		
		return sendStatus;
	
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

	/*
	 	@Override
	public String checkIfMobileSaved(Long vendorId, Integer entityType,Integer mobileType,
			Long mobile) {

		String status=mobileVerificationService.checkIfMobileAlreadyExist(vendorId, entityType,mobileType, mobile);
		
		return status;
		
	}

	@Override
	public String checkIfEmailSaved(Long vendorId, Integer entityType,Integer emailType,
			String email) {

		String status=emailVerificationService.checkIfEmailAlreadyExist(vendorId, entityType,emailType, email);
		
		return status;
		
	}
	
		@Override
	public MobileVerificationDetails saveMobileVerificationDetails(
			MobileVerificationDetails mobileVerificationDetails) {

		MobileVerificationDetails savedEntity=mobileVerificationService.saveDetails(mobileVerificationDetails);
		
		return savedEntity;
		
	}
	


	@Override
	public EmailVerificationDetails saveEmailVerificationDetails(
			EmailVerificationDetails emailVerificationDetails) {

		EmailVerificationDetails savedEntity=emailVerificationService.saveDetails(emailVerificationDetails);
		
		return savedEntity;
		
	}
	 
	 
	 * 
	 */
	
}	