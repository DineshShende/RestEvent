package com.projectx.rest.fixture.quickregister;

import java.util.Date;

import com.google.gson.Gson;
import com.projectx.data.domain.quickregister.UpdateEmailHashDTO;
import com.projectx.data.domain.quickregister.UpdateEmailMobileVerificationStatus;
import com.projectx.data.domain.quickregister.UpdateMobilePinDTO;
import com.projectx.mvc.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.mvc.domain.quickregister.CustomerIdTypeUpdatedByDTO;
import com.projectx.mvc.domain.quickregister.CustomerQuickRegisterEntityDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;


public class QuickRegisterDataFixture {

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
	public static String CUST_UPDATED_BY="CUST_ONLINE";
	
	
	public static Integer CUST_MOBILE_VERIFICATION_ATTEMPTS=0;
	public static Date CUST_MOBILE_PIN_SENT_TIME=new Date();
	public static Date CUST_EMAIL_HASH_SENT_TIME=new Date();
	public static Date CUST_LAST_STATUS_CHANGE_TIME=new Date();
	
	public static String CUST_PASSWORD_DEFAULT="123456";
	public static String CUST_PASSWORD_TYPE_DEFAULT="Default";
	public static String CUST_PASSWORD_CHANGED="654321";
	public static String CUST_PASSWORD_TYPE_CHANGED="Changed";
	
	public static Long VENDOR_MOBILE=8625867370L;
	
	public static String VENDOR_EMAIL="tedmosby@gmail.com";
		
	
	
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
	
	private static Gson gson=new Gson();
	

	public static Integer CUST_MOBILEPIN=101010;
	public static Integer CUST_MOBILEPIN_UPDATED=102010;
	public static String CUST_EMAILHASH="02b99c87926ed36ed1b41afccf9b05f9efd6e54e6e9d116b8ed3a7eaf257b85a";
	public static String CUST_EMAILHASH_UPDATED="277f7fb2ede95f935b08c63273471c9077ace61f1cbb1f376b2983032fda5321";
	
	public static Integer ENTITY_TYPE_CUSTOMER=1;
	public static Integer ENTITY_TYPE_VENDOR=2;
	
	public static Integer MOB_TYPE_PRIMARY=1;
	
	public static Integer EMAIL_TYPE_PRIMARY=1;

	public static Integer MOB_TYPE_SECONDARY=2;
	
	

	
	public static QuickRegisterEntity standardEmailMobileCustomer()
	{
		return new QuickRegisterEntity(CUST_ID,CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,CUST_MOBILE,CUST_PIN_CODE,CUST_IS_EMAIL_VERIFIED_FALSE,
				CUST_IS_MOBILE_VERIFIED_FALSE,ENTITY_TYPE_CUSTOMER,CUST_INSERT_TIME,CUST_UPDATE_TIME,CUST_UPDATED_BY);
		
	}
	
	public static QuickRegisterEntity standardEmailMobileCustomerWithError()
	{
		return new QuickRegisterEntity(CUST_ID,CUST_FIRSTNAME,CUST_LASTNAME,null,null,CUST_PIN_CODE,CUST_IS_EMAIL_VERIFIED_FALSE,
				CUST_IS_MOBILE_VERIFIED_FALSE,ENTITY_TYPE_CUSTOMER,CUST_INSERT_TIME,CUST_UPDATE_TIME,CUST_UPDATED_BY);
		
	}
	
	public static QuickRegisterEntity standardMobileCustomer()
	{
		return new QuickRegisterEntity(CUST_ID,CUST_FIRSTNAME,CUST_LASTNAME,null,CUST_MOBILE,CUST_PIN_CODE,CUST_IS_EMAIL_VERIFIED_FALSE,
				CUST_IS_MOBILE_VERIFIED_FALSE,ENTITY_TYPE_CUSTOMER,CUST_INSERT_TIME,CUST_UPDATE_TIME,CUST_UPDATED_BY);
	}
	
