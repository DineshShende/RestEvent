package com.projectx.rest.repositoryImpl.completeregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.completeregister.UpdateAddressDTO;
import com.projectx.data.domain.completeregister.UpdateEmailVerificationStatusDTO;
import com.projectx.data.domain.completeregister.UpdateMobileVerificationStatusDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;
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
		
		CustomerDetails result=restTemplate.getForObject(env.getProperty("data.url")+"/customer/completeregister/"+customerId,
				CustomerDetails.class);
		
		return result;

		
	}

	/*
	@Override
	public CustomerDetails updateFirmAddress(UpdateAddressDTO addressDTO) {

		CustomerDetails updatedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/completeregister/updateFirmAddress",
				addressDTO, CustomerDetails.class);

		return updatedEntity;

	}

	@Override
	public CustomerDetails updateHomeAddress(UpdateAddressDTO addressDTO) {

		CustomerDetails updatedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/completeregister/updateHomeAddress",
				addressDTO, CustomerDetails.class);

		return updatedEntity;
		
		
	}
*/
	@Override
	public Integer updateMobileVerificationStatus(
			UpdateMobileVerificationStatusDTO verificationStatusDTO) {

		Integer updatedStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/completeregister/updateMobileVerificationStatus",
				verificationStatusDTO, Integer.class);

		return updatedStatus;
	}

	@Override
	public Integer updateSecondaryMobileVerificationStatus(
			UpdateMobileVerificationStatusDTO verificationStatusDTO) {

		Integer updatedStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/completeregister/updateSecondaryMobileVerificationStatus",
				verificationStatusDTO, Integer.class);

		return updatedStatus;
	
	}

	@Override
	public Integer updateEmailVerificationStatus(
			UpdateEmailVerificationStatusDTO verificationStatusDTO) {

		Integer updatedStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/completeregister/updateEmailVerificationStatus",
				verificationStatusDTO, Integer.class);

		return updatedStatus;

		
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
