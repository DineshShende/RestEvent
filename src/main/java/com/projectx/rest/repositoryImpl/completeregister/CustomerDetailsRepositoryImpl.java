package com.projectx.rest.repositoryImpl.completeregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.completeregister.UpdateAddressDTO;
import com.projectx.data.domain.completeregister.UpdateMobileVerificationStatusDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.quickregister.CustomerDocument;
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
	public CustomerDetails save(CustomerDetails customerDetails) {
		
		CustomerDetails savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/completeregister",
				customerDetails, CustomerDetails.class);

		return savedEntity;

		
	}

	@Override
	public CustomerDetails findOne(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerDetails updateFirmAddress(UpdateAddressDTO addressDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerDetails updateHomeAddress(UpdateAddressDTO addressDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateMobileVerificationStatus(
			UpdateMobileVerificationStatusDTO verificationStatusDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateSecondaryMobileVerificationStatus(
			UpdateMobileVerificationStatusDTO verificationStatusDTO) {
		// TODO Auto-generated method stub
		return null;
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
