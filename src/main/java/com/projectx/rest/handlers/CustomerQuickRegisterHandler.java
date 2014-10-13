package com.projectx.rest.handlers;

import static com.projectx.rest.fixtures.CustomerQuickRegisterDataFixture.REGISTER_EMAIL_ALREADY_REGISTERED;
import static com.projectx.rest.fixtures.CustomerQuickRegisterDataFixture.REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED;
import static com.projectx.rest.fixtures.CustomerQuickRegisterDataFixture.REGISTER_MOBILE_ALREADY_REGISTERED;
import static com.projectx.rest.fixtures.CustomerQuickRegisterDataFixture.REGISTER_NOT_REGISTERED;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.repository.CustomerQuickRegisterRepository;
import com.projectx.rest.services.CustomerQuickRegisterService;
import com.projectx.rest.utils.HandleCustomerVerification;
import com.projectx.web.domain.CustomerQuickRegisterEntityDTO;

@Component
//@ActiveProfiles(value="Dev")
@PropertySource(value="classpath:/application.properties")
public class CustomerQuickRegisterHandler implements
		CustomerQuickRegisterService {

	@Autowired
	CustomerQuickRegisterRepository customerQuickRegisterRepository;
	
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
	public CustomerQuickRegisterEntityDTO populateStatus(
			CustomerQuickRegisterEntityDTO customer) throws Exception {

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

		customer.setStatus(status);

		return customer;
	}

	@Override
	public CustomerQuickRegisterEntity handleNewCustomerQuickRegistration(
			CustomerQuickRegisterEntityDTO customer) {

		CustomerQuickRegisterEntity customerToProcess = customer.toCustomerQuickRegisterEntity();
				
		if (customerToProcess.getStatus().equals(
				"EmailMobileVerificationPending")) {
			customerToProcess.setEmailHash(handleCustomerVerification.generateEmailHash());
			customerToProcess.setMobilePin(handleCustomerVerification.genarateMobilePin());
		} else if (customerToProcess.getStatus().equals(
				"EmailVerificationPending")) {
			customerToProcess.setEmailHash(handleCustomerVerification.generateEmailHash());
		} else if (customerToProcess.getStatus().equals(
				"MobileVerificationPending")) {
			customerToProcess.setMobilePin(handleCustomerVerification.genarateMobilePin());
		}

		return customerToProcess;
	}

	@Override
	public CustomerQuickRegisterEntity saveNewCustomerQuickRegisterEntity(
			CustomerQuickRegisterEntity customer) throws Exception {

		return customerQuickRegisterRepository.save(customer);
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
										
			
			if(updatedStatus==UPDATE_SUCESS)
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
		
			
			lastStatusChangedTime=new Date();
		//	int updatedStatus=customerQuickRegisterRepository.
		//								updateStatusByCustomerId(fetchedEntity.getCustomerId(),fetchedEntity.getStatus()).intValue();
		//	if(updatedStatus==UPDATE_SUCESS)
		//		return true;
		//	else
		//		return false;
		}
		else
		{
			int currentAttemptCount=fetchedEntity.getMobileVerificationAttempts();
			fetchedEntity.setMobileVerificationAttempts(currentAttemptCount++);
			//return false;
		}
		
		fetchedEntity.setLastStatusChangedTime(lastStatusChangedTime);
		
		int updatedStatus=customerQuickRegisterRepository.updateStatusAndMobileVerificationAttemptsByCustomerId(customerId, fetchedEntity.getStatus(), 
				fetchedEntity.getLastStatusChangedTime(), fetchedEntity.getMobileVerificationAttempts());
	
		if(updatedStatus==UPDATE_SUCESS)
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
	public String composeSMS(CustomerQuickRegisterEntity customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String composeEmail(CustomerQuickRegisterEntity customer) {
		
		StringBuilder messageBuilder=new StringBuilder();
		
		messageBuilder.append("Hi "+customer.getFirstName()+" "+customer.getLastName()+"\n");
		messageBuilder.append("Thanks for connecting with us!!\n Please Click Below link to activate your account\n");
		messageBuilder.append(env.getProperty("mvc.url")+"/"+customer.getCustomerId()+"/verifyEmail/"+customer.getEmailHash());
		
		System.out.println(messageBuilder.toString());
		
		return messageBuilder.toString();
	}

	@Override
	public void sendPinSMS(CustomerQuickRegisterEntity customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendHashEmail(CustomerQuickRegisterEntity customer) {
		
		String message=composeEmail(customer);
		
		handleCustomerVerification.sendEmailHash(customer.getEmail(), message);
	}

	@Override
	public void clearDataForTesting() {
		customerQuickRegisterRepository.clearCustomerQuickRegister();

		
	}
	
	/*

	@Override
	public String generateEmailHash() {

		String allPossibleChar="01234567789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		StringBuilder sb=new StringBuilder();
		SecureRandom secureRandom=new SecureRandom();
		
		for(int i=0;i<10;i++)
		{
			sb.append(allPossibleChar.charAt(secureRandom.nextInt(allPossibleChar.length())));
		}
		
		String password=sb.toString();
		
		MessageDigest md = null;
		try {
				md = MessageDigest.getInstance("SHA-256");
		}
		catch (NoSuchAlgorithmException e)
		{
			
			e.printStackTrace();
		}
        md.update(password.getBytes());
 
        byte byteData[] = md.digest();
 
        
        StringBuffer sbNew = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
        	sbNew.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
		
        String emailHash=sbNew.toString();
        
        
		return emailHash;
		
	}

	@Override
	public Integer genarateMobilePin() {

		SecureRandom secureRandom=new SecureRandom();
		int number=0;
		
		for(int i=0;i<6;i++)
		{
			number=(number*10)+secureRandom.nextInt(10);
		}	
		
		return number;
	}

	*/
	
	/*AFTER USER SUBMITS FORM WITH DETAILS WE WILL CHECK IF CUSTOMER(EMAIL,MOBILE) IS
	 *ALREADY REGISTERED,IF NOT REGISTERED POPULATE THE STATUS OF CUSTOMER,,DEPENDING ON  
	 * STATUS SEND VERIFICATION EMAIL AND MOBILE PIN DEPENDING ON PROVIDED INFORMATION 
	 * IN CASE OF MOBILE VERIFICATIION THE TIME WILL BE GIVEN FOR 2 MINS.AND FOR EMAIL 
	 * WE WILL SEND HASHED STRING EMBEDDED IN URL
	 */

	

}
