package com.projectx.rest.fixture;

import com.projectx.data.domain.UpdateEmailHashDTO;
import com.projectx.data.domain.UpdateMobilePinDTO;
import com.projectx.data.domain.VerifyEmailHashDTO;
import com.projectx.data.domain.VerifyMobilePinDTO;
import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.web.domain.CustomerQuickRegisterEntityDTO;
import com.projectx.web.domain.GetByCustomerIdDTO;


public class CustomerQuickRegisterDataFixture {

	public static Long CUST_ID=212L;
	public static String CUST_FIRSTNAME="dinesh";
	public static String CUST_LASTNAME="shende";
	
	public static String CUST_EMAIL="dineshshe@gmail.com";
	public static Long CUST_MOBILE=9960821869L;
	
	public static Integer CUST_PIN=413133;
	
	public static String CUST_STATUS_EMAILMOBILE="EmailMobileVerificationPending";
	public static String CUST_STATUS_EMAIL="EmailVerificationPending";
	public static String CUST_STATUS_MOBILE="MobileVerificationPending";
	
	public static String STATUS_EMAIL_VERFIED_MOBILE_PENDING="EmailVerifiedMobileVerficationPending";
	public static String STATUS_MOBILE_VERFIED_EMAIL_PENDING="MobileVerifiedEmailVerficationPending";
	public static String STATUS_EMAIL_MOBILE_VERIFIED="EmailMobileVerified";
	public static String STATUS_MOBILE_VERFIED="MobileVerified";
	public static String STATUS_EMAIL_VERFIED="EmailVerified";
	
	public static String REGISTER_NOT_REGISTERED="NOT_REGISTERED";
	public static String REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED="EMAIL_MOBILE_ALREADY_REGISTERED";
	public static String REGISTER_MOBILE_ALREADY_REGISTERED="MOBILE_ALREADY_REGISTERED";
	public static String REGISTER_EMAIL_ALREADY_REGISTERED="EMAIL_ALREADY_REGISTERED";
	

