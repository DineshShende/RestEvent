package com.projectx.rest.handlers.quickregister;

import static com.projectx.rest.fixtures.quickregister.CustomerQuickRegisterDataFixture.*;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.EmailVerificationDetailNotFoundException;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterEntityNotFoundException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.repository.quickregister.EmailVericationDetailsRepository;
import com.projectx.rest.services.completeregister.CustomerDetailsService;
import com.projectx.rest.services.completeregister.TransactionalUpdatesService;
import com.projectx.rest.services.completeregister.VendorDetailsService;
import com.projectx.rest.services.quickregister.AuthenticationService;
import com.projectx.rest.services.quickregister.QuickRegisterService;
import com.projectx.rest.services.quickregister.EmailVerificationService;
import com.projectx.rest.utils.HandleVerificationService;
import com.projectx.rest.utils.InformationMapper;
import com.projectx.rest.utils.MessageBuilder;
import com.projectx.rest.utils.MessagerSender;

@Component

public class EmailVerificationHandler implements EmailVerificationService {

	@Autowired
	HandleVerificationService handleCustomerVerification; 
	
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
	TransactionalUpdatesService transactionalUpdatesService;
	
	@Autowired
	MessageBuilder messageBuilder;
	
	@Autowired
	MessagerSender messagerSender;
	
	@Autowired
	InformationMapper informationMapper;
	
	private static final Integer UPDATE_SUCESS=new Integer(1);
	
	private static final Integer EMAIL_VALIDITY_TIME_IN_HRS=24;
	
	private static final Integer ENTITY_TYPE_CUSTOMER=new Integer(1);
	
	private static final Integer ENTITY_TYPE_VENDOR=new Integer(2);
	
