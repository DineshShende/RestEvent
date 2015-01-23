package com.projectx.rest.repositoryImpl.completeregister;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.completeregister.VehicleList;
import com.projectx.rest.domain.completeregister.DriverDetails;
import com.projectx.rest.domain.completeregister.VehicleDetailsDTO;
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
	public VehicleDetailsDTO save(VehicleDetailsDTO vehicleDetails) {
		
		
		VehicleDetailsDTO savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/vehicle",
				vehicleDetails, VehicleDetailsDTO.class);

		return savedEntity;
		
	}

	@Override
	public Boolean deleteById(Long vehicleId) {

		
		Boolean result=restTemplate.getForObject(env.getProperty("data.url")+"/vehicle/deleteById/"+vehicleId,
				Boolean.class);
		
		return result;
		
	}

	@Override
	public List<VehicleDetailsDTO> getVehiclesByVendorId(Long vendorId) {
		
		VehicleList result=restTemplate.getForObject(env.getProperty("data.url")+"/vehicle/getVehiclesByVendorId/"+vendorId,
				VehicleList.class);
		
		return result.getVehicleList();
	

		
	}
	
	@Override
	public VehicleDetailsDTO findOne(Long vehicleId) {

		VehicleDetailsDTO result=restTemplate.getForObject(env.getProperty("data.url")+"/vehicle/getById/"+vehicleId,
				VehicleDetailsDTO.class);
		
		return result;
		
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
