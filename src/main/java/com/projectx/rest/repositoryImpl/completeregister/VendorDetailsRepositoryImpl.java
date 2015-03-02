package com.projectx.rest.repositoryImpl.completeregister;

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

import com.projectx.data.domain.completeregister.UpdateEmailVerificationStatusUpdatedByDTO;
import com.projectx.data.domain.completeregister.UpdateMobileVerificationStatusUpdatedByDTO;
import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.completeregister.VendorDetailsAlreadyPresentException;
import com.projectx.rest.exception.repository.completeregister.VendorDetailsNotFoundException;
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
	public VendorDetails save(VendorDetails vendorDetails)throws VendorDetailsAlreadyPresentException,ValidationFailedException {
	
		HttpEntity<VendorDetails> entity=new HttpEntity<VendorDetails>(vendorDetails);
		
		ResponseEntity<VendorDetails> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("data.url")+"/vendor/save",HttpMethod.POST,
					entity, VendorDetails.class);
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.CREATED)
			return result.getBody();
		else
			throw new VendorDetailsAlreadyPresentException();
		
	}

	@Override
	public VendorDetails update(VendorDetails vendorDetails) {
		
		VendorDetails updatedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/vendor/update",
				vendorDetails, VendorDetails.class);
		
		return updatedEntity;

		
	}

	@Override
	public VendorDetails findOne(Long vendorId) throws VendorDetailsNotFoundException{
	
		ResponseEntity<VendorDetails> result=restTemplate
				.exchange(env.getProperty("data.url")+"/vendor/getById/"+vendorId, HttpMethod.GET, null, VendorDetails.class);
		
		if(result.getStatusCode()==HttpStatus.FOUND)
			return result.getBody();
		else
			throw new VendorDetailsNotFoundException();

	}

	@Override
	public Integer updateEmailVerificationStatus(
			UpdateEmailVerificationStatusUpdatedByDTO updateVerificationStatusDTO) {

		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/vendor/updateEmailVerificationStatus",
				updateVerificationStatusDTO, Integer.class);
		
		return updateStatus;

	}

	@Override
	public Integer updateMobileVerificationStatus(
			UpdateMobileVerificationStatusUpdatedByDTO updateVerificationStatusDTO) {

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
