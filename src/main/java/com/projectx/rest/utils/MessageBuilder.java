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
@Profile(value={"Dev","Test"})
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
	
	public String composeEmailWithPassword(Long customerId,String firstName,String lastName,String emailPassword)
	{
		
		StringBuilder messageBuilder=new StringBuilder();
		
		messageBuilder.append("Hi "+firstName+" "+lastName+"\n");
		messageBuilder.append("As per your new password Request!!\n Click here to login with new password\n");
		messageBuilder.append(env.getProperty("mvc.url")+"/customer/quickregister/emailPasswordVerification/"+customerId+"/"+emailPassword);
		
		//System.out.println(messageBuilder.toString());
		
		return messageBuilder.toString();
		
	}

	
	public String composeSMSWithMobilePin(String firstName,String lastName,Integer mobilePin)
	{
		StringBuilder messageBuilder=new StringBuilder();
		
		messageBuilder.append("Hi "+firstName+" "+lastName+"\n");
		messageBuilder.append("Thanks for connecting with us!!\n Enter given OTP in provided textbox.OTP="+mobilePin);
		
		//System.out.println(messageBuilder.toString());
		
		return messageBuilder.toString();
	}
	
	
	public String composeEmailWithEmailHash(Long customerId,String firstName,String lastName,String email,String emailHash)
	{
		StringBuilder messageBuilder=new StringBuilder();
		
		messageBuilder.append("Hi "+firstName+" "+lastName+"\n");
		messageBuilder.append("Thanks for connecting with us!!\n Please Click Below link to activate your account\n");
		messageBuilder.append(env.getProperty("mvc.url")+"/customer/quickregister/verifyEmailHash/"+customerId+"/"+email+"/"+emailHash);
		
		//System.out.println(messageBuilder.toString());
		
		return messageBuilder.toString();
	}
	
}
