package com.projectx.rest.repositoryImpl.completeregister;

import java.util.List;

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

import ch.qos.logback.core.pattern.util.RestrictedEscapeUtil;

import com.projectx.data.domain.completeregister.DriverList;
import com.projectx.data.domain.completeregister.UpdateMobileVerificationStatusUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.DriverDetails;
import com.projectx.rest.exception.repository.completeregister.DriverDetailsAlreadyPresentException;
import com.projectx.rest.exception.repository.completeregister.DriverDetailsNotFoundException;
import com.projectx.rest.exception.repository.completeregister.DriverDetailsUpdateFailedException;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.repository.completeregister.DriverDetailsRepository;


@Component
@Profile(value={"Dev","Prod"})
@PropertySource(value="classpath:/application.properties")

public class DriverDetailsRepositoryImpl implements DriverDetailsRepository {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	
	@Override
	public DriverDetails save(DriverDetails driverDetails) throws DriverDetailsAlreadyPresentException,ValidationFailedException{
		
		HttpEntity<DriverDetails> entity=new HttpEntity<DriverDetails>(driverDetails);
		
		
		ResponseEntity<DriverDetails> savedEntity=null;
		
		try{
			savedEntity=restTemplate.exchange(env.getProperty("data.url")+"/driver", HttpMethod.POST,
					entity, DriverDetails.class);
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
				
		if(savedEntity.getStatusCode()==HttpStatus.CREATED)		
			return savedEntity.getBody();
		else
			throw new DriverDetailsAlreadyPresentException();

		
	}

	@Override
	public DriverDetails update(DriverDetails driverDetails) throws DriverDetailsUpdateFailedException,ValidationFailedException{

		HttpEntity<DriverDetails> entity=new HttpEntity<DriverDetails>(driverDetails);
		
		
		ResponseEntity<DriverDetails> savedEntity=null;
		
		try{
			savedEntity=restTemplate.exchange(env.getProperty("data.url")+"/driver/update", HttpMethod.POST,
					entity, DriverDetails.class);
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		if(savedEntity.getStatusCode()==HttpStatus.OK)
			return savedEntity.getBody();
		else
			throw new DriverDetailsUpdateFailedException();
	}

	@Override
	public Integer updateMobileAndMobileVerificationStatus(Long driverId,
			Long mobile, Boolean status,String updatedBy) {

		UpdateMobileVerificationStatusUpdatedByDTO mobileVerificationStatusDTO=
				new UpdateMobileVerificationStatusUpdatedByDTO(driverId, mobile, status,updatedBy);
		
		HttpEntity<UpdateMobileVerificationStatusUpdatedByDTO> entity=
				new HttpEntity<UpdateMobileVerificationStatusUpdatedByDTO>(mobileVerificationStatusDTO);
		
		ResponseEntity<Integer> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("data.url")+"/driver/updateMobileAndMobileVerificationStatus",
					HttpMethod.POST,entity, Integer.class);

		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		return result.getBody();
		
	}

	@Override
	public List<DriverDetails> getDriversByVendorId(Long vendorId) {
	
		DriverList result=restTemplate.getForObject(env.getProperty("data.url")+"/driver/getDriversByVendorId/"+vendorId,
				DriverList.class);
		
		return result.getDriverList();


	}

	
	@Override
	public DriverDetails findOne(Long driverId) {

		ResponseEntity<DriverDetails> savedEntity=restTemplate.exchange(env.getProperty("data.url")+"/driver/getById/"+driverId, HttpMethod.GET,
				null, DriverDetails.class);
		
	
		if(savedEntity.getStatusCode()==HttpStatus.FOUND)
				return savedEntity.getBody();
		else
			throw new DriverDetailsNotFoundException();

		
		
	}

	@Override
	public Boolean deleteById(Long driverId) {
		
		Boolean result=restTemplate.getForObject(env.getProperty("data.url")+"/driver/deleteById/"+driverId,
				Boolean.class);
		
		return result;

		
	}

	@Override
	public Integer count() {

		Integer result=restTemplate.getForObject(env.getProperty("data.url")+"/driver/count",
				Integer.class);
		
		return result;


	}

	@Override
	public Boolean clearTestData() {
		
		Boolean result=restTemplate.getForObject(env.getProperty("data.url")+"/driver/clearTestData",
				Boolean.class);
		
		return result;


		
	}


}
