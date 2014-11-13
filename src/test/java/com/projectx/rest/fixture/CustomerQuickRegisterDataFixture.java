package com.projectx.rest.fixture;

import java.util.Date;

import com.projectx.data.domain.UpdateEmailHashDTO;
import com.projectx.data.domain.UpdateEmailMobileVerificationStatus;
import com.projectx.data.domain.UpdateMobilePinDTO;
import com.projectx.rest.domain.CustomerEmailVerificationDetails;
import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.web.domain.CustomerQuickRegisterEntityDTO;
import com.projectx.web.domain.CustomerIdDTO;


public class CustomerQuickRegisterDataFixture {

	public static Long CUST_ID=212L;
	public static String CUST_FIRSTNAME="dinesh";
	public static String CUST_LASTNAME="shende";
	
	public static String CUST_EMAIL="dineshshe@gmail.com";
	public static Long CUST_MOBILE=9960821869L;
	
	public static Integer CUST_PIN_CODE=413133;
	
	public static Boolean CUST_IS_EMAIL_VERIFIED_FALSE=false;
	public static Boolean CUST_IS_MOBILE_VERIFIED_FALSE=false;
	public static Boolean CUST_IS_EMAIL_VERIFIED_TRUE=true;
	public static Boolean CUST_IS_MOBILE_VERIFIED_TRUE=true;
	
