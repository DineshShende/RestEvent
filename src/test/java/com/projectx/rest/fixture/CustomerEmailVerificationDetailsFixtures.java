package com.projectx.rest.fixture;

import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.*;

import com.projectx.data.domain.CustomerEmailVerificationDetailsByCustomerIdAndEmailDTO;
import com.projectx.data.domain.UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO;
import com.projectx.data.domain.CustomerIdEmailDTO;
import com.projectx.rest.domain.CustomerEmailVerificationDetails;


public class CustomerEmailVerificationDetailsFixtures {
	
	public static String CUST_EMAIL_TYPE_PRIMARY="PRIMARY";
	public static String CUST_EMAIL_TYPE_SECONDARY="SECONDARY";
	public static Integer CUST_RESEND_COUNT=0;
	

	public static CustomerEmailVerificationDetails standardCustomerEmailVerificationDetails()
	{
		return new CustomerEmailVerificationDetails(CUST_ID, CUST_EMAIL, CUST_EMAIL_TYPE_PRIMARY, CUST_EMAILHASH, CUST_EMAIL_HASH_SENT_TIME,CUST_RESEND_COUNT);
	}
	
	
	public static CustomerEmailVerificationDetailsByCustomerIdAndEmailDTO standardCustomerEmailVerificationDetailsByCustomerIdAndEmailDTO()
	{
		return new CustomerEmailVerificationDetailsByCustomerIdAndEmailDTO(CUST_ID, CUST_EMAIL);
	}
	
	public static UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO standardUpdateEmailHashAndEmailHashSentTimeDTO()
	{
		return new UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO(CUST_ID, CUST_EMAIL, CUST_EMAILHASH_UPDATED, CUST_EMAIL_HASH_SENT_TIME,CUST_RESEND_COUNT+1);
	}
	
	public static CustomerIdEmailDTO standardCustomerIdEmailDTO()
	{
		return new CustomerIdEmailDTO(CUST_ID, CUST_EMAIL);
	}
	
	
	
}
