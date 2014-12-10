package com.projectx.rest.repositoryImpl.quickregister;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.quickregister.domain.CustomerIdTypeEmailDTO;
import com.projectx.data.quickregister.domain.UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.repository.quickregister.EmailVericationDetailsRepository;


@Component
@Profile(value="Dev")
@PropertySource(value="classpath:/application.properties")
public class EmailVerificationDetailsRepositoryImpl implements EmailVericationDetailsRepository {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	
	
	@Override
	public EmailVerificationDetails save(
			EmailVerificationDetails mobileVerificationDetails) {
		
		EmailVerificationDetails savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/emailVerification/saveEmailVerificationDetails", mobileVerificationDetails, EmailVerificationDetails.class);
		
		return savedEntity;
	}

	@Override
	public EmailVerificationDetails getEmailVerificationDetailsByCustomerIdTypeAndEmail(
			Long customerId,Integer customerType, String email) {
		
		CustomerIdTypeEmailDTO emailDTO=new CustomerIdTypeEmailDTO(customerId,customerType, email);
		
		EmailVerificationDetails savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/emailVerification/getEmailVerificationDetailsByCustomerIdAndEmail", emailDTO, EmailVerificationDetails.class);
		
		return savedEntity;
	}

	@Override
	public Integer resetEmailHashAndEmailHashSentTime(Long customerId,Integer customerType,
			String email, String emailHash, Date emailHashSentTime,
			Integer resetCount) {
		UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO dto=new UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO
				(customerId,customerType, email, emailHash, emailHashSentTime, resetCount);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/emailVerification/resetEmailHashAndEmailHashSentTime", dto, Integer.class);
		
		return updateStatus;
	}

	@Override
	public Integer incrementResendCountByCustomerIdAndEmail(Long customerId,Integer customerType,
			String email) {
		
		CustomerIdTypeEmailDTO emailDTO=new CustomerIdTypeEmailDTO(customerId,customerType, email);
		
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
		Boolean status=restTemplate.getForObject(env.getProperty("data.url")+"/customer/quickregister/emailVerification/clearForTesting", Boolean.class);
		
		return status;
	}

	

	

}
