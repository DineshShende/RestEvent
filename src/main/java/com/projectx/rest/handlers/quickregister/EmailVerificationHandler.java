package com.projectx.rest.handlers.quickregister;

import static com.projectx.rest.fixtures.quickregister.CustomerQuickRegisterDataFixture.*;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.repository.quickregister.EmailVericationDetailsRepository;
import com.projectx.rest.services.completeregister.CustomerDetailsService;
import com.projectx.rest.services.completeregister.VendorDetailsService;
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
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	VendorDetailsService vendorDetailsService;
	
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
	
	private static final Integer ENTITY_TYPE_CUSTOMER=new Integer(1);
	
	private static final Integer ENTITY_TYPE_VENDOR=new Integer(2);
	
	
	@Override
	public EmailVerificationDetails createEntity(
			Long customerId,Integer customerType,String email,Integer emailType,String updatedBy) {
		EmailVerificationDetails emailVerificationDetails=new EmailVerificationDetails();
		
		EmailVerificationDetailsKey  key=new EmailVerificationDetailsKey(customerId,customerType,emailType);
		
		emailVerificationDetails.setKey(key);
		emailVerificationDetails.setEmailHash(handleCustomerVerification.generateEmailHash());
		emailVerificationDetails.setEmailHashSentTime(new Date());
		emailVerificationDetails.setEmail(email);
		emailVerificationDetails.setResendCount(0);
		emailVerificationDetails.setUpdatedBy(updatedBy);
		emailVerificationDetails.setInsertTime(new Date());
		emailVerificationDetails.setUpdateTime(new Date());
		
		return emailVerificationDetails;
	}
	
	@Override
	public EmailVerificationDetails saveDetails(
			EmailVerificationDetails emailVerificationDetails) {
		
		EmailVerificationDetails verificationDetails=customerEmailVericationDetailsRepository.save(emailVerificationDetails);
		
		return verificationDetails;
	}



	@Override
	public EmailVerificationDetails getByEntityIdTypeAndEmailType(
			Long customerId,Integer customerType, Integer emailType) {
		EmailVerificationDetails fetchedEmailVerificationDetails=customerEmailVericationDetailsRepository.
					getByEntityIdTypeAndEmailType(customerId,customerType, emailType);
		return fetchedEmailVerificationDetails;
	}


	@Override
	public Boolean verifyEmailHashUpdateStatusAndSendPassword(Long customerId,Integer customerType,Integer emailType, String emailHash) {
		
		QuickRegisterEntity fetchedEntity=customerQuickRegisterService.getByEntityId(customerId);
		
	
		if(verifyEmailHash(customerId, customerType, emailType, emailHash))
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
	public Boolean verifyEmailHash(Long customerId,Integer customerType,Integer emailType, String emailHash) {
		
		
		EmailVerificationDetails emailVerificationDetails=getByEntityIdTypeAndEmailType(customerId, customerType,emailType);
			
		if(emailVerificationDetails.getKey()==null)
			return false;
	
		Date emailHashSentTime=emailVerificationDetails.getEmailHashSentTime();
		
		Date verificationTime=new Date();
		
		long dateDiffernce=verificationTime.getTime()-emailHashSentTime.getTime();
		
		long diffHours = dateDiffernce / (60 * 60 * 1000);
		
		
		if(emailVerificationDetails.getEmailHash().equals(emailHash) && diffHours<EMAIL_VALIDITY_TIME_IN_HRS)
		{
			return true;			
		}
		else
		{
			return false;
		}
		
	}

	@Override
	public Boolean sendOrResendOrResetEmailHash(Long customerId,Integer customerType,Integer emailType,Boolean resetFlag,Boolean resendFlag) {
		
		Integer updateStatus=new Integer(1);
		Boolean sentStatus=false;
		
		Integer emailHashUpdateStatus=UPDATE_SUCESS;
		
		EmailVerificationDetails emailVerificationDetails=customerEmailVericationDetailsRepository
				.getByEntityIdTypeAndEmailType(customerId, customerType, emailType);
		
		if(resetFlag || emailVerificationDetails.getEmailHash()==null)
		{	
			emailHashUpdateStatus= updateEmailHash(customerId,customerType, emailType);
			resetFlag=true;
		}
		
		String firstName=null;
		String lastName = null;
		
		QuickRegisterEntity quickRegisterEntity=customerQuickRegisterService.getByEntityId(customerId);
		
		if(customerType.equals(ENTITY_TYPE_CUSTOMER))
		{
			if(quickRegisterEntity.getCustomerId()!=null)
			{
				firstName=quickRegisterEntity.getFirstName();
				lastName=quickRegisterEntity.getLastName();
			}
			else
			{	
				CustomerDetails customerDetails=customerDetailsService.findById(customerId);
				firstName=customerDetails.getFirstName();
				lastName=customerDetails.getLastName();
			}
			
		}
		else if(customerType.equals(ENTITY_TYPE_VENDOR))
		{
			if(quickRegisterEntity.getCustomerId()!=null)
			{
				firstName=quickRegisterEntity.getFirstName();
				lastName=quickRegisterEntity.getLastName();
			}
			else
			{
				VendorDetails vendorDetails=vendorDetailsService.findById(customerId);
				firstName=vendorDetails.getFirstName();
				lastName=vendorDetails.getLastName();
			}
		}		
		
		if(resetFlag)
		emailVerificationDetails=customerEmailVericationDetailsRepository
				.getByEntityIdTypeAndEmailType(customerId,customerType, emailType);
		
		if(resendFlag)
			updateStatus=customerEmailVericationDetailsRepository.incrementResendCountByCustomerIdAndEmail(customerId, customerType, emailType);

		
		if(updateStatus.equals(UPDATE_SUCESS) && emailHashUpdateStatus.equals(UPDATE_SUCESS))
			sentStatus=messagerSender.sendHashEmail(customerId, firstName, lastName,
						emailVerificationDetails.getEmail(), emailVerificationDetails.getEmailHash());
		
		if(updateStatus.equals(UPDATE_SUCESS) && sentStatus && emailHashUpdateStatus.equals(UPDATE_SUCESS))
			return true;
		else
			return false;
	}

	@Override
	public Boolean sendEmailHash(Long customerId, Integer customerType,
			Integer emailType) {
	
		Boolean result=sendOrResendOrResetEmailHash(customerId, customerType, emailType, false, false);
		
		return result;
		
	}

	@Override
	public Boolean reSendEmailHash(Long customerId, Integer customerType,
			Integer emailType) {

		Boolean result=sendOrResendOrResetEmailHash(customerId, customerType, emailType, false, true);
		
		return result;
		
	}
	
	@Override
	public Boolean reSetEmailHash(Long customerId, Integer customerType,
			Integer emailType) {

		Boolean result=sendOrResendOrResetEmailHash(customerId, customerType, emailType, true, false);
		
		return result;
		
	}


	
	@Override
	public Integer updateEmailHash(Long customerId,Integer customerType,Integer emailType) {
		
		String emailHash=handleCustomerVerification.generateEmailHash();
		
		return customerEmailVericationDetailsRepository.resetEmailHashAndEmailHashSentTime(customerId,customerType, emailType, emailHash, new Date(), 0);
		
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

	@Override
	public EmailVerificationDetails getByEmail(
			String email) {
		
		EmailVerificationDetails fetchedEmailVerificationDetails=customerEmailVericationDetailsRepository.
				getByEmail(email);
	return fetchedEmailVerificationDetails;

	}

	


	
	/*
	@Override
	public String checkIfEmailAlreadyExist(Long customerId,Integer customerType,Integer emailType,String email) {
		
		EmailVerificationDetails fetchedEntity=customerEmailVericationDetailsRepository.getByEmail(email);
		
		if(fetchedEntity.getKey()!=null && fetchedEntity.getKey().getCustomerId().equals(customerId)&&
				fetchedEntity.getKey().getCustomerType().equals(customerType) && fetchedEntity.getKey().getEmailType().equals(emailType))
			return "EXIST";
		else if(fetchedEntity.getKey()!=null)
			return "EXISTWITHOTHERENTITY";
		else
			return "NOTEXIST";
	}
*/
	
}
