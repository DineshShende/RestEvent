package com.projectx.rest.handlers;

import static com.projectx.rest.fixtures.CustomerQuickRegisterDataFixture.*;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.projectx.data.domain.LoginVerificationDTO;
import com.projectx.data.domain.UpdatePasswordDTO;
import com.projectx.rest.domain.CustomerAuthenticationDetails;
import com.projectx.rest.domain.CustomerQuickDetailsSentStatusEntity;
import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.repository.CustomerAuthenticationDetailsRepository;
import com.projectx.rest.repository.CustomerQuickRegisterRepository;
import com.projectx.rest.services.CustomerQuickRegisterService;
import com.projectx.rest.utils.HandleCustomerVerification;
import com.projectx.web.domain.CustomerIdDTO;
import com.projectx.web.domain.CustomerQuickRegisterEntityDTO;

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
	HandleCustomerVerification handleCustomerVerification;

	@Autowired
	Environment env;
	
	private final int EMAIL_VALIDITY_TIME_IN_HRS=24;
	private final int UPDATE_SUCESS=1;
	private final int MAX_MOBILE_VERIFICATION_ATTEMPTS=3;
	
	
	@Override
	public String checkIfAlreadyRegistered(
			CustomerQuickRegisterEntityDTO customer) throws Exception {

		String status=null;
		
		Boolean emailAlreadyExist = false;

		Boolean mobileAlreadyExist = false;

		if (customer.getEmail() == null && customer.getMobile() == null)
			throw new Exception();

		if (customer.getEmail() != null
				&& customerQuickRegisterRepository.countByEmail(customer
						.getEmail()) > 0)
			emailAlreadyExist = true;

		if (customer.getMobile() != null
				&& customerQuickRegisterRepository.countByMobile(customer
						.getMobile()) > 0)
			mobileAlreadyExist = true;

		if (emailAlreadyExist && mobileAlreadyExist)
			status=REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED;
		else if(!emailAlreadyExist && mobileAlreadyExist)
			status=REGISTER_MOBILE_ALREADY_REGISTERED;
		else if(emailAlreadyExist && !mobileAlreadyExist)
			status=REGISTER_EMAIL_ALREADY_REGISTERED;
		else if(!emailAlreadyExist && !mobileAlreadyExist)
			status=REGISTER_NOT_REGISTERED;
		
		return status;
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
	public CustomerQuickDetailsSentStatusEntity handleNewCustomerQuickRegister(
			CustomerQuickRegisterEntityDTO customer) throws Exception {
		
		CustomerQuickRegisterEntity customerWithStatusPopulated=populateStatus(customer);
		
		CustomerQuickRegisterEntity initialisedCustomer=initializeNewCustomerQuickRegistrationEntity(customerWithStatusPopulated);

		CustomerQuickRegisterEntity savedEntity=saveNewCustomerQuickRegisterEntity(initialisedCustomer);
		
		CustomerAuthenticationDetails savedLoginDetails=saveCustomerAuthenticationDetails(savedEntity);
		
		CustomerQuickDetailsSentStatusEntity customerStatusEntity=sendVerificationDetails(savedEntity);
		
		return customerStatusEntity;
	}

	@Override
	public CustomerQuickDetailsSentStatusEntity sendVerificationDetails(CustomerQuickRegisterEntity customer) throws UnirestException {
		
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
	
		return new CustomerQuickDetailsSentStatusEntity(finalStatus,customer);
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
		
		//TODO
		
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
	
		//TODO
		
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
		
		CustomerAuthenticationDetails fetchedEntity=customerAuthenticationDetailsRepository.loginVerification(loginVerificationDTO.getEmail(),
																loginVerificationDTO.getMobile(), loginVerificationDTO.getPassword());
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
	public String composeMessageWithPassword(CustomerQuickRegisterEntity customer)
	{
		StringBuilder messageBuilder=new StringBuilder();
		
		messageBuilder.append("Hi "+customer.getFirstName()+" "+customer.getLastName()+"\n");
		messageBuilder.append("Your login password is="+customer.getPassword());
		
		return null;
	}
	
	@Override
	public Boolean sendPasswordSMS(CustomerQuickRegisterEntity customer)
	{
		String message=composeMessageWithPassword(customer);
		
		return handleCustomerVerification.sendSMS(customer.getMobile(), message);
	}
	
	
	@Override
	public Boolean sendPasswordEmail(CustomerQuickRegisterEntity customer)
	{
		String message=composeMessageWithPassword(customer);
		
		return handleCustomerVerification.sendEmail(customer.getEmail(), message);
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
			//passwordUpdateStatus=customerQuickRegisterRepository.updatePassword(customer.getCustomerId(), customer.getPassword(), customer.getPasswordType());
			 passwordUpdateStatus=customerAuthenticationDetailsRepository.updatePasswordAndPasswordType(customerAuthenticationDetails.getCustomerId(),
					 																						customerAuthenticationDetails.getPassword(), customerAuthenticationDetails.getPasswordType());
		}
		
			
		if(customer.isEmailMobileVerified() || customer.isEmailVerifiedMobileVerficationPending()||customer.isEmailVerified())
			sendPasswordEmail(customer);
		
		if(customer.isEmailMobileVerified()||customer.isMobileVerifiedEmailVerficationPending()|| customer.isMobileVerified())
			sendPinSMS(customer);
		
		
		if(passwordUpdateStatus==UPDATE_SUCESS && emailSendStatus && smsSendStatus)
			return true;
		else
			return false;
		
		
	}
	
	
	@Override
	public Boolean resetPassword(CustomerIdDTO customerIdDTO) 
	{
		CustomerQuickRegisterEntity customer=customerQuickRegisterRepository.findByCustomerId(customerIdDTO.getCustomerId());
		
		customer.setPassword(null);
		customer.setPasswordType(null);
		
		return sendDefaultPassword(customer,true);
	}
	
	@Override
	public Boolean updatePassword(UpdatePasswordDTO updatePasswordDTO)
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
	public void clearDataForTesting() {
		customerQuickRegisterRepository.clearCustomerQuickRegister();

		
	}




}
