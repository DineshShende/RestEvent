package com.projectx.rest.fixture.quickregister;

import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;

import java.util.Date;

import com.projectx.data.quickregister.domain.CustomerIdTypeMobileDTO;
import com.projectx.data.quickregister.domain.CustomerMobileVerificationDetailsByCustomerIdTypeAndMobileDTO;
import com.projectx.data.quickregister.domain.MobileDTO;
import com.projectx.data.quickregister.domain.UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO;
import com.projectx.data.quickregister.domain.UpdateMobileVerificationAttemptsDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsKey;
import com.projectx.web.domain.quickregister.VerifyMobileDTO;

public class MobileVericationDetailsFixtures {

	
	public static String CUST_MOBILE_TYPE_PRIMARY="PRIMARY";
	public static String CUST_MOBILE_TYPE_SECONDARY="SECONDARY";
	public static Integer CUST_RESEND_COUNT=0;
	
	public static Date CUST_DATE=new Date();
	//public static Integer CUST_TYPE_CUSTOMER=1;
	//public static Integer CUST_TYPE_VENDOR=2;
	public static String CUST_UPDATED_BY="CUST_ONLINE";
				
	public static MobileVerificationDetailsKey standardMobileVerificationDetailsKey()
	{
		return new MobileVerificationDetailsKey(CUST_ID, CUST_TYPE_CUSTOMER, CUST_MOBILE);
	}
	
	public static MobileVerificationDetails standardCustomerMobileVerificationDetails()
	{
		return new MobileVerificationDetails(standardMobileVerificationDetailsKey(), CUST_MOBILE_TYPE_PRIMARY, CUST_MOBILEPIN, CUST_RESEND_COUNT, CUST_RESEND_COUNT, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
	}
	
	
	public static CustomerMobileVerificationDetailsByCustomerIdTypeAndMobileDTO standardCustomerMobileVerificationDetailsByCustomerIdAndMobileDTO()
	{
		return new CustomerMobileVerificationDetailsByCustomerIdTypeAndMobileDTO(CUST_ID,CUST_TYPE_CUSTOMER, CUST_MOBILE);
	}
	
	public static UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO standardUpdateMobilePinAndMobileVerificationAttemptsDTO()
	{
		return new UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO(CUST_ID,CUST_TYPE_CUSTOMER, CUST_MOBILE, CUST_MOBILEPIN_UPDATED, CUST_MOBILE_VERIFICATION_ATTEMPTS+1,CUST_RESEND_COUNT+1);
	}
	

	public static UpdateMobileVerificationAttemptsDTO standardUpdateMobileVerificationAttemptsDTO()
	{
		return new UpdateMobileVerificationAttemptsDTO(CUST_ID,CUST_TYPE_CUSTOMER, CUST_MOBILE, 1);
	}
	
	public static CustomerIdTypeMobileDTO standardCustomerIdMobileDTO()
	{
		return new CustomerIdTypeMobileDTO(CUST_ID,CUST_TYPE_CUSTOMER, CUST_MOBILE);
	}
	
	public static String standardJsonUpdateMobilePinDTOMVC()
	{
		return "{\"customerId\":"+CUST_ID+",\"customerType\":"+CUST_TYPE_CUSTOMER+",\"mobile\":"+CUST_MOBILE+"}";
	}
	
	
	public static String standardJsonVerifyMobilePinDTO()
	{
		return "{\"customerId\":"+CUST_ID+",\"customerType\":"+CUST_TYPE_CUSTOMER+",\"mobile\":"+CUST_MOBILE+",\"mobilePin\":"+CUST_MOBILEPIN+"}";
		   
		//return gson.toJson(standardJsonVerifyMobilePinDTO()); 
	}

	
}
