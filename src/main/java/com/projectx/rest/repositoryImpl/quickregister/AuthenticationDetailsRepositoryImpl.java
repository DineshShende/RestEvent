package com.projectx.rest.repositoryImpl.quickregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.quickregister.UpdateEmailPassword;
import com.projectx.data.domain.quickregister.UpdatePasswordEmailPasswordAndPasswordTypeDTO;
import com.projectx.data.domain.quickregister.VerifyLoginDetailsDataDTO;
import com.projectx.mvc.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.mvc.domain.quickregister.GetByEmailDTO;
import com.projectx.mvc.domain.quickregister.GetByMobileDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.repository.quickregister.AuthenticationDetailsRepository;

@Component
@Profile("Dev")
@PropertySource(value="classpath:/application.properties")
public class AuthenticationDetailsRepositoryImpl implements AuthenticationDetailsRepository{

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	/*
	@Value("${data.url}")
	String baseUrl;
	*/
	
	@Override
	public AuthenticationDetails save(
			AuthenticationDetails authenticationDetails) {
		
		AuthenticationDetails savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/saveLoginDetails", authenticationDetails, AuthenticationDetails.class);
		
		//System.out.println(baseUrl);
		
		return savedEntity;
		
	}

	@Override
	public AuthenticationDetails getByCustomerIdType(Long customerId,Integer customerType) {
		
		CustomerIdTypeDTO customerIdDTO=new CustomerIdTypeDTO(customerId,customerType);
		
		AuthenticationDetails fetchedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/getLoginDetailsByCustomerIdType", customerIdDTO, AuthenticationDetails.class);

		if(fetchedEntity==null)
			return new AuthenticationDetails();
		
		return fetchedEntity;
	}

	@Override
	public Integer updatePasswordEmailPasswordAndPasswordTypeAndCounts(Long customerId,Integer customerType,String password,
			String emailPassword, String passwordType) {
		
		UpdatePasswordEmailPasswordAndPasswordTypeDTO passwordAndPasswordTypeDTO=new UpdatePasswordEmailPasswordAndPasswordTypeDTO(customerId,customerType, password, emailPassword,passwordType);

		System.out.println(passwordAndPasswordTypeDTO);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/updatePasswordEmailPasswordAndPasswordTypeAndCounts", passwordAndPasswordTypeDTO, Integer.class);
		
		return updateStatus;
	}

	/*
	@Override
	public Integer updateEmailPasswordAndPasswordTypeAndCounts(Long customerId,Integer customerType,
			String emailPassword) {
		UpdateEmailPassword emailPasswordDTO=new UpdateEmailPassword(customerId,customerType, emailPassword);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/updateEmailPasswordAndPasswordTypeAndCounts", emailPasswordDTO, Integer.class);
		
		return updateStatus;
	}
	*/

	@Override
	public Integer incrementResendCount(Long customerId,Integer customerType) {

		CustomerIdTypeDTO customerIdDTO=new CustomerIdTypeDTO(customerId,customerType);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/incrementResendCount", customerIdDTO, Integer.class);
		
		return updateStatus;
	}

	@Override
	public Integer incrementLastUnsucessfullAttempts(Long customerId,Integer customerType) {

		CustomerIdTypeDTO customerIdDTO=new CustomerIdTypeDTO(customerId,customerType);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/incrementLastUnsucessfullAttempts", customerIdDTO, Integer.class);
		
		return updateStatus;
	}

	@Override
	public AuthenticationDetails getByEmail(
			String email) {
		GetByEmailDTO getByEmailDTO=new GetByEmailDTO(email);
		
		AuthenticationDetails fetchedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/getLoginDetailsByEmail", getByEmailDTO, AuthenticationDetails.class);

		if(fetchedEntity==null)
			return new AuthenticationDetails();
		
		return fetchedEntity;

	}

	@Override
	public AuthenticationDetails getByMobile(
			Long mobile) {

		GetByMobileDTO getByMobilelDTO=new GetByMobileDTO(mobile);
		
		AuthenticationDetails fetchedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/getLoginDetailsByMobile", getByMobilelDTO, AuthenticationDetails.class);

		if(fetchedEntity==null)
			return new AuthenticationDetails();
		
		return fetchedEntity;

	}

	@Override
	public Integer count() {
		Integer count=restTemplate.getForObject(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/getCount", Integer.class);
		
		return count;
	}

	@Override
	public Boolean clearLoginDetailsForTesting() {
		Boolean status=restTemplate.getForObject(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/clearForTesting", Boolean.class);
		
		return status;
	}
	
	/*
	@Override
	public Integer updatePasswordAndPasswordType(Long customerId,
			String password, String passwordType) {

		UpdatePasswordAndPasswordTypeDTO updatePasswordAndPasswordTypeDTO=new UpdatePasswordAndPasswordTypeDTO(customerId, password, passwordType);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/updatePassword", updatePasswordAndPasswordTypeDTO, Integer.class);
				
		return updateStatus;
	}

	@Override
	public CustomerAuthenticationDetails loginVerification(String email,
			Long mobile, String password) {
		
		VerifyLoginDetailsDataDTO loginVerificationDTO=new VerifyLoginDetailsDataDTO(email, mobile, password);
		
		//System.out.println(loginVerificationDTO);
		
		CustomerAuthenticationDetails verificationEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/verifyLoginDetails", loginVerificationDTO, CustomerAuthenticationDetails.class);
		
		return verificationEntity;
	}
*/
	

}
