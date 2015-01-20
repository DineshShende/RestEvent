package com.projectx.rest.fixture.quickregister;

import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;

import java.util.Date;

import com.google.gson.Gson;
import com.projectx.data.domain.quickregister.CustomerEmailVerificationDetailsByCustomerIdTypeAndEmailTypeDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.data.domain.quickregister.UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO;
import com.projectx.data.domain.quickregister.UpdateEmailHashDTO;
import com.projectx.mvc.domain.quickregister.VerifyEmailHashDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsKey;


public class EmailVerificationDetailsFixtures {
	
	public static Integer CUST_EMAIL_TYPE_PRIMARY=1;
	public static Integer CUST_EMAIL_TYPE_SECONDARY=2;
	public static Integer CUST_RESEND_COUNT=0;

	//public static Integer CUST_TYPE_CUSTOMER=1;
	//public static Integer CUST_TYPE_VENDOR=2;
	public static Date CUST_DATE=new Date();
	public static String CUST_UPDATED_BY="CUST_ONLINE";
	
	private static Gson gson=new Gson();
	
	public static EmailVerificationDetailsKey standardEmailVerificationDetailsKey()
	{
		return new EmailVerificationDetailsKey(CUST_ID, ENTITY_TYPE_CUSTOMER, CUST_EMAIL_TYPE_PRIMARY);
	}

	public static EmailVerificationDetails standardCustomerEmailVerificationDetails()
	{
		return new EmailVerificationDetails(standardEmailVerificationDetailsKey(), CUST_EMAIL, CUST_EMAILHASH, CUST_DATE, CUST_RESEND_COUNT, CUST_DATE, CUST_UPDATE_TIME, CUST_UPDATED_BY);
	}
	
	public static EmailVerificationDetails standardCustomerEmailVerificationDetailsWithOutPassword()
	{
		return new EmailVerificationDetails(standardEmailVerificationDetailsKey(), CUST_EMAIL, CUST_EMAILHASH, null, CUST_RESEND_COUNT, null, null, null);
	}
	
	
	public static CustomerEmailVerificationDetailsByCustomerIdTypeAndEmailTypeDTO standardCustomerEmailVerificationDetailsByCustomerIdAndEmailDTO()
	{
		return new CustomerEmailVerificationDetailsByCustomerIdTypeAndEmailTypeDTO(CUST_ID,ENTITY_TYPE_CUSTOMER, CUST_EMAIL_TYPE_PRIMARY);
	}
	
	public static UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO standardUpdateEmailHashAndEmailHashSentTimeDTO()
	{
		return new UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO(CUST_ID,ENTITY_TYPE_CUSTOMER, CUST_EMAIL_TYPE_PRIMARY, CUST_EMAILHASH_UPDATED, CUST_EMAIL_HASH_SENT_TIME,CUST_RESEND_COUNT+1);
	}
	
	public static CustomerIdTypeEmailTypeDTO standardCustomerIdEmailDTO()
	{
		return new CustomerIdTypeEmailTypeDTO(CUST_ID,ENTITY_TYPE_CUSTOMER, CUST_EMAIL_TYPE_PRIMARY);
	}
	
	public static String standardJsonUpdateEmailHashDTOMVC()
	{
		
		return "{\"customerId\":"+CUST_ID+",\"customerType\":"+ENTITY_TYPE_CUSTOMER+",\"emailType\":\""+CUST_EMAIL_TYPE_PRIMARY+"\"}";
	}
	
	
	
	
	public static String standardJsonVerifyEmailHashDTO(Long customerId,Integer entityType,Integer emailType,String emailHash)
	{
		
		VerifyEmailHashDTO verifyEmailHashDTO=new VerifyEmailHashDTO(customerId, entityType, emailType, emailHash);
		
		System.out.println(gson.toJson(verifyEmailHashDTO));
		  
		return gson.toJson(verifyEmailHashDTO);
	}
	

	
}
