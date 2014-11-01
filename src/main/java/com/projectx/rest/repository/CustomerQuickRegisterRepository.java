package com.projectx.rest.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.CustomerQuickRegisterEntity;

@Repository
public interface CustomerQuickRegisterRepository {

	 CustomerQuickRegisterEntity save(CustomerQuickRegisterEntity customer) throws Exception;
	
	 List<CustomerQuickRegisterEntity> findAll();
	 
	 CustomerQuickRegisterEntity findByCustomerId(Long customerId);
	 
	 Integer countByEmail(String email);
	 
	 Integer countByMobile(Long mobile);
 
	 Integer updateStatusAndMobileVerificationAttemptsByCustomerId(Long customerId,String status,Date lastStatusChaneTime,
			 										Integer mobileVerificationAttempts);
	 
	 Integer updateEmailHash(Long customerId,String emailHash,Date updateTime);
	 
	 Integer updateMobilePin(Long customerId,Integer mobilePin,Date updateTime);
	 
	 Integer updatePassword(Long customerId,String password,String passwordType);
	 
	 Integer updateEmailHashAndMobilePinSentTime(Long customerId,Date emailHashSentTine,Date mobilePinSentTime);
	 
	 void clearCustomerQuickRegister();

	 	
}
