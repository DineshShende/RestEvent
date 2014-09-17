package com.projectx.rest.fixture;

import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.domain.CustomerQuickRegisterKey;
import com.projectx.web.domain.CustomerQuickRegisterEntityDTO;


public class CustomerQuickRegisterDataFixture {

	public static String CUST_FIRSTNAME="dinesh";
	public static String CUST_LASTNAME="shende";
	
	public static String CUST_EMAIL="dineshshe@gmail.com";
	public static Long CUST_MOBILE=9960821869L;
	
	public static Integer CUST_PIN=413133;
	
	public static String CUST_STATUS_EMAILMOBILE="EmailMobileVerificationPending";
	public static String CUST_STATUS_EMAIL="EmailVerificationPending";
	public static String CUST_STATUS_MOBILE="MobileVerificationPending";

	public static Integer CUST_MOBILEPIN=101010;
	public static Long CUST_EMAILHASH=1010101010L;

	public static CustomerQuickRegisterKey standardEmailMobileCustomerKey()
	{
		return new CustomerQuickRegisterKey(CUST_EMAIL, CUST_MOBILE);
	}
	
	public static CustomerQuickRegisterKey standardEmailCustomerKey()
	{
		return new CustomerQuickRegisterKey(CUST_EMAIL, 0L);
	}
	
	
	public static CustomerQuickRegisterKey standardMobileCustomerKey()
	{
		return new CustomerQuickRegisterKey("", CUST_MOBILE);
	}
	
	
	public static CustomerQuickRegisterEntity standardEmailMobileCustomer()
	{
		return new CustomerQuickRegisterEntity(standardEmailMobileCustomerKey(),CUST_FIRSTNAME, CUST_LASTNAME, CUST_PIN,CUST_STATUS_EMAILMOBILE, CUST_MOBILEPIN, CUST_EMAILHASH);
		
	}
	
	public static CustomerQuickRegisterEntity standardMobileCustomer()
	{
		return new CustomerQuickRegisterEntity(standardMobileCustomerKey(),CUST_FIRSTNAME, CUST_LASTNAME,  CUST_PIN,CUST_STATUS_MOBILE, CUST_MOBILEPIN, null);
		
	}
	
	public static CustomerQuickRegisterEntity standardEmailCustomer()
	{
		return new CustomerQuickRegisterEntity(standardEmailCustomerKey(),CUST_FIRSTNAME, CUST_LASTNAME,  CUST_PIN,CUST_STATUS_EMAIL, null, CUST_EMAILHASH);
		
	}
	
	public static CustomerQuickRegisterEntityDTO standardEmailCustomerDTO()
	{
		return new CustomerQuickRegisterEntityDTO(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,0L,CUST_PIN,CUST_STATUS_EMAIL);
	}
	
	public static CustomerQuickRegisterEntityDTO standardMobileCustomerDTO()
	{
		return new CustomerQuickRegisterEntityDTO(CUST_FIRSTNAME,CUST_LASTNAME,"",CUST_MOBILE,CUST_PIN,CUST_STATUS_MOBILE);
	}
	
	public static CustomerQuickRegisterEntityDTO standardEmailMobileCustomerDTO()
	{
		return new CustomerQuickRegisterEntityDTO(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,CUST_MOBILE,CUST_PIN,CUST_STATUS_EMAILMOBILE);
	}
	
	
	public static CustomerQuickRegisterEntityDTO standardEmailCustomerDTOWithOutStatus()
	{
		return new CustomerQuickRegisterEntityDTO(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,0L,CUST_PIN,"");
	}
	
	public static CustomerQuickRegisterEntityDTO standardMobileCustomerDTOWithOutStatus()
	{
		return new CustomerQuickRegisterEntityDTO(CUST_FIRSTNAME,CUST_LASTNAME,"",CUST_MOBILE,CUST_PIN,"");
	}
	
	public static CustomerQuickRegisterEntityDTO standardEmailMobileCustomerDTOWithOutStatus()
	{
		return new CustomerQuickRegisterEntityDTO(CUST_FIRSTNAME,CUST_LASTNAME,CUST_EMAIL,CUST_MOBILE,CUST_PIN,"");
	}
	

	
	
}