	public static QuickRegisterEntity standardEmailCustomer()
	{
		return new QuickRegisterEntity(CUST_ID,CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,null,CUST_PIN_CODE,CUST_IS_EMAIL_VERIFIED_FALSE,
				CUST_IS_MOBILE_VERIFIED_FALSE,ENTITY_TYPE_CUSTOMER,CUST_INSERT_TIME,CUST_UPDATE_TIME,CUST_UPDATED_BY);
		
	}

	
	public static QuickRegisterEntity standardEmailMobileVendor()
	{
		return new QuickRegisterEntity(CUST_ID,CUST_FIRSTNAME,CUST_LASTNAME,VENDOR_EMAIL,VENDOR_MOBILE,CUST_PIN_CODE,CUST_IS_EMAIL_VERIFIED_FALSE,
				CUST_IS_MOBILE_VERIFIED_FALSE,ENTITY_TYPE_VENDOR,CUST_INSERT_TIME,CUST_UPDATE_TIME,CUST_UPDATED_BY);
		
	}
	
	public static QuickRegisterEntity standardMobileVendor()
	{
		return new QuickRegisterEntity(CUST_ID,CUST_FIRSTNAME,CUST_LASTNAME,null,VENDOR_MOBILE,CUST_PIN_CODE,CUST_IS_EMAIL_VERIFIED_FALSE,
				CUST_IS_MOBILE_VERIFIED_FALSE,ENTITY_TYPE_VENDOR,CUST_INSERT_TIME,CUST_UPDATE_TIME,CUST_UPDATED_BY);
	}
	
	public static QuickRegisterEntity standardEmailVendor()
	{
		return new QuickRegisterEntity(CUST_ID,CUST_FIRSTNAME,CUST_LASTNAME,VENDOR_EMAIL,null,CUST_PIN_CODE,CUST_IS_EMAIL_VERIFIED_FALSE,
				CUST_IS_MOBILE_VERIFIED_FALSE,ENTITY_TYPE_VENDOR,CUST_INSERT_TIME,CUST_UPDATE_TIME,CUST_UPDATED_BY);
		
	}

	
	public static CustomerQuickRegisterEntityDTO standardEmailCustomerDTO()
	{
		return new CustomerQuickRegisterEntityDTO(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,null,CUST_PIN_CODE,ENTITY_TYPE_CUSTOMER,CUST_UPDATED_BY);
	}
	
	public static CustomerQuickRegisterEntityDTO standardMobileCustomerDTO()
	{
		return new CustomerQuickRegisterEntityDTO(CUST_FIRSTNAME,CUST_LASTNAME,null,CUST_MOBILE,CUST_PIN_CODE,ENTITY_TYPE_CUSTOMER,CUST_UPDATED_BY);
	}
	
	public static CustomerQuickRegisterEntityDTO standardEmailMobileCustomerDTO()
	{
		return new CustomerQuickRegisterEntityDTO(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,CUST_MOBILE,CUST_PIN_CODE,ENTITY_TYPE_CUSTOMER,CUST_UPDATED_BY);
	}
	
	
	public static CustomerQuickRegisterEntityDTO standardEmailCustomerDTOWithOutStatus()
	{
		return new CustomerQuickRegisterEntityDTO(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,null,CUST_PIN_CODE,ENTITY_TYPE_CUSTOMER,CUST_UPDATED_BY);
	}
	
	public static CustomerQuickRegisterEntityDTO standardMobileCustomerDTOWithOutStatus()
	{
		return new CustomerQuickRegisterEntityDTO(CUST_FIRSTNAME,CUST_LASTNAME,null,CUST_MOBILE,CUST_PIN_CODE,ENTITY_TYPE_CUSTOMER,CUST_UPDATED_BY);
	}
	
