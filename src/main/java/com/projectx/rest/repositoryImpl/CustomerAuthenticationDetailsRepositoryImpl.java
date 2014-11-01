package com.projectx.rest.repositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.LoginVerificationDTO;
import com.projectx.data.domain.UpdatePasswordAndPasswordTypeDTO;
import com.projectx.rest.domain.CustomerAuthenticationDetails;
import com.projectx.rest.repository.CustomerAuthenticationDetailsRepository;
import com.projectx.web.domain.CustomerIdDTO;

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
		
		CustomerAuthenticationDetails savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/saveLoginDetails", authenticationDetails, CustomerAuthenticationDetails.class);
		
		return savedEntity;
		
	}

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
		
		LoginVerificationDTO loginVerificationDTO=new LoginVerificationDTO(email, mobile, password);
		
		//System.out.println(loginVerificationDTO);
		
		CustomerAuthenticationDetails verificationEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/verifyLoginDetails", loginVerificationDTO, CustomerAuthenticationDetails.class);
		
		return verificationEntity;
	}

	@Override
	public Integer count() {
		Integer count=restTemplate.getForObject(env.getProperty("data.url")+"/customer/quickregister/loginDetailsCount", Integer.class);
		
		return count;
	}

	@Override
	public Boolean clearLoginDetailsForTesting() {
		Boolean status=restTemplate.getForObject(env.getProperty("data.url")+"/customer/quickregister/clearLoginDetailsForTesting", Boolean.class);
		
		return status;
	}

	@Override
	public CustomerAuthenticationDetails getByCustomerId(Long customerId) {
		
		CustomerIdDTO customerIdDTO=new CustomerIdDTO(customerId);
		
		CustomerAuthenticationDetails fetchedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/getLoginDetailsByCustomerId", customerIdDTO, CustomerAuthenticationDetails.class);

		return fetchedEntity;
	}
	
	

}
