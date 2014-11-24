package com.projectx.rest.repositoryImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.CustomerIdEmailDTO;
import com.projectx.data.domain.UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO;
import com.projectx.rest.domain.CustomerEmailVerificationDetails;
import com.projectx.rest.repository.CustomerEmailVericationDetailsRepository;


@Component
@Profile(value="Dev")
@PropertySource(value="classpath:/application.properties")
public class CustomerEmailVerificationDetailsRepositoryImpl implements CustomerEmailVericationDetailsRepository {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	
	
	@Override
	public CustomerEmailVerificationDetails save(
			CustomerEmailVerificationDetails mobileVerificationDetails) {
		
		CustomerEmailVerificationDetails savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/emailVerification/saveEmailVerificationDetails", mobileVerificationDetails, CustomerEmailVerificationDetails.class);
		
		return savedEntity;
	}

	@Override
	public CustomerEmailVerificationDetails getEmailVerificationDetailsByCustomerIdAndEmail(
			Long customerId, String email) {
		
		CustomerIdEmailDTO emailDTO=new CustomerIdEmailDTO(customerId, email);
		
		CustomerEmailVerificationDetails savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/emailVerification/getEmailVerificationDetailsByCustomerIdAndEmail", emailDTO, CustomerEmailVerificationDetails.class);
		
		return savedEntity;
	}

	@Override
	public Integer resetEmailHashAndEmailHashSentTime(Long customerId,
			String email, String emailHash, Date emailHashSentTime,
			Integer resetCount) {
		UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO dto=new UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO
				(customerId, email, emailHash, emailHashSentTime, resetCount);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/emailVerification/resetEmailHashAndEmailHashSentTime", dto, Integer.class);
		
		return updateStatus;
	}

	@Override
	public Integer incrementResendCountByCustomerIdAndEmail(Long customerId,
			String email) {
		
		CustomerIdEmailDTO emailDTO=new CustomerIdEmailDTO(customerId, email);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/emailVerification/incrementResendCountByCustomerIdAndEmail", emailDTO, Integer.class);
		
		return updateStatus;
	}

	@Override
	public Long count() {
		Long count=restTemplate.getForObject(env.getProperty("data.url")+"/customer/quickregister/emailVerification/getCount", Long.class);
		
		return count;
	}

	@Override
	public Boolean clearTestData() {
		Boolean status=restTemplate.getForObject(env.getProperty("data.url")+"/customer/quickregister/clearEmailVerificationForTesting", Boolean.class);
		
		return status;
	}

	

	

}
