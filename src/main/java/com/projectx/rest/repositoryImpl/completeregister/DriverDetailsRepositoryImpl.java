package com.projectx.rest.repositoryImpl.completeregister;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.completeregister.DriverList;
import com.projectx.data.domain.completeregister.UpdateMobileVerificationStatusDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.DriverDetails;
import com.projectx.rest.repository.completeregister.DriverDetailsRepository;


@Component
@Profile(value={"Dev"})
@PropertySource(value="classpath:/application.properties")

public class DriverDetailsRepositoryImpl implements DriverDetailsRepository {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	
	@Override
	public DriverDetails save(DriverDetails driverDetails) {
		
		DriverDetails savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/driver",
				driverDetails, DriverDetails.class);

		return savedEntity;

		
	}

	@Override
	public DriverDetails update(DriverDetails driverDetails) {

		DriverDetails savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/driver/update",
				driverDetails, DriverDetails.class);

		return savedEntity;
	}

	@Override
	public Integer updateMobileAndMobileVerificationStatus(Long driverId,
			Long mobile, Boolean status) {

		UpdateMobileVerificationStatusDTO mobileVerificationStatusDTO=
				new UpdateMobileVerificationStatusDTO(driverId, mobile, status);
		
		Integer result=restTemplate.postForObject(env.getProperty("data.url")+"/driver/updateMobileAndMobileVerificationStatus",
				mobileVerificationStatusDTO, Integer.class);

		return result;
		
	}

	@Override
	public List<DriverDetails> getDriversByVendorId(Long vendorId) {
	
		DriverList result=restTemplate.getForObject(env.getProperty("data.url")+"/driver/getDriversByVendorId/"+vendorId,
				DriverList.class);
		
		return result.getDriverList();


	}

	
	@Override
	public DriverDetails findOne(Long driverId) {

		DriverDetails result=restTemplate.getForObject(env.getProperty("data.url")+"/driver/getById/"+driverId,
				DriverDetails.class);
		
		return result;

		
		
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
