package com.projectx.rest.fixture;

import static com.projectx.rest.fixtures.CustomerQuickRegisterDataFixture.*; 


import com.projectx.data.domain.UpdateEmailPassword;
import com.projectx.data.domain.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.data.domain.VerifyLoginDetailsDataDTO;
import com.projectx.rest.domain.CustomerAuthenticationDetails;
import com.projectx.web.domain.CustomerIdDTO;
import com.projectx.web.domain.LoginVerificationDTO;
import com.projectx.web.domain.LoginVerificationWithDefaultEmailPasswordDTO;
import com.projectx.web.domain.UpdatePasswordDTO;


public class CustomerAuthenticationDetailsDataFixtures {
	

	public static Integer CUST_RESEND_COUNT=0;
	public static Integer CUST_LOGIN_VERIFICATION_ATTEMPTS=0;
	
	
	public static CustomerAuthenticationDetails standardCustomerEmailMobileAuthenticationDetails()
	{
		
		return new CustomerAuthenticationDetails(CUST_ID, CUST_EMAIL, CUST_MOBILE, CUST_PASSWORD_DEFAULT, CUST_PASSWORD_TYPE_DEFAULT, CUST_EMAILHASH, CUST_RESEND_COUNT, CUST_LOGIN_VERIFICATION_ATTEMPTS);
	}
	
	public static CustomerAuthenticationDetails standardCustomerEmailAuthenticationDetails()
	{
		return new CustomerAuthenticationDetails(CUST_ID, CUST_EMAIL,CUST_MOBILE, CUST_PASSWORD_DEFAULT, CUST_PASSWORD_TYPE_DEFAULT,  CUST_EMAILHASH, CUST_RESEND_COUNT, CUST_LOGIN_VERIFICATION_ATTEMPTS);
	}

	public static CustomerAuthenticationDetails standardCustomerMobileAuthenticationDetails()
	{
		return new CustomerAuthenticationDetails(CUST_ID, null,CUST_MOBILE, CUST_PASSWORD_DEFAULT, CUST_PASSWORD_TYPE_DEFAULT, null, CUST_RESEND_COUNT, CUST_LOGIN_VERIFICATION_ATTEMPTS);
	}

	public static CustomerAuthenticationDetails standardCustomerEmailMobileAuthenticationDetailsWithNewPassword()
	{
		return new CustomerAuthenticationDetails(CUST_ID, CUST_EMAIL, CUST_MOBILE, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED,null,CUST_RESEND_COUNT,CUST_LOGIN_VERIFICATION_ATTEMPTS);
	}
	
	public static CustomerAuthenticationDetails standardCustomerEmailAuthenticationDetailsWithNewPassword()
	{
		return new CustomerAuthenticationDetails(CUST_ID, CUST_EMAIL, null, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED,null,CUST_RESEND_COUNT,CUST_LOGIN_VERIFICATION_ATTEMPTS);
	}

	public static CustomerAuthenticationDetails standardCustomerMobileAuthenticationDetailsWithNewPassword()
	{
		return new CustomerAuthenticationDetails(CUST_ID, null, CUST_MOBILE, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED,null,CUST_RESEND_COUNT,CUST_LOGIN_VERIFICATION_ATTEMPTS);
	}

	
	public static UpdatePasswordAndPasswordTypeDTO standardUpdatePasswordAndPasswordTypeDTO()
	{
		return new UpdatePasswordAndPasswordTypeDTO(CUST_ID, CUST_PASSWORD_CHANGED,CUST_PASSWORD_TYPE_CHANGED);
	}
//	
//	public static UpdateCountByCustomerId standardUpdateCountByCustomerId()
//	{
//		return new UpdateCountByCustomerId(CUST_ID, CUST_RESEND_COUNT+1);
//	}

	public static UpdateEmailPassword standardUpdateEmailPassword()
	{
		return new UpdateEmailPassword(CUST_ID, CUST_EMAILHASH_UPDATED);
	}
	
	public static UpdatePasswordDTO standardUpdatePasswordDTO()
	{
		return new UpdatePasswordDTO(CUST_ID, CUST_PASSWORD_CHANGED);
	}
	