	public static Integer CUST_MOBILEPIN=101010;
	public static Integer CUST_MOBILEPIN_UPDATED=102010;
	public static Long CUST_EMAILHASH=1010101010L;
	public static Long CUST_EMAILHASH_UPDATED=1020102010L;

	
	public static CustomerQuickRegisterEntity standardEmailMobileCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME,CUST_EMAIL,CUST_MOBILE, CUST_PIN,CUST_STATUS_EMAILMOBILE, CUST_MOBILEPIN, CUST_EMAILHASH);
	}
	
	public static CustomerQuickRegisterEntity standardMobileCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME,null,CUST_MOBILE, CUST_PIN,CUST_STATUS_MOBILE, CUST_MOBILEPIN, null);
		
	}
	
	public static CustomerQuickRegisterEntity standardEmailCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME,CUST_EMAIL,null, CUST_PIN,CUST_STATUS_EMAIL, null, CUST_EMAILHASH);
		
	}

	public static CustomerQuickRegisterEntity standardEmailMobileCustomerWOCustoemrId()
	{
		return new CustomerQuickRegisterEntity(null,CUST_FIRSTNAME, CUST_LASTNAME,CUST_EMAIL,CUST_MOBILE, CUST_PIN,CUST_STATUS_EMAILMOBILE, CUST_MOBILEPIN, CUST_EMAILHASH);
	}
	
	public static CustomerQuickRegisterEntity standardMobileCustomerWOCustoemrId()
	{
		return new CustomerQuickRegisterEntity(null,CUST_FIRSTNAME, CUST_LASTNAME,null,CUST_MOBILE, CUST_PIN,CUST_STATUS_MOBILE, CUST_MOBILEPIN, null);
		
	}
	
	public static CustomerQuickRegisterEntity standardEmailCustomerWOCustoemrId()
	{
		return new CustomerQuickRegisterEntity(null,CUST_FIRSTNAME, CUST_LASTNAME,CUST_EMAIL,null, CUST_PIN,CUST_STATUS_EMAIL, null, CUST_EMAILHASH);
		
	}
	
	
	public static CustomerQuickRegisterEntityDTO standardEmailCustomerDTO()
	{
		return new CustomerQuickRegisterEntityDTO(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,null,CUST_PIN,CUST_STATUS_EMAIL);
	}
	
	public static CustomerQuickRegisterEntityDTO standardMobileCustomerDTO()
	{
		return new CustomerQuickRegisterEntityDTO(CUST_FIRSTNAME,CUST_LASTNAME,null,CUST_MOBILE,CUST_PIN,CUST_STATUS_MOBILE);
	}
	
	public static CustomerQuickRegisterEntityDTO standardEmailMobileCustomerDTO()
	{
		return new CustomerQuickRegisterEntityDTO(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,CUST_MOBILE,CUST_PIN,CUST_STATUS_EMAILMOBILE);
	}
	
	
	public static CustomerQuickRegisterEntityDTO standardEmailCustomerDTOWithOutStatus()
	{
		return new CustomerQuickRegisterEntityDTO(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,null,CUST_PIN,null);
	}
	
	public static CustomerQuickRegisterEntityDTO standardMobileCustomerDTOWithOutStatus()
	{
		return new CustomerQuickRegisterEntityDTO(CUST_FIRSTNAME,CUST_LASTNAME,null,CUST_MOBILE,CUST_PIN,null);
	}
	
	public static CustomerQuickRegisterEntityDTO standardEmailMobileCustomerDTOWithOutStatus()
	{
		return new CustomerQuickRegisterEntityDTO(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,CUST_MOBILE,CUST_PIN,null);
	}
	

	public static String standardJsonEmailMobileCustomer()
	{
		return "{\"firstName\":\"dinesh\",\"lastName\":\"shende\",\"email\":\"dineshshe@gmail.com\",\"mobile\":9960821869,\"pin\":413133}";
		//return "{\"firstName\":\"dinesh\",\"lastName\":\"shende\",\"email\":\"dineshshe@gmail.com\",\"mobile\":\"9960821869\",\"pin\":\"413133\",\"status\":\"\"}";
		        	
	}
	
	public static String standardJsonEmailCustomer()
	{
		return "{\"firstName\":\"dinesh\",\"lastName\":\"shende\",\"email\":\"dineshshe@gmail.com\",\"pin\":413133}";
	}
	
	public static String standardJsonEmailCustomerForEmailVerification()
	{
		return "{\"firstName\":\"dinesh\",\"lastName\":\"shende\",\"email\":\"dineshshe\",\"pin\":413133}";
	}
	
	public static String standardJsonMobileCustomer()
	{
		return "{\"firstName\":\"dinesh\",\"lastName\":\"shende\",\"mobile\":9960821869,\"pin\":413133}";
	}
	
	public static GetByCustomerIdDTO standardGetByCustomerIdDTO()
	{
		return new GetByCustomerIdDTO(CUST_ID);
	}
	
	
	public static VerifyMobilePinDTO standardVerifyMobilePinDTO()
	{
		return new VerifyMobilePinDTO(CUST_ID, CUST_MOBILEPIN);
	}

	
	public static VerifyEmailHashDTO standardVerifyEmailHashDTO()
	{
		return new VerifyEmailHashDTO(CUST_ID, CUST_EMAILHASH);
	}


	public static UpdateMobilePinDTO standardUpdateMobilePinDTO()
	{
		return new UpdateMobilePinDTO(CUST_ID, CUST_MOBILEPIN);
	}

	
	public static UpdateEmailHashDTO standardUpdateEmailHashDTO()
	{
		return new UpdateEmailHashDTO(CUST_ID, CUST_EMAILHASH);
	}
	
	public static String standardJsonGetByCustomerIdDTO()
	{
		return "{\"customerId\":212}";
	}
	
	public static String standardJsonVerifyEmailHashDTO()
	{
		return "{\"customerId\":212,\"emailHash\":1010101010}";
		       
	}
	
	public static String standardJsonVerifyMobilePinDTO()
	{
		return "{\"customerId\":212,\"mobilePin\":101010}";
		       
	}
	
	public static String standardJsonUpdateEmailHashDTO()
	{
		return "{\"customerId\":212,\"emailHash\":1020102010}";
		       
	}
	
	public static String standardJsonUpdateMobilePinDTO()
	{
		return "{\"customerId\":212,\"mobilePin\":102010}";
		       
	}
	
}
