package com.projectx.rest.repositoryImpl.quickregister;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
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
	public QuickRegisterEntity save(QuickRegisterEntity customer) {
		
		QuickRegisterEntity savedEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister", customer, QuickRegisterEntity.class);
		
		return savedEntity;
	}

	@Override
	public List<QuickRegisterEntity> findAll() {
		
		ResponseCustomerList response=restTemplate.getForObject(env.getProperty("data.url")+"/customer/quickregister/getAll", ResponseCustomerList.class);

		return response.getCustomerList();

	}

	@Override
	public QuickRegisterEntity findByCustomerId(Long customerId) {
		
		CustomerIdDTO customerIdDTO=new CustomerIdDTO(customerId);
		
		QuickRegisterEntity quickRegisterEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/getEntityByCustomerId", customerIdDTO, QuickRegisterEntity.class);
		
		return quickRegisterEntity;
		
	}
	

	@Override
	public QuickRegisterEntity findByEmail(String email) {
		
		EmailDTO emailDTO=new EmailDTO(email);
		
		QuickRegisterEntity quickRegisterEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/getCustomerQuickRegisterEntityByEmail", emailDTO, QuickRegisterEntity.class);
		
		return quickRegisterEntity;
	}

	@Override
	public QuickRegisterEntity findByMobile(Long mobile) {
		
		MobileDTO mobileDTO=new MobileDTO(mobile);
		
		QuickRegisterEntity quickRegisterEntity=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/getCustomerQuickRegisterEntityByMobile", mobileDTO, QuickRegisterEntity.class);
		
		return quickRegisterEntity;
	}

	
	@Override
	public Integer updateMobileVerificationStatus(Long customerId,
			Boolean status, Date updateTime, String updatedBy) {
		
		UpdateEmailMobileVerificationStatus mobileVerificationStatus=new UpdateEmailMobileVerificationStatus(customerId, status, updateTime, updatedBy);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/updateMobileVerificationStatus", mobileVerificationStatus, Integer.class);
		
		
		return updateStatus;
	}

	@Override
	public Integer updateEmailVerificationStatus(Long customerId,
			Boolean status, Date updateTime, String updatedBy) {
		
		UpdateEmailMobileVerificationStatus mobileVerificationStatus=new UpdateEmailMobileVerificationStatus(customerId, status, updateTime, updatedBy);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/updateEmailVerificationStatus", mobileVerificationStatus, Integer.class);
		
		
		return updateStatus;
		
	}

	
	@Override
	public void clearCustomerQuickRegister() {
		restTemplate.getForObject(env.getProperty("data.url")+"/customer/quickregister/clearForTesting", Boolean.class);

	}





	
	/*	
	@Override
	public CustomerQuickRegisterEntity save(CustomerQuickRegisterEntity customer)
			throws Exception {
		
		CustomerQuickEntitySaveDTO customerToDTO=customer.toCustomerQuickRegisterDTO();
		
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
		
		EmailDTO emailDTO=new EmailDTO(email);
		
		Integer  emailCount=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/getEmailCount", 
				emailDTO, Integer.class);

		return emailCount;
	}

	@Override
	public Integer countByMobile(Long mobile) {

		MobileDTO mobileDTO=new MobileDTO(mobile);
		
		Integer  mobileCount=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/getMobileCount", 
				mobileDTO, Integer.class);

		return mobileCount;
	}

	@Override
	public Integer updateStatusAndMobileVerificationAttemptsByCustomerId(
			Long customerId, String status, Date lastStatusChaneTime,
			Integer mobileVerificationAttempts) {
		
		UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO updateStatusMobileVerification=
				new UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO(customerId,status,lastStatusChaneTime,mobileVerificationAttempts);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/updateStatusAndMobileVerificationAttempts", 
															updateStatusMobileVerification, Integer.class);
		
		return updateStatus;
	}

	@Override
	public Integer updateEmailHash(Long customerId, String emailHash,
			Date updateTime) {
		UpdateEmailHashDTO emailHashDTO=new UpdateEmailHashDTO(customerId, emailHash, updateTime);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/updateEmailHash", emailHashDTO, Integer.class);

		return updateStatus;
	}

	@Override
	public Integer updateMobilePin(Long customerId, Integer mobilePin,
			Date updateTime) {
		UpdateMobilePinDTO mobilePinDTO=new UpdateMobilePinDTO(customerId, mobilePin, updateTime);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/updateMobilePin", mobilePinDTO, Integer.class);

		return updateStatus;
	}
/*
	@Override
	public Integer updatePassword(Long customerId, String password,
			String passwordType) {
		
		UpdatePasswordDTO passwordDTO=new UpdatePasswordDTO(customerId, password, passwordType);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/updatePassword", passwordDTO, Integer.class);

		return updateStatus;
	}
*//*
	@Override
	public Integer updateEmailHashAndMobilePinSentTime(Long customerId,
			Date emailHashSentTime, Date mobilePinSentTime) {
		UpdateEmailHashAndMobilePinSentTimeDTO emailHashAndMobilePinSentTimeDTO=
				new UpdateEmailHashAndMobilePinSentTimeDTO(customerId, emailHashSentTime, mobilePinSentTime);
		
		Integer updateStatus=restTemplate.postForObject(env.getProperty("data.url")+"/customer/quickregister/updateEmailHashAndMobilePinSentTime", emailHashAndMobilePinSentTimeDTO, Integer.class);

		return updateStatus;
	}
*/

	
//	@Override
//	public Long deleteByCustomerId(Long customerId) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
}
