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

import com.mashape.unirest.http.exceptions.UnirestException;
import com.projectx.data.domain.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.data.domain.VerifyLoginDetailsDataDTO;
import com.projectx.rest.domain.CustomerAuthenticationDetails;
import com.projectx.rest.domain.CustomerDocument;
import com.projectx.rest.domain.CustomerQuickRegisterStatusEntity;
import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.repository.CustomerAuthenticationDetailsRepository;
import com.projectx.rest.repository.CustomerDocumentRepository;
import com.projectx.rest.repository.CustomerQuickRegisterRepository;
import com.projectx.rest.services.CustomerQuickRegisterService;
import com.projectx.rest.utils.HandleCustomerVerification;
import com.projectx.web.domain.CustomerIdDTO;
import com.projectx.web.domain.CustomerQuickRegisterEntityDTO;
import com.projectx.web.domain.CustomerQuickRegisterStringStatusEntity;
import com.projectx.web.domain.LoginVerificationDTO;

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
	CustomerDocumentRepository customerDocumentRepository;
	
	@Autowired 
	HandleCustomerVerification handleCustomerVerification;

	@Autowired
	Environment env;
	
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
		
		if(customer.getEmail()!=null && entityByEmail.getCustomerId()!=null)
		{
			returnEntity=entityByEmail;
			
			emailAlreadyExist=true;
			
			if(entityByEmail.isEmailVerified() || entityByEmail.isEmailVerifiedMobileVerficationPending()|| entityByEmail.isEmailMobileVerified())
				emailVerified=true;
		}
		
		if(customer.getMobile()!=null && entityByMobile.getCustomerId()!=null)
		{
			returnEntity=entityByMobile;
			
			mobileAlreadyExist=true;
			
			if(entityByMobile.isMobileVerified()||entityByMobile.isMobileVerifiedEmailVerficationPending()||entityByMobile.isEmailMobileVerified())
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
	public CustomerQuickRegisterEntity populateStatus(
			CustomerQuickRegisterEntityDTO customer) throws Exception {

		CustomerQuickRegisterEntity customerToProcess = customer.toCustomerQuickRegisterEntity();
		
		String status = null;

		if (customer.getEmail() == null && customer.getMobile() == null)
			throw new Exception();

		if (customer.getEmail() != null && customer.getMobile() != null) {
			status = "EmailMobileVerificationPending";
		} else if (customer.getEmail() != null) {
			status = "EmailVerificationPending";
		} else if (customer.getMobile() != null) {
			status = "MobileVerificationPending";
		}

		customerToProcess.setStatus(status);
		customerToProcess.setLastStatusChangedTime(new Date());
		
		return customerToProcess;
	}

	@Override
	public CustomerQuickRegisterEntity initializeNewCustomerQuickRegistrationEntity(
			CustomerQuickRegisterEntity customer) {

		if (customer.getStatus().equals(
				"EmailMobileVerificationPending")) {
			customer.setEmailHash(handleCustomerVerification.generateEmailHash());
			customer.setMobilePin(handleCustomerVerification.genarateMobilePin());
			customer.setMobileVerificationAttempts(0);
		} else if (customer.getStatus().equals(
				"EmailVerificationPending")) {
			customer.setEmailHash(handleCustomerVerification.generateEmailHash());
		} else if (customer.getStatus().equals(
				"MobileVerificationPending")) {
			customer.setMobilePin(handleCustomerVerification.genarateMobilePin());
			customer.setMobileVerificationAttempts(0);
		}
		
		return customer;
	}

	@Override
	public CustomerQuickRegisterEntity saveNewCustomerQuickRegisterEntity(
			CustomerQuickRegisterEntity customer) throws Exception {
				
		return customerQuickRegisterRepository.save(customer);
	}

	
	@Override
	public CustomerAuthenticationDetails saveCustomerAuthenticationDetails(CustomerQuickRegisterEntity entity)
	{
		CustomerAuthenticationDetails newEntity=new CustomerAuthenticationDetails(entity.getCustomerId(), entity.getEmail(), 
																								entity.getMobile(), null, null);
		
		CustomerAuthenticationDetails savedEntity=customerAuthenticationDetailsRepository.save(newEntity);
		
		return savedEntity;
	}

	@Override
	public CustomerQuickRegisterStatusEntity handleNewCustomerQuickRegister(
			CustomerQuickRegisterEntityDTO customer) throws Exception {
		
		CustomerQuickRegisterEntity customerWithStatusPopulated=populateStatus(customer);
		
		CustomerQuickRegisterEntity initialisedCustomer=initializeNewCustomerQuickRegistrationEntity(customerWithStatusPopulated);

		CustomerQuickRegisterEntity savedEntity=saveNewCustomerQuickRegisterEntity(initialisedCustomer);
		
		CustomerAuthenticationDetails savedLoginDetails=saveCustomerAuthenticationDetails(savedEntity);
		
		CustomerQuickRegisterStatusEntity customerStatusEntity=sendVerificationDetails(savedEntity);
		
		return customerStatusEntity;
	}

	@Override
	public CustomerQuickRegisterStatusEntity sendVerificationDetails(CustomerQuickRegisterEntity customer) throws UnirestException {
		
		Boolean emailSentStatus=true;
		Boolean mobileSentStatus=true;
		
		Boolean finalStatus=false;
		
		if (customer.getStatus().equals(
				"EmailMobileVerificationPending")) {
			emailSentStatus=sendHashEmail(customer);
			customer.setEmailHashSentTime(new Date());
			mobileSentStatus=sendPinSMS(customer);
			customer.setMobilePinSentTime(new Date());
			
		} else if (customer.getStatus().equals(
				"EmailVerificationPending")) {
			emailSentStatus=sendHashEmail(customer);
			customer.setEmailHashSentTime(new Date());
		} else if (customer.getStatus().equals(
				"MobileVerificationPending")) {
			mobileSentStatus=sendPinSMS(customer);
			customer.setMobilePinSentTime(new Date());
		}
		
		Integer updatedStatus=customerQuickRegisterRepository.updateEmailHashAndMobilePinSentTime(customer.getCustomerId(),customer.getEmailHashSentTime() ,customer.getMobilePinSentTime());
				
		if(updatedStatus==UPDATE_SUCESS && emailSentStatus && mobileSentStatus)
			finalStatus= true;
		else
			finalStatus=false;
	
		return new CustomerQuickRegisterStatusEntity(finalStatus,customer);
	}
	

	@Override
	public CustomerAuthenticationDetails getLoginDetailsByCustomerId(Long customerId)
	{
		return customerAuthenticationDetailsRepository.getByCustomerId(customerId);
	}
	
	@Override
	public CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByCustomerId(
			Long customerId) {
		
		return customerQuickRegisterRepository.findByCustomerId(customerId);
	}



	@Override
	public Boolean verifyEmailHash(Long customerId, String emailHash) {
		
		CustomerQuickRegisterEntity fetchedEntity=getCustomerQuickRegisterEntityByCustomerId(customerId);
	
		
		if(fetchedEntity.getCustomerId()==null)
			return false;
	
		Date emailHashSentTime=fetchedEntity.getEmailHashSentTime();
		
		Date verificationTime=new Date();
		
		long dateDiffernce=verificationTime.getTime()-emailHashSentTime.getTime();
		
		long diffHours = dateDiffernce / (60 * 60 * 1000);
		
		
		if(fetchedEntity.getEmailHash().equals(emailHash) && diffHours<=EMAIL_VALIDITY_TIME_IN_HRS)
		{
							
			if(fetchedEntity.isMobileVerifiedEmailVerficationPending())
			{
				fetchedEntity.setStatusEmailMobileVerified();
			}
			else if(fetchedEntity.isEmailMobileVerificationPending())
			{
				fetchedEntity.setStatusEmailVerifiedMobileVerficationPending();
			}
			else if(fetchedEntity.isEmailVerificationPending())
			{
				fetchedEntity.setStatusEmailVerified();
			}
			
			fetchedEntity.setLastStatusChangedTime(new Date());
			
			int updatedStatus=customerQuickRegisterRepository.updateStatusAndMobileVerificationAttemptsByCustomerId(customerId, fetchedEntity.getStatus(), 
						fetchedEntity.getLastStatusChangedTime(), fetchedEntity.getMobileVerificationAttempts());
										
			
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
	public Boolean verifyMobilePin(Long customerId, Integer mobilePin) {
	
		CustomerQuickRegisterEntity fetchedEntity=getCustomerQuickRegisterEntityByCustomerId(customerId);
		
		Date lastStatusChangedTime=fetchedEntity.getLastStatusChangedTime();
		
		Boolean verificationStatus=false;
		
		Boolean sendPasswordStatus=true;
		
		if(fetchedEntity.getCustomerId()==null)
			return false;
		
		
		
		if(fetchedEntity.getMobilePin().equals(mobilePin) && fetchedEntity.getMobileVerificationAttempts()<MAX_MOBILE_VERIFICATION_ATTEMPTS)
		{
			if(fetchedEntity.isEmailMobileVerificationPending())
				fetchedEntity.setStatusMobileVerifiedEmailVerficationPending();
			else if(fetchedEntity.isEmailVerifiedMobileVerficationPending())
				fetchedEntity.setStatusEmailMobileVerified();
			else if(fetchedEntity.isMobileVerificationPending())
				fetchedEntity.setStatusMobileVerified();
		
			verificationStatus=true;
			
			lastStatusChangedTime=new Date();
			
			sendPasswordStatus=sendDefaultPassword(fetchedEntity, false);
				
		}
		else
		{
			int currentAttemptCount=fetchedEntity.getMobileVerificationAttempts();
			fetchedEntity.setMobileVerificationAttempts(currentAttemptCount+1);
		
		}
		
		fetchedEntity.setLastStatusChangedTime(lastStatusChangedTime);
		
		int updatedStatus=customerQuickRegisterRepository.updateStatusAndMobileVerificationAttemptsByCustomerId(customerId, fetchedEntity.getStatus(), 
				fetchedEntity.getLastStatusChangedTime(), fetchedEntity.getMobileVerificationAttempts());
	
		if(updatedStatus==UPDATE_SUCESS && verificationStatus && sendPasswordStatus)
			return true;
		else
			return false;
		
	}

	@Override
	public CustomerAuthenticationDetails verifyLoginDetails(
			LoginVerificationDTO loginVerificationDTO) {
		
		VerifyLoginDetailsDataDTO loginDetailsDataDTO;
		
		if(isMobileNumber(loginVerificationDTO.getLoginEntity()))
		{
			loginDetailsDataDTO=new VerifyLoginDetailsDataDTO(null, Long.parseLong(loginVerificationDTO.getLoginEntity()), loginVerificationDTO.getPassword());
		}
		else
		{
			loginDetailsDataDTO=new VerifyLoginDetailsDataDTO(loginVerificationDTO.getLoginEntity(), null, loginVerificationDTO.getPassword());
		}
		
		CustomerAuthenticationDetails fetchedEntity=customerAuthenticationDetailsRepository.loginVerification(loginDetailsDataDTO.getEmail(),
																loginDetailsDataDTO.getMobile(), loginDetailsDataDTO.getPassword());
		return fetchedEntity;
	}

	
	
	@Override
	public String composeEmailWithEmailHash(CustomerQuickRegisterEntity customer) {
		
		StringBuilder messageBuilder=new StringBuilder();
		
		messageBuilder.append("Hi "+customer.getFirstName()+" "+customer.getLastName()+"\n");
		messageBuilder.append("Thanks for connecting with us!!\n Please Click Below link to activate your account\n");
		messageBuilder.append(env.getProperty("mvc.url")+"/customer/quickregister/verifyEmailHash/"+customer.getCustomerId()+"/"+customer.getEmailHash());
		
		//System.out.println(messageBuilder.toString());
		
		return messageBuilder.toString();
	}

	@Override
	public Boolean sendHashEmail(CustomerQuickRegisterEntity customer) {
		
		String message=composeEmailWithEmailHash(customer);
		
		return handleCustomerVerification.sendEmail(customer.getEmail(), message);
	}


	
	
	@Override
	public String composeSMSWithMobilePin(CustomerQuickRegisterEntity customer) {
		
		StringBuilder messageBuilder=new StringBuilder();
		
		messageBuilder.append("Hi "+customer.getFirstName()+" "+customer.getLastName()+"\n");
		messageBuilder.append("Thanks for connecting with us!!\n Enter given OTP in provided textbox.OTP="+customer.getMobilePin());
		
		//System.out.println(messageBuilder.toString());
		
		return messageBuilder.toString();
	}

	@Override
	public Boolean sendPinSMS(CustomerQuickRegisterEntity customer)  {
		
		String message=composeSMSWithMobilePin(customer);
		
		return handleCustomerVerification.sendSMS(customer.getMobile(), message);
		
	}

	@Override
	public String composeMessageWithPassword(CustomerQuickRegisterEntity customer,CustomerAuthenticationDetails authenticationDetails)
	{
		StringBuilder messageBuilder=new StringBuilder();
		
		messageBuilder.append("Hi "+customer.getFirstName()+" "+customer.getLastName()+"\n");
		messageBuilder.append("Your login password is="+authenticationDetails.getPassword());
		
		return messageBuilder.toString();
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
		
		CustomerAuthenticationDetails customerAuthenticationDetails=customerAuthenticationDetailsRepository.getByCustomerId(customer.getCustomerId());
		
		if(!resetFlag && (customerAuthenticationDetails.getPasswordType()!=null && customerAuthenticationDetails.getPasswordType().equals(CUST_PASSWORD_TYPE_CHANGED)))
			return true;
		
		if(customerAuthenticationDetails.getPassword()==null || resetFlag)
		{	
			String password=handleCustomerVerification.generatePassword();
			customerAuthenticationDetails.setPassword(password);
			customerAuthenticationDetails.setPasswordType(CUST_PASSWORD_TYPE_DEFAULT);

			 passwordUpdateStatus=customerAuthenticationDetailsRepository.updatePasswordAndPasswordType(customerAuthenticationDetails.getCustomerId(),
					 																						customerAuthenticationDetails.getPassword(), customerAuthenticationDetails.getPasswordType());
		}
		
		CustomerAuthenticationDetails customerAuthenticationDetailsUpdated=customerAuthenticationDetailsRepository.getByCustomerId(customer.getCustomerId());
		
		String message=composeMessageWithPassword(customer, customerAuthenticationDetailsUpdated);
			
		if(customer.isEmailMobileVerified() || customer.isEmailVerifiedMobileVerficationPending()||customer.isEmailVerified())
			sendPasswordEmail(customer.getEmail(),message);
		
		if(customer.isEmailMobileVerified()||customer.isMobileVerifiedEmailVerficationPending()|| customer.isMobileVerified())
			sendPasswordSMS(customer.getMobile(),message);
		
		
		if(passwordUpdateStatus==UPDATE_SUCESS && emailSendStatus && smsSendStatus)
			return true;
		else
			return false;
		
		
	}
	
	@Override
	public Boolean updatePassword(UpdatePasswordAndPasswordTypeDTO updatePasswordDTO)
	{
		Integer updateStatus=customerAuthenticationDetailsRepository.updatePasswordAndPasswordType(updatePasswordDTO.getCustomerId(),
				updatePasswordDTO.getPassword(), CUST_PASSWORD_TYPE_CHANGED);
		
		if(updateStatus==UPDATE_SUCESS)
			return true;
		else
			return false;
	}
	
	
	@Override
	public Integer updateEmailHash(Long customerId) {
		
		String emailHash=handleCustomerVerification.generateEmailHash();
		
		return customerQuickRegisterRepository.updateEmailHash(customerId, emailHash,new Date());
		
	}

	@Override
	public Integer updateMobilePin(Long customerId) {
		
		Integer mobilePin=handleCustomerVerification.genarateMobilePin();
		
		return customerQuickRegisterRepository.updateMobilePin(customerId, mobilePin,new Date());
	}

		
	@Override
	public Boolean reSendMobilePin(Long customerId)  {
	
		Integer updateStatus=0;
		
		Integer mobilePin=handleCustomerVerification.genarateMobilePin();
		
		updateStatus=customerQuickRegisterRepository.updateMobilePin(customerId, mobilePin,new Date());
		
		CustomerQuickRegisterEntity customer=customerQuickRegisterRepository.findByCustomerId(customerId);
		
		Boolean sentStatus=sendPinSMS(customer);
		
		if(updateStatus==UPDATE_SUCESS && sentStatus)
			return true;
		else
			return false;
	}

	@Override
	public Boolean reSendEmailHash(Long customerId) {
		
		Integer updateStatus=0;
		
		String emailHash=handleCustomerVerification.generateEmailHash();
		
		updateStatus= customerQuickRegisterRepository.updateEmailHash(customerId, emailHash,new Date());
		
		CustomerQuickRegisterEntity customer=customerQuickRegisterRepository.findByCustomerId(customerId);
		
		Boolean sentStatus=sendHashEmail(customer);
		
		if(updateStatus==UPDATE_SUCESS && sentStatus)
			return true;
		else
			return false;
	}

	@Override
	public CustomerQuickRegisterEntity resetPasswordByEmailOrMobileRedirect(String entity) {
		
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
	public void clearDataForTesting() {
		customerQuickRegisterRepository.clearCustomerQuickRegister();

		
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
	public CustomerDocument saveCustomerDocument(
			CustomerDocument customerDocument) {
		
		return customerDocumentRepository.saveCustomerDocument(customerDocument);
	}

	@Override
	public CustomerDocument getCustomerDocumentById(
			Long customerId) {
		
		return customerDocumentRepository.getCustomerDocumentByCustomerId(customerId);
	}


	private Boolean isMobileNumber(String entity)
	{
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(entity, pos);
		return (entity.length() == pos.getIndex()&&entity.length()==10);
		
	}
	


}
