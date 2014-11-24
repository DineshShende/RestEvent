package com.projectx.rest.repositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.CustomerIdDTO;
import com.projectx.data.domain.CustomerIdMobileDTO;
import com.projectx.data.domain.UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO;
import com.projectx.rest.domain.CustomerEmailVerificationDetails;
import com.projectx.rest.domain.CustomerMobileVerificationDetails;
import com.projectx.rest.repository.CustomerMobileVerificationDetailsRepository;

@Component
@Profile(value="Dev")
@PropertySource(value="classpath:/application.properties")
public class CustomerMobileVerificationDetailsRepositoryImpl implements CustomerMobileVerificationDetailsRepository{

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	
	@Override
	public CustomerMobileVerificationDetails save(
			CustomerMobileVerificationDetails mobileVerificationDetails) {
			
		CustomerMobileVerificationDetails savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/mobileVerification/saveMobileVerificationDetails", mobileVerificationDetails, CustomerMobileVerificationDetails.class);
		
		return savedEntity;
		
	}

	@Override
	public CustomerMobileVerificationDetails getMobileVerificationDetailsByCustomerIdAndMobile(
			Long customerId, Long mobile) {

		CustomerIdMobileDTO mobileDTO=new CustomerIdMobileDTO(customerId, mobile);
		
		CustomerMobileVerificationDetails savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/mobileVerification/getMobileVerificationDetailsByCustomerIdAndMobile", mobileDTO, CustomerMobileVerificationDetails.class);
		
		if(savedEntity==null)
			savedEntity=new CustomerMobileVerificationDetails();
		
		return savedEntity;
	}

	@Override
	public Integer updateMobilePinAndMobileVerificationAttemptsAndResendCount(
			Long customerId, Long mobile, Integer mobilePin,
			Integer mobileVerificationAttempts, Integer resendCount) {
		
		
		UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO dto=new UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO
				(customerId, mobile, mobilePin, mobileVerificationAttempts, resendCount);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/mobileVerification/updateMobilePinAndMobileVerificationAttemptsAndResendCount", dto, Integer.class);
		
		return updateStatus;
	}

	@Override
	public Integer incrementMobileVerificationAttempts(Long customerId,
			Long mobile) {
			
		CustomerIdMobileDTO mobileDTO=new CustomerIdMobileDTO(customerId, mobile);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/mobileVerification/incrementMobileVerificationAttempts", mobileDTO, Integer.class);
		
		return updateStatus;

	}

	@Override
	public Integer incrementResendCount(Long customerId, Long mobile) {

		CustomerIdMobileDTO mobileDTO=new CustomerIdMobileDTO(customerId, mobile);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/mobileVerification/incrementResendCount", mobileDTO, Integer.class);
		
		return updateStatus;	}

	@Override
	public Long count() {
		Long count=restTemplate.getForObject(env.getProperty("data.url")+"/customer/quickregister/mobileVerification/getCount", Long.class);
		
		return count;
	}

	@Override
	public Boolean clearTestData() {
		Boolean status=restTemplate.getForObject(env.getProperty("data.url")+"/customer/quickregister/clearMobileVerificationForTesting", Boolean.class);
		
		return status;
	}

	

}
