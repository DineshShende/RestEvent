package com.projectx.rest.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.CustomerAuthenticationDetails;
import com.projectx.rest.domain.CustomerEmailVerificationDetails;
import com.projectx.rest.domain.CustomerMobileVerificationDetails;
import com.projectx.rest.domain.CustomerQuickRegisterEntity;

@Component
@Profile(value={"Dev","Test"})
@PropertySource(value="classpath:/application.properties")
public class MessageBuilder {

	
	@Autowired
	Environment env;
	
	public String composeSMSWithPassword(CustomerQuickRegisterEntity customer,CustomerAuthenticationDetails authenticationDetails)
	{
		StringBuilder messageBuilder=new StringBuilder();
		
		messageBuilder.append("Hi "+customer.getFirstName()+" "+customer.getLastName()+"\n");
		messageBuilder.append("Your login password is="+authenticationDetails.getPassword());
		
		return messageBuilder.toString();
	}
	
	public String composeEmailWithPassword(CustomerQuickRegisterEntity customer,CustomerAuthenticationDetails authenticationDetails)
	{
		
		StringBuilder messageBuilder=new StringBuilder();
		
		messageBuilder.append("Hi "+customer.getFirstName()+" "+customer.getLastName()+"\n");
		messageBuilder.append("As per your new password Request!!\n Click here to login with new password\n");
		messageBuilder.append(env.getProperty("mvc.url")+"/customer/quickregister/emailPasswordVerification/"+customer.getCustomerId()+"/"+authenticationDetails.getEmailPassword());
		
		//System.out.println(messageBuilder.toString());
		
		return messageBuilder.toString();
		
	}

	
	public String composeSMSWithMobilePin(CustomerQuickRegisterEntity customer,CustomerMobileVerificationDetails mobileVerificationDetails)
	{
		StringBuilder messageBuilder=new StringBuilder();
		
		messageBuilder.append("Hi "+customer.getFirstName()+" "+customer.getLastName()+"\n");
		messageBuilder.append("Thanks for connecting with us!!\n Enter given OTP in provided textbox.OTP="+mobileVerificationDetails.getMobilePin());
		
		//System.out.println(messageBuilder.toString());
		
		return messageBuilder.toString();
	}
	
	
	public String composeEmailWithEmailHash(CustomerQuickRegisterEntity customer,CustomerEmailVerificationDetails emailVerificationDetails)
	{
		StringBuilder messageBuilder=new StringBuilder();
		
		messageBuilder.append("Hi "+customer.getFirstName()+" "+customer.getLastName()+"\n");
		messageBuilder.append("Thanks for connecting with us!!\n Please Click Below link to activate your account\n");
		messageBuilder.append(env.getProperty("mvc.url")+"/customer/quickregister/verifyEmailHash/"+customer.getCustomerId()+"/"+emailVerificationDetails.getEmail()+"/"+emailVerificationDetails.getEmailHash());
		
		//System.out.println(messageBuilder.toString());
		
		return messageBuilder.toString();
	}
	
}