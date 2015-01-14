package com.projectx.rest.repositoryImpl.quickregister;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.data.domain.quickregister.EmailDTO;
import com.projectx.data.domain.quickregister.UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsKey;
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
	public EmailVerificationDetails getByEntityIdTypeAndEmailType(
			Long customerId,Integer customerType, Integer emailType) {
		
		CustomerIdTypeEmailTypeDTO emailDTO=new CustomerIdTypeEmailTypeDTO(customerId,customerType, emailType);
		
		EmailVerificationDetails savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/emailVerification/getEmailVerificationDetailsByCustomerIdAndEmail", emailDTO, EmailVerificationDetails.class);
		
		return savedEntity;
	}

	@Override
	public Integer resetEmailHashAndEmailHashSentTime(Long customerId,Integer customerType,
			Integer emailType, String emailHash, Date emailHashSentTime,
			Integer resetCount) {
		UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO dto=new UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO
				(customerId,customerType, emailType, emailHash, emailHashSentTime, resetCount);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/emailVerification/resetEmailHashAndEmailHashSentTime", dto, Integer.class);
		
		return updateStatus;
	}

	@Override
	public Integer incrementResendCountByCustomerIdAndEmail(Long customerId,Integer customerType,
			Integer emailType) {
		
		CustomerIdTypeEmailTypeDTO emailDTO=new CustomerIdTypeEmailTypeDTO(customerId,customerType, emailType);
		
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

	@Override
	public Boolean delete(EmailVerificationDetailsKey key) {
		
		Boolean updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/emailVerification/deleteByKey", key, Boolean.class);
		
		return updateStatus;
	
		
	}

	@Override
	public EmailVerificationDetails getByEmail(
			String email) {
		
		EmailDTO emailDTO=new EmailDTO(email);
		
		EmailVerificationDetails savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/emailVerification/getEmailVerificationDetailsByEmail", emailDTO, EmailVerificationDetails.class);
		
		return savedEntity;
	}

	

	

}
