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

import com.projectx.data.domain.request.FreightRequestByVendorList;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.repository.request.FreightRequestByVendorRepository;

@Component
@Profile(value={"Dev"})
@PropertySource(value="classpath:/application.properties")
public class FreightRequestByVendorRepositoryImpl implements
		FreightRequestByVendorRepository {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	
	
	@Override
	public FreightRequestByVendor save(
			FreightRequestByVendor freightRequestByVendor)throws ResourceAlreadyPresentException,ValidationFailedException {

		HttpEntity<FreightRequestByVendor> entity=new HttpEntity<FreightRequestByVendor>(freightRequestByVendor);
		
		ResponseEntity<FreightRequestByVendor> result=null;
		
		try{
		
			result=restTemplate.exchange(env.getProperty("data.url")+"/request/freightRequestByVendor",HttpMethod.POST,
					entity, FreightRequestByVendor.class);

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
	public FreightRequestByVendor getById(Long requestId) throws ResourceNotFoundException{
		

		ResponseEntity<FreightRequestByVendor> result=restTemplate.exchange(env.getProperty("data.url")+"/request/freightRequestByVendor/getById/"+requestId,
				HttpMethod.GET,null,FreightRequestByVendor.class);
		
		if(result.getStatusCode()==HttpStatus.FOUND)
			return result.getBody();
		else
			throw new ResourceNotFoundException();
		

	}

	@Override
	public Boolean deleteById(Long requestId) {
		
		ResponseEntity<Boolean> result=restTemplate.exchange(env.getProperty("data.url")+"/request/freightRequestByVendor/deleteById/"+requestId,
				HttpMethod.GET,null,Boolean.class);
		
		return result.getBody();
		
	}



	@Override
	public List<FreightRequestByVendor> findByVendor(Long vendorId) {

		FreightRequestByVendorList result=restTemplate.getForObject(env.getProperty("data.url")+"/request/freightRequestByVendor/findByVendorId/"+vendorId,
				FreightRequestByVendorList.class);
		
		return result.getRequestList();


		
	}

	@Override
	public List<FreightRequestByVendor> getMatchingVendorReqFromCustomerReq(
			FreightRequestByCustomer freightRequestByCustomer) {
		
		FreightRequestByVendorList result=restTemplate.postForObject(env.getProperty("data.url")+"/request/freightRequestByVendor/getMatchingVendorReqFromCustomerReq",
				freightRequestByCustomer, FreightRequestByVendorList.class);
		
		return result.getRequestList();


		
		
	}
	
	@Override
	public Boolean clearTestData() {
		

		Boolean result=restTemplate.getForObject(env.getProperty("data.url")+"/request/freightRequestByVendor/clearTestData",
				Boolean.class);
		
		return result;

	}

	@Override
	public Integer count() {
		
		Integer result=restTemplate.getForObject(env.getProperty("data.url")+"/request/freightRequestByVendor/count",
				Integer.class);
		
		return result;
		
		
	}

}
