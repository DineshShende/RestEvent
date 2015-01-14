package com.projectx.rest.repositoryImpl.quickregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.quickregister.CustomerIdDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.data.domain.quickregister.EmailDTO;
import com.projectx.data.domain.quickregister.MobileDTO;
import com.projectx.data.domain.quickregister.UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsKey;
import com.projectx.rest.repository.quickregister.MobileVerificationDetailsRepository;

@Component
@Profile(value="Dev")
@PropertySource(value="classpath:/application.properties")
public class MobileVerificationDetailsRepositoryImpl implements MobileVerificationDetailsRepository{

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	
	@Override
	public MobileVerificationDetails save(
			MobileVerificationDetails mobileVerificationDetails) {
		
		System.out.println(mobileVerificationDetails);
		
		MobileVerificationDetails savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/mobileVerification/saveMobileVerificationDetails", mobileVerificationDetails, MobileVerificationDetails.class);
		
		System.out.println(savedEntity);
		
		return savedEntity;
		
	}

	@Override
	public MobileVerificationDetails geByEntityIdTypeAndMobileType(
			Long customerId,Integer customerType, Integer mobileType) {

		CustomerIdTypeMobileTypeDTO mobileDTO=new CustomerIdTypeMobileTypeDTO(customerId,customerType, mobileType);
		
		MobileVerificationDetails savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/mobileVerification/getMobileVerificationDetailsByCustomerIdAndMobile", mobileDTO, MobileVerificationDetails.class);
		
		if(savedEntity==null)
			savedEntity=new MobileVerificationDetails();
		
		return savedEntity;
	}

	@Override
	public Integer updateMobilePinAndMobileVerificationAttemptsAndResendCount(
			Long customerId,Integer customerType, Integer mobileType, Integer mobilePin,
			Integer mobileVerificationAttempts, Integer resendCount) {
		
		
		UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO dto=new UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO
				(customerId,customerType, mobileType, mobilePin, mobileVerificationAttempts, resendCount);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/mobileVerification/updateMobilePinAndMobileVerificationAttemptsAndResendCount", dto, Integer.class);
		
		return updateStatus;
	}

	@Override
	public Integer incrementMobileVerificationAttempts(Long customerId,Integer customerType,
			Integer mobileType) {
			
		CustomerIdTypeMobileTypeDTO mobileDTO=new CustomerIdTypeMobileTypeDTO(customerId,customerType, mobileType);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/mobileVerification/incrementMobileVerificationAttempts", mobileDTO, Integer.class);
		
		return updateStatus;

	}

	@Override
	public Integer incrementResendCount(Long customerId,Integer customerType, Integer mobileType) {

		CustomerIdTypeMobileTypeDTO mobileDTO=new CustomerIdTypeMobileTypeDTO(customerId,customerType, mobileType);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/mobileVerification/incrementResendCount", mobileDTO, Integer.class);
		
		return updateStatus;	}

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


	

	@Override
	public Boolean delete(MobileVerificationDetailsKey key) {
		
		Boolean updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/mobileVerification/deleteByKey", key, Boolean.class);
		
		return updateStatus;

	}

	@Override
	public MobileVerificationDetails getByMobile(
			Long mobile) {
		
		MobileDTO mobileDTO=new MobileDTO(mobile);
		
		MobileVerificationDetails savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/mobileVerification/getMobileVerificationDetailsByMobile", mobileDTO, MobileVerificationDetails.class);
		
		return savedEntity;

	}

}
