package com.projectx.rest.fixtures;

import java.util.Date;

import com.projectx.rest.domain.CustomerQuickRegisterEntity;



public class CustomerQuickRegisterDataFixture {
	
	
	
	public static String REGISTER_NOT_REGISTERED="NOT_REGISTERED";
	public static String REGISTER_EMAIL_MOBILE_ALREADY_REGISTERED="EMAIL_MOBILE_ALREADY_REGISTERED";
	public static String REGISTER_MOBILE_ALREADY_REGISTERED="MOBILE_ALREADY_REGISTERED";
	public static String REGISTER_EMAIL_ALREADY_REGISTERED="EMAIL_ALREADY_REGISTERED";

	public static Long CUST_ID=212L;
	public static String CUST_FIRSTNAME="dinesh";
	public static String CUST_LASTNAME="shende";
	
	public static String CUST_EMAIL="dineshshe@gmail.com";
	public static String CUST_EMAIL_OTHER="shendedinesh@gmail.com";
	public static Long CUST_MOBILE=9960821869L;
	
	public static Integer CUST_PIN=413133;
	
	public static String CUST_STATUS_EMAILMOBILE="EmailMobileVerificationPending";
	public static String CUST_STATUS_EMAIL="EmailVerificationPending";
	public static String CUST_STATUS_MOBILE="MobileVerificationPending";

	public static Integer CUST_MOBILEPIN=101010;
	public static Integer CUST_MOBILEPIN_UPDATED=102010;
	public static String CUST_EMAILHASH="02b99c87926ed36ed1b41afccf9b05f9efd6e54e6e9d116b8ed3a7eaf257b85a";
	public static String CUST_EMAILHASH_UPDATED="277f7fb2ede95f935b08c63273471c9077ace61f1cbb1f376b2983032fda5321";
	public static Integer CUST_MOBILEPIN_VERIFICATION_ATTEMPTS=0;
	public static Date CUST_EMAIL_HASH_SENT_TIME=new Date();
	public static Date CUST_MOBILE_PIN_SENT_TIME=new Date();
	public static Date CUST_LAST_STATUS_CHANGED_TIME=new Date();
	public static String CUST_PASSWORD_DEFAULT="123456";
	public static String CUST_PASSWORD_TYPE_DEFAULT="Default";
	public static String CUST_PASSWORD_CHANGED="654321";
	public static String CUST_PASSWORD_TYPE_CHANGED="Changed";
	
	
	public static String STATUS_EMAIL_VERFIED_MOBILE_PENDING="EmailVerifiedMobileVerficationPending";
	public static String STATUS_MOBILE_VERFIED_EMAIL_PENDING="MobileVerifiedEmailVerficationPending";
	public static String STATUS_EMAIL_MOBILE_VERIFIED="EmailMobileVerified";
	public static String STATUS_MOBILE_VERFIED="MobileVerified";
	public static String STATUS_EMAIL_VERFIED="EmailVerified";
	
	//private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	public static CustomerQuickRegisterEntity standardEmailMobileCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL,CUST_MOBILE, CUST_PIN,CUST_STATUS_EMAILMOBILE, CUST_MOBILEPIN, CUST_EMAILHASH,CUST_MOBILEPIN_VERIFICATION_ATTEMPTS,null,null,CUST_LAST_STATUS_CHANGED_TIME,null,null);
		
	}
	
	public static CustomerQuickRegisterEntity standardEmailMobileCustomerWithPassword()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL,CUST_MOBILE, CUST_PIN,CUST_STATUS_EMAILMOBILE, CUST_MOBILEPIN, CUST_EMAILHASH,CUST_MOBILEPIN_VERIFICATION_ATTEMPTS,null,null,CUST_LAST_STATUS_CHANGED_TIME,CUST_PASSWORD_DEFAULT,CUST_PASSWORD_TYPE_DEFAULT);
		
	}
	
	public static CustomerQuickRegisterEntity standardMobileCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME, null,CUST_MOBILE, CUST_PIN,CUST_STATUS_MOBILE, CUST_MOBILEPIN, null,CUST_MOBILEPIN_VERIFICATION_ATTEMPTS,null,null,CUST_LAST_STATUS_CHANGED_TIME,null,null);
		
	}
	
	public static CustomerQuickRegisterEntity standardEmailCustomer()
	{
		return new CustomerQuickRegisterEntity(CUST_ID,CUST_FIRSTNAME, CUST_LASTNAME, CUST_EMAIL,null, CUST_PIN,CUST_STATUS_EMAIL, null, CUST_EMAILHASH,CUST_MOBILEPIN_VERIFICATION_ATTEMPTS,null,null,CUST_LAST_STATUS_CHANGED_TIME,null,null);
		
	}

	
}

