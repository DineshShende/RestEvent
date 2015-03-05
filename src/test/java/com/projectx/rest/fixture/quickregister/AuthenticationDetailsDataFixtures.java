package com.projectx.rest.fixture.quickregister;

import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.projectx.data.domain.quickregister.UpdateEmailPassword;
import com.projectx.data.domain.quickregister.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.data.domain.quickregister.UpdatePasswordEmailPasswordAndPasswordTypeDTO;
import com.projectx.data.domain.quickregister.VerifyLoginDetailsDataDTO;
import com.projectx.mvc.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.mvc.domain.quickregister.CustomerIdTypeUpdatedByDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationWithDefaultEmailPasswordDTO;
import com.projectx.mvc.domain.quickregister.UpdatePasswordDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;


public class AuthenticationDetailsDataFixtures {
	
	
	private static Gson gson=new Gson();
	
	public static Integer CUST_RESEND_COUNT=0;
	public static Integer CUST_LOGIN_VERIFICATION_ATTEMPTS=0;
	
	//public static Integer CUST_TYPE_CUSTOMER=1;
	//public static Integer CUST_TYPE_VENDOR=2;
	
	public static Date CUST_DATE=new Date();
	
	
	
	
	public static AuthenticationDetailsKey standardAuthenticationDetailsKey()
	{
		return new AuthenticationDetailsKey(CUST_ID, ENTITY_TYPE_CUSTOMER);
	}
	
	
	
	public static AuthenticationDetails standardCustomerEmailMobileAuthenticationDetails()
	{
		
		return new AuthenticationDetails(standardAuthenticationDetailsKey(), CUST_EMAIL, CUST_MOBILE, CUST_PASSWORD_DEFAULT, CUST_PASSWORD_TYPE_DEFAULT, CUST_EMAILHASH, CUST_RESEND_COUNT, CUST_LOGIN_VERIFICATION_ATTEMPTS,CUST_DATE,CUST_DATE,CUST_UPDATED_BY);
	}
	
	

	public static AuthenticationDetails standardCustomerEmailAuthenticationDetailsWithOutPassword()
	{
		return new AuthenticationDetails(standardAuthenticationDetailsKey(), CUST_EMAIL,CUST_MOBILE, null, null,  null, CUST_RESEND_COUNT, CUST_LOGIN_VERIFICATION_ATTEMPTS,CUST_DATE,CUST_DATE,CUST_UPDATED_BY);
	}

	
	public static AuthenticationDetails standardCustomerEmailAuthenticationDetails()
	{
		return new AuthenticationDetails(standardAuthenticationDetailsKey(), CUST_EMAIL,CUST_MOBILE, CUST_PASSWORD_DEFAULT, CUST_PASSWORD_TYPE_DEFAULT,  CUST_EMAILHASH, CUST_RESEND_COUNT, CUST_LOGIN_VERIFICATION_ATTEMPTS,CUST_DATE,CUST_DATE,CUST_UPDATED_BY);
	}

	public static AuthenticationDetails standardCustomerMobileAuthenticationDetails()
	{
		return new AuthenticationDetails(standardAuthenticationDetailsKey(), null,CUST_MOBILE, CUST_PASSWORD_DEFAULT, CUST_PASSWORD_TYPE_DEFAULT, null, CUST_RESEND_COUNT, CUST_LOGIN_VERIFICATION_ATTEMPTS,CUST_DATE,CUST_DATE,CUST_UPDATED_BY);
	}

	public static AuthenticationDetails standardCustomerEmailMobileAuthenticationDetailsWithNewPassword()
	{
		return new AuthenticationDetails(standardAuthenticationDetailsKey(), CUST_EMAIL, CUST_MOBILE, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED,null,CUST_RESEND_COUNT,CUST_LOGIN_VERIFICATION_ATTEMPTS,CUST_DATE,CUST_DATE,CUST_UPDATED_BY);
	}
	
	public static AuthenticationDetails standardCustomerEmailAuthenticationDetailsWithNewPassword()
	{
		return new AuthenticationDetails(standardAuthenticationDetailsKey(), CUST_EMAIL, null, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED,null,CUST_RESEND_COUNT,CUST_LOGIN_VERIFICATION_ATTEMPTS,CUST_DATE,CUST_DATE,CUST_UPDATED_BY);
	}

