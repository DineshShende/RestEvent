package com.projectx.rest.repositoryImpl.quickregister;

import java.util.Date;
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

import com.projectx.data.domain.quickregister.CustomerIdDTO;
import com.projectx.data.domain.quickregister.CustomerQuickEntitySaveDTO;
import com.projectx.data.domain.quickregister.EmailDTO;
import com.projectx.data.domain.quickregister.MobileDTO;
import com.projectx.data.domain.quickregister.ResponseCustomerList;
import com.projectx.data.domain.quickregister.UpdateEmailHashAndMobilePinSentTimeDTO;
import com.projectx.data.domain.quickregister.UpdateEmailHashDTO;
import com.projectx.data.domain.quickregister.UpdateEmailMobileVerificationStatus;
import com.projectx.data.domain.quickregister.UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO;
import com.projectx.data.domain.quickregister.UpdateMobilePinDTO;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterDetailsAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterEntityNotFoundException;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterEntityNotSavedException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.repository.quickregister.QuickRegisterRepository;

@Component
@Profile("Dev")
@PropertySource(value="classpath:/application.properties")
public class QuickRegisterRepositoryImpl implements
		QuickRegisterRepository {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	@Override
	public QuickRegisterEntity save(QuickRegisterEntity customer) throws QuickRegisterDetailsAlreadyPresentException,ValidationFailedException{
		
		HttpEntity<QuickRegisterEntity> entity=new HttpEntity<QuickRegisterEntity>(customer);
	
		ResponseEntity<QuickRegisterEntity> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister",
					HttpMethod.POST, entity, QuickRegisterEntity.class);
	
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.CREATED)
			return result.getBody();
		else
			throw new QuickRegisterDetailsAlreadyPresentException();
		
				
	}

	@Override
	public List<QuickRegisterEntity> findAll() {
		
		ResponseCustomerList response=restTemplate.getForObject(env.getProperty("data.url")+"/customer/quickregister/getAll", ResponseCustomerList.class);
		
		//restTemplate.

		return response.getCustomerList();

	}

	@Override
	public QuickRegisterEntity findByCustomerId(Long customerId) throws ResourceNotFoundException{
		
		CustomerIdDTO customerIdDTO=new CustomerIdDTO(customerId);
		
		HttpEntity<CustomerIdDTO> entity=new HttpEntity<CustomerIdDTO>(customerIdDTO);
		
		ResponseEntity<QuickRegisterEntity> result=restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister/getEntityByCustomerId",
				HttpMethod.POST, entity, QuickRegisterEntity.class);

		if(result.getStatusCode()==HttpStatus.FOUND)
			return result.getBody();
		else
			throw new ResourceNotFoundException();

		
	}
	

	@Override
	public QuickRegisterEntity findByEmail(String email) throws ResourceNotFoundException {
		
		EmailDTO emailDTO=new EmailDTO(email);
		
		HttpEntity<EmailDTO> entity=new HttpEntity<EmailDTO>(emailDTO);
		
		ResponseEntity<QuickRegisterEntity> result=restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister/getCustomerQuickRegisterEntityByEmail",
				HttpMethod.POST, entity, QuickRegisterEntity.class);

		if(result.getStatusCode()==HttpStatus.FOUND)
			return result.getBody();
		else
			throw new ResourceNotFoundException();
	}

	@Override
	public QuickRegisterEntity findByMobile(Long mobile) throws ResourceNotFoundException{
		
		MobileDTO mobileDTO=new MobileDTO(mobile);
		
		HttpEntity<MobileDTO> entity=new HttpEntity<MobileDTO>(mobileDTO);
		
		ResponseEntity<QuickRegisterEntity> result=restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister/getCustomerQuickRegisterEntityByMobile",
				HttpMethod.POST, entity, QuickRegisterEntity.class);

		if(result.getStatusCode()==HttpStatus.FOUND)
			return result.getBody();
		else
			throw new ResourceNotFoundException();
	}

	
	@Override
	public Integer updateMobileVerificationStatus(Long customerId,
			Boolean status, Date updateTime, String updatedBy) {
		
		UpdateEmailMobileVerificationStatus mobileVerificationStatus=new UpdateEmailMobileVerificationStatus(customerId, status, updateTime, updatedBy);
		
		ResponseEntity<Integer> updateStatus=restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister/updateMobileVerificationStatus",
				HttpMethod.POST,new HttpEntity<UpdateEmailMobileVerificationStatus>(mobileVerificationStatus), Integer.class);
		
		
		return updateStatus.getBody();
	}

	@Override
	public Integer updateEmailVerificationStatus(Long customerId,
			Boolean status, Date updateTime, String updatedBy) {
		
		UpdateEmailMobileVerificationStatus mobileVerificationStatus=new UpdateEmailMobileVerificationStatus(customerId, status, updateTime, updatedBy);
		
		ResponseEntity<Integer> updateStatus=restTemplate.exchange(env.getProperty("data.url")+"/customer/quickregister/updateEmailVerificationStatus", 
				HttpMethod.POST,new HttpEntity<UpdateEmailMobileVerificationStatus>(mobileVerificationStatus), Integer.class);
		
		
		return updateStatus.getBody();
		
	}

	
	@Override
	public void clearCustomerQuickRegister() {
		restTemplate.getForObject(env.getProperty("data.url")+"/customer/quickregister/clearForTesting", Boolean.class);

	}


}
