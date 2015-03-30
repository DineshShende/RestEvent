package com.projectx.rest.handlers.quickregister;

import static com.projectx.rest.fixtures.quickregister.CustomerQuickRegisterDataFixture.*;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.projectx.data.domain.quickregister.MobilePinPasswordDTO;
import com.projectx.mvc.domain.quickregister.CustomerQuickRegisterEntityDTO;
import com.projectx.mvc.domain.quickregister.CustomerQuickRegisterStringStatusEntity;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterEmailMobileVerificationEntity;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterStatusEntity;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.EmailVerificationDetailNotFoundException;
import com.projectx.rest.exception.repository.quickregister.MobileVerificationDetailsNotFoundException;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterDetailsAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterEntityNotFoundException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.repository.completeregister.DocumentDetailsRepository;
import com.projectx.rest.repository.completeregister.TransactionalUpdatesRepository;
import com.projectx.rest.repository.quickregister.QuickRegisterRepository;
import com.projectx.rest.services.quickregister.EmailVerificationService;
import com.projectx.rest.services.quickregister.MobileVerificationService;
import com.projectx.rest.services.quickregister.QuickRegisterService;
import com.projectx.rest.utils.HandleVerificationService;
import com.projectx.rest.utils.MessageBuilder;
import com.projectx.rest.utils.MessagerSender;

