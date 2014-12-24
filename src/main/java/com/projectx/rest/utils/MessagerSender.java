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
	HandleCustomerVerification handleCustomerVerification;
	
	
	public Boolean sendHashEmail(Long customerId,String firstName,String lastName,String email,String emailHash) {
		
		String message=messageBuilder.composeEmailWithEmailHash(customerId, firstName, lastName, email, emailHash);		
		
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
	
	
	
	public Boolean sendPasswordEmail(Long customerId,String firstName,String lastName,String email,String emailPassword)
	{
		String message=messageBuilder.composeEmailWithPassword(customerId, firstName, lastName, emailPassword);
		
		return handleCustomerVerification.sendEmail(email, message);
	}
	
}
