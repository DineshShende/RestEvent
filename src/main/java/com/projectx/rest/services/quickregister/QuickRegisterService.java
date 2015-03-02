package com.projectx.rest.services.quickregister;


import java.util.Date;

import com.projectx.data.domain.quickregister.UpdatePasswordEmailPasswordAndPasswordTypeDTO;
import com.projectx.mvc.domain.quickregister.CustomerIdTypeDTO;
import com.projectx.mvc.domain.quickregister.CustomerQuickRegisterEntityDTO;
import com.projectx.mvc.domain.quickregister.CustomerQuickRegisterStringStatusEntity;
import com.projectx.mvc.domain.quickregister.LoginVerificationDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationWithDefaultEmailPasswordDTO;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterEmailMobileVerificationEntity;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterStatusEntity;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterDetailsAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;

public interface QuickRegisterService {
	

	CustomerQuickRegisterStringStatusEntity checkIfAlreadyRegistered(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	QuickRegisterEntity populateVerificationFields(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	QuickRegisterEntity initializeNewCustomerQuickRegistrationEntity(QuickRegisterEntity customer);
	
	CustomerQuickRegisterEmailMobileVerificationEntity saveNewCustomerQuickRegisterEntity(QuickRegisterEntity customer) 
			throws QuickRegisterDetailsAlreadyPresentException,ValidationFailedException;
	
	QuickRegisterEntity saveCustomerQuickRegisterEntity(QuickRegisterEntity customerQuickRegisterEntity)
		throws QuickRegisterDetailsAlreadyPresentException,ValidationFailedException,ResourceNotFoundException;
	
	CustomerQuickRegisterStatusEntity handleNewCustomerQuickRegister(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	QuickRegisterEntity getByEntityId(Long customerId);

	CustomerQuickRegisterStatusEntity sendVerificationDetails(QuickRegisterEntity customer,EmailVerificationDetails emailVerificationDetails,
			MobileVerificationDetails mobileVerificationDetails) throws ResourceNotFoundException;
	
	QuickRegisterEntity getByEmail(String email) throws ResourceNotFoundException;
	
	QuickRegisterEntity getByMobile(Long mobile) throws ResourceNotFoundException;

	Integer updateMobileVerificationStatus(Long customerId,Boolean status,Date updateTime,String updatedBy);
	
	Integer updateEmailVerificationStatus(Long customerId,Boolean status,Date updateTime,String updatedBy);
	
	//Testing
	void clearDataForTesting();

		
}