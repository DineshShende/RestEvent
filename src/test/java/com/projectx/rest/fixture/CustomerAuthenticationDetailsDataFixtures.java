package com.projectx.rest.fixture;

import static com.projectx.rest.fixtures.CustomerQuickRegisterDataFixture.*; 

import com.projectx.data.domain.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.data.domain.VerifyLoginDetailsDataDTO;
import com.projectx.rest.domain.CustomerAuthenticationDetails;
import com.projectx.web.domain.CustomerIdDTO;
import com.projectx.web.domain.LoginVerificationDTO;


public class CustomerAuthenticationDetailsDataFixtures {
	

	
	
	public static CustomerAuthenticationDetails standardCustomerEmailMobileAuthenticationDetails()
	{
		return new CustomerAuthenticationDetails(CUST_ID, CUST_EMAIL, CUST_MOBILE, CUST_PASSWORD_DEFAULT, CUST_PASSWORD_TYPE_DEFAULT);
	}
	
	public static CustomerAuthenticationDetails standardCustomerEmailAuthenticationDetails()
	{
		return new CustomerAuthenticationDetails(CUST_ID, CUST_EMAIL, null, CUST_PASSWORD_DEFAULT, CUST_PASSWORD_TYPE_DEFAULT);
	}

	public static CustomerAuthenticationDetails standardCustomerMobileAuthenticationDetails()
	{
		return new CustomerAuthenticationDetails(CUST_ID, null, CUST_MOBILE, CUST_PASSWORD_DEFAULT, CUST_PASSWORD_TYPE_DEFAULT);
	}

	public static CustomerAuthenticationDetails standardCustomerEmailMobileAuthenticationDetailsWithNewPassword()
	{
		return new CustomerAuthenticationDetails(CUST_ID, CUST_EMAIL, CUST_MOBILE, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED);
	}
	
	public static CustomerAuthenticationDetails standardCustomerEmailAuthenticationDetailsWithNewPassword()
	{
		return new CustomerAuthenticationDetails(CUST_ID, CUST_EMAIL, null, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED);
	}

	public static CustomerAuthenticationDetails standardCustomerMobileAuthenticationDetailsWithNewPassword()
	{
		return new CustomerAuthenticationDetails(CUST_ID, null, CUST_MOBILE, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED);
	}

	
	public static UpdatePasswordAndPasswordTypeDTO standardUpdatePasswordAndPasswordTypeDTO()
	{
		return new UpdatePasswordAndPasswordTypeDTO(CUST_ID, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED);
	}

	
	public static LoginVerificationDTO standardLoginVerificationWithEmail()
	{
		return new LoginVerificationDTO(CUST_EMAIL, CUST_PASSWORD_DEFAULT);
	}
	
	public static LoginVerificationDTO standardLoginVerificationWithMobile()
	{
		return new LoginVerificationDTO(Long.toString(CUST_MOBILE), CUST_PASSWORD_DEFAULT);
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
		StringBuilder jsonBuilder=new StringBuilder();

		if(loginVerificationDTO.getLoginEntity()!=null)
			jsonBuilder.append("{\"loginEntity\":\""+loginVerificationDTO.getLoginEntity()+"\",");
		else
			jsonBuilder.append("{\"loginEntity\":"+loginVerificationDTO.getLoginEntity()+",");

		jsonBuilder.append("\"password\":\""+loginVerificationDTO.getPassword()+"\"}");
		
	//	System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
	}
	
	
	public static String standardJsonUpdatePasswordAndPasswordType(UpdatePasswordAndPasswordTypeDTO dto)
	{
		StringBuilder jsonBuilder=new StringBuilder();

		jsonBuilder.append("{\"customerId\":"+dto.getCustomerId()+",");
		
		jsonBuilder.append("\"password\":\""+dto.getPassword()+"\"}");
	
		
	//	System.out.println(jsonBuilder.toString());
		
		return jsonBuilder.toString();
		
	}
	
	
}
