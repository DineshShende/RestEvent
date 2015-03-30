package com.projectx.rest.repositoryImpl.handshake;

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

import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.handshake.DealDetails;
import com.projectx.rest.exception.repository.completeregister.CustomerDetailsAlreadyPresentException;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.repository.handshake.DealDetailsRepository;


@Component
@Profile(value={"Dev","Prod"})
@PropertySource(value="classpath:/application.properties")
public class DealDetailsRepositoryImpl implements DealDetailsRepository {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	@Override
	public DealDetails save(DealDetails dealDetails) {

		HttpEntity<DealDetails> httpEntity=new HttpEntity<DealDetails>(dealDetails);
		
		ResponseEntity<DealDetails> savedEntity=null;
		
		try{
			savedEntity=restTemplate.exchange(env.getProperty("data.url")+"/deal/save", HttpMethod.POST,
				httpEntity, DealDetails.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException("Validation Failed for :"+dealDetails);
		}
		
		if(savedEntity.getStatusCode()==HttpStatus.CREATED)
				return savedEntity.getBody();
		else
			throw new ResourceAlreadyPresentException();
		
		
	}

	@Override
	public DealDetails getByDealId(Long dealId) {
		
		ResponseEntity<DealDetails> savedEntity=null;
		
		try{
			savedEntity=restTemplate.exchange(env.getProperty("data.url")+"/deal/getByDealId/"+dealId, HttpMethod.GET,
				null, DealDetails.class);
		}catch(RestClientException e)
		{
			throw new ResourceNotFoundException();
		}
		
		if(savedEntity.getStatusCode()==HttpStatus.OK)
			return savedEntity.getBody();
		
		throw new ResourceNotFoundException();
	}

	@Override
	public DealDetails getByCustomerRequestId(Long freightRequestByCustomerId) {
		
		ResponseEntity<DealDetails> savedEntity=null;
		
		try{
			savedEntity=restTemplate.exchange(env.getProperty("data.url")+"/deal/getByCustomerRequestId/"+freightRequestByCustomerId, HttpMethod.GET,
				null, DealDetails.class);
		}catch(RestClientException e)
		{
			throw new ResourceNotFoundException();
		}
		
		if(savedEntity.getStatusCode()==HttpStatus.OK)
			return savedEntity.getBody();
		
		throw new ResourceNotFoundException();
		
		
	}

	@Override
	public DealDetails getByVendorRequestId(Long freightRequestByVendorId) {
		
		ResponseEntity<DealDetails> savedEntity=null;
		
		try{
			savedEntity=restTemplate.exchange(env.getProperty("data.url")+"/deal/getByVendorRequestId/"+freightRequestByVendorId, HttpMethod.GET,
				null, DealDetails.class);
		}catch(RestClientException e)
		{
			throw new ResourceNotFoundException();
		}
		
		if(savedEntity.getStatusCode()==HttpStatus.OK)
			return savedEntity.getBody();
		
		throw new ResourceNotFoundException();
		
	}

	@Override
	public Integer count() {

		Integer count=restTemplate.getForObject(env.getProperty("data.url")+"/deal/count", Integer.class);
		
		return count;
	}

	@Override
	public Boolean clearTestData() {
		
		Boolean result=restTemplate.getForObject(env.getProperty("data.url")+"/deal/clearTestData", Boolean.class);
		
		return result;
	}

}
