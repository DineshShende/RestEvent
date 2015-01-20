package com.projectx.rest.handlers.quickregister;

import static com.projectx.rest.fixtures.quickregister.CustomerQuickRegisterDataFixture.*;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.projectx.mvc.domain.quickregister.CustomerQuickRegisterEntityDTO;
import com.projectx.mvc.domain.quickregister.CustomerQuickRegisterStringStatusEntity;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterEmailMobileVerificationEntity;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterStatusEntity;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.repository.completeregister.DocumentDetailsRepository;
import com.projectx.rest.repository.completeregister.TransactionalUpdatesRepository;
import com.projectx.rest.repository.quickregister.QuickRegisterRepository;
import com.projectx.rest.services.quickregister.EmailVerificationService;
import com.projectx.rest.services.quickregister.MobileVerificationService;
import com.projectx.rest.services.quickregister.QuickRegisterService;
import com.projectx.rest.utils.HandleCustomerVerification;
import com.projectx.rest.utils.MessageBuilder;
import com.projectx.rest.utils.MessagerSender;

@Component
@Profile(value={"Dev","Test"})
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
	HandleCustomerVerification handleCustomerVerification;

	@Autowired
	MessageBuilder messageBuilder;
	
	@Autowired
	MessagerSender messagerSender;
	
	private static final Integer CUST_TYPE_CUSTOMER=new Integer(1);
	
	
	@Override
	public CustomerQuickRegisterStringStatusEntity checkIfAlreadyRegistered(
			CustomerQuickRegisterEntityDTO customer) throws Exception {

		String status=null;
		
		Boolean emailAlreadyExist = false;
		
		Boolean emailVerified=false;

		Boolean mobileAlreadyExist = false;
		
		Boolean mobileVerified=false;
	
		QuickRegisterEntity returnEntity=new QuickRegisterEntity();
		
		EmailVerificationDetails entityByEmail=new EmailVerificationDetails();
		
		MobileVerificationDetails entityByMobile=new MobileVerificationDetails();
		
		if(customer.getEmail()!=null)
			entityByEmail=emailVerificationService.getByEmail(customer.getEmail());
		
		if(customer.getMobile()!=null)
			entityByMobile=mobileVerificationService.getByMobile(customer.getMobile());
		

		if(entityByEmail.getKey()!=null && entityByMobile.getKey()!=null && !entityByEmail.getKey().getCustomerId().equals(entityByMobile.getKey().getCustomerId())&&!entityByEmail.getKey().getCustomerType().equals(entityByMobile.getKey().getCustomerType()))
		{
			return new CustomerQuickRegisterStringStatusEntity(REGISTER_FISHY, new QuickRegisterEntity());
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
		
		return new CustomerQuickRegisterStringStatusEntity(status, returnEntity);
	}

	@Override
	public QuickRegisterEntity populateVerificationFields(
			CustomerQuickRegisterEntityDTO customer) throws Exception {

		QuickRegisterEntity customerToProcess = customer.toCustomerQuickRegisterEntity();
		
		if (customerToProcess.getEmail() == null && customerToProcess.getMobile() == null)
			throw new Exception();

		if (customerToProcess.getEmail() != null ) {
			customerToProcess.setIsEmailVerified(false);
		} 
		
		if(customerToProcess.getMobile()!=null){
			customerToProcess.setIsMobileVerified(false);
		}
		//TODO
		customerToProcess.setCustomerType(customer.getCustomerType());

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
			QuickRegisterEntity customer) throws Exception {
				
		//System.out.println(customer);
		/*
		QuickRegisterEntity savedCustomerQuickRegisterEntity= customerQuickRegisterRepository.save(customer);
		EmailVerificationDetails savedCustomerEmailVerificationDetails=new EmailVerificationDetails();
		MobileVerificationDetails savedCustomerMobileVerificationDetails=new MobileVerificationDetails();
		
		if(savedCustomerQuickRegisterEntity.getEmail()!=null)
		{
			EmailVerificationDetails newCustomerEmailVerificationDetails=emailVerificationService
					.createEntity(savedCustomerQuickRegisterEntity.getCustomerId(),savedCustomerQuickRegisterEntity.getCustomerType(),
							savedCustomerQuickRegisterEntity.getEmail(),CUST_EMAIL_TYPE_PRIMARY,savedCustomerQuickRegisterEntity.getUpdatedBy());
			savedCustomerEmailVerificationDetails=emailVerificationService.saveDetails(newCustomerEmailVerificationDetails);
		}
		
		if(savedCustomerQuickRegisterEntity.getMobile()!=null)
		{
			MobileVerificationDetails newCustomerMobileVerificationDetails=mobileVerificationService
					.createEntity(savedCustomerQuickRegisterEntity.getCustomerId(),savedCustomerQuickRegisterEntity.getCustomerType(),
							savedCustomerQuickRegisterEntity.getMobile(),CUST_MOBILE_TYPE_PRIMARY,savedCustomerQuickRegisterEntity.getUpdatedBy());
			savedCustomerMobileVerificationDetails=mobileVerificationService.saveDetails(newCustomerMobileVerificationDetails);

			//TODO
			//If exception occurs here
		}
		
		AuthenticationDetails customerAuthenticationDetails=authenticationHandler.createCustomerAuthenticationDetails(savedCustomerQuickRegisterEntity);
		authenticationHandler.saveCustomerAuthenticationDetails(customerAuthenticationDetails);
		*/
		
		CustomerQuickRegisterEmailMobileVerificationEntity resultEntity=transactionalUpdatesRepository.saveNewQuickRegisterEntity(customer);
		
		
		if(resultEntity.getCustomerEmailVerificationDetails().getKey()!=null&&resultEntity.getCustomerEmailVerificationDetails().getEmailHash()==null)
			emailVerificationService.updateEmailHash(resultEntity.getCustomerEmailVerificationDetails().getKey().getCustomerId(),
					resultEntity.getCustomerEmailVerificationDetails().getKey().getCustomerType(),resultEntity.getCustomerEmailVerificationDetails().getKey().getEmailType());
			
			
		if(resultEntity.getCustomerMobileVerificationDetails().getKey()!=null && resultEntity.getCustomerMobileVerificationDetails().getMobilePin()==null)	
			mobileVerificationService.updateMobilePin(resultEntity.getCustomerMobileVerificationDetails().getKey().getCustomerId(),
					resultEntity.getCustomerMobileVerificationDetails().getKey().getCustomerType(), resultEntity.getCustomerMobileVerificationDetails().getKey().getMobileType());
			
		
		return resultEntity;
	}


		@Override
	public CustomerQuickRegisterStatusEntity handleNewCustomerQuickRegister(
			CustomerQuickRegisterEntityDTO customer) throws Exception {
		
		QuickRegisterEntity customerWithStatusPopulated=populateVerificationFields(customer);
		
		QuickRegisterEntity initialisedCustomer=initializeNewCustomerQuickRegistrationEntity(customerWithStatusPopulated);

		CustomerQuickRegisterEmailMobileVerificationEntity savedEntity=saveNewCustomerQuickRegisterEntity(initialisedCustomer);
		
		CustomerQuickRegisterStatusEntity customerStatusEntity=sendVerificationDetails(savedEntity.getCustomerQuickRegisterEntity(),savedEntity.getCustomerEmailVerificationDetails(),
																			savedEntity.getCustomerMobileVerificationDetails());
		
		return customerStatusEntity;
	}

	@Override
	public CustomerQuickRegisterStatusEntity sendVerificationDetails(QuickRegisterEntity customer,
			EmailVerificationDetails emailVerificationDetails,MobileVerificationDetails mobileVerificationDetails) {
		
		Boolean emailSentStatus=true;
		Boolean mobileSentStatus=true;
		
		Boolean finalStatus=false;
		
		if(customer.getEmail()!=null&&!customer.getIsEmailVerified())
		{
			emailSentStatus=messagerSender
					.sendHashEmail(customer.getCustomerId(), customer.getFirstName(), customer.getEmail(), 
							customer.getEmail(), emailVerificationDetails.getEmailHash());
		}
		
		if(customer.getMobile()!=null && !customer.getIsMobileVerified())
		{
			mobileSentStatus=messagerSender.sendPinSMS(customer.getFirstName(), customer.getLastName(),
					customer.getMobile(), mobileVerificationDetails.getMobilePin());
			
			
		}
		
				
		if(emailSentStatus && mobileSentStatus)
			finalStatus= true;
		else
			finalStatus=false;
	
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
			QuickRegisterEntity customerQuickRegisterEntity) {
		
		QuickRegisterEntity quickRegisterEntity=customerQuickRegisterRepository.save(customerQuickRegisterEntity);
		
		return quickRegisterEntity;
	}

	
	
	@Override
	public QuickRegisterEntity getByEntityId(
			Long customerId) {
		
		return customerQuickRegisterRepository.findByCustomerId(customerId);
	}

	
	@Override
	public QuickRegisterEntity getByEmail(
			String email) {
		
		return customerQuickRegisterRepository.findByEmail(email);
	}

	@Override
	public QuickRegisterEntity getByMobile(
			Long mobile) {
		
		return customerQuickRegisterRepository.findByMobile(mobile);
	}


	
	@Override
	public void clearDataForTesting() {
		customerQuickRegisterRepository.clearCustomerQuickRegister();
		authenticationHandler.clearTestData();
		mobileVerificationService.clearTestData();
		emailVerificationService.clearTestData();
		
	}


}
