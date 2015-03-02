package com.projectx.rest.repository.quickregister;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.projectx.data.domain.quickregister.UpdateEmailMobileVerificationStatus;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterDetailsAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterEntityNotFoundException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;

@Repository
public interface QuickRegisterRepository {

	 QuickRegisterEntity save(QuickRegisterEntity customer) throws QuickRegisterDetailsAlreadyPresentException,ValidationFailedException;
	
	 List<QuickRegisterEntity> findAll();
	 
	 QuickRegisterEntity findByCustomerId(Long customerId) throws ResourceNotFoundException;
	 
	 QuickRegisterEntity findByEmail(String email) throws ResourceNotFoundException;
	 
	 QuickRegisterEntity findByMobile(Long mobile) throws ResourceNotFoundException;
	 
	 Integer updateMobileVerificationStatus(Long customerId,Boolean status,Date updateTime,String updatedBy);
	 
	 Integer updateEmailVerificationStatus(Long customerId,Boolean status,Date updateTime,String updatedBy);
	 

	 void clearCustomerQuickRegister();
	 	
}
