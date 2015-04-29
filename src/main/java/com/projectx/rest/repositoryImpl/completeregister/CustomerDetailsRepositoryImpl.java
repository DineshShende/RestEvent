package com.projectx.rest.repositoryImpl.completeregister;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@Profile(value={"Dev","Prod"})
@PropertySource(value="classpath:/application.properties")
public class CustomerDetailsRepositoryImpl implements CustomerDetailsRepository {

	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	@Value("${CUSTOMER_DETAILS_NOT_FOUND_BY_ID}")
	private String CUSTOMER_DETAILS_NOT_FOUND_BY_ID;


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
			throw new CustomerDetailsNotFoundException(CUSTOMER_DETAILS_NOT_FOUND_BY_ID);

		
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
