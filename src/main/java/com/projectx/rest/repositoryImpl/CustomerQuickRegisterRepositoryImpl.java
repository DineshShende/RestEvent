package com.projectx.rest.repositoryImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.CustomerIdDTO;
import com.projectx.data.domain.GetEmailCountDTO;
import com.projectx.data.domain.GetMobileCountDTO;
import com.projectx.data.domain.ResponseCustomerList;
import com.projectx.data.domain.UpdateEmailHashDTO;
import com.projectx.data.domain.UpdateMobilePinDTO;
import com.projectx.data.domain.UpdateStatusByCustomerId;
import com.projectx.data.domain.VerifyEmailHashDTO;
import com.projectx.data.domain.VerifyMobilePinDTO;
import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.repository.CustomerQuickRegisterRepository;
import com.projectx.web.domain.CustomerQuickRegisterSaveDTO;

@Component
@Profile("Dev")
@PropertySource(value="classpath:/application.properties")
public class CustomerQuickRegisterRepositoryImpl implements
		CustomerQuickRegisterRepository {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;
	
	@Override
	public CustomerQuickRegisterEntity save(CustomerQuickRegisterEntity customer)
			throws Exception {
		
	   CustomerQuickRegisterSaveDTO customerToDTO=customer.toCustomerQuickRegisterDTO();
		
		CustomerQuickRegisterEntity savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister", 
				customerToDTO, CustomerQuickRegisterEntity.class);
			
		return savedEntity;
	}

	@Override
	public List<CustomerQuickRegisterEntity> findAll() {
		
		ResponseCustomerList response=restTemplate.getForObject(env.getProperty("data.url")+"/customer/quickregister/getAll", ResponseCustomerList.class);

		return response.getCustomerList();
	}

	@Override
	public CustomerQuickRegisterEntity findByCustomerId(Long customerId) {
		
		CustomerIdDTO customerIdDTO=new CustomerIdDTO(customerId);
		
		CustomerQuickRegisterEntity fetchedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/getEntityByCustomerId",
																		customerIdDTO, CustomerQuickRegisterEntity.class);
		return fetchedEntity;
	}

	@Override
	public Integer countByEmail(String email) {
		
		GetEmailCountDTO emailDTO=new GetEmailCountDTO(email);
		
		Integer  emailCount=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/getEmailCount", 
				emailDTO, Integer.class);

		return emailCount;
	}

	@Override
	public Integer countByMobile(Long mobile) {

		GetMobileCountDTO mobileDTO=new GetMobileCountDTO(mobile);
		
		Integer  mobileCount=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/getMobileCount", 
				mobileDTO, Integer.class);

		return mobileCount;
	}

	@Override
	public Integer verifyEmailHash(Long customerId, Long emailHash) {
		VerifyEmailHashDTO verifyEmailHashDTO=new VerifyEmailHashDTO(customerId, emailHash);
		
		Integer  mobileCount=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/verifyEmailHash", 
				verifyEmailHashDTO, Integer.class);
		
		return mobileCount;
	}

	
	@Override
	public Integer verifyMobilePin(Long customerId, Integer mobilePin) {
		
		VerifyMobilePinDTO verifyMobilePinDTO=new VerifyMobilePinDTO(customerId, mobilePin);
		
		Integer  mobileCount=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/verifyMobilePin", 
				verifyMobilePinDTO, Integer.class);
		
		return mobileCount;
	}

	@Override
	public String getStatusByCustomerId(Long customerId) {
		
		CustomerIdDTO customerIdDTO=new CustomerIdDTO(customerId);
		
		String status=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/getStatusByCustomerId",
																		customerIdDTO, String.class);
		return status;
		
	}

	@Override
	public Integer updateStatusByCustomerId(Long customerId, String status) {

		UpdateStatusByCustomerId updateStatusDTO=new UpdateStatusByCustomerId(customerId, status);
		
		Integer result=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/updateStatusByCustomerId",
				updateStatusDTO, Integer.class);
				
		return result;
	}

	@Override
	public Integer updateEmailHash(Long customerId, Long emailHash) {
		
		UpdateEmailHashDTO updateHashDTO=new UpdateEmailHashDTO(customerId, emailHash);
		
		Integer result=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/updateEmailHash",
				updateHashDTO, Integer.class);
				
		return result;
	}

	@Override
	public Integer updateMobilePin(Long customerId, Integer mobilePin) {

		UpdateMobilePinDTO updatePinDTO=new UpdateMobilePinDTO(customerId, mobilePin);
		
		Integer result=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/updateMobilePin",
				updatePinDTO, Integer.class);
				
		return result;	

	}

	@Override
	public void clearCustomerQuickRegister() {
		restTemplate.getForObject(env.getProperty("data.url")+"/customer/quickregister/clearForTesting", Boolean.class);

	}

	@Override
	public Long deleteByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
