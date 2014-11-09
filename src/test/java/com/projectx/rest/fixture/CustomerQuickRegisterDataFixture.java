package com.projectx.rest.fixture;

import java.util.Date;

import com.projectx.data.domain.UpdateEmailHashDTO;
import com.projectx.data.domain.UpdateMobilePinDTO;
import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.web.domain.CustomerQuickRegisterEntityDTO;
import com.projectx.web.domain.CustomerIdDTO;


public class CustomerQuickRegisterDataFixture {

	public static Long CUST_ID=212L;
	public static String CUST_FIRSTNAME="dinesh";
	public static String CUST_LASTNAME="shende";
	
	public static String CUST_EMAIL="dineshshe@gmail.com";
	public static Long CUST_MOBILE=9960821869L;
	
	public static Integer CUST_PIN=413133;
	
	public static Integer CUST_MOBILE_VERIFICATION_ATTEMPTS=0;
	public static Date CUST_MOBILE_PIN_SENT_TIME=new Date();
	public static Date CUST_EMAIL_HASH_SENT_TIME=new Date();
	public static Date CUST_LAST_STATUS_CHANGE_TIME=new Date();
	
	public static String CUST_PASSWORD_DEFAULT="123456";
	public static String CUST_PASSWORD_TYPE_DEFAULT="Default";
	public static String CUST_PASSWORD_CHANGED="654321";
	public static String CUST_PASSWORD_TYPE_CHANGED="Changed";
	
	
	
	public static String CUST_STATUS_EMAILMOBILE="EmailMobileVerificationPending";
	public static String CUST_STATUS_EMAIL="EmailVerificationPending";
	public static String CUST_STATUS_MOBILE="MobileVerificationPending";
	
	public static String STATUS_EMAIL_VERFIED_MOBILE_PENDING="EmailVerifiedMobileVerficationPending";
	public static String STATUS_MOBILE_VERFIED_EMAIL_PENDING="MobileVerifiedEmailVerficationPending";
	public static String STATUS_EMAIL_MOBILE_VERIFIED="EmailMobileVerified";
	public static String STATUS_MOBILE_VERFIED="MobileVerified";
	public static String STATUS_EMAIL_VERFIED="EmailVerified";
	
	public static String REGISTER_NOT_REGISTERED="NOT_REGISTERED";
	public static String REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_UNVERIFIED="EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_UNVERIFIED";
	public static String REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_VERIFIED="EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_VERIFIED";
	public static String REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_MOBILE_VERIFIED="EMAIL_MOBILE_ALREADY_REGISTERED_MOBILE_VERIFIED";
	public static String REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_VERIFIED="EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_VERIFIED";
	public static String REGISTER_MOBILE_ALREADY_REGISTERED_NOT_VERIFIED="MOBILE_ALREADY_REGISTERED_NOT_VERIFIED";
	public static String REGISTER_MOBILE_ALREADY_REGISTERED_VERIFIED="MOBILE_ALREADY_REGISTERED_VERIFIED";
	public static String REGISTER_EMAIL_ALREADY_REGISTERED_NOT_VERIFIED="EMAIL_ALREADY_REGISTERED_NOT_VERIFIED";
	public static String REGISTER_EMAIL_ALREADY_REGISTERED_VERIFIED="EMAIL_ALREADY_REGISTERED_VERIFIED";
	
	
	

