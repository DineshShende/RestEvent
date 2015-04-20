package com.projectx.rest.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;

@Component
@Profile(value={"Dev","Test","Prod"})
@PropertySource(value="classpath:/application.properties")
public class MessagerSender {

	
	@Autowired
	MessageBuilder messageBuilder;
	
	@Autowired
	HandleVerificationService handleCustomerVerification;
	
	
	public Boolean sendHashEmail(Long customerId,Integer customerType,Integer entityType,String firstName,String lastName,String email,
			String emailHash,Integer requestedBy,Long requestedById) {
		
		String message=messageBuilder.composeEmailWithEmailHash(customerId,customerType,entityType, firstName, lastName, 
				emailHash,requestedBy,requestedById);		
		
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
	
	public Boolean exchangeContactAferDeal(Long mobile,Long requestId,Long dealId,Long  contactNumber,String contactName)
	{
		String message=messageBuilder.composeSMSWithDealAndExchaneContact(requestId, dealId, contactNumber, contactName);
		
		System.out.println("ExchangeContact:"+message);
		
		return handleCustomerVerification.sendSMS(mobile, message);
	}
	
}