	public static LoginVerificationDTO standardLoginVerificationWithEmail()
	{
		return new LoginVerificationDTO(CUST_EMAIL, CUST_PASSWORD_DEFAULT);
	}
	
	public static LoginVerificationDTO standardLoginVerificationWithMobile()
	{
		return new LoginVerificationDTO(Long.toString(CUST_MOBILE), CUST_PASSWORD_DEFAULT);
	}
	
	
	public static LoginVerificationWithDefaultEmailPasswordDTO standardEmailLoginVerification()
	{
		return new LoginVerificationWithDefaultEmailPasswordDTO(CUST_ID, CUST_EMAILHASH);
	}
	
	public static VerifyLoginDetailsDataDTO standardVerifyLoginDetailsDataWithEmail()
	{
		return new VerifyLoginDetailsDataDTO(CUST_EMAIL, null, CUST_PASSWORD_DEFAULT);
	}
	
	public static VerifyLoginDetailsDataDTO standardVerifyLoginDetailsDataWithMobile()
	{
		return new VerifyLoginDetailsDataDTO(null, CUST_MOBILE, CUST_PASSWORD_DEFAULT);
	}
	
	public static VerifyLoginDetailsDataDTO standardVerifyLoginDetailsDataWithEmailNewPassword()
	{
		return new VerifyLoginDetailsDataDTO(CUST_EMAIL, null, CUST_PASSWORD_CHANGED);
	}
	
	public static VerifyLoginDetailsDataDTO standardVerifyLoginDetailsDataWithMobileNewPassword()
	{
		return new VerifyLoginDetailsDataDTO(null, CUST_MOBILE, CUST_PASSWORD_CHANGED);
	}
	
	
	public static CustomerIdDTO standardCustomerIdDTO()
	{
		return new CustomerIdDTO(CUST_ID);
	}
	
	
	public static String standardJsonCustomerIdDTO(CustomerIdDTO customerIdDTO)
	{
		System.out.println("{\"customerId\":"+customerIdDTO.getCustomerId()+"}");
		
		return "{\"customerId\":"+customerIdDTO.getCustomerId()+"}";
	}
	
	public static String standardJsonCustomerAuthenticationDetails(CustomerAuthenticationDetails standardCustomer)
	{
	
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"customerId\":"+standardCustomer.getCustomerId()+",");
		
		if(standardCustomer.getEmail()!=null)
			jsonBuilder.append("\"email\":\""+standardCustomer.getEmail()+"\",");
		else
			jsonBuilder.append("\"email\":"+standardCustomer.getEmail()+",");
		
		jsonBuilder.append("\"mobile\":"+standardCustomer.getMobile()+",");
		
			
		jsonBuilder.append("\"password\":\""+standardCustomer.getPassword()+"\",");
		jsonBuilder.append("\"passwordType\":\""+standardCustomer.getPasswordType()+"\"}");
		
		//System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
		
		
	}
	
	public static String standardJsonLoginVerification(LoginVerificationDTO loginVerificationDTO)
	{

		return "{\"loginEntity\":\""+loginVerificationDTO.getLoginEntity()+"\",\"password\":\""+loginVerificationDTO.getPassword()+"\"}";
		
		//return gson.toJson(loginVerificationDTO);
	}
	
	
	public static String standardJsonUpdatePasswordAndPasswordType(UpdatePasswordDTO dto)
	{
		StringBuilder jsonBuilder=new StringBuilder();

		jsonBuilder.append("{\"customerId\":"+standardUpdatePasswordAndPasswordTypeDTO().getCustomerId()+",");
		
		jsonBuilder.append("\"password\":\""+standardUpdatePasswordAndPasswordTypeDTO().getPassword()+"\"}");
		
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
		
	}
	
	public static String standJsonEmailPasswordLoginVerification()
	{
		StringBuilder jsonBuilder=new StringBuilder();

		jsonBuilder.append("{\"customerId\":"+standardEmailLoginVerification().getCustomerId()+",");
		
		jsonBuilder.append("\"emailPassword\":\""+standardEmailLoginVerification().getEmailPassword()+"\"}");
		
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();

	}
	
	
}
