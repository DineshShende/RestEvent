package com.projectx.rest.fixture.quickregister;

import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;

import java.util.Date;

import com.projectx.data.quickregister.domain.CustomerEmailVerificationDetailsByCustomerIdTypeAndEmailDTO;
import com.projectx.data.quickregister.domain.CustomerIdTypeEmailDTO;
import com.projectx.data.quickregister.domain.UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsKey;


public class EmailVerificationDetailsFixtures {
	
	public static String CUST_EMAIL_TYPE_PRIMARY="PRIMARY";
	public static String CUST_EMAIL_TYPE_SECONDARY="SECONDARY";
	public static Integer CUST_RESEND_COUNT=0;

	//public static Integer CUST_TYPE_CUSTOMER=1;
	//public static Integer CUST_TYPE_VENDOR=2;
	public static Date CUST_DATE=new Date();
	public static String CUST_UPDATED_BY="CUST_ONLINE";
	
	
	public static EmailVerificationDetailsKey standardEmailVerificationDetailsKey()
	{
		return new EmailVerificationDetailsKey(CUST_ID, CUST_TYPE_CUSTOMER, CUST_EMAIL);
	}

	public static EmailVerificationDetails standardCustomerEmailVerificationDetails()
	{
		return new EmailVerificationDetails(standardEmailVerificationDetailsKey(), CUST_EMAIL_TYPE_PRIMARY, CUST_EMAILHASH, CUST_DATE, CUST_RESEND_COUNT, CUST_DATE, CUST_UPDATE_TIME, CUST_UPDATED_BY);
	}
	
	public static EmailVerificationDetails standardCustomerEmailVerificationDetailsWithOutPassword()
	{
		return new EmailVerificationDetails(standardEmailVerificationDetailsKey(), CUST_EMAIL_TYPE_PRIMARY, CUST_EMAILHASH, null, CUST_RESEND_COUNT, null, null, null);
	}
	
	
	public static CustomerEmailVerificationDetailsByCustomerIdTypeAndEmailDTO standardCustomerEmailVerificationDetailsByCustomerIdAndEmailDTO()
	{
		return new CustomerEmailVerificationDetailsByCustomerIdTypeAndEmailDTO(CUST_ID,CUST_TYPE_CUSTOMER, CUST_EMAIL);
	}
	
	public static UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO standardUpdateEmailHashAndEmailHashSentTimeDTO()
	{
		return new UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO(CUST_ID,CUST_TYPE_CUSTOMER, CUST_EMAIL, CUST_EMAILHASH_UPDATED, CUST_EMAIL_HASH_SENT_TIME,CUST_RESEND_COUNT+1);
	}
	
	public static CustomerIdTypeEmailDTO standardCustomerIdEmailDTO()
	{
		return new CustomerIdTypeEmailDTO(CUST_ID,CUST_TYPE_CUSTOMER, CUST_EMAIL);
	}
	
	public static String standardJsonUpdateEmailHashDTOMVC()
	{
		return "{\"customerId\":"+CUST_ID+",\"customerType\":"+CUST_TYPE_CUSTOMER+",\"email\":\""+CUST_EMAIL+"\"}";
	}
	
	
	
	
	public static String standardJsonVerifyEmailHashDTO()
	{
		
		StringBuilder jsonBuilder=new StringBuilder();
		jsonBuilder.append("{\"customerId\":"+CUST_ID+",\"email\":\"");
		jsonBuilder.append(CUST_EMAIL);
		jsonBuilder.append("\",\"customerType\":"+CUST_TYPE_CUSTOMER+",\"emailHash\":\""+CUST_EMAILHASH+"\"}");
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
		  
		//return gson.toJson(standardJsonVerifyEmailHashDTO());
	}
	

	
}
