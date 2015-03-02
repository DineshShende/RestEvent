package com.projectx.rest.repositoryImpl.completeregister;

import javax.validation.ValidationException;

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

import com.projectx.data.domain.completeregister.UpdateAddressDTO;
import com.projectx.data.domain.completeregister.UpdateEmailVerificationStatusUpdatedByDTO;
import com.projectx.data.domain.completeregister.UpdateMobileVerificationStatusUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.exception.repository.completeregister.CustomerDetailsAlreadyPresentException;
import com.projectx.rest.exception.repository.completeregister.CustomerDetailsNotFoundException;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.repository.completeregister.CustomerDetailsRepository;

@Component
@Profile(value={"Dev"})
@PropertySource(value="classpath:/application.properties")
public class CustomerDetailsRepositoryImpl implements CustomerDetailsRepository {

	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;


	@Override
	public CustomerDetails save(CustomerDetails customerDetails) throws CustomerDetailsAlreadyPresentException,ValidationFailedException{
		
		
		HttpEntity<CustomerDetails> httpEntity=new HttpEntity<CustomerDetails>(customerDetails);
		
		
		ResponseEntity<CustomerDetails> savedEntity=null;
		
		try{
			savedEntity=restTemplate.exchange(env.getProperty("data.url")+"/customer/completeregister", HttpMethod.POST,
				httpEntity, CustomerDetails.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException("Validation Failed for :"+customerDetails);
		}
		
		if(savedEntity.getStatusCode()==HttpStatus.CREATED)
				return savedEntity.getBody();
		else
			throw new CustomerDetailsAlreadyPresentException();
		
	}

	@Override
	public CustomerDetails findOne(Long customerId) throws CustomerDetailsNotFoundException{
		
		
		ResponseEntity<CustomerDetails> result=restTemplate.exchange(env.getProperty("data.url")+"/customer/completeregister/"+customerId,
				HttpMethod.GET,null, CustomerDetails.class);

		if(result.getStatusCode()==HttpStatus.FOUND)
			return result.getBody();
		else
			throw new CustomerDetailsNotFoundException();

		
	}


	@Override
	public Integer updateMobileVerificationStatus(
			UpdateMobileVerificationStatusUpdatedByDTO verificationStatusDTO)
			throws CustomerDetailsAlreadyPresentException,ValidationFailedException
	{

		ResponseEntity<Integer> updatedStatus=null;
		
		HttpEntity<UpdateMobileVerificationStatusUpdatedByDTO> entity=new HttpEntity<UpdateMobileVerificationStatusUpdatedByDTO>(verificationStatusDTO);
		
		try{
			updatedStatus=restTemplate.exchange(env.getProperty("data.url")+"/customer/completeregister/updateMobileVerificationStatus",
					HttpMethod.POST,entity, Integer.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(updatedStatus.getStatusCode()==HttpStatus.OK)
			return updatedStatus.getBody();
		else
			throw new CustomerDetailsAlreadyPresentException();
	}

	@Override
	public Integer updateSecondaryMobileVerificationStatus(
			UpdateMobileVerificationStatusUpdatedByDTO verificationStatusDTO) throws CustomerDetailsAlreadyPresentException,ValidationFailedException{

		
		ResponseEntity<Integer> updatedStatus=null;
		
		HttpEntity<UpdateMobileVerificationStatusUpdatedByDTO> entity=new HttpEntity<UpdateMobileVerificationStatusUpdatedByDTO>(verificationStatusDTO);
		
		try{
			updatedStatus=restTemplate.exchange(env.getProperty("data.url")+"/customer/completeregister/updateSecondaryMobileVerificationStatus",
					HttpMethod.POST,entity, Integer.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(updatedStatus.getStatusCode()==HttpStatus.OK)
			return updatedStatus.getBody();
		else
			throw new CustomerDetailsAlreadyPresentException();
	
	}

	@Override
	public Integer updateEmailVerificationStatus(
			UpdateEmailVerificationStatusUpdatedByDTO verificationStatusDTO) throws CustomerDetailsAlreadyPresentException, ValidationFailedException{

		HttpEntity<UpdateEmailVerificationStatusUpdatedByDTO> entity=new HttpEntity<UpdateEmailVerificationStatusUpdatedByDTO>(verificationStatusDTO);
		
		ResponseEntity<Integer> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("data.url")+"/customer/completeregister/updateEmailVerificationStatus",
					HttpMethod.POST,entity, Integer.class);

		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.OK)
			return result.getBody();
		else
			throw new CustomerDetailsAlreadyPresentException();

		
	}

	
	@Override
	public Boolean deleteAll() {
		Boolean result=restTemplate.getForObject(env.getProperty("data.url")+"/customer/completeregister/clearTestData",
				 Boolean.class);
		
		return result;
	}

	@Override
	public Long count() {
		Long count=restTemplate.getForObject(env.getProperty("data.url")+"/customer/completeregister/count",
				 Long.class);

		return count;
	}


}