	public static AuthenticationDetails standardCustomerMobileAuthenticationDetailsWithNewPassword()
	{
		return new AuthenticationDetails(standardAuthenticationDetailsKey(), null, CUST_MOBILE, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED,null,CUST_RESEND_COUNT,CUST_LOGIN_VERIFICATION_ATTEMPTS,CUST_DATE,CUST_DATE,CUST_UPDATED_BY);
	}

	
	public static UpdatePasswordEmailPasswordAndPasswordTypeDTO standardUpdatePasswordEmailPasswordAndPasswordTypeDTO()
	{
		return new UpdatePasswordEmailPasswordAndPasswordTypeDTO(CUST_ID,ENTITY_TYPE_CUSTOMER, CUST_PASSWORD_CHANGED,CUST_EMAILHASH_UPDATED,CUST_PASSWORD_TYPE_CHANGED,CUST_UPDATED_BY);
	}
//	
//	public static UpdateCountByCustomerId standardUpdateCountByCustomerId()
//	{
//		return new UpdateCountByCustomerId(CUST_ID, CUST_RESEND_COUNT+1);
//	}

	public static UpdateEmailPassword standardUpdateEmailPassword()
	{
		return new UpdateEmailPassword(CUST_ID,ENTITY_TYPE_CUSTOMER, CUST_EMAILHASH_UPDATED);
	}
	
	public static UpdatePasswordDTO standardUpdatePasswordDTO()
	{
		return new UpdatePasswordDTO(CUST_ID,ENTITY_TYPE_CUSTOMER, CUST_PASSWORD_CHANGED,CUST_UPDATED_BY);
	}
	
	public static UpdatePasswordAndPasswordTypeDTO standardUpdatePasswordAndPasswordTypeDTO()
	{
		return new UpdatePasswordAndPasswordTypeDTO(CUST_ID,ENTITY_TYPE_CUSTOMER, CUST_PASSWORD_CHANGED,CUST_PASSWORD_TYPE_CHANGED,CUST_UPDATED_BY);
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
		return new LoginVerificationWithDefaultEmailPasswordDTO(CUST_ID,ENTITY_TYPE_CUSTOMER, CUST_EMAILHASH);
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
	
	
	public static CustomerIdTypeDTO standardCustomerIdDTO()
	{
		return new CustomerIdTypeDTO(CUST_ID,ENTITY_TYPE_CUSTOMER);
	}
	
	public static CustomerIdTypeUpdatedByDTO standardCustomerIdTypeUpdatedByDTO()
	{
		return new CustomerIdTypeUpdatedByDTO(CUST_ID,ENTITY_TYPE_CUSTOMER,CUST_UPDATED_BY);
	}
	
	public static String standardJsonCustomerIdDTO(CustomerIdTypeDTO customerIdDTO)
	{
		System.out.println("{\"customerId\":"+customerIdDTO.getCustomerId()+"}");
		
		return "{\"customerId\":"+customerIdDTO.getCustomerId()+",\"customerType\":"+customerIdDTO.getCustomerType()+"}";
	}
	
	
	public static String standardJsonCustomerAuthenticationDetails(AuthenticationDetails standardCustomer)
	{
	
		StringBuilder jsonBuilder=new StringBuilder();
		
		jsonBuilder.append("{\"key\":{\"customerId\":"+standardCustomer.getKey().getCustomerId()+",\"customerType\":"
		+standardCustomer.getKey().getCustomerType()+"},");
		
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
	
	
	public static String standardJsonUpdatePasswordAndPasswordTypeUpdatedBy(UpdatePasswordAndPasswordTypeDTO dto)
	{
		System.out.println(gson.toJson(dto));
		
		return gson.toJson(dto);
		
	}
	
	public static String standardJsonUpdatePasswordAndPasswordType(UpdatePasswordDTO dto)
	{
		/*
		StringBuilder jsonBuilder=new StringBuilder();

		jsonBuilder.append("{\"customerId\":"+standardUpdatePasswordEmailPasswordAndPasswordTypeDTO().getCustomerId()+",");
		
		jsonBuilder.append("\"customerType\":"+standardUpdatePasswordEmailPasswordAndPasswordTypeDTO().getCustomerType()+",");
		jsonBuilder.append("\"password\":\""+standardUpdatePasswordEmailPasswordAndPasswordTypeDTO().getPassword()+"\"}");
	//	jsonBuilder.append("\"passwordType\":\""+standardUpdatePasswordAndPasswordTypeDTO().getPasswordType()+"\"}");
		
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
		*/
		
		return gson.toJson(dto);
	}
	
	public static String standJsonEmailPasswordLoginVerification()
	{
		StringBuilder jsonBuilder=new StringBuilder();

		jsonBuilder.append("{\"customerId\":"+standardEmailLoginVerification().getCustomerId()+",");
		
		jsonBuilder.append("\"customerType\":"+standardEmailLoginVerification().getCustomerType()+",");
		
		jsonBuilder.append("\"emailPassword\":\""+standardEmailLoginVerification().getEmailPassword()+"\"}");
		
		
		System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();

	}
	
	
}