	private Integer EMAIL_REQ=1;
	
	
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
			EmailVerificationDetails emailVerificationDetails) throws ResourceAlreadyPresentException,ValidationFailedException{
		
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
	public Boolean verifyEmailHashUpdateStatusAndSendPassword(Long customerId,Integer customerType,Integer emailType, String emailHash,String requestBy) 
							throws EmailVerificationDetailNotFoundException,QuickRegisterEntityNotFoundException
	{
		
		Integer updateStatus=UPDATE_SUCESS;
		Boolean sendPasswordStatus=true;
		Boolean updatedStatusDetails=true;
		
		
		if(verifyEmailHash(customerId, customerType, emailType, emailHash))
		{
			try{
				QuickRegisterEntity fetchedEntity=customerQuickRegisterService.getByEntityId(customerId);
				
				fetchedEntity.setIsEmailVerified(true);
				
				fetchedEntity.setUpdateTime(new Date());
				fetchedEntity.setUpdatedBy("ONLINE_CUST");
			
				updateStatus=customerQuickRegisterService.updateEmailVerificationStatus(fetchedEntity.getCustomerId(), fetchedEntity.getIsEmailVerified(),
					fetchedEntity.getUpdateTime(), fetchedEntity.getUpdatedBy());										
			
				sendPasswordStatus=authenticationHandler.sendDefaultPassword(fetchedEntity.getCustomerId(),fetchedEntity.getCustomerType(), false,EMAIL_REQ,requestBy);
			
			}catch(ResourceNotFoundException e)
			{
				 updatedStatusDetails=transactionalUpdatesService
							.updateEmailInDetailsEnityAndAuthenticationDetails(customerId, customerType, emailType,requestBy);
				
			}
			
			if(updateStatus.equals(UPDATE_SUCESS) && sendPasswordStatus && updatedStatusDetails)
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
	public Boolean verifyEmailHash(Long customerId,Integer customerType,Integer emailType, String emailHash) 
			throws EmailVerificationDetailNotFoundException {
		
		
		EmailVerificationDetails emailVerificationDetails=getByEntityIdTypeAndEmailType(customerId, customerType,emailType);
		
	
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
	public Boolean sendOrResendOrResetEmailHash(Long customerId,Integer customerType,Integer emailType,Boolean resetFlag,Boolean resendFlag,
			String requestedBy)throws ResourceNotFoundException
	{
		
		Integer updateStatus=new Integer(1);
		Boolean sentStatus=false;
		
		Integer emailHashUpdateStatus=UPDATE_SUCESS;
		
		EmailVerificationDetails emailVerificationDetails=customerEmailVericationDetailsRepository
				.getByEntityIdTypeAndEmailType(customerId, customerType, emailType);
		
		if(resetFlag || emailVerificationDetails.getEmailHash()==null)
		{	
			emailHashUpdateStatus= updateEmailHash(customerId,customerType, emailType,requestedBy);
			resetFlag=true;
		}
		
		HashMap<String ,Object> basicInfo=informationMapper.getBasicInfoByEntityIdType(customerId, customerType);
		
		String firstName=(String)basicInfo.get("firstName");
		String lastName =(String)basicInfo.get("lastName");
		
		
		if(resetFlag)
		emailVerificationDetails=customerEmailVericationDetailsRepository
				.getByEntityIdTypeAndEmailType(customerId,customerType, emailType);
		
		if(resendFlag)
			updateStatus=customerEmailVericationDetailsRepository.incrementResendCountByCustomerIdAndEmail(customerId, customerType, emailType,requestedBy);

		
		if(updateStatus.equals(UPDATE_SUCESS) && emailHashUpdateStatus.equals(UPDATE_SUCESS))
			sentStatus=messagerSender.sendHashEmail(customerId,customerType, emailType, firstName, lastName,
						emailVerificationDetails.getEmail(), emailVerificationDetails.getEmailHash(),emailVerificationDetails.getUpdatedBy());
		
		if(updateStatus.equals(UPDATE_SUCESS) && sentStatus && emailHashUpdateStatus.equals(UPDATE_SUCESS))
			return true;
		else
			return false;
	}

	@Override
	public Boolean sendEmailHash(Long customerId, Integer customerType,
			Integer emailType,String requestedBy)throws ResourceNotFoundException {
	
		Boolean result=sendOrResendOrResetEmailHash(customerId, customerType, emailType, false, false,requestedBy);
		
		return result;
		
	}

	@Override
	public Boolean reSendEmailHash(Long customerId, Integer customerType,
			Integer emailType,String requestedBy) throws ResourceNotFoundException{

		Boolean result=sendOrResendOrResetEmailHash(customerId, customerType, emailType, false, true,requestedBy);
		
		return result;
		
	}
	
	@Override
	public Boolean reSetEmailHash(Long customerId, Integer customerType,
			Integer emailType,String requestedBy) throws ResourceNotFoundException{

		Boolean result=sendOrResendOrResetEmailHash(customerId, customerType, emailType, true, false,requestedBy);
		
		return result;
		
	}


	
	@Override
	public Integer updateEmailHash(Long customerId,Integer customerType,Integer emailType,String requestedBy) {
		
		String emailHash=handleCustomerVerification.generateEmailHash();
		
		return customerEmailVericationDetailsRepository.resetEmailHashAndEmailHashSentTime(customerId,customerType, emailType, emailHash,
				new Date(), 0,requestedBy);
		
	}

	@Override
	public Boolean deleteByKey(EmailVerificationDetailsKey key) {
		
		Boolean deletionStatus=customerEmailVericationDetailsRepository.delete(key);
		
		return deletionStatus;
	}

	@Override
	public EmailVerificationDetails getByEmail(
			String email) throws ResourceNotFoundException{
		
		EmailVerificationDetails fetchedEmailVerificationDetails=customerEmailVericationDetailsRepository.
				getByEmail(email);
	return fetchedEmailVerificationDetails;

	}

	
	
	@Override
	public Boolean clearTestData() {

		return customerEmailVericationDetailsRepository.clearTestData();
	}
	
	@Override
	public Integer count() {
		
		return customerEmailVericationDetailsRepository.count().intValue();
	}

	

	
}
