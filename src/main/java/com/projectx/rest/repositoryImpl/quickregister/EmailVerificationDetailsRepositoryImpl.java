package com.projectx.rest.repositoryImpl.quickregister;

import java.util.Date;

import javax.xml.ws.http.HTTPException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.data.domain.quickregister.EmailDTO;
import com.projectx.data.domain.quickregister.UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsKey;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.EmailVerificationDetailNotFoundException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
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
			EmailVerificationDetails mobileVerificationDetails) throws ResourceAlreadyPresentException,ValidationFailedException{
		
		HttpEntity<EmailVerificationDetails> entity=new HttpEntity<EmailVerificationDetails>(mobileVerificationDetails);
		
		ResponseEntity<EmailVerificationDetails> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister/emailVerification/saveEmailVerificationDetails", HttpMethod.POST,
					entity, EmailVerificationDetails.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.CREATED)
			return result.getBody();
		else
			throw new ResourceAlreadyPresentException();
	}

	@Override
	public EmailVerificationDetails getByEntityIdTypeAndEmailType(
			Long customerId,Integer customerType, Integer emailType) throws EmailVerificationDetailNotFoundException{
		
		CustomerIdTypeEmailTypeDTO emailDTO=new CustomerIdTypeEmailTypeDTO(customerId,customerType, emailType);

		HttpEntity<CustomerIdTypeEmailTypeDTO> entity=new HttpEntity<CustomerIdTypeEmailTypeDTO>(emailDTO);
		
		ResponseEntity<EmailVerificationDetails> result=
				restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister/emailVerification/getEmailVerificationDetailsByCustomerIdAndEmail",
						HttpMethod.POST, entity, EmailVerificationDetails.class);
		
		if(result.getStatusCode()==HttpStatus.FOUND)
			return result.getBody();
		else
			throw new EmailVerificationDetailNotFoundException();

		
	}

	@Override
	public Integer resetEmailHashAndEmailHashSentTime(Long customerId,Integer customerType,
			Integer emailType, String emailHash, Date emailHashSentTime,
			Integer resetCount,String updatedBy) {
		UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO dto=new UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO
				(customerId,customerType, emailType, emailHash, emailHashSentTime, resetCount,updatedBy);
		
		ResponseEntity<Integer> updateStatus=null;
		
		try{
			updateStatus=restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister/emailVerification/resetEmailHashAndEmailHashSentTime", 
					HttpMethod.POST,new HttpEntity<UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO>(dto), Integer.class);
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		return updateStatus.getBody();
	}

	@Override
	public Integer incrementResendCountByCustomerIdAndEmail(Long customerId,Integer customerType,
			Integer emailType,String updatedBy) {
		
		CustomerIdTypeEmailTypeUpdatedByDTO emailDTO=new CustomerIdTypeEmailTypeUpdatedByDTO(customerId,customerType, emailType,updatedBy);
		
		ResponseEntity<Integer> updateStatus=null;
		
		try{
			updateStatus=restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister/emailVerification/incrementResendCountByCustomerIdAndEmail",
					HttpMethod.POST,new HttpEntity<CustomerIdTypeEmailTypeUpdatedByDTO>(emailDTO), Integer.class);
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		return updateStatus.getBody();
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
	public EmailVerificationDetails getByEmail(	String email) throws EmailVerificationDetailNotFoundException{
		
		EmailDTO emailDTO=new EmailDTO(email);
		
		HttpEntity<EmailDTO> entity=new HttpEntity<EmailDTO>(emailDTO);
		
		ResponseEntity<EmailVerificationDetails> result=
				restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister/emailVerification/getEmailVerificationDetailsByEmail",
						HttpMethod.POST, entity, EmailVerificationDetails.class);
		
		if(result.getStatusCode()==HttpStatus.FOUND)
			return result.getBody();
		else
			throw new EmailVerificationDetailNotFoundException();
		
		
	}

	

	

}