	public static CustomerQuickRegisterEntityDTO standardEmailMobileCustomerDTOWithOutStatus()
	{
		return new CustomerQuickRegisterEntityDTO(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,CUST_MOBILE,CUST_PIN_CODE,ENTITY_TYPE_CUSTOMER,CUST_UPDATED_BY);
	}
	
	
	public static QuickRegisterEntity standardEmailMobileCustomerAfterStatusPopulation()
	{
		return new QuickRegisterEntity(null, CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL, CUST_MOBILE, CUST_PIN_CODE, CUST_IS_EMAIL_VERIFIED_FALSE, CUST_IS_MOBILE_VERIFIED_FALSE,ENTITY_TYPE_CUSTOMER, new Date(), new Date(),  CUST_UPDATED_BY);
	}
	
	public static QuickRegisterEntity standardMobileCustomerAfterStatusPopulation()
	{
		return new QuickRegisterEntity(null, CUST_FIRSTNAME, CUST_LASTNAME, null, CUST_MOBILE, CUST_PIN_CODE, null, CUST_IS_MOBILE_VERIFIED_FALSE,ENTITY_TYPE_CUSTOMER, new Date(), new Date(), CUST_UPDATED_BY);
	}
	
	public static QuickRegisterEntity standardEmailCustomerAfterStatusPopulation()
	{
		return new QuickRegisterEntity(null, CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL, null, CUST_PIN_CODE, CUST_IS_EMAIL_VERIFIED_FALSE, null,ENTITY_TYPE_CUSTOMER, new Date(), new Date(),  CUST_UPDATED_BY);
		
	}
	
	public static QuickRegisterEntity standardEmailMobileCustomerAfterInitialization()
	{
		return new QuickRegisterEntity(null, CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL, CUST_MOBILE, CUST_PIN_CODE, CUST_IS_EMAIL_VERIFIED_FALSE, CUST_IS_MOBILE_VERIFIED_FALSE,ENTITY_TYPE_CUSTOMER, CUST_INSERT_TIME, CUST_UPDATE_TIME, CUST_UPDATED_BY);
	}
	
	public static QuickRegisterEntity standardMobileCustomerAfterInitialization()
	{
		return new QuickRegisterEntity(null, CUST_FIRSTNAME, CUST_LASTNAME, null, CUST_MOBILE, CUST_PIN_CODE, null, CUST_IS_MOBILE_VERIFIED_FALSE,ENTITY_TYPE_CUSTOMER, CUST_INSERT_TIME, CUST_UPDATE_TIME, CUST_UPDATED_BY);
	}
	
	public static QuickRegisterEntity standardEmailCustomerAfterInitialization()
	{
		return new QuickRegisterEntity(null, CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL, null, CUST_PIN_CODE, CUST_IS_EMAIL_VERIFIED_FALSE,null,ENTITY_TYPE_CUSTOMER, CUST_INSERT_TIME, CUST_UPDATE_TIME, CUST_UPDATED_BY);
		
	}
		
	public static QuickRegisterEntity standardEmailMobileCustomerAfterSaving()
	{
		return new QuickRegisterEntity(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL, CUST_MOBILE, CUST_PIN_CODE, CUST_IS_EMAIL_VERIFIED_FALSE, CUST_IS_MOBILE_VERIFIED_FALSE,ENTITY_TYPE_CUSTOMER, CUST_INSERT_TIME, CUST_UPDATE_TIME, CUST_UPDATED_BY);
	}
	
	public static QuickRegisterEntity standardMobileCustomerAfterSaving()
	{
		return new QuickRegisterEntity(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, null, CUST_MOBILE, CUST_PIN_CODE, null, CUST_IS_MOBILE_VERIFIED_FALSE,ENTITY_TYPE_CUSTOMER, CUST_INSERT_TIME, CUST_UPDATE_TIME, CUST_UPDATED_BY);
	}
	
