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
	
	
	public Boolean sendHashEmail(QuickRegisterEntity customer, EmailVerificationDetails emailVerificationDetails) {
		
		String message=messageBuilder.composeEmailWithEmailHash(customer,emailVerificationDetails);
		
		return handleCustomerVerification.sendEmail(customer.getEmail(), message);
	}


	
	public Boolean sendPinSMS(QuickRegisterEntity customer,MobileVerificationDetails mobileVerificationDetails)  {
		
		String message=messageBuilder.composeSMSWithMobilePin(customer,mobileVerificationDetails);
		
		return handleCustomerVerification.sendSMS(customer.getMobile(), message);
		
	}


	
	
	public Boolean sendPasswordSMS(Long mobile,String message)
	{
		return handleCustomerVerification.sendSMS(mobile, message);
	}
	
	
	
	public Boolean sendPasswordEmail(String email,String message)
	{
		return handleCustomerVerification.sendEmail(email, message);
	}
	
}
