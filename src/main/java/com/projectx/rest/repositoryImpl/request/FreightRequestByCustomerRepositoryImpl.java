package com.projectx.rest.repositoryImpl.request;

import java.util.List;

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

import com.projectx.data.domain.request.FreightRequestByCustomerList;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.repository.request.FreightRequestByCustomerRepository;

@Component
@Profile(value={"Dev"})
@PropertySource(value="classpath:/application.properties")
public class FreightRequestByCustomerRepositoryImpl implements
		FreightRequestByCustomerRepository {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	
	
	@Override
	public FreightRequestByCustomer save(
			FreightRequestByCustomer freightRequestByCustomer) throws ResourceAlreadyPresentException,ValidationFailedException{

		HttpEntity<FreightRequestByCustomer> entity=new HttpEntity<FreightRequestByCustomer>(freightRequestByCustomer);
		
		ResponseEntity<FreightRequestByCustomer> result=null; 
		
		try{
			result=restTemplate.exchange(env.getProperty("data.url")+"/request/freightByRequestCustomer",HttpMethod.POST,
					entity, FreightRequestByCustomer.class);

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
	public FreightRequestByCustomer getById(Long requestId) throws ResourceNotFoundException{
		

		ResponseEntity<FreightRequestByCustomer> result=restTemplate.exchange(env.getProperty("data.url")+"/request/freightByRequestCustomer/getById/"+requestId,
				HttpMethod.GET,null,FreightRequestByCustomer.class);;
		
		if(result.getStatusCode()==HttpStatus.FOUND)		
			return result.getBody();
		else
			throw new ResourceNotFoundException();

	}

	@Override
	public Boolean deleteById(Long requestId) {

		ResponseEntity<Boolean> result=restTemplate.exchange(env.getProperty("data.url")+"/request/freightByRequestCustomer/deleteById/"+requestId,
				HttpMethod.GET,null,Boolean.class);
		
		return result.getBody();
		
	}



	@Override
	public List<FreightRequestByCustomer> findByCustomerId(Long customerId) {
	
		
		FreightRequestByCustomerList result=restTemplate.getForObject(env.getProperty("data.url")+"/request/freightByRequestCustomer/findByCustomer/"+customerId,
				FreightRequestByCustomerList.class);
		
		return result.getRequestList();

		
	}

	@Override
	public List<FreightRequestByCustomer> getMatchingCustReqForVendorReq(
			FreightRequestByVendor freightRequestByVendor) {

		FreightRequestByCustomerList result=restTemplate.postForObject(env.getProperty("data.url")+"/request/freightByRequestCustomer/getMatchingCustReqForVendorReq",
				freightRequestByVendor, FreightRequestByCustomerList.class);
				
				
		return result.getRequestList();
	}

	@Override
	public Boolean clearTestData() {

		Boolean result=restTemplate.getForObject(env.getProperty("data.url")+"/request/freightByRequestCustomer/clearTestData",
				Boolean.class);
		
		return result;
		
	}

	@Override
	public Integer count() {

		Integer result=restTemplate.getForObject(env.getProperty("data.url")+"/request/freightByRequestCustomer/count",
				Integer.class);
		
		return result;
		
	}
	
}