	public static QuickRegisterEntity standardEmailCustomerAfterSaving()
	{
		return new QuickRegisterEntity(CUST_ID, CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL, null, CUST_PIN_CODE, CUST_IS_EMAIL_VERIFIED_FALSE, null,ENTITY_TYPE_CUSTOMER, CUST_INSERT_TIME, CUST_UPDATE_TIME, CUST_UPDATED_BY);
		
	}


	public static UpdateEmailMobileVerificationStatus standardUpdateEmailMobileVerificationStatus()
	{
		return new UpdateEmailMobileVerificationStatus(CUST_ID, CUST_IS_EMAIL_VERIFIED_TRUE,new Date(),"TEST");
	}
	
	
	public static com.projectx.mvc.domain.quickregister.UpdateEmailHashDTO standardUpdateEmailHashDTOMVC()
	{
		return new com.projectx.mvc.domain.quickregister.UpdateEmailHashDTO(CUST_ID,ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_CUSTOMER,CUST_UPDATED_BY);
	}
	
	public static com.projectx.mvc.domain.quickregister.UpdateMobilePinDTO standardUpdateMobilePinDTOMVC()
	{
		return new com.projectx.mvc.domain.quickregister.UpdateMobilePinDTO(CUST_ID,ENTITY_TYPE_CUSTOMER, ENTITY_TYPE_CUSTOMER);
	}
	
	
	
	public static String standardJsonEmailMobileCustomer()
	{
		System.out.println(gson.toJson(standardEmailMobileCustomerDTO()));
		
		return gson.toJson(standardEmailMobileCustomerDTO());
		        	
	}
	

	public static String standardJsonEmailCustomer()
	{
		System.out.println(gson.toJson(standardEmailCustomerDTO()));
		
		return gson.toJson(standardEmailCustomerDTO());
	
	}
		
	public static String standardJsonMobileCustomer()
	{
		System.out.println(gson.toJson(standardMobileCustomerDTO()));
		
		return gson.toJson(standardMobileCustomerDTO());
	
	}
	
	public static CustomerIdTypeDTO standardGetByCustomerIdDTO()
	{
		return new CustomerIdTypeDTO(CUST_ID,ENTITY_TYPE_CUSTOMER);
	}
	
	
	public static String standardJsonCustomerIdDTO()
	{
		return "{\"customerId\":"+CUST_ID+",\"customerType\":"+ENTITY_TYPE_CUSTOMER+"}";
	}
	
	public static String standardJsonCustomerIdUpdatedByDTO(CustomerIdTypeUpdatedByDTO dto)
	{
		//System.out.println("{\"customerId\":"+CUST_ID+",\"customerType\":"+ENTITY_TYPE_CUSTOMER+",\"requestedBy\":\""+CUST_UPDATED_BY+"\"}");
		
		//return "{\"customerId\":"+CUST_ID+",\"customerType\":"+ENTITY_TYPE_CUSTOMER+",\"requestedBy\":\""+CUST_UPDATED_BY+"\"}";
		
		return gson.toJson(dto);
	}

	
	public static String standardJsonQuickRegister()
	{
		System.out.println(gson.toJson(standardEmailMobileCustomer()));
		
		return gson.toJson(standardEmailMobileCustomer());
	}
	
	public static String standardJsonQuickRegister(QuickRegisterEntity quickRegisterEntity)
	{
		System.out.println(gson.toJson(quickRegisterEntity));
		
		return gson.toJson(quickRegisterEntity);
	}
	
	public static String standardJsonQuickRegisterVendor()
	{
		System.out.println(gson.toJson(standardEmailMobileVendor()));
		
		return gson.toJson(standardEmailMobileVendor());
	}
	
	public static String standardJsonQuickRegisterVendor(QuickRegisterEntity quickRegisterEntity)
	{
		System.out.println(gson.toJson(quickRegisterEntity));
		
		return gson.toJson(quickRegisterEntity);
	}
}