	public static Date CUST_INSERT_TIME=new Date();
	public static Date CUST_UPDATE_TIME=new Date();
	public static String CUST_UPDATED_BY="ONLINE_CUST";
	
	
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
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,CUST_MOBILE,CUST_PIN_CODE,CUST_IS_EMAIL_VERIFIED_FALSE,
				CUST_IS_MOBILE_VERIFIED_FALSE,CUST_INSERT_TIME,CUST_UPDATE_TIME,CUST_UPDATED_BY);
		
	}
	
	public static CustomerQuickRegisterEntity standardMobileCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME,CUST_LASTNAME,null,CUST_MOBILE,CUST_PIN_CODE,CUST_IS_EMAIL_VERIFIED_FALSE,
				CUST_IS_MOBILE_VERIFIED_FALSE,CUST_INSERT_TIME,CUST_UPDATE_TIME,CUST_UPDATED_BY);
	}
	
	public static CustomerQuickRegisterEntity standardEmailCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,null,CUST_PIN_CODE,CUST_IS_EMAIL_VERIFIED_FALSE,
				CUST_IS_MOBILE_VERIFIED_FALSE,CUST_INSERT_TIME,CUST_UPDATE_TIME,CUST_UPDATED_BY);
		
	}

	
	public static CustomerQuickRegisterEntityDTO standardEmailCustomerDTO()
	{
		return new CustomerQuickRegisterEntityDTO(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,null,CUST_PIN_CODE);
	}
	
	public static CustomerQuickRegisterEntityDTO standardMobileCustomerDTO()
	{
		return new CustomerQuickRegisterEntityDTO(CUST_FIRSTNAME,CUST_LASTNAME,null,CUST_MOBILE,CUST_PIN_CODE);
	}
	
	public static CustomerQuickRegisterEntityDTO standardEmailMobileCustomerDTO()
	{
		return new CustomerQuickRegisterEntityDTO(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,CUST_MOBILE,CUST_PIN_CODE);
	}
	
	
	public static CustomerQuickRegisterEntityDTO standardEmailCustomerDTOWithOutStatus()
	{
		return new CustomerQuickRegisterEntityDTO(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,null,CUST_PIN_CODE);
	}
	
	public static CustomerQuickRegisterEntityDTO standardMobileCustomerDTOWithOutStatus()
	{
		return new CustomerQuickRegisterEntityDTO(CUST_FIRSTNAME,CUST_LASTNAME,null,CUST_MOBILE,CUST_PIN_CODE);
	}
	
	public static CustomerQuickRegisterEntityDTO standardEmailMobileCustomerDTOWithOutStatus()
	{
		return new CustomerQuickRegisterEntityDTO(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,CUST_MOBILE,CUST_PIN_CODE);
	}
	
	
	public static CustomerQuickRegisterEntity standardEmailMobileCustomerAfterStatusPopulation()
	{
		return new CustomerQuickRegisterEntity(null, CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL, CUST_MOBILE, CUST_PIN_CODE, CUST_IS_EMAIL_VERIFIED_FALSE, CUST_IS_MOBILE_VERIFIED_FALSE, null, null, null);
	}
	
	public static CustomerQuickRegisterEntity standardMobileCustomerAfterStatusPopulation()
	{
		return new CustomerQuickRegisterEntity(null, CUST_FIRSTNAME, CUST_LASTNAME, null, CUST_MOBILE, CUST_PIN_CODE, null, CUST_IS_MOBILE_VERIFIED_FALSE, null, null, null);
	}
	
	public static CustomerQuickRegisterEntity standardEmailCustomerAfterStatusPopulation()
	{
		return new CustomerQuickRegisterEntity(null, CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL, null, CUST_PIN_CODE, CUST_IS_EMAIL_VERIFIED_FALSE, null, null, null, null);
		
	}
	
	public static CustomerQuickRegisterEntity standardEmailMobileCustomerAfterInitialization()
	{
		return new CustomerQuickRegisterEntity(null, CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL, CUST_MOBILE, CUST_PIN_CODE, CUST_IS_EMAIL_VERIFIED_FALSE, CUST_IS_MOBILE_VERIFIED_FALSE, CUST_INSERT_TIME, CUST_UPDATE_TIME, CUST_UPDATED_BY);
	}
	
	public static CustomerQuickRegisterEntity standardMobileCustomerAfterInitialization()
	{
		return new CustomerQuickRegisterEntity(null, CUST_FIRSTNAME, CUST_LASTNAME, null, CUST_MOBILE, CUST_PIN_CODE, null, CUST_IS_MOBILE_VERIFIED_FALSE, CUST_INSERT_TIME, CUST_UPDATE_TIME, CUST_UPDATED_BY);
	}
	
	public static CustomerQuickRegisterEntity standardEmailCustomerAfterInitialization()
	{
		return new CustomerQuickRegisterEntity(null, CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL, null, CUST_PIN_CODE, CUST_IS_EMAIL_VERIFIED_FALSE,null, CUST_INSERT_TIME, CUST_UPDATE_TIME, CUST_UPDATED_BY);
		
	}
		
	public static CustomerQuickRegisterEntity standardEmailMobileCustomerAfterSaving()
	{
		return new CustomerQuickRegisterEntity(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL, CUST_MOBILE, CUST_PIN_CODE, CUST_IS_EMAIL_VERIFIED_FALSE, CUST_IS_MOBILE_VERIFIED_FALSE, CUST_INSERT_TIME, CUST_UPDATE_TIME, CUST_UPDATED_BY);
	}
	
	public static CustomerQuickRegisterEntity standardMobileCustomerAfterSaving()
	{
		return new CustomerQuickRegisterEntity(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, null, CUST_MOBILE, CUST_PIN_CODE, null, CUST_IS_MOBILE_VERIFIED_FALSE, CUST_INSERT_TIME, CUST_UPDATE_TIME, CUST_UPDATED_BY);
	}
	
	public static CustomerQuickRegisterEntity standardEmailCustomerAfterSaving()
	{
		return new CustomerQuickRegisterEntity(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL, null, CUST_PIN_CODE, CUST_IS_EMAIL_VERIFIED_FALSE, null, CUST_INSERT_TIME, CUST_UPDATE_TIME, CUST_UPDATED_BY);
		
	}
	/*
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
*/
	

	

	public static UpdateEmailMobileVerificationStatus standardUpdateEmailMobileVerificationStatus()
	{
		return new UpdateEmailMobileVerificationStatus(CUST_ID, CUST_IS_EMAIL_VERIFIED_TRUE,new Date(),"TEST");
	}
	
	
	public static com.projectx.web.domain.UpdateEmailHashDTO standardUpdateEmailHashDTOMVC()
	{
		return new com.projectx.web.domain.UpdateEmailHashDTO(CUST_ID, CUST_EMAIL);
	}
	
	public static com.projectx.web.domain.UpdateMobilePinDTO standardUpdateMobilePinDTOMVC()
	{
		return new com.projectx.web.domain.UpdateMobilePinDTO(CUST_ID, CUST_MOBILE);
	}
	
	
	public static String standardJsonUpdateEmailHashDTOMVC()
	{
		return "{\"customerId\":"+CUST_ID+",\"email\":\""+CUST_EMAIL+"\"}";
	}
	
	public static String standardJsonUpdateMobilePinDTOMVC()
	{
		return "{\"customerId\":"+CUST_ID+",\"mobile\":"+CUST_MOBILE+"}";
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
		jsonBuilder.append("{\"customerId\":"+CUST_ID+",\"email\":\"");
		jsonBuilder.append(CUST_EMAIL);
		jsonBuilder.append("\",\"emailHash\":\""+CUST_EMAILHASH+"\"}");
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
		  
		//return gson.toJson(standardJsonVerifyEmailHashDTO());
	}
	
	public static String standardJsonVerifyMobilePinDTO()
	{
		return "{\"customerId\":"+CUST_ID+",\"mobile\":"+CUST_MOBILE+",\"mobilePin\":"+CUST_MOBILEPIN+"}";
		   
		//return gson.toJson(standardJsonVerifyMobilePinDTO()); 
	}

	
}
