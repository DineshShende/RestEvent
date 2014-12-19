package com.projectx.rest.repository.quickregister;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.projectx.data.domain.quickregister.UpdateEmailMobileVerificationStatus;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;

@Repository
public interface QuickRegisterRepository {

	 QuickRegisterEntity save(QuickRegisterEntity customer);
	
	 List<QuickRegisterEntity> findAll();
	 
	 QuickRegisterEntity findByCustomerId(Long customerId);
	 
	 QuickRegisterEntity findByEmail(String email);
	 
	 QuickRegisterEntity findByMobile(Long mobile);
	 
	 Integer updateMobileVerificationStatus(Long customerId,Boolean status,Date updateTime,String updatedBy);
	 
	 Integer updateEmailVerificationStatus(Long customerId,Boolean status,Date updateTime,String updatedBy);
	 

	 void clearCustomerQuickRegister();

	 
	 
	 //Integer countByEmail(String email);
	 
	 //Integer countByMobile(Long mobile);
 
	// Integer updateStatusAndMobileVerificationAttemptsByCustomerId(Long customerId,String status,Date lastStatusChaneTime,
			 										//Integer mobileVerificationAttempts);
	 
	 //Integer updateEmailHash(Long customerId,String emailHash,Date updateTime);
	 
	 //Integer updateMobilePin(Long customerId,Integer mobilePin,Date updateTime);
	 
	 //Integer updateEmailHashAndMobilePinSentTime(Long customerId,Date emailHashSentTine,Date mobilePinSentTime);
	 
	 	
}
