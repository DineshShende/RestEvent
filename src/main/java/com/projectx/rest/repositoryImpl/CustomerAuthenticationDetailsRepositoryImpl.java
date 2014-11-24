package com.projectx.rest.repositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.UpdateEmailPassword;
import com.projectx.data.domain.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.data.domain.VerifyLoginDetailsDataDTO;
import com.projectx.rest.domain.CustomerAuthenticationDetails;
import com.projectx.rest.repository.CustomerAuthenticationDetailsRepository;
import com.projectx.web.domain.CustomerIdDTO;
import com.projectx.web.domain.GetByEmailDTO;
import com.projectx.web.domain.GetByMobileDTO;
import com.projectx.web.domain.LoginVerificationDTO;

@Component
@Profile("Dev")
@PropertySource(value="classpath:/application.properties")
public class CustomerAuthenticationDetailsRepositoryImpl implements CustomerAuthenticationDetailsRepository{

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	
	@Override
	public CustomerAuthenticationDetails save(
			CustomerAuthenticationDetails authenticationDetails) {
		
		CustomerAuthenticationDetails savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/saveLoginDetails", authenticationDetails, CustomerAuthenticationDetails.class);
		
		return savedEntity;
		
	}
	@Override
	public Integer count() {
		Integer count=restTemplate.getForObject(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/loginDetailsCount", Integer.class);
		
		return count;
	}

	@Override
	public Boolean clearLoginDetailsForTesting() {
		Boolean status=restTemplate.getForObject(env.getProperty("data.url")+"/customer/quickregister/clearLoginDetailsForTesting", Boolean.class);
		
		return status;
	}

	@Override
	public CustomerAuthenticationDetails getCustomerAuthenticationDetailsByCustomerId(Long customerId) {
		
		CustomerIdDTO customerIdDTO=new CustomerIdDTO(customerId);
		
		CustomerAuthenticationDetails fetchedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/getLoginDetailsByCustomerId", customerIdDTO, CustomerAuthenticationDetails.class);

		if(fetchedEntity==null)
			return new CustomerAuthenticationDetails();
		
		return fetchedEntity;
	}

	@Override
	public Integer updatePasswordAndPasswordTypeAndCounts(Long customerId,
			String password, String passwordType) {
		
		UpdatePasswordAndPasswordTypeDTO passwordAndPasswordTypeDTO=new UpdatePasswordAndPasswordTypeDTO(customerId, password, passwordType);

		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/updatePasswordAndPasswordTypeAndCounts", passwordAndPasswordTypeDTO, Integer.class);
		
		return updateStatus;
	}

	@Override
	public Integer updateEmailPasswordAndPasswordTypeAndCounts(Long customerId,
			String emailPassword) {
		UpdateEmailPassword emailPasswordDTO=new UpdateEmailPassword(customerId, emailPassword);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/updateEmailPasswordAndPasswordTypeAndCounts", emailPasswordDTO, Integer.class);
		
		return updateStatus;
	}

	@Override
	public Integer incrementResendCount(Long customerId) {

		CustomerIdDTO customerIdDTO=new CustomerIdDTO(customerId);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/incrementResendCount", customerIdDTO, Integer.class);
		
		return updateStatus;
	}

	@Override
	public Integer incrementLastUnsucessfullAttempts(Long customerId) {

		CustomerIdDTO customerIdDTO=new CustomerIdDTO(customerId);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/incrementLastUnsucessfullAttempts", customerIdDTO, Integer.class);
		
		return updateStatus;
	}

	@Override
	public CustomerAuthenticationDetails getCustomerAuthenticationDetailsByEmail(
			String email) {
		GetByEmailDTO getByEmailDTO=new GetByEmailDTO(email);
		
		CustomerAuthenticationDetails fetchedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/getLoginDetailsByEmail", getByEmailDTO, CustomerAuthenticationDetails.class);

		if(fetchedEntity==null)
			return new CustomerAuthenticationDetails();
		
		return fetchedEntity;

	}

	@Override
	public CustomerAuthenticationDetails getCustomerAuthenticationDetailsByMobile(
			Long mobile) {

		GetByMobileDTO getByMobilelDTO=new GetByMobileDTO(mobile);
		
		CustomerAuthenticationDetails fetchedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/customerAuthentication/getLoginDetailsByMobile", getByMobilelDTO, CustomerAuthenticationDetails.class);

		if(fetchedEntity==null)
			return new CustomerAuthenticationDetails();
		
		return fetchedEntity;

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
