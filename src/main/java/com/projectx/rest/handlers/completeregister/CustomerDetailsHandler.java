package com.projectx.rest.handlers.completeregister;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;





import com.projectx.data.domain.completeregister.UpdateMobileVerificationStatusDTO;
import com.projectx.mvc.domain.completeregister.CustomerDetailsAndUpdateStatusDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.repository.completeregister.CustomerDetailsRepository;
import com.projectx.rest.services.completeregister.CustomerDetailsService;
import com.projectx.rest.services.completeregister.TransactionalUpdatesService;
import com.projectx.rest.services.quickregister.EmailVerificationService;
import com.projectx.rest.services.quickregister.MobileVerificationService;
import com.projectx.rest.utils.MessagerSender;

@Component
@Profile(value={"Dev","Test"})
public class CustomerDetailsHandler implements CustomerDetailsService {

	
	@Autowired
	CustomerDetailsRepository customerDetailsRepository;
	
	@Autowired
	MobileVerificationService mobileVerificationService;
	
	@Autowired
	EmailVerificationService emailVerificationService;
	
	@Autowired
	TransactionalUpdatesService transactionalUpdatesService;
	
	@Autowired
	MessagerSender messagerSender;
	
		
	@Override
	public CustomerDetails createCustomerDetailsFromQuickRegisterEntity(
			QuickRegisterEntity quickRegisterEntity) {
	
		
		CustomerDetails newEntity=transactionalUpdatesService.deleteQuickRegisterEntityCreateDetails(quickRegisterEntity).getCustomerDetails();
		
		return newEntity;
			
	}

	
	
	@Override
	public CustomerDetails mergeCustomerDetails(CustomerDetails customerDetails) {
		
		CustomerDetails resultEntity=transactionalUpdatesService.updateCustomerDetails(customerDetails);
				
		return resultEntity;
	}

	

	@Override
	public Boolean verifyMobileDetails(Long customerId, Integer customerType,
			 Integer mobileType,Integer mobilePin) {
		
		Boolean verificationStatus=mobileVerificationService
				.verifyMobilePin(customerId, customerType, mobileType, mobilePin);
		
		if(verificationStatus)
		{
			Boolean result=transactionalUpdatesService.updateMobileInDetailsEnityAndAuthenticationDetails(customerId, customerType, mobileType);
			
			return result;
		}
		else
			return false;
	}

	
	@Override
	public Boolean verifyEmailDetails(Long customerId, Integer customerType,
			Integer emailType,  String emailHash) {
		
		Boolean verificationStatus=emailVerificationService.verifyEmailHash(customerId, customerType,emailType, emailHash);
		
		if(verificationStatus)
		{
			Boolean result=transactionalUpdatesService
					.updateEmailInDetailsEnityAndAuthenticationDetails(customerId, customerType, emailType);
			
			return result;
		}
		else
		{
			return false;
		}
			
	}
	

	@Override
	public Boolean sendMobileVerificationDetails(Long customerId,
			Integer customerType, Integer mobileType) {

		Boolean sendStatus=mobileVerificationService.sendMobilePin(customerId, customerType, mobileType);
		
		return sendStatus;
	}

	@Override
	public Boolean sendEmailVerificationDetails(Long customerId,
			Integer customerType, Integer emailType) {

		Boolean sendStatus=emailVerificationService.sendEmailHash(customerId, customerType, emailType);
		
		return sendStatus;
	}

	@Override
	public CustomerDetails setMetaData(CustomerDetails customerDetails,CustomerDetails oldEntity) {
	
		if(customerDetails.getIsEmailVerified()==null)
			customerDetails.setIsEmailVerified(oldEntity.getIsEmailVerified());
		
		if(customerDetails.getIsMobileVerified()==null)
			customerDetails.setIsMobileVerified(oldEntity.getIsMobileVerified());
		
		if(customerDetails.getIsSecondaryMobileVerified()==null)
			customerDetails.setIsSecondaryMobileVerified(oldEntity.getIsSecondaryMobileVerified());
	
		return customerDetails;
	}


	@Override
	public CustomerDetails findById(Long customerId) {
		
		CustomerDetails fetchedEntity=customerDetailsRepository.findOne(customerId);
		
		return fetchedEntity;

	}


	@Override
	public void clearTestData() {

		customerDetailsRepository.deleteAll();
		
			
	}

	
	@Override
	public Integer count() {
		
		return customerDetailsRepository.count().intValue();
		
	}

}
