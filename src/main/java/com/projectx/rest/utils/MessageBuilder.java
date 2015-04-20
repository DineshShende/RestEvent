package com.projectx.rest.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;

@Component
@Profile(value={"Dev","Test","Prod"})
@PropertySource(value="classpath:/application.properties")
public class MessageBuilder {

	
	@Autowired
	Environment env;
	
	public String composeSMSWithPassword(String firstName,String lastName,String password)
	{
		StringBuilder messageBuilder=new StringBuilder();
		
		messageBuilder.append("Hi "+firstName+" "+lastName+"\n");
		messageBuilder.append("Your login password is="+password);
		
		return messageBuilder.toString();
	}
	
	public String composeEmailWithPassword(Long customerId,Integer customerType,String firstName,String lastName,String emailPassword)
	{
		
		StringBuilder messageBuilder=new StringBuilder();
		
		messageBuilder.append("Hi "+firstName+" "+lastName+"\n");
		messageBuilder.append("As per your new password Request!!\n Click here to login with new password\n");
		messageBuilder.append(env.getProperty("mvc.url")+"/quickregister/emailPasswordVerification/"+customerId+"/"+customerType+"/"+emailPassword);
		
		//System.out.println(messageBuilder.toString());
		
		return messageBuilder.toString();
		
	}

	
	public String composeSMSWithMobilePin(String firstName,String lastName,Integer mobilePin)
	{
		StringBuilder messageBuilder=new StringBuilder();
		
		messageBuilder.append("Hi "+firstName+" "+lastName+"\n");
		messageBuilder.append("Thanks for connecting with us!!\n Enter given ");
		messageBuilder.append("OTP in provided textbox.OTP="+mobilePin);
		
		//System.out.println(messageBuilder.toString());
		
		return messageBuilder.toString();
	}
	
	
	public String composeEmailWithEmailHash(Long customerId,Integer customerType,Integer entityType,
			String firstName,String lastName,String emailHash,Integer requestedBy,Long requestedById)
	{
		StringBuilder messageBuilder=new StringBuilder();
		
		messageBuilder.append("Hi "+firstName+" "+lastName+"\n");
		messageBuilder.append("Thanks for connecting with us!!\n Please Click Below link to activate your account\n");
		messageBuilder.append(env.getProperty("mvc.url")+"/quickregister/verifyEmailHash/"+customerId+"/"+customerType+"/"+entityType+"/"+requestedBy+"/"+requestedById+"/"+emailHash);
		

		
		return messageBuilder.toString();
	}
	
	public String composeSMSWithDealAndExchaneContact(Long requestId,Long dealId,Long contactNumber,String contactName)
	{
	
		StringBuilder builder=new StringBuilder();
		
		builder.append("Hi ").append(contactName).append(" ");
		builder.append("Deal has been closed for request:"+requestId);
		builder.append("The contact Number of other party is:"+contactNumber);
		builder.append("The deal Id is:"+dealId);
		
		return builder.toString();
		
	}
	
}
