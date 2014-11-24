package com.projectx.rest.fixture;

import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.*;

import com.projectx.data.domain.CustomerMobileVerificationDetailsByCustomerIdAndMobileDTO;
import com.projectx.data.domain.MobileDTO;
import com.projectx.data.domain.UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO;
import com.projectx.data.domain.CustomerIdMobileDTO;
import com.projectx.data.domain.UpdateMobileVerificationAttemptsDTO;
import com.projectx.rest.domain.CustomerMobileVerificationDetails;
import com.projectx.web.domain.VerifyMobileDTO;

public class CustomerMobileVericationDetailsFixtures {

	
	public static String CUST_MOBILE_TYPE_PRIMARY="PRIMARY";
	public static String CUST_MOBILE_TYPE_SECONDARY="SECONDARY";
	public static Integer CUST_RESEND_COUNT=0;
	
	
	public static CustomerMobileVerificationDetails standardCustomerMobileVerificationDetails()
	{
		return new CustomerMobileVerificationDetails(CUST_ID, CUST_MOBILE_TYPE_PRIMARY, CUST_MOBILE, CUST_MOBILEPIN, CUST_MOBILE_VERIFICATION_ATTEMPTS,CUST_RESEND_COUNT);
	}
	
	
	public static CustomerMobileVerificationDetailsByCustomerIdAndMobileDTO standardCustomerMobileVerificationDetailsByCustomerIdAndMobileDTO()
	{
		return new CustomerMobileVerificationDetailsByCustomerIdAndMobileDTO(CUST_ID, CUST_MOBILE);
	}
	
	public static UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO standardUpdateMobilePinAndMobileVerificationAttemptsDTO()
	{
		return new UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO(CUST_ID, CUST_MOBILE, CUST_MOBILEPIN_UPDATED, CUST_MOBILE_VERIFICATION_ATTEMPTS+1,CUST_RESEND_COUNT+1);
	}
	

	public static UpdateMobileVerificationAttemptsDTO standardUpdateMobileVerificationAttemptsDTO()
	{
		return new UpdateMobileVerificationAttemptsDTO(CUST_ID, CUST_MOBILE, 1);
	}
	
	public static CustomerIdMobileDTO standardCustomerIdMobileDTO()
	{
		return new CustomerIdMobileDTO(CUST_ID, CUST_MOBILE);
	}
	
	
	
}
