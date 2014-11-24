package com.projectx.rest.handlers;

import static com.projectx.rest.fixtures.CustomerQuickRegisterDataFixture.*;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.projectx.data.domain.UpdatePasswordAndPasswordTypeDTO;

import com.projectx.rest.domain.CustomerAuthenticationDetails;
import com.projectx.rest.domain.CustomerDocument;
import com.projectx.rest.domain.CustomerEmailVerificationDetails;
import com.projectx.rest.domain.CustomerMobileVerificationDetails;
import com.projectx.rest.domain.CustomerQuickRegisterEmailMobileVerificationEntity;
import com.projectx.rest.domain.CustomerQuickRegisterStatusEntity;
import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.repository.CustomerAuthenticationDetailsRepository;
import com.projectx.rest.repository.CustomerDocumentRepository;
import com.projectx.rest.repository.CustomerEmailVericationDetailsRepository;
import com.projectx.rest.repository.CustomerMobileVerificationDetailsRepository;
import com.projectx.rest.repository.CustomerQuickRegisterRepository;
import com.projectx.rest.services.CustomerQuickRegisterService;
import com.projectx.rest.utils.HandleCustomerVerification;
import com.projectx.rest.utils.MessageBuilder;
import com.projectx.web.domain.CustomerIdDTO;
import com.projectx.web.domain.CustomerQuickRegisterEntityDTO;
import com.projectx.web.domain.CustomerQuickRegisterStringStatusEntity;
import com.projectx.web.domain.LoginVerificationDTO;
import com.projectx.web.domain.LoginVerificationWithDefaultEmailPasswordDTO;