@Component
@PropertySource(value="classpath:/application.properties")
public class QuickRegisterHandler implements
		QuickRegisterService {

	@Autowired
	QuickRegisterRepository customerQuickRegisterRepository;
	
	@Autowired
	AuthenticationHandler authenticationHandler; 
	
	@Autowired
	EmailVerificationService emailVerificationService;
	
	@Autowired
	MobileVerificationService mobileVerificationService; 
	
	@Autowired
	DocumentDetailsRepository customerDocumentRepository;

	@Autowired
	TransactionalUpdatesRepository transactionalUpdatesRepository;
	
	@Autowired 
	HandleVerificationService handleCustomerVerification;

	@Autowired
	MessageBuilder messageBuilder;
	
	@Autowired
	MessagerSender messagerSender;
	
	private  Integer ENTITY_TYPE_CUSTOMER=1;
	private  Integer ENTITY_TYPE_VENDOR=2;
	
	private  Integer ENTITY_TYPE_PRIMARY=1;
	private  Integer ENTITY_TYPE_SECONDARY=2;
	
	@Override
	public CustomerQuickRegisterStatusEntity checkIfAlreadyRegistered(
			CustomerQuickRegisterEntityDTO customer)  {

		String status=null;
		
		Boolean emailAlreadyExist = false;
		
		Boolean emailVerified=false;

		Boolean mobileAlreadyExist = false;
		
		Boolean mobileVerified=false;
	
		QuickRegisterEntity returnEntity=new QuickRegisterEntity();
		
		EmailVerificationDetails entityByEmail=new EmailVerificationDetails();
		
		MobileVerificationDetails entityByMobile=new MobileVerificationDetails();
		
		if(customer.getEmail()!=null)
		{
			try{
				entityByEmail=emailVerificationService.getByEmail(customer.getEmail());
			}catch(EmailVerificationDetailNotFoundException e)
			{
				//entityByEmail=new EmailVerificationDetails();
			}
		}
		
		if(customer.getMobile()!=null)
		{
			try{
				entityByMobile=mobileVerificationService.getByMobile(customer.getMobile());
			}catch(MobileVerificationDetailsNotFoundException e)
			{
				//entityByMobile=new MobileVerificationDetails();
			}
			
		}
		

		if(entityByEmail.getKey()!=null && entityByMobile.getKey()!=null && !entityByEmail.getKey().getCustomerId().equals(entityByMobile.getKey().getCustomerId())&&!entityByEmail.getKey().getCustomerType().equals(entityByMobile.getKey().getCustomerType()))
		{
			return new CustomerQuickRegisterStatusEntity(REGISTER_FISHY, new QuickRegisterEntity());
		}
		
		
		if(customer.getEmail()!=null && entityByEmail.getKey()!=null)
		{
			returnEntity=getByEmail(customer.getEmail());
			
			emailAlreadyExist=true;
			
			if(returnEntity.getIsEmailVerified())
				emailVerified=true;
		}
		
		if(customer.getMobile()!=null && entityByMobile.getKey()!=null)
		{
			returnEntity=getByMobile(customer.getMobile());
			
			mobileAlreadyExist=true;
			
			if(returnEntity.getIsMobileVerified())
				mobileVerified=true;
		}
		
		
		if(!emailAlreadyExist && !mobileAlreadyExist)
			status=REGISTER_NOT_REGISTERED;
		else if(emailAlreadyExist && mobileAlreadyExist)
		{
			if(emailVerified && mobileVerified)
				status=REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_VERIFIED;
			else if (mobileVerified)
				status=REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_MOBILE_VERIFIED;
			else if(emailVerified)
				status=REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_VERIFIED;
			else
				status=REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_UNVERIFIED;
		}
		else if(emailAlreadyExist)
		{
			if(emailVerified)
				status=REGISTER_EMAIL_ALREADY_REGISTERED_VERIFIED;
			else
				status=REGISTER_EMAIL_ALREADY_REGISTERED_NOT_VERIFIED;
		}
		else if(mobileAlreadyExist)
		{
			if(mobileVerified)
				status=REGISTER_MOBILE_ALREADY_REGISTERED_VERIFIED;
			else
				status=REGISTER_MOBILE_ALREADY_REGISTERED_NOT_VERIFIED;
		}
		
		return new CustomerQuickRegisterStatusEntity(status, returnEntity);
	}

	@Override
	public QuickRegisterEntity populateVerificationFields(
			CustomerQuickRegisterEntityDTO customer) {

		QuickRegisterEntity customerToProcess = customer.toCustomerQuickRegisterEntity();
		
		if (customerToProcess.getEmail() != null ) {
			customerToProcess.setIsEmailVerified(false);
		} 
		
		if(customerToProcess.getMobile()!=null){
			customerToProcess.setIsMobileVerified(false);
		}
		//TODO
		customerToProcess.setCustomerType(customer.getCustomerType());
		customerToProcess.setUpdateTime(new Date());
		customerToProcess.setInsertTime(new Date());

		return customerToProcess;
	}

	@Override
	public QuickRegisterEntity initializeNewCustomerQuickRegistrationEntity(
			QuickRegisterEntity customer) {

		customer.setInsertTime(new Date());
		customer.setUpdatedBy("CUST_ONLINE");
		customer.setUpdateTime(new Date());
		
		return customer;
	}
	
	@Override
	public CustomerQuickRegisterEmailMobileVerificationEntity saveNewCustomerQuickRegisterEntity(
			QuickRegisterEntity customer) throws QuickRegisterDetailsAlreadyPresentException,ValidationFailedException {
				
		
		CustomerQuickRegisterEmailMobileVerificationEntity resultEntity=transactionalUpdatesRepository.saveNewQuickRegisterEntity(customer);
		
		
		if(resultEntity.getCustomerEmailVerificationDetails().getKey()!=null&&resultEntity.getCustomerEmailVerificationDetails().getEmailHash()==null)
			emailVerificationService.updateEmailHash(resultEntity.getCustomerEmailVerificationDetails().getKey().getCustomerId(),
					resultEntity.getCustomerEmailVerificationDetails().getKey().getCustomerType(),resultEntity.getCustomerEmailVerificationDetails().getKey().getEmailType(),
					resultEntity.getCustomerEmailVerificationDetails().getUpdatedBy());
			
			
		if(resultEntity.getCustomerMobileVerificationDetails().getKey()!=null && resultEntity.getCustomerMobileVerificationDetails().getMobilePin()==null)	
			mobileVerificationService.updateMobilePin(resultEntity.getCustomerMobileVerificationDetails().getKey().getCustomerId(),
					resultEntity.getCustomerMobileVerificationDetails().getKey().getCustomerType(), resultEntity.getCustomerMobileVerificationDetails().getKey().getMobileType(),
					resultEntity.getCustomerQuickRegisterEntity().getUpdatedBy());
			
		
		return resultEntity;
	}


		@Override
	public CustomerQuickRegisterStatusEntity handleNewCustomerQuickRegister(
			CustomerQuickRegisterEntityDTO customer) throws ResourceAlreadyPresentException,ResourceNotFoundException,ValidationFailedException {
		
		QuickRegisterEntity customerWithStatusPopulated=populateVerificationFields(customer);
		
		QuickRegisterEntity initialisedCustomer=initializeNewCustomerQuickRegistrationEntity(customerWithStatusPopulated);

		CustomerQuickRegisterEmailMobileVerificationEntity savedEntity=saveNewCustomerQuickRegisterEntity(initialisedCustomer);
		
		CustomerQuickRegisterStatusEntity customerStatusEntity=sendVerificationDetails(savedEntity.getCustomerQuickRegisterEntity(),savedEntity.getCustomerEmailVerificationDetails(),
																			savedEntity.getCustomerMobileVerificationDetails());
		
		return customerStatusEntity;
	}

	@Override
	public CustomerQuickRegisterStatusEntity sendVerificationDetails(QuickRegisterEntity customer,
			EmailVerificationDetails emailVerificationDetails,MobileVerificationDetails mobileVerificationDetails)
					throws ResourceNotFoundException{
		
		Boolean emailSentStatus=true;
		Boolean mobileSentStatus=true;
		
		String finalStatus=null;
		
		if(customer.getEmail()!=null && emailVerificationDetails.getEmailHash()==null)
			emailVerificationDetails=emailVerificationService.getByEntityIdTypeAndEmailType(customer.getCustomerId(), customer.getCustomerType(),
					ENTITY_TYPE_PRIMARY);
		
		if(customer.getMobile()!=null && mobileVerificationDetails.getMobilePin()==null)
			mobileVerificationDetails=mobileVerificationService.getByEntityIdTypeAndMobileType(customer.getCustomerId(), customer.getCustomerType(),
					ENTITY_TYPE_PRIMARY);
		
		if(customer.getEmail()!=null&&!customer.getIsEmailVerified())
		{
			emailSentStatus=messagerSender
					.sendHashEmail(customer.getCustomerId(),ENTITY_TYPE_CUSTOMER,ENTITY_TYPE_PRIMARY, customer.getFirstName(), customer.getLastName(), 
							customer.getEmail(), emailVerificationDetails.getEmailHash(),emailVerificationDetails.getUpdatedBy());
		}
		
		if(customer.getMobile()!=null && !customer.getIsMobileVerified())
		{
			mobileSentStatus=messagerSender.sendPinSMS(customer.getFirstName(), customer.getLastName(),
					customer.getMobile(), mobileVerificationDetails.getMobilePin());
			
			
		}
		
				
		if(emailSentStatus && mobileSentStatus)
			finalStatus= REGISTER_REGISTERED_SUCESSFULLY;
		else
			finalStatus=REGISTER_REGISTERED_SUCESSFULLY_UNABLE_TO_SEND_VERIFICATION_DETAILS;
	
		return new CustomerQuickRegisterStatusEntity(finalStatus,customer);
	}
	
	@Override
	public Integer updateMobileVerificationStatus(Long customerId,
			Boolean status, Date updateTime, String updatedBy) {
	
		Integer updatedStatus=customerQuickRegisterRepository.updateMobileVerificationStatus(customerId, status, updateTime, updatedBy);
		
		return updatedStatus;
	}
	

	@Override
	public Integer updateEmailVerificationStatus(Long customerId,
			Boolean status, Date updateTime, String updatedBy) {
		

		Integer updatedStatus=customerQuickRegisterRepository.updateEmailVerificationStatus(customerId, status, updateTime, updatedBy);
		
		return updatedStatus;
	}

	@Override
	public QuickRegisterEntity saveCustomerQuickRegisterEntity(
			QuickRegisterEntity customerQuickRegisterEntity) throws QuickRegisterDetailsAlreadyPresentException,ValidationFailedException {
		
		QuickRegisterEntity quickRegisterEntity=customerQuickRegisterRepository.save(customerQuickRegisterEntity);
		
		return quickRegisterEntity;
	}

	
	
	@Override
	public QuickRegisterEntity getByEntityId(
			Long customerId)throws QuickRegisterEntityNotFoundException {
		
		return customerQuickRegisterRepository.findByCustomerId(customerId);
	}

	
	@Override
	public QuickRegisterEntity getByEmail(
			String email) throws QuickRegisterEntityNotFoundException{
		
		return customerQuickRegisterRepository.findByEmail(email);
	}

	@Override
	public QuickRegisterEntity getByMobile(
			Long mobile) throws QuickRegisterEntityNotFoundException{
		
		return customerQuickRegisterRepository.findByMobile(mobile);
	}


	
	@Override
	public void clearDataForTesting() {
		customerQuickRegisterRepository.clearCustomerQuickRegister();
		authenticationHandler.clearTestData();
		mobileVerificationService.clearTestData();
		emailVerificationService.clearTestData();
		
	}

	@Override
	public List<MobilePinPasswordDTO> getTestData() {

		return customerQuickRegisterRepository.getTestData();
	}


}
