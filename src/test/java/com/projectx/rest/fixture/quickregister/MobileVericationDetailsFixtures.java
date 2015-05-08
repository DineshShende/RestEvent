package com.projectx.rest.fixture.quickregister;

import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;

import java.util.Date;

import com.google.gson.Gson;
import com.projectx.data.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeMobileTypeRequestedByDTO;
import com.projectx.data.domain.quickregister.CustomerMobileVerificationDetailsByCustomerIdTypeAndMobileTypeDTO;
import com.projectx.data.domain.quickregister.MobileDTO;
import com.projectx.data.domain.quickregister.SendResendResetMobilePinDTO;
import com.projectx.data.domain.quickregister.UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO;
import com.projectx.data.domain.quickregister.UpdateMobileVerificationAttemptsDTO;
import com.projectx.mvc.domain.quickregister.VerifyMobileDTO;
import com.projectx.mvc.domain.quickregister.VerifyMobilePinDTO;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsKey;

public class MobileVericationDetailsFixtures {

	
	public static Integer CUST_MOBILE_TYPE_PRIMARY=1;
	public static Integer CUST_MOBILE_TYPE_SECONDARY=2;
	public static Integer CUST_RESEND_COUNT=0;
	
	public static Date CUST_DATE=new Date();
	//public static Integer CUST_TYPE_CUSTOMER=1;
	//public static Integer CUST_TYPE_VENDOR=2;
	
	static Gson gson=new Gson();
	
				
	public static MobileVerificationDetailsKey standardMobileVerificationDetailsKey()
	{
		return new MobileVerificationDetailsKey(CUST_ID, ENTITY_TYPE_CUSTOMER, CUST_MOBILE_TYPE_PRIMARY);
	}
	
	public static MobileVerificationDetails standardCustomerMobileVerificationDetails()
	{
		return new MobileVerificationDetails(standardMobileVerificationDetailsKey(), CUST_MOBILE, CUST_MOBILEPIN, CUST_RESEND_COUNT,
				CUST_RESEND_COUNT, CUST_DATE, CUST_DATE, CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
	}
	
	public static MobileVerificationDetails standardCustomerMobileVerificationDetailsNull()
	{
		return new MobileVerificationDetails(standardMobileVerificationDetailsKey(), CUST_MOBILE, null, CUST_RESEND_COUNT,
				CUST_RESEND_COUNT, CUST_DATE, CUST_DATE, CUST_UPDATED_BY,CUST_UPDATED_BY,CUST_ID,CUST_ID);
	}
	
	
	public static CustomerMobileVerificationDetailsByCustomerIdTypeAndMobileTypeDTO standardCustomerMobileVerificationDetailsByCustomerIdAndMobileDTO()
	{
		return new CustomerMobileVerificationDetailsByCustomerIdTypeAndMobileTypeDTO(CUST_ID,ENTITY_TYPE_CUSTOMER, CUST_MOBILE_TYPE_PRIMARY);
	}
	
	public static UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO standardUpdateMobilePinAndMobileVerificationAttemptsDTO()
	{
		return new UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO(CUST_ID,ENTITY_TYPE_CUSTOMER, CUST_MOBILE_TYPE_PRIMARY, CUST_MOBILEPIN_UPDATED, CUST_MOBILE_VERIFICATION_ATTEMPTS+1,
				CUST_RESEND_COUNT+1,CUST_UPDATED_BY,CUST_ID);
	}
	

	public static UpdateMobileVerificationAttemptsDTO standardUpdateMobileVerificationAttemptsDTO()
	{
		return new UpdateMobileVerificationAttemptsDTO(CUST_ID,ENTITY_TYPE_CUSTOMER, CUST_MOBILE, 1);
	}
	
	public static CustomerIdTypeMobileTypeDTO standardCustomerIdMobileDTO()
	{
		return new CustomerIdTypeMobileTypeDTO(CUST_ID,ENTITY_TYPE_CUSTOMER, CUST_MOBILE_TYPE_PRIMARY);
	}
	
	public static CustomerIdTypeMobileTypeRequestedByDTO standardCustomerIdTypeMobileTypeUpdatedByDTO()
	{
		return new CustomerIdTypeMobileTypeRequestedByDTO(CUST_ID,ENTITY_TYPE_CUSTOMER, CUST_MOBILE_TYPE_PRIMARY,CUST_UPDATED_BY,CUST_ID);
	}
	
	public static String standardJsonUpdateMobilePinDTOMVC()
	{
		return "{\"customerId\":"+CUST_ID+",\"customerType\":"+ENTITY_TYPE_CUSTOMER+",\"mobileType\":"+CUST_MOBILE_TYPE_PRIMARY+"}";
	}
	
	
	
	public static String standardJsonUpdateMobilePinUpdatedByDTOMVC(CustomerIdTypeMobileTypeRequestedByDTO dto)
	{
		return gson.toJson(dto);
	}
	
	
	public static String standardJsonVerifyMobilePinDTO(VerifyMobilePinDTO verifyMobilePinDTO)
	{
		return gson.toJson(verifyMobilePinDTO); 
	}

	public static String standardJsonSendResendResetMobilePinDTO(SendResendResetMobilePinDTO verifyMobilePinDTO)
	{
		return gson.toJson(verifyMobilePinDTO); 
	}
	
}