@Component
@Profile(value={"Dev","Test"})
@PropertySource(value="classpath:/application.properties")
public class CustomerQuickRegisterHandler implements
		CustomerQuickRegisterService {

	@Autowired
	CustomerQuickRegisterRepository customerQuickRegisterRepository;
	
	@Autowired
	CustomerAuthenticationDetailsRepository customerAuthenticationDetailsRepository;
	
	@Autowired
	CustomerEmailVericationDetailsRepository customerEmailVericationDetailsRepository;
	
	@Autowired
	CustomerMobileVerificationDetailsRepository customerMobileVerificationDetailsRepository; 
	
	@Autowired
	CustomerDocumentRepository customerDocumentRepository;
	
	@Autowired 
	HandleCustomerVerification handleCustomerVerification;

	@Autowired
	MessageBuilder messageBuilder;
	
	
	private final int EMAIL_VALIDITY_TIME_IN_HRS=24;
	private final int UPDATE_SUCESS=1;
	private final int MAX_MOBILE_VERIFICATION_ATTEMPTS=3;
	
	
	@Override
	public CustomerQuickRegisterStringStatusEntity checkIfAlreadyRegistered(
			CustomerQuickRegisterEntityDTO customer) throws Exception {

		String status=null;
		
		Boolean emailAlreadyExist = false;
		
		Boolean emailVerified=false;

		Boolean mobileAlreadyExist = false;
		
		Boolean mobileVerified=false;
	
		CustomerQuickRegisterEntity returnEntity=new CustomerQuickRegisterEntity();
		
		CustomerQuickRegisterEntity entityByEmail=returnEntity;
		
		CustomerQuickRegisterEntity entityByMobile=returnEntity;
		
		if(customer.getEmail()!=null)
			entityByEmail=customerQuickRegisterRepository.findByEmail(customer.getEmail());
		
		if(customer.getMobile()!=null)
			entityByMobile=customerQuickRegisterRepository.findByMobile(customer.getMobile());
		

		if(entityByEmail.getCustomerId()!=null && entityByMobile.getCustomerId()!=null && !entityByEmail.getCustomerId().equals(entityByMobile.getCustomerId()))
		{
			return new CustomerQuickRegisterStringStatusEntity(REGISTER_FISHY, new CustomerQuickRegisterEntity());
		}
		
		
		if(customer.getEmail()!=null && entityByEmail.getCustomerId()!=null)
		{
			returnEntity=entityByEmail;
			
			emailAlreadyExist=true;
			
			if(entityByEmail.getIsEmailVerified())
				emailVerified=true;
		}
		
		if(customer.getMobile()!=null && entityByMobile.getCustomerId()!=null)
		{
			returnEntity=entityByMobile;
			
			mobileAlreadyExist=true;
			
			if(entityByMobile.getIsMobileVerified())
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
	public CustomerQuickRegisterEntity populateVerificationFields(
			CustomerQuickRegisterEntityDTO customer) throws Exception {

		CustomerQuickRegisterEntity customerToProcess = customer.toCustomerQuickRegisterEntity();
		
		if (customerToProcess.getEmail() == null && customerToProcess.getMobile() == null)
			throw new Exception();

		if (customerToProcess.getEmail() != null ) {
			customerToProcess.setIsEmailVerified(false);
		} 
		
		if(customerToProcess.getMobile()!=null){
			customerToProcess.setIsMobileVerified(false);
		}

		return customerToProcess;
	}

	@Override
	public CustomerQuickRegisterEntity initializeNewCustomerQuickRegistrationEntity(
			CustomerQuickRegisterEntity customer) {

		customer.setInsertTime(new Date());
		customer.setUpdatedBy("ONLINE_CUST");
		customer.setUpdateTime(new Date());
		
		return customer;
	}
	
	
	
	@Override
	public CustomerQuickRegisterEmailMobileVerificationEntity saveNewCustomerQuickRegisterEntity(
			CustomerQuickRegisterEntity customer) throws Exception {
				
		CustomerQuickRegisterEntity savedCustomerQuickRegisterEntity= customerQuickRegisterRepository.save(customer);
		CustomerEmailVerificationDetails savedCustomerEmailVerificationDetails=new CustomerEmailVerificationDetails();
		CustomerMobileVerificationDetails savedCustomerMobileVerificationDetails=new CustomerMobileVerificationDetails();
		
		if(savedCustomerQuickRegisterEntity.getEmail()!=null)
		{
			CustomerEmailVerificationDetails newCustomerEmailVerificationDetails=createCustomerEmailVerificationEntity(savedCustomerQuickRegisterEntity);
			savedCustomerEmailVerificationDetails=saveCustomerEmailVerificationDetails(newCustomerEmailVerificationDetails);
		}
		
		if(savedCustomerQuickRegisterEntity.getMobile()!=null)
		{
			CustomerMobileVerificationDetails newCustomerMobileVerificationDetails=createCustomerMobileVerificationEntity(savedCustomerQuickRegisterEntity);
			savedCustomerMobileVerificationDetails=saveCustomerMobileVerificationDetails(newCustomerMobileVerificationDetails);

		}
		
		CustomerAuthenticationDetails customerAuthenticationDetails=createCustomerAuthenticationDetails(savedCustomerQuickRegisterEntity);
		saveCustomerAuthenticationDetails(customerAuthenticationDetails);
				
		return new CustomerQuickRegisterEmailMobileVerificationEntity(savedCustomerQuickRegisterEntity, savedCustomerEmailVerificationDetails, savedCustomerMobileVerificationDetails);
	}


	
	@Override
	public CustomerMobileVerificationDetails createCustomerMobileVerificationEntity(
			CustomerQuickRegisterEntity customerQuickRegisterEntity) {
		
		CustomerMobileVerificationDetails mobileVerificationDetails=new CustomerMobileVerificationDetails();
		mobileVerificationDetails.setCustomerId(customerQuickRegisterEntity.getCustomerId());
		mobileVerificationDetails.setMobile(customerQuickRegisterEntity.getMobile());
		mobileVerificationDetails.setMobileType(CUST_MOBILE_TYPE_PRIMARY);
		mobileVerificationDetails.setMobilePin(handleCustomerVerification.genarateMobilePin());
		mobileVerificationDetails.setMobileVerificationAttempts(0);
		mobileVerificationDetails.setResendCount(0);
		
		
		return mobileVerificationDetails;
	}

	@Override
	public CustomerEmailVerificationDetails createCustomerEmailVerificationEntity(
			CustomerQuickRegisterEntity customerQuickRegisterEntity) {
		CustomerEmailVerificationDetails emailVerificationDetails=new CustomerEmailVerificationDetails();
		emailVerificationDetails.setCustomerId(customerQuickRegisterEntity.getCustomerId());
		emailVerificationDetails.setEmail(customerQuickRegisterEntity.getEmail());
		emailVerificationDetails.setEmailHash(handleCustomerVerification.generateEmailHash());
		emailVerificationDetails.setEmailHashSentTime(new Date());
		emailVerificationDetails.setEmailType(CUST_EMAIL_TYPE_PRIMARY);
		emailVerificationDetails.setResendCount(0);
		
		return emailVerificationDetails;
	}
	
	@Override
	public CustomerAuthenticationDetails createCustomerAuthenticationDetails(
			CustomerQuickRegisterEntity customerQuickRegisterEntity) {
		
		CustomerAuthenticationDetails customerAuthenticationDetails=new CustomerAuthenticationDetails();
		customerAuthenticationDetails.setCustomerId(customerQuickRegisterEntity.getCustomerId());
		customerAuthenticationDetails.setEmail(customerQuickRegisterEntity.getEmail());
		customerAuthenticationDetails.setMobile(customerQuickRegisterEntity.getMobile());
		customerAuthenticationDetails.setLastUnsucessfullAttempts(0);;
		customerAuthenticationDetails.setResendCount(0);
		
		return customerAuthenticationDetails;
	}

	
	
		
	@Override
	public CustomerQuickRegisterStatusEntity handleNewCustomerQuickRegister(
			CustomerQuickRegisterEntityDTO customer) throws Exception {
		
		CustomerQuickRegisterEntity customerWithStatusPopulated=populateVerificationFields(customer);
		
		CustomerQuickRegisterEntity initialisedCustomer=initializeNewCustomerQuickRegistrationEntity(customerWithStatusPopulated);

		CustomerQuickRegisterEmailMobileVerificationEntity savedEntity=saveNewCustomerQuickRegisterEntity(initialisedCustomer);
		
		CustomerQuickRegisterStatusEntity customerStatusEntity=sendVerificationDetails(savedEntity.getCustomerQuickRegisterEntity(),savedEntity.getCustomerEmailVerificationDetails(),
																			savedEntity.getCustomerMobileVerificationDetails());
		
		return customerStatusEntity;
	}

	@Override
	public CustomerQuickRegisterStatusEntity sendVerificationDetails(CustomerQuickRegisterEntity customer,CustomerEmailVerificationDetails emailVerificationDetails,CustomerMobileVerificationDetails mobileVerificationDetails) {
		
		Boolean emailSentStatus=true;
		Boolean mobileSentStatus=true;
		
		Boolean finalStatus=false;
		
		if(customer.getEmail()!=null&&!customer.getIsEmailVerified())
		{
			emailSentStatus=sendHashEmail(customer,emailVerificationDetails);
		}
		
		if(customer.getMobile()!=null && !customer.getIsMobileVerified())
		{
			mobileSentStatus=sendPinSMS(customer, mobileVerificationDetails);
		}
		
				
		if(emailSentStatus && mobileSentStatus)
			finalStatus= true;
		else
			finalStatus=false;
	
		return new CustomerQuickRegisterStatusEntity(finalStatus,customer);
	}
	

	@Override
	public Boolean verifyEmailHash(Long customerId,String email, String emailHash) {
		
		CustomerQuickRegisterEntity fetchedEntity=getCustomerQuickRegisterEntityByCustomerId(customerId);
		CustomerEmailVerificationDetails emailVerificationDetails=getCustomerEmailVerificationDetailsByCustomerIdAndEmail(customerId, email);
			
		if(fetchedEntity.getCustomerId()==null||emailVerificationDetails.getCustomerId()==null)
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
			
			int updatedStatus=customerQuickRegisterRepository.updateEmailVerificationStatus(fetchedEntity.getCustomerId(), fetchedEntity.getIsEmailVerified(),
					fetchedEntity.getUpdateTime(), fetchedEntity.getUpdatedBy());										
			
			Boolean sendPasswordStatus=sendDefaultPassword(fetchedEntity, false);
			
			
			if(updatedStatus==UPDATE_SUCESS && sendPasswordStatus)
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
	public Boolean verifyMobilePin(Long customerId,Long mobile, Integer mobilePin) {
	
		CustomerQuickRegisterEntity fetchedEntity=getCustomerQuickRegisterEntityByCustomerId(customerId);
		CustomerMobileVerificationDetails mobileVerificationDetails=getCustomerMobileVerificationDetailsByCustomerIdAndMobile(customerId, mobile);
		
		Boolean verificationStatus=false;
		
		Boolean sendPasswordStatus=true;
		
		Integer customerQuickRegisterUpdateStatus=UPDATE_SUCESS;
		Integer customerMobileVerificationDetailsStatus=UPDATE_SUCESS;
		
		if(fetchedEntity.getCustomerId()==null || mobileVerificationDetails.getCustomerId()==null)
			return false;
		
		
		if(mobileVerificationDetails.getMobilePin().equals(mobilePin) && mobileVerificationDetails.getMobileVerificationAttempts()<MAX_MOBILE_VERIFICATION_ATTEMPTS)
		{
			fetchedEntity.setIsMobileVerified(true);
			
			verificationStatus=true;
			
			sendPasswordStatus=sendDefaultPassword(fetchedEntity, false);
			
			fetchedEntity.setUpdateTime(new Date());
			fetchedEntity.setUpdatedBy("ONLINE_CUST");
			
			customerQuickRegisterUpdateStatus=customerQuickRegisterRepository.updateMobileVerificationStatus(fetchedEntity.getCustomerId(), 
					fetchedEntity.getIsMobileVerified(), fetchedEntity.getUpdateTime(), fetchedEntity.getUpdatedBy());
			
				
		}
		else
		{
			int currentAttemptCount=mobileVerificationDetails.getMobileVerificationAttempts();
			mobileVerificationDetails.setMobileVerificationAttempts(currentAttemptCount+1);
		
			customerMobileVerificationDetailsStatus=customerMobileVerificationDetailsRepository.updateMobilePinAndMobileVerificationAttemptsAndResendCount(fetchedEntity.getCustomerId(), fetchedEntity.getMobile(),
					mobileVerificationDetails.getMobilePin(), mobileVerificationDetails.getMobileVerificationAttempts(), mobileVerificationDetails.getResendCount());
		
		}
		
		if(customerQuickRegisterUpdateStatus==UPDATE_SUCESS&&customerMobileVerificationDetailsStatus==UPDATE_SUCESS && verificationStatus && sendPasswordStatus)
			return true;
		else
			return false;
		
	}

	@Override
	public CustomerAuthenticationDetails verifyLoginDetails(
			LoginVerificationDTO loginVerificationDTO) {
		
		CustomerAuthenticationDetails customerAuthenticationDetails=new CustomerAuthenticationDetails();
		
		if(isMobileNumber(loginVerificationDTO.getLoginEntity()))
		{
			customerAuthenticationDetails=customerAuthenticationDetailsRepository.getCustomerAuthenticationDetailsByMobile(Long.parseLong(loginVerificationDTO.getLoginEntity()));
		}
		else
		{
			customerAuthenticationDetails=customerAuthenticationDetailsRepository.getCustomerAuthenticationDetailsByEmail(loginVerificationDTO.getLoginEntity());
		}
		
		if(customerAuthenticationDetails.getCustomerId()!=null && !loginVerificationDTO.getPassword().equals(customerAuthenticationDetails.getPassword()))
		{
			customerAuthenticationDetailsRepository.incrementLastUnsucessfullAttempts(customerAuthenticationDetails.getCustomerId());
			
			customerAuthenticationDetails=new CustomerAuthenticationDetails();	
			
		}
		
		return customerAuthenticationDetails;
	}

	
	
	@Override
	public CustomerAuthenticationDetails verifyDefaultEmailLoginDetails(
			LoginVerificationWithDefaultEmailPasswordDTO emailPasswordDTO) {

		CustomerAuthenticationDetails customerAuthenticationDetails=new CustomerAuthenticationDetails(); 
		
		customerAuthenticationDetails=customerAuthenticationDetailsRepository.getCustomerAuthenticationDetailsByCustomerId(emailPasswordDTO.getCustomerId());
		
		if(customerAuthenticationDetails.getCustomerId()!=null &&
				customerAuthenticationDetails.getEmailPassword().equals(emailPasswordDTO.getEmailPassword()))
		{
			return customerAuthenticationDetails;
		}
		else
		{
			customerAuthenticationDetails=new CustomerAuthenticationDetails();
			return customerAuthenticationDetails;
		}
		
		
	}

	@Override
	public String composeEmailWithEmailHash(CustomerQuickRegisterEntity customer,CustomerEmailVerificationDetails emailVerificationDetails) {
		
		return messageBuilder.composeEmailWithEmailHash(customer, emailVerificationDetails);
		
	}

	@Override
	public Boolean sendHashEmail(CustomerQuickRegisterEntity customer, CustomerEmailVerificationDetails emailVerificationDetails) {
		
		String message=composeEmailWithEmailHash(customer,emailVerificationDetails);
		
		return handleCustomerVerification.sendEmail(customer.getEmail(), message);
	}

	@Override
	public String composeSMSWithMobilePin(CustomerQuickRegisterEntity customer,CustomerMobileVerificationDetails mobileVerificationDetails) {
		
		return messageBuilder.composeSMSWithMobilePin(customer, mobileVerificationDetails);
	}

	@Override
	public Boolean sendPinSMS(CustomerQuickRegisterEntity customer,CustomerMobileVerificationDetails mobileVerificationDetails)  {
		
		String message=composeSMSWithMobilePin(customer,mobileVerificationDetails);
		
		return handleCustomerVerification.sendSMS(customer.getMobile(), message);
		
	}

	@Override
	public String composeSMSWithPassword(CustomerQuickRegisterEntity customer,
			CustomerAuthenticationDetails authenticationDetails) {
		return messageBuilder.composeSMSWithPassword(customer, authenticationDetails);
		
	}

	@Override
	public String composeEmailWithPassword(
			CustomerQuickRegisterEntity customer,
			CustomerAuthenticationDetails authenticationDetails) {
	
		return messageBuilder.composeEmailWithPassword(customer, authenticationDetails);
		
	}
	
	
	@Override
	public Boolean sendPasswordSMS(Long mobile,String message)
	{
		return handleCustomerVerification.sendSMS(mobile, message);
	}
	
	
	@Override
	public Boolean sendPasswordEmail(String email,String message)
	{
		return handleCustomerVerification.sendEmail(email, message);
	}
	
	
	@Override
	public Boolean sendDefaultPassword(CustomerQuickRegisterEntity customer,Boolean resetFlag) 
	{
		Boolean emailSendStatus=true;
		Boolean smsSendStatus=true;
		Integer passwordUpdateStatus=new Integer(1);
		Integer emailPasswordUpdateStatus=new Integer(1);
		
		CustomerAuthenticationDetails customerAuthenticationDetails=customerAuthenticationDetailsRepository.getCustomerAuthenticationDetailsByCustomerId(customer.getCustomerId());
		
		if(!resetFlag && (customerAuthenticationDetails.getPasswordType()!=null && customerAuthenticationDetails.getPasswordType().equals(CUST_PASSWORD_TYPE_CHANGED)))
			return true;
		
		if(customerAuthenticationDetails.getPassword()==null || resetFlag)
		{	
			String password=handleCustomerVerification.generatePassword();
			customerAuthenticationDetails.setPassword(password);
			customerAuthenticationDetails.setPasswordType(CUST_PASSWORD_TYPE_DEFAULT);

			 passwordUpdateStatus=customerAuthenticationDetailsRepository.updatePasswordAndPasswordTypeAndCounts (customerAuthenticationDetails.getCustomerId(),
					 																						customerAuthenticationDetails.getPassword(), customerAuthenticationDetails.getPasswordType());
			 if(customer.getEmail()!=null)
			 {	 
				 String emailPassword=handleCustomerVerification.generateEmailHash();
				 customerAuthenticationDetails.setEmailPassword(emailPassword);
				 emailPasswordUpdateStatus=customerAuthenticationDetailsRepository.updateEmailPasswordAndPasswordTypeAndCounts(customerAuthenticationDetails.getCustomerId(),customerAuthenticationDetails.getEmailPassword());
						 
			 }			 
			 
		}
		
		//CustomerAuthenticationDetails customerAuthenticationDetailsUpdated=customerAuthenticationDetailsRepository
		//		.getCustomerAuthenticationDetailsByCustomerId(customer.getCustomerId());
		
		String message;
			
		if(customer.getEmail()!=null && customer.getIsEmailVerified())
		{
			message=composeEmailWithPassword(customer, customerAuthenticationDetails);
			sendPasswordEmail(customer.getEmail(),message);
		}	
		
		if(customer.getMobile()!=null && customer.getIsMobileVerified())
		{
			message=composeSMSWithPassword(customer, customerAuthenticationDetails);
			sendPasswordSMS(customer.getMobile(),message);
		}	
		
		if(passwordUpdateStatus==UPDATE_SUCESS && emailSendStatus && smsSendStatus && emailPasswordUpdateStatus==UPDATE_SUCESS)
			return true;
		else
			return false;
		
		
	}
	
	
	@Override
	public Boolean resendDefaultPassword(
			CustomerQuickRegisterEntity customer) {

		CustomerAuthenticationDetails customerAuthenticationDetails=customerAuthenticationDetailsRepository.getCustomerAuthenticationDetailsByCustomerId(customer.getCustomerId());
		
		String message;
		
		if(customer.getEmail()!=null && customer.getIsEmailVerified())
		{
			message=composeEmailWithPassword(customer, customerAuthenticationDetails);
			sendPasswordEmail(customer.getEmail(),message);
		}	
		
		if(customer.getMobile()!=null && customer.getIsMobileVerified())
		{
			message=composeSMSWithPassword(customer, customerAuthenticationDetails);
			sendPasswordSMS(customer.getMobile(),message);
		}	
		
		Integer updateStatus=customerAuthenticationDetailsRepository.incrementResendCount(customer.getCustomerId());
		
		if(updateStatus==UPDATE_SUCESS)
			return true;
		else
			return false;
	}

	@Override
	public Boolean updatePassword(UpdatePasswordAndPasswordTypeDTO updatePasswordDTO)
	{
		Integer updateStatus=customerAuthenticationDetailsRepository.updatePasswordAndPasswordTypeAndCounts(updatePasswordDTO.getCustomerId(),
				updatePasswordDTO.getPassword(), CUST_PASSWORD_TYPE_CHANGED);
		
		if(updateStatus==UPDATE_SUCESS)
			return true;
		else
			return false;
	}
	
	
	@Override
	public Integer updateEmailHash(Long customerId,String email) {
		
		String emailHash=handleCustomerVerification.generateEmailHash();
		
		return customerEmailVericationDetailsRepository.resetEmailHashAndEmailHashSentTime(customerId, email, emailHash, new Date(), 0);
		
	}

	@Override
	public Integer updateMobilePin(Long customerId,Long mobile) {
		
		Integer mobilePin=handleCustomerVerification.genarateMobilePin();

		return customerMobileVerificationDetailsRepository.updateMobilePinAndMobileVerificationAttemptsAndResendCount(customerId, mobile, mobilePin, 0, 0);
	}

		
	@Override
	public Boolean reSetMobilePin(Long customerId,Long mobile)  {
	
		Integer updateStatus=0;
		
		updateStatus=updateMobilePin(customerId, mobile);
		
		CustomerQuickRegisterEntity customer=customerQuickRegisterRepository.findByCustomerId(customerId);
		CustomerMobileVerificationDetails mobileVerificationDetails=customerMobileVerificationDetailsRepository.getMobileVerificationDetailsByCustomerIdAndMobile(customer.getCustomerId(), customer.getMobile());
		
		Boolean sentStatus=sendPinSMS(customer,mobileVerificationDetails);
		
		if(updateStatus==UPDATE_SUCESS && sentStatus)
			return true;
		else
			return false;
	}



	@Override
	public Boolean reSetEmailHash(Long customerId,String email) {
		
		Integer updateStatus=0;
			
		updateStatus= updateEmailHash(customerId, email);
		
		CustomerQuickRegisterEntity customer=customerQuickRegisterRepository.findByCustomerId(customerId);
		CustomerEmailVerificationDetails emailVerificationDetails=customerEmailVericationDetailsRepository
				.getEmailVerificationDetailsByCustomerIdAndEmail(customer.getCustomerId(), customer.getEmail());
		
		Boolean sentStatus=sendHashEmail(customer,emailVerificationDetails);
		
		if(updateStatus==UPDATE_SUCESS && sentStatus)
			return true;
		else
			return false;
	}

	@Override
	public Boolean reSendEmailHash(Long customerId, String email) {

		Integer updateStatus=0;
		Boolean sentStatus=false;
		
		CustomerQuickRegisterEntity customer=customerQuickRegisterRepository.findByCustomerId(customerId);
		CustomerEmailVerificationDetails emailVerificationDetails=customerEmailVericationDetailsRepository
				.getEmailVerificationDetailsByCustomerIdAndEmail(customer.getCustomerId(), customer.getEmail());
		
		
		updateStatus=customerEmailVericationDetailsRepository.incrementResendCountByCustomerIdAndEmail(customerId, email);
		
		if(updateStatus==UPDATE_SUCESS)
			sentStatus=sendHashEmail(customer,emailVerificationDetails);
				
		if(updateStatus==UPDATE_SUCESS && sentStatus)
			return true;
		else
			return false;
		
	}

	@Override
	public Boolean reSendMobilePin(Long customerId, Long mobile) {
		
		Integer updateStatus=0;
		Boolean sentStatus=false;
		
		CustomerQuickRegisterEntity customer=customerQuickRegisterRepository.findByCustomerId(customerId);
		CustomerMobileVerificationDetails mobileVerificationDetails=customerMobileVerificationDetailsRepository
				.getMobileVerificationDetailsByCustomerIdAndMobile(customer.getCustomerId(), customer.getMobile());
		
		updateStatus=customerMobileVerificationDetailsRepository.incrementResendCount(customerId, mobile);
	
		
		if(updateStatus==UPDATE_SUCESS)
			sentStatus=sendPinSMS(customer,mobileVerificationDetails);
		
		if(updateStatus==UPDATE_SUCESS && sentStatus)
			return true;
		else
			return false;
	}
	
	
	@Override
	public CustomerQuickRegisterEntity resetPasswordByEmailOrMobileRedirect(String entity) {
		
		//TODO
		
		CustomerQuickRegisterEntity quickRegisterEntity=new CustomerQuickRegisterEntity();
		
		if(isMobileNumber(entity))
		{
			quickRegisterEntity=getCustomerQuickRegisterEntityByMobile(Long.parseLong(entity));
		}
		else
		{
			quickRegisterEntity=getCustomerQuickRegisterEntityByEmail(entity);
		}
				
		return quickRegisterEntity;
	}

	@Override
	public Boolean resetPassword(CustomerIdDTO customerIdDTO) 
	{
		CustomerQuickRegisterEntity customer=customerQuickRegisterRepository.findByCustomerId(customerIdDTO.getCustomerId());
		
		return sendDefaultPassword(customer,true);
	}
	
	


	@Override
	public Boolean resendPassword(CustomerIdDTO customerIdDTO) {

		CustomerQuickRegisterEntity customer=customerQuickRegisterRepository.findByCustomerId(customerIdDTO.getCustomerId());
		
		return resendDefaultPassword(customer);
	}

	@Override
	public CustomerAuthenticationDetails getLoginDetailsByCustomerId(Long customerId)
	{
		return customerAuthenticationDetailsRepository.getCustomerAuthenticationDetailsByCustomerId(customerId);
	}
	
	@Override
	public CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByCustomerId(
			Long customerId) {
		
		return customerQuickRegisterRepository.findByCustomerId(customerId);
	}


	@Override
	public CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByEmail(
			String email) {
		
		return customerQuickRegisterRepository.findByEmail(email);
	}

	@Override
	public CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByMobile(
			Long mobile) {
		
		return customerQuickRegisterRepository.findByMobile(mobile);
	}
	
	@Override
	public CustomerQuickRegisterEntity saveCustomerQuickRegisterEntity(
			CustomerQuickRegisterEntity customerQuickRegisterEntity) {
		
		CustomerQuickRegisterEntity quickRegisterEntity=customerQuickRegisterRepository.save(customerQuickRegisterEntity);
		
		return quickRegisterEntity;
	}

	
	@Override
	public CustomerEmailVerificationDetails saveCustomerEmailVerificationDetails(
			CustomerEmailVerificationDetails emailVerificationDetails) {
		
		CustomerEmailVerificationDetails verificationDetails=customerEmailVericationDetailsRepository.save(emailVerificationDetails);
		
		return verificationDetails;
	}

	@Override
	public CustomerMobileVerificationDetails saveCustomerMobileVerificationDetails(
			CustomerMobileVerificationDetails mobileVerificationDetails) {
		
		CustomerMobileVerificationDetails verificationDetails=customerMobileVerificationDetailsRepository.save(mobileVerificationDetails);
		
		return verificationDetails;
	}


	@Override
	public CustomerAuthenticationDetails saveCustomerAuthenticationDetails(
			CustomerAuthenticationDetails entity) {
		
		CustomerAuthenticationDetails customerAuthenticationDetails=customerAuthenticationDetailsRepository.save(entity);
		
		return customerAuthenticationDetails;
	}

	@Override
	public CustomerEmailVerificationDetails getCustomerEmailVerificationDetailsByCustomerIdAndEmail(
			Long customerId, String email) {
		CustomerEmailVerificationDetails fetchedEmailVerificationDetails=customerEmailVericationDetailsRepository.
					getEmailVerificationDetailsByCustomerIdAndEmail(customerId, email);
		return fetchedEmailVerificationDetails;
	}

	@Override
	public CustomerMobileVerificationDetails getCustomerMobileVerificationDetailsByCustomerIdAndMobile(
			Long customerId, Long mobile) {
		CustomerMobileVerificationDetails fetchedMobileVerificationDetails=customerMobileVerificationDetailsRepository.
					getMobileVerificationDetailsByCustomerIdAndMobile(customerId, mobile);
		return fetchedMobileVerificationDetails;
	}

	@Override
	public CustomerDocument saveCustomerDocument(
			CustomerDocument customerDocument) {
		
		return customerDocumentRepository.saveCustomerDocument(customerDocument);
	}

	@Override
	public CustomerDocument getCustomerDocumentById(
			Long customerId) {
		
		return customerDocumentRepository.getCustomerDocumentByCustomerId(customerId);
	}

	
	@Override
	public void clearDataForTesting() {
		customerQuickRegisterRepository.clearCustomerQuickRegister();
		customerAuthenticationDetailsRepository.clearLoginDetailsForTesting();
		customerEmailVericationDetailsRepository.clearTestData();
		customerMobileVerificationDetailsRepository.clearTestData();

		
	}
	
	
	
	private Boolean isMobileNumber(String entity)
	{
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(entity, pos);
		return (entity.length() == pos.getIndex()&&entity.length()==10);
		
	}



}
