package com.projectx.rest.repositoryImpl.completeregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.completeregister.UpdateEmailVerificationStatusDTO;
import com.projectx.data.domain.completeregister.UpdateMobileVerificationStatusDTO;
import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.repository.completeregister.VendorDetailsRepository;

@Component
@Profile(value="Dev")
@PropertySource(value="classpath:/application.properties")
public class VendorDetailsRepositoryImpl implements VendorDetailsRepository {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	
	@Override
	public VendorDetails save(VendorDetails vendorDetails) {
	
		VendorDetails savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/vendor/save",
				vendorDetails, VendorDetails.class);
		
		return savedEntity;
		
	}

	@Override
	public VendorDetails update(VendorDetails vendorDetails) {
		
		VendorDetails updatedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/vendor/update",
				vendorDetails, VendorDetails.class);
		
		return updatedEntity;

		
	}

	@Override
	public VendorDetails findOne(Long vendorId) {
	
		VendorDetails fetchedEntity=restTemplate.getForObject(env.getProperty("data.url")+"/vendor/getById/"+vendorId,
				VendorDetails.class);

		return fetchedEntity;

	}

	@Override
	public Integer updateEmailVerificationStatus(
			UpdateEmailVerificationStatusDTO updateVerificationStatusDTO) {

		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/vendor/updateEmailVerificationStatus",
				updateVerificationStatusDTO, Integer.class);
		
		return updateStatus;

	}

	@Override
	public Integer updateMobileVerificationStatus(
			UpdateMobileVerificationStatusDTO updateVerificationStatusDTO) {

		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/vendor/updateMobileVerificationStatus",
				updateVerificationStatusDTO, Integer.class);
		
		return updateStatus;

	}

	@Override
	public Integer count() {
		
		Integer count=restTemplate.getForObject(env.getProperty("data.url")+"/vendor/count",
				Integer.class);

		return count;

	}

	@Override
	public Boolean clearTestData() {

		Boolean status=restTemplate.getForObject(env.getProperty("data.url")+"/vendor/clearTestData",
				Boolean.class);

		return status;

	}

}
