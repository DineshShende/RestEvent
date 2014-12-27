package com.projectx.rest.handlers.quickregister;

import static com.projectx.rest.fixtures.quickregister.CustomerQuickRegisterDataFixture.*;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.repository.quickregister.EmailVericationDetailsRepository;
import com.projectx.rest.services.quickregister.AuthenticationService;
import com.projectx.rest.services.quickregister.QuickRegisterService;
import com.projectx.rest.services.quickregister.EmailVerificationService;
import com.projectx.rest.utils.HandleCustomerVerification;
import com.projectx.rest.utils.MessageBuilder;
import com.projectx.rest.utils.MessagerSender;

@Component
@Profile(value={"Dev","Test"})

public class EmailVerificationHandler implements EmailVerificationService {

	@Autowired
	HandleCustomerVerification handleCustomerVerification; 
	
	@Autowired
	QuickRegisterService customerQuickRegisterService; 
	
	@Autowired
	AuthenticationService authenticationHandler;
	
	@Autowired
	EmailVericationDetailsRepository customerEmailVericationDetailsRepository;
	
	
	@Autowired
	MessageBuilder messageBuilder;
	
	@Autowired
	MessagerSender messagerSender;
	
	private static final Integer UPDATE_SUCESS=new Integer(1);
	
	private static final Integer EMAIL_VALIDITY_TIME_IN_HRS=24;
	
	@Override
	public EmailVerificationDetails createCustomerEmailVerificationEntity(
			Long customerId,Integer customerType,String email,Integer emailType,String updatedBy) {
		EmailVerificationDetails emailVerificationDetails=new EmailVerificationDetails();
		
		EmailVerificationDetailsKey  key=new EmailVerificationDetailsKey(customerId,customerType,email);
		
		emailVerificationDetails.setKey(key);
		emailVerificationDetails.setEmailHash(handleCustomerVerification.generateEmailHash());
		emailVerificationDetails.setEmailHashSentTime(new Date());
		emailVerificationDetails.setEmailType(emailType);
		emailVerificationDetails.setResendCount(0);
		emailVerificationDetails.setUpdatedBy(updatedBy);
		emailVerificationDetails.setInsertTime(new Date());
		emailVerificationDetails.setInsertTime(new Date());
		
		return emailVerificationDetails;
	}
	
	@Override
	public EmailVerificationDetails saveCustomerEmailVerificationDetails(
			EmailVerificationDetails emailVerificationDetails) {
		
		EmailVerificationDetails verificationDetails=customerEmailVericationDetailsRepository.save(emailVerificationDetails);
		
		return verificationDetails;
	}



	@Override
	public EmailVerificationDetails getCustomerEmailVerificationDetailsByCustomerIdTypeAndEmail(
			Long customerId,Integer customerType, String email) {
		EmailVerificationDetails fetchedEmailVerificationDetails=customerEmailVericationDetailsRepository.
					getEmailVerificationDetailsByCustomerIdTypeAndEmail(customerId,customerType, email);
		return fetchedEmailVerificationDetails;
	}

	
	
	@Override
	public Boolean verifyEmailHash(Long customerId,Integer customerType,String email, String emailHash) {
		
		QuickRegisterEntity fetchedEntity=customerQuickRegisterService.getCustomerQuickRegisterEntityByCustomerId(customerId);
		EmailVerificationDetails emailVerificationDetails=getCustomerEmailVerificationDetailsByCustomerIdTypeAndEmail(customerId, customerType,email);
			
		if(fetchedEntity.getCustomerId()==null||emailVerificationDetails.getKey()==null)
			return false;
	
		Date emailHashSentTime=emailVerificationDetails.getEmailHashSentTime();
		
		Date verificationTime=new Date();
		
		long dateDiffernce=verificationTime.getTime()-emailHashSentTime.getTime();
		
		long diffHours = dateDiffernce / (60 * 60 * 1000);
		
		
		if(emailVerificationDetails.getEmailHash().equals(emailHash) && diffHours<EMAIL_VALIDITY_TIME_IN_HRS)
		{
							
			fetchedEntity.setIsEmailVerified(true);
			
			fetchedEntity.setUpdateTime(new Date());
			fetchedEntity.setUpdatedBy("ONLINE_CUST");
			
			Integer updatedStatus=customerQuickRegisterService.updateEmailVerificationStatus(fetchedEntity.getCustomerId(), fetchedEntity.getIsEmailVerified(),
					fetchedEntity.getUpdateTime(), fetchedEntity.getUpdatedBy());										
			
			Boolean sendPasswordStatus=authenticationHandler.sendDefaultPassword(fetchedEntity, false);
			
			
			if(updatedStatus.equals(UPDATE_SUCESS) && sendPasswordStatus)
				return true;
			else
				return false;
		}
		else
		{
			return false;
		}
		
	}

	@Override
	public Boolean reSetEmailHash(Long customerId,Integer customerType,String email) {
		
		Integer updateStatus=new Integer(0);
			
		updateStatus= updateEmailHash(customerId,customerType, email);
		
		QuickRegisterEntity customer=customerQuickRegisterService.getCustomerQuickRegisterEntityByCustomerId(customerId);
		EmailVerificationDetails emailVerificationDetails=customerEmailVericationDetailsRepository
				.getEmailVerificationDetailsByCustomerIdTypeAndEmail(customer.getCustomerId(),customer.getCustomerType(), customer.getEmail());
		
		Boolean sentStatus=messagerSender
				.sendHashEmail(customer.getCustomerId(), customer.getFirstName(), customer.getLastName(),
						customer.getEmail(), emailVerificationDetails.getEmailHash());
		
		if(updateStatus.equals(UPDATE_SUCESS) && sentStatus)
			return true;
		else
			return false;
	}

	@Override
	public Boolean reSendEmailHash(Long customerId,Integer customerType, String email) {

		Integer updateStatus=new Integer(0);
		Boolean sentStatus=false;
		
		QuickRegisterEntity customer=customerQuickRegisterService.getCustomerQuickRegisterEntityByCustomerId(customerId);
		EmailVerificationDetails emailVerificationDetails=customerEmailVericationDetailsRepository
				.getEmailVerificationDetailsByCustomerIdTypeAndEmail(customer.getCustomerId(),customer.getCustomerType(), customer.getEmail());
		
		
		updateStatus=customerEmailVericationDetailsRepository.incrementResendCountByCustomerIdAndEmail(customerId,customerType, email);
		
		if(updateStatus.equals(UPDATE_SUCESS))
			sentStatus=messagerSender
				.sendHashEmail(customer.getCustomerId(), customer.getFirstName(), customer.getLastName(),
						customer.getEmail(), emailVerificationDetails.getEmailHash());
				
		if(updateStatus.equals(UPDATE_SUCESS) && sentStatus)
			return true;
		else
			return false;
		
	}

	@Override
	public Integer updateEmailHash(Long customerId,Integer customerType,String email) {
		
		String emailHash=handleCustomerVerification.generateEmailHash();
		
		return customerEmailVericationDetailsRepository.resetEmailHashAndEmailHashSentTime(customerId,customerType, email, emailHash, new Date(), 0);
		
	}

	@Override
	public Boolean clearTestData() {

		return customerEmailVericationDetailsRepository.clearTestData();
	}

	@Override
	public Boolean deleteByKey(EmailVerificationDetailsKey key) {
		
		Boolean deletionStatus=customerEmailVericationDetailsRepository.delete(key);
		
		return deletionStatus;
	}

	@Override
	public Integer count() {
		
		return customerEmailVericationDetailsRepository.count().intValue();
	}

	
}
