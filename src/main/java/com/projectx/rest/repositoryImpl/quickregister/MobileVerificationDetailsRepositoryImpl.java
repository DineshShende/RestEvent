package com.projectx.rest.repositoryImpl.quickregister;

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

import com.projectx.data.domain.quickregister.CustomerIdDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeMobileTypeRequestedByDTO;
import com.projectx.data.domain.quickregister.EmailDTO;
import com.projectx.data.domain.quickregister.MobileDTO;
import com.projectx.data.domain.quickregister.UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsKey;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.MobileVerificationDetailsNotFoundException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
import com.projectx.rest.repository.quickregister.MobileVerificationDetailsRepository;

@Component
@Profile(value={"Dev","Prod"})
@PropertySource(value="classpath:/application.properties")
public class MobileVerificationDetailsRepositoryImpl implements MobileVerificationDetailsRepository{

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	
	@Override
	public MobileVerificationDetails save(
			MobileVerificationDetails mobileVerificationDetails) throws ResourceAlreadyPresentException,ValidationFailedException {
		
		HttpEntity<MobileVerificationDetails> entity=new HttpEntity<MobileVerificationDetails>(mobileVerificationDetails);
		
		ResponseEntity<MobileVerificationDetails> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister/mobileVerification/saveMobileVerificationDetails",
					HttpMethod.POST,entity, MobileVerificationDetails.class);
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
	public MobileVerificationDetails geByEntityIdTypeAndMobileType (
			Long customerId,Integer customerType, Integer mobileType) throws MobileVerificationDetailsNotFoundException{

		CustomerIdTypeMobileTypeDTO mobileDTO=new CustomerIdTypeMobileTypeDTO(customerId,customerType, mobileType);
		
		HttpEntity<CustomerIdTypeMobileTypeDTO> entity=new HttpEntity<CustomerIdTypeMobileTypeDTO>(mobileDTO);
		
		ResponseEntity<MobileVerificationDetails> result=restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister/mobileVerification/getMobileVerificationDetailsByCustomerIdAndMobile",
				HttpMethod.POST, entity, MobileVerificationDetails.class);
		
		if(result.getStatusCode()==HttpStatus.FOUND)
				return result.getBody();
		else
			throw new MobileVerificationDetailsNotFoundException();
	}

	@Override
	public Integer updateMobilePinAndMobileVerificationAttemptsAndResendCount(
			Long customerId,Integer customerType, Integer mobileType, Integer mobilePin,
			Integer mobileVerificationAttempts, Integer resendCount,String updatedBy) {
		
		
		UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO dto=new UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO
				(customerId,customerType, mobileType, mobilePin, mobileVerificationAttempts, resendCount,updatedBy);
		
		ResponseEntity<Integer> updateStatus=null;
		
		try{
			updateStatus=restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister/mobileVerification/updateMobilePinAndMobileVerificationAttemptsAndResendCount",
					HttpMethod.POST,new HttpEntity<UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO>(dto), Integer.class);
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		return updateStatus.getBody();
	}

	@Override
	public Integer incrementMobileVerificationAttempts(Long customerId,Integer customerType,
			Integer mobileType,String updatedBy) {
			
		CustomerIdTypeMobileTypeRequestedByDTO mobileDTO=new CustomerIdTypeMobileTypeRequestedByDTO(customerId,customerType, mobileType,updatedBy);
		
		ResponseEntity<Integer> updateStatus=null;
		
		try{
			updateStatus=restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister/mobileVerification/incrementMobileVerificationAttempts",
					HttpMethod.POST,new HttpEntity<CustomerIdTypeMobileTypeRequestedByDTO>(mobileDTO), Integer.class);
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		return updateStatus.getBody();

	}

	@Override
	public Integer incrementResendCount(Long customerId,Integer customerType, Integer mobileType,String updatedBy) {

		CustomerIdTypeMobileTypeRequestedByDTO mobileDTO=new CustomerIdTypeMobileTypeRequestedByDTO(customerId,customerType, mobileType,updatedBy);
		
		ResponseEntity<Integer> updateStatus=null;
		
		try{
			updateStatus=restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister/mobileVerification/incrementResendCount",
					HttpMethod.POST,new HttpEntity<CustomerIdTypeMobileTypeRequestedByDTO>(mobileDTO), Integer.class);
			
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		return updateStatus.getBody();	
		
	}

	
	@Override
	public MobileVerificationDetails getByMobile(
			Long mobile) throws MobileVerificationDetailsNotFoundException{
		
		MobileDTO mobileDTO=new MobileDTO(mobile);
		
		HttpEntity<MobileDTO> entity=new HttpEntity<MobileDTO>(mobileDTO);
		
		ResponseEntity<MobileVerificationDetails> result=restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister/mobileVerification/getMobileVerificationDetailsByMobile",
				HttpMethod.POST, entity, MobileVerificationDetails.class);
		
		if(result.getStatusCode()==HttpStatus.FOUND)
				return result.getBody();
		else
			throw new MobileVerificationDetailsNotFoundException();

	}
	
	@Override
	public Boolean delete(MobileVerificationDetailsKey key) {
		
		ResponseEntity<Boolean> updateStatus=restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister/mobileVerification/deleteByKey",
				HttpMethod.POST,new HttpEntity<MobileVerificationDetailsKey>(key), Boolean.class);
		
		return updateStatus.getBody();

	}
	
	@Override
	public Long count() {
		Long count=restTemplate.getForObject(env.getProperty("data.url")+"/customer/quickregister/mobileVerification/getCount", Long.class);
		
		return count;
	}

	@Override
	public Boolean clearTestData() {
		Boolean status=restTemplate.getForObject(env.getProperty("data.url")+"/customer/quickregister/mobileVerification/clearForTesting", Boolean.class);
		
		return status;
	}



}
