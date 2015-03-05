package com.projectx.rest.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;

@Component
@Profile(value={"Dev","Test"})
@PropertySource(value="classpath:/application.properties")
public class MessagerSender {

	
	@Autowired
	MessageBuilder messageBuilder;
	
	@Autowired
	HandleVerificationService handleCustomerVerification;
	
	
	public Boolean sendHashEmail(Long customerId,Integer customerType,Integer entityType,String firstName,String lastName,String email,
			String emailHash,String requestedBy) {
		
		String message=messageBuilder.composeEmailWithEmailHash(customerId,customerType,entityType, firstName, lastName,  emailHash,requestedBy);		
		
		return handleCustomerVerification.sendEmail(email, message);
	}


	
	public Boolean sendPinSMS(String firstName,String lastName,Long mobile,Integer mobilePin)  {
		
		String message=messageBuilder.composeSMSWithMobilePin(firstName, lastName, mobilePin);
		
		return handleCustomerVerification.sendSMS(mobile, message);
		
	}

	
	public Boolean sendPasswordSMS(String firstName,String lastName,Long mobile,String password)
	{
		String message=messageBuilder.composeSMSWithPassword(firstName, lastName, password);
		
		return handleCustomerVerification.sendSMS(mobile, message);
	}
	
	
	
	public Boolean sendPasswordEmail(Long customerId,Integer customerType,String firstName,String lastName,String email,String emailPassword)
	{
		String message=messageBuilder.composeEmailWithPassword(customerId,customerType, firstName, lastName, emailPassword);
		
		return handleCustomerVerification.sendEmail(email, message);
	}
	
}
