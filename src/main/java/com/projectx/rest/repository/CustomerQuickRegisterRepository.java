package com.projectx.rest.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.projectx.rest.domain.CustomerQuickRegisterEntity;

@Component
public interface CustomerQuickRegisterRepository {

	 CustomerQuickRegisterEntity save(CustomerQuickRegisterEntity customer) throws Exception;
	
	 List<CustomerQuickRegisterEntity> findAll();
	 
	 CustomerQuickRegisterEntity findByCustomerId(Long customerId);
	 
	 Integer countByEmail(String email);
	 
	 Integer countByMobile(Long mobile);
	 
	// Integer verifyEmailHash(Long customerId,Long emailHash);
	 
	// Integer verifyMobilePin(Long customerId,Integer mobilePin);
	 
	 Integer updateStatusAndMobileVerificationAttemptsByCustomerId(Long customerId,String status,Date lastStatusChaneTime,
			 										Integer mobileVerificationAttempts);
	 
	 Integer updateEmailHash(Long customerId,String emailHash,Date updateTime);
	 
	 Integer updateMobilePin(Long customerId,Integer mobilePin,Date updateTime);
	 
	 void clearCustomerQuickRegister();

	 //For testing implementations
	 Long deleteByCustomerId(Long customerId); 
	 

	 //CustomerQuickRegisterEntity findByEmail(String email);
	
	 //CustomerQuickRegisterEntity findByMobile(Long mobile);
	
	 
	 //Long deleteByEmail(String email);
	 
	 //Long deleteByMobile(Long mobile);
	
	 //String fetchStatusByEmail(String email) throws Exception;
	 
	 //String fetchStatusByMobile(Long mobile) throws Exception;
	 
	 //Integer updateStatusAfterMobileVerification(Long mobile,String status);
	 
	 //Integer updateStatusAfterEmailVerfication(String email,String status);
 	
/*
	 void clearCustomerQuickRegister();
	 Boolean checkIfAlreadyExist(CustomerQuickRegisterKey key);
	 CustomerQuickRegisterEntity getByKey(CustomerQuickRegisterKey key);
*/	 
		
	 
	
}