	public static Integer CUST_MOBILEPIN=101010;
	public static Integer CUST_MOBILEPIN_UPDATED=102010;
	public static String CUST_EMAILHASH="02b99c87926ed36ed1b41afccf9b05f9efd6e54e6e9d116b8ed3a7eaf257b85a";
	public static String CUST_EMAILHASH_UPDATED="277f7fb2ede95f935b08c63273471c9077ace61f1cbb1f376b2983032fda5321";

	
	public static CustomerQuickRegisterEntity standardEmailMobileCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME,CUST_EMAIL,CUST_MOBILE, CUST_PIN,CUST_STATUS_EMAILMOBILE, CUST_MOBILEPIN, CUST_EMAILHASH,CUST_MOBILE_VERIFICATION_ATTEMPTS,
																															CUST_MOBILE_PIN_SENT_TIME,CUST_EMAIL_HASH_SENT_TIME,CUST_LAST_STATUS_CHANGE_TIME,null,null);
	}
	
	public static CustomerQuickRegisterEntity standardMobileCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME,null,CUST_MOBILE, CUST_PIN,CUST_STATUS_MOBILE, CUST_MOBILEPIN, null,CUST_MOBILE_VERIFICATION_ATTEMPTS,
				CUST_MOBILE_PIN_SENT_TIME,null,CUST_LAST_STATUS_CHANGE_TIME,null,null);
	}
	
	public static CustomerQuickRegisterEntity standardEmailCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME,CUST_EMAIL,null, CUST_PIN,CUST_STATUS_EMAIL, null, CUST_EMAILHASH,null,
				null,CUST_EMAIL_HASH_SENT_TIME,CUST_LAST_STATUS_CHANGE_TIME,null,null);
		
	}

	
	public static CustomerQuickRegisterEntity standardEmailMobileCustomerAfterStatusPopulation()
	{
		return new CustomerQuickRegisterEntity(null,CUST_FIRSTNAME, CUST_LASTNAME,CUST_EMAIL,CUST_MOBILE, CUST_PIN,CUST_STATUS_EMAILMOBILE, null, null,null,
				null,null,new Date(),null,null);
	}
	
	public static CustomerQuickRegisterEntity standardMobileCustomerAfterStatusPopulation()
	{
		return new CustomerQuickRegisterEntity(null,CUST_FIRSTNAME, CUST_LASTNAME,null,CUST_MOBILE, CUST_PIN,CUST_STATUS_MOBILE, null, null,null,
				null,null,new Date(),null,null);
	}
	
	public static CustomerQuickRegisterEntity standardEmailCustomerAfterStatusPopulation()
	{
		return new CustomerQuickRegisterEntity(null,CUST_FIRSTNAME, CUST_LASTNAME,CUST_EMAIL,null, CUST_PIN,CUST_STATUS_EMAIL, null, null, null,
				null,null,new Date(),null,null);
		
	}
	
	public static CustomerQuickRegisterEntity standardEmailMobileCustomerAfterInitialization()
	{
		return new CustomerQuickRegisterEntity(null,CUST_FIRSTNAME, CUST_LASTNAME,CUST_EMAIL,CUST_MOBILE, CUST_PIN,CUST_STATUS_EMAILMOBILE, CUST_MOBILEPIN, CUST_EMAILHASH,CUST_MOBILE_VERIFICATION_ATTEMPTS,
				null,null,new Date(),null,null);
	}
	
	public static CustomerQuickRegisterEntity standardMobileCustomerAfterInitialization()
	{
		return new CustomerQuickRegisterEntity(null,CUST_FIRSTNAME, CUST_LASTNAME,null,CUST_MOBILE, CUST_PIN,CUST_STATUS_MOBILE, CUST_MOBILEPIN, null,CUST_MOBILE_VERIFICATION_ATTEMPTS,
				null,null,new Date(),null,null);
	}
	
	public static CustomerQuickRegisterEntity standardEmailCustomerAfterStatusInitialization()
	{
		return new CustomerQuickRegisterEntity(null,CUST_FIRSTNAME, CUST_LASTNAME,CUST_EMAIL,null, CUST_PIN,CUST_STATUS_EMAIL, null, CUST_EMAILHASH, null,
				null,null,new Date(),null,null);
		
	}
	
	public static CustomerQuickRegisterEntity standardEmailMobileCustomerAfterSaving()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME,CUST_EMAIL,CUST_MOBILE, CUST_PIN,CUST_STATUS_EMAILMOBILE, CUST_MOBILEPIN, CUST_EMAILHASH,CUST_MOBILE_VERIFICATION_ATTEMPTS,
				null,null,new Date(),null,null);
	}
	
	public static CustomerQuickRegisterEntity standardMobileCustomerAfterSaving()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME,null,CUST_MOBILE, CUST_PIN,CUST_STATUS_MOBILE, CUST_MOBILEPIN, null,CUST_MOBILE_VERIFICATION_ATTEMPTS,
				null,null,new Date(),null,null);
	}
	
	public static CustomerQuickRegisterEntity standardEmailCustomerAfterSaving()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME,CUST_EMAIL,null, CUST_PIN,CUST_STATUS_EMAIL, null, CUST_EMAILHASH, null,
				null,null,new Date(),null,null);
		
	}
	
	public static CustomerQuickRegisterEntity standardEmailMobileCustomerAfterVerificatinDetailsSent()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME,CUST_EMAIL,CUST_MOBILE, CUST_PIN,CUST_STATUS_EMAILMOBILE, CUST_MOBILEPIN, CUST_EMAILHASH,CUST_MOBILE_VERIFICATION_ATTEMPTS,
																															CUST_MOBILE_PIN_SENT_TIME,CUST_EMAIL_HASH_SENT_TIME,CUST_LAST_STATUS_CHANGE_TIME,null,null);
	}
	
	public static CustomerQuickRegisterEntity standardMobileCustomerAfterVerificatinDetailsSent()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME,null,CUST_MOBILE, CUST_PIN,CUST_STATUS_MOBILE, CUST_MOBILEPIN, null,CUST_MOBILE_VERIFICATION_ATTEMPTS,
				CUST_MOBILE_PIN_SENT_TIME,null,CUST_LAST_STATUS_CHANGE_TIME,null,null);
	}
	
	public static CustomerQuickRegisterEntity standardEmailCustomerAfterVerificatinDetailsSent()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME,CUST_EMAIL,null, CUST_PIN,CUST_STATUS_EMAIL, null, CUST_EMAILHASH,null,
				null,CUST_EMAIL_HASH_SENT_TIME,CUST_LAST_STATUS_CHANGE_TIME,null,null);
		
	}

	
	/*
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
	*/
	
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
	
	public static String standardJsonEmailMobileCustomerOther()
	{
		return "{\"firstName\":\"dinesh\",\"lastName\":\"shende\",\"email\":\"dineshshende@gmail.com\",\"mobile\":8598058044,\"pin\":413133}";

		        	
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
	
	public static CustomerIdDTO standardGetByCustomerIdDTO()
	{
		return new CustomerIdDTO(CUST_ID);
	}
	
/*	
	public static VerifyMobilePinDTO standardVerifyMobilePinDTO()
	{
		return new VerifyMobilePinDTO(CUST_ID, CUST_MOBILEPIN);
	}

	
	public static VerifyEmailHashDTO standardVerifyEmailHashDTO()
	{
		return new VerifyEmailHashDTO(CUST_ID, CUST_EMAILHASH);
	}
*/
	public static String standardJsonGetByCustomerIdDTO()
	{
		return "{\"customerId\":212}";
	}
	
	public static String standardJsonCustomerIdDTO()
	{
		return "{\"customerId\":212}";
	}
	
	public static String standardJsonResetPasswordRedirect(String entity)
	{
		System.out.println("{\"entity\":\""+entity+"\"}");
		
		return "{\"entity\":"+entity+"}";
	}
	
	
	public static String standardJsonVerifyEmailHashDTO()
	{
		StringBuilder jsonBuilder=new StringBuilder();
		jsonBuilder.append("{\"customerId\":212,\"emailHash\":\"");
		jsonBuilder.append(CUST_EMAILHASH);
		jsonBuilder.append("\"}");
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
		       
	}
	
	public static String standardJsonVerifyMobilePinDTO()
	{
		return "{\"customerId\":212,\"mobilePin\":101010}";
		       
	}
	/*
	public static String standardJsonUpdateEmailHashDTO()
	{
		return "{\"customerId\":212,\"emailHash\":1020102010}";
		       
	}
	
	public static String standardJsonUpdateMobilePinDTO()
	{
		return "{\"customerId\":212,\"mobilePin\":102010}";
		       
	}
	*/

	/*
	public static UpdateMobilePinDTO standardUpdateMobilePinDTO()
	{
		return new UpdateMobilePinDTO(CUST_ID, CUST_MOBILEPIN);
	}

	
	public static UpdateEmailHashDTO standardUpdateEmailHashDTO()
	{
		return new UpdateEmailHashDTO(CUST_ID, CUST_EMAILHASH);
	}
*/	

	
}
