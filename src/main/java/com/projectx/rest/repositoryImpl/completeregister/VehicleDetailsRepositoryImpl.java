package com.projectx.rest.repositoryImpl.completeregister;

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

import com.projectx.data.domain.completeregister.VehicleList;
import com.projectx.rest.domain.completeregister.DriverDetails;
import com.projectx.rest.domain.completeregister.VehicleDetails;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.completeregister.VehicleDetailsAlreadyPresentException;
import com.projectx.rest.exception.repository.completeregister.VehicleDetailsNotFoundException;
import com.projectx.rest.repository.completeregister.VehicleDetailsRepository;

@Component
@Profile(value={"Dev"})
@PropertySource(value="classpath:/application.properties")

public class VehicleDetailsRepositoryImpl implements VehicleDetailsRepository {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	
	@Override
	public VehicleDetails save(VehicleDetails vehicleDetails) throws VehicleDetailsAlreadyPresentException,ValidationFailedException{
		
		
		HttpEntity<VehicleDetails> entity=new HttpEntity<VehicleDetails>(vehicleDetails);
		
		ResponseEntity<VehicleDetails> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("data.url")+"/vehicle",
					HttpMethod.POST,entity, VehicleDetails.class);

		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.CREATED)
			return result.getBody();
		else
			throw new VehicleDetailsAlreadyPresentException();
		
		
	}

	@Override
	public Boolean deleteById(Long vehicleId) {

		
		Boolean result=restTemplate.getForObject(env.getProperty("data.url")+"/vehicle/deleteById/"+vehicleId,
				Boolean.class);
		
		return result;
		
	}

	@Override
	public List<VehicleDetails> getVehiclesByVendorId(Long vendorId) {
		
		VehicleList result=restTemplate.getForObject(env.getProperty("data.url")+"/vehicle/getVehiclesByVendorId/"+vendorId,
				VehicleList.class);
		
		return result.getVehicleList();
	

		
	}

	@Override
	public VehicleDetails findByRegistrationNumber(String registrationNumber) throws VehicleDetailsNotFoundException{

				
		ResponseEntity<VehicleDetails> result=restTemplate.exchange(env.getProperty("data.url")+"/vehicle/getByRegistrationNumber/"+registrationNumber, 
				HttpMethod.GET, null, VehicleDetails.class);
		
		if(result.getStatusCode()==HttpStatus.FOUND)		
			return result.getBody();
		else
			throw new VehicleDetailsNotFoundException();


	}

	
	@Override
	public VehicleDetails findOne(Long vehicleId) throws VehicleDetailsNotFoundException {

		ResponseEntity<VehicleDetails> result=restTemplate.exchange(env.getProperty("data.url")+"/vehicle/getById/"+vehicleId, 
				HttpMethod.GET, null, VehicleDetails.class);
		
		if(result.getStatusCode()==HttpStatus.FOUND)		
			return result.getBody();
		else
			throw new VehicleDetailsNotFoundException();
		
	}

	@Override
	public Integer count() {
		
		Integer result=restTemplate.getForObject(env.getProperty("data.url")+"/vehicle/count",
				Integer.class);
		
		return result;

	
	}

	@Override
	public Boolean clearTestData() {
		
		
		Boolean result=restTemplate.getForObject(env.getProperty("data.url")+"/vehicle/clearTestData",
				Boolean.class);
		
		return result;
		
	}


	

}
